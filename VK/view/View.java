package VK.view;
import javax.swing.*;

import VK.model.Model;

/**
 * 
 * @author Hylke de Vries
 * @version 0.0
 */

@SuppressWarnings("serial")
public class View extends JPanel {
	private Model model;
	
	public View(Model newModel) {
		this.model=newModel;
		this.model.addView(this);
		setSize(200, 200);
		setVisible(true);
	}
	
	public void setModel(Model newModel) {
		this.model=newModel;
	}
	
	public Model getModel() {
		return this.model;
	}
	
	public void updateView() {
		repaint();
	}
}
