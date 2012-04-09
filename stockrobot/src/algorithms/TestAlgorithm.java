package algorithms;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import generic.Log;
import generic.Pair;
import database.jpa.IJPAAlgortihm;
import database.jpa.tables.AlgorithmSettingDouble;
import database.jpa.tables.AlgorithmSettingLong;
import database.jpa.tables.PortfolioHistory;
import database.jpa.tables.StockNames;
import database.jpa.tables.StockPrices;
import portfolio.IPortfolio;
import robot.IRobot_Algorithms;
import trader.ITrader;

/**
 * @author daniel
 *
 * A simple test algorithm based on a algorithm Kristian has come up with
 */
public class TestAlgorithm implements IAlgorithm{
	
	IRobot_Algorithms robot;
	IPortfolio portfolio;
	ITrader trader = null;
	IJPAAlgortihm jpaHelper = null;
	private long buySetting = 3;
	private long sellSetting = 3;
	
	public TestAlgorithm(IRobot_Algorithms robot, IPortfolio portfolio, ITrader trader) {
		this.robot = robot;
		this.portfolio = portfolio;
		this.trader = trader;
		this.jpaHelper = robot.getJPAHelper();
		
		System.out.println("Inside TestAlgorithm constructor");
	}
	
	@Override
	public boolean update() {
		
		Log.instance().log( Log.TAG.VERY_VERBOSE, "Algo1: UPDATE!" );
		
		if (portfolio.getPortfolioTable().getBalance() < 1000000) {
			return false;
		}
		
		List<StockPrices> ownedStockes = jpaHelper.getCurrentStocks(portfolio.getPortfolioTable());
		for (StockPrices sp : ownedStockes ) {
			
			Log.instance().log( Log.TAG.VERY_VERBOSE, "Algo1: Checking ownedStocks!" );
			
			List<StockPrices> cs = jpaHelper.getNLatest(sp, 3);
			if (cs.size() == 3) {
				long last = Long.MAX_VALUE;
				boolean sell = true;
				for (int i = 2; i >= 0; i--) {
					if (cs.get(i).getBuy() < last) {
						last = cs.get(i).getBuy();
					}
					else
						sell = false;
				}
				
				if (sell) {
					//Sell all
					Set<PortfolioHistory> ph = portfolio.getPortfolioTable().getHistory();
					for (PortfolioHistory pHistory : ph) {
						if (pHistory.getSoldDate() == null) {
							trader.sellStock(sp, pHistory.getAmount(), portfolio.getPortfolioTable());
							//jpaHelper.updateObject(pHistory);
						}
					}
					
				}
			}
		}
		for (Pair<StockNames, List<StockPrices>> stockInfo: jpaHelper.getStockInfo(3)) {
			boolean buy = true;
			long last = Long.MAX_VALUE;
			for (int i = 0; i < stockInfo.getRight().size(); i++) {
				
				if (stockInfo.getRight().get(i).getBuy() >= last)
					buy = false;
				last = stockInfo.getRight().get(i).getBuy();
			}
			
			//Buy!
			if (buy) {
				Log.instance().log( Log.TAG.VERY_VERBOSE, "Algo1: BUY!" );
				//Buy a couple of stock, if the stockprice is NOT zero (avoid divide by zero)
				long firstStockBuyPrice = stockInfo.getRight().get(0).getBuy();
				if( firstStockBuyPrice != 0 ) {
					
					long amount = (long) (portfolio.getPortfolioTable().getBalance()/10/firstStockBuyPrice); 
					
					if (amount > 0)
						buyStock( stockInfo.getRight().get(0), amount );
				}
			}
		}
		
		return true;
	}
	private void buyStock(StockPrices stockPrices, long amount) {
		trader.buyStock(stockPrices, amount, portfolio.getPortfolioTable());
		
	}

	@Override
	public String getName() {
		return "TestAlgoritm1";
	}
	@Override
	public String getDescription() {
		return "Test algorithm1\n\nBuys when a stock has gone up 3 times in a row\nSells when a stock has gone down 3 times in a row";
	}

	@Override
	public Set<AlgorithmSettingDouble> getDefaultDoubleSettings() {
		Set<AlgorithmSettingDouble> doubleSettings = new HashSet<AlgorithmSettingDouble>();
		return doubleSettings;
	}

	@Override
	public Set<AlgorithmSettingLong> getDefaultLongSettings() {
		Set<AlgorithmSettingLong> longSettings = new HashSet<AlgorithmSettingLong>();
		longSettings.add(new AlgorithmSettingLong("buy", 3, "Number of times a stock has to climb before buying", 1, 1, 100));
		longSettings.add(new AlgorithmSettingLong("sell", 3, "Number of times a stock has to drop before selling", 2, 1, 100));
		return longSettings;
	}

	@Override
	public boolean giveDoubleSettings(Set<AlgorithmSettingDouble> doubleSettings) {
		return false;
	}

	@Override
	public boolean giveLongSettings(Set<AlgorithmSettingLong> longSettings) {
		for (AlgorithmSettingLong asl : longSettings) {
			if (asl.getName().contains("buy")) {
				this.buySetting = asl.getValue();
			}
			else if (asl.getName().contains("sell")) {
				this.sellSetting = asl.getValue();
			}
			else 
				return false;
		}
		return true;
	}
}
