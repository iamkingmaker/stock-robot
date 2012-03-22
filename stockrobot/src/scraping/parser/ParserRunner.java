package scraping.parser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import scraping.database.IInserter;
import scraping.database.Inserter;
import scraping.database.JPAInserter;
import scraping.model.ParserStock;
import scraping.scheduler.IScheduler;
import scraping.scheduler.Scheduler;

/**
 * Parser runner.
 * @author Erik
 *
 */
public class ParserRunner implements IParserRunner {
	
	boolean run = false;
	boolean close = false;
	AvanzaParser parser;
	IScheduler scheduler;
	IInserter inserter;
	
	public ParserRunner(){
		parser = new AvanzaParser();
		inserter = new JPAInserter();
		scheduler = new Scheduler();
		
	}
	@Override
	public void run() {
		ArrayList<URL> avanzaURLList = new ArrayList<URL>();
		try {
			avanzaURLList.add(new URL("https://www.avanza.se/aza/aktieroptioner/kurslistor/kurslistor.jsp?cc=SE&lkey=LargeCap.SE"));
			avanzaURLList.add(new URL("https://www.avanza.se/aza/aktieroptioner/kurslistor/kurslistor.jsp?cc=SE&lkey=MidCap.SE"));
			avanzaURLList.add(new URL("https://www.avanza.se/aza/aktieroptioner/kurslistor/kurslistor.jsp?cc=SE&lkey=SmallCap.SE"));
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		while(!close){
			while(run){
				
				//Should run right now?
				if( scheduler.shouldRun() ) {
					
					ArrayList<ParserStock> stockList = null;
					Long timeBefore = System.currentTimeMillis();
					for(URL url : avanzaURLList){
						stockList = parser.parse(url, "LargeCap");
//						for(ParserStock s : stockList){
//							System.out.println(s);
//						}
						
					    inserter.insertStockData(stockList);

					}
					Long timeElapsed = System.currentTimeMillis() - timeBefore;
					System.out.println("Parsing done in:" +timeElapsed + " ms.");
					try {
						Thread.sleep(60000-timeElapsed);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			while(!run){
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/** Pause the parser until run() is called. */
	@Override
	public void hold() {
		//TODO: Delete?
		run = false;
	}
	
	/**
	 *  Stops the Runner
	 */
	public boolean stopRunner() {
		//TODO: Delete?
		if(!close){
			close = true;
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 *  Stops the parser
	 *  
	 */
	public boolean stopParser() {
		//TODO: Delete?
		if(run){
			run = false;
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 *  Stops the parser
	 *  
	 */
	public boolean startParser() {
		//TODO: Delete?
		if(!run){
			run = true;
			return true;
		}
		else{
			return false;
		}
	}
	
}