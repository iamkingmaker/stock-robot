package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import portfolio.IPortfolioHandler;
import portfolio.PortfolioHandler;

public class PortfolioController {

	public PortfolioController(PortfolioGui gui, IPortfolioHandler portfolioHandler){
		
		portfolioHandler.addAddObserver(gui);
		gui.addBalanceHistoryListener(new BalanceHistoryListener());
		gui.addChangeAlgorithmListener(new ChangeAlgorithmListener());
		gui.addChangePortfolioListener(new ChangePortfolioListener());
	}
	
	/**
	 * Listener for the button history in portfolio gui
	 */
	private class BalanceHistoryListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			System.out.println("Pressed history");
		}
	}
	
	/**
	 * Listener for the button change algorithm in portfolio gui
	 */
	private class ChangeAlgorithmListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			System.out.println("Pressed change");
		}
	}
	
	/**
	 * Listener for the button change algorithm in portfolio gui
	 */
	private class ChangePortfolioListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String portfolio = (String)((JComboBox)e.getSource()).getSelectedItem();
			System.out.println(portfolio);
		}


	}
}
