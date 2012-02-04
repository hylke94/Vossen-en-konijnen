package VK.view;

import javax.swing.*;

import VK.model.Model;

public abstract class AbstractView extends JPanel {

	private static final long serialVersionUID = 1L;
	
	protected Model model;

	public AbstractView(Model newModel) {
		this.model=newModel;
		this.model.addView(this);
	}
	
	public Model getModel() {
		return this.model;
	}
	
	public void updateView(){
		repaint();
	}
}