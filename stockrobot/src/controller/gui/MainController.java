package controller.gui;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.Map;

import view.IView;

public class MainController implements IController {
	IView view;
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display(Object model) {
		view.display(null);
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, ActionListener> getActionListeners() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addSubController(IController subController) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return "MainMenu";
	}

	@Override
	public void defineSubControllers() {
		// TODO Auto-generated method stub
		
	}

}
