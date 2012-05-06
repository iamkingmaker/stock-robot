package model.scraping.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for Harvester.
 * @author Erik
 *
 */
public class HarvesterController {
	
	private Harvester model;
	private HarvesterView view;

	public HarvesterController(Harvester model, HarvesterView view) {
		this.model = model;
		this.view = view;
		
	    view.addbtnStartParserListener(new StartBtnListener());
	    view.addbtnStopParserListener(new StopBtnListener());
	    
	}
	
	
	private class StartBtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			model.startParser();
		}
	}
	
	private class StopBtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			model.stopParser();
		}
	}

    

}
