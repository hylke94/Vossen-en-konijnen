package VK.controller;

import javax.swing.*;

import VK.model.Model;

@SuppressWarnings("serial")
public abstract class AbstractController extends JPanel {
	
	protected Model model;
	
	public AbstractController(Model newModel) {
		this.model=newModel;
	}
}