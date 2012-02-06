package VK.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.*;

import VK.model.AbstractModel;

@SuppressWarnings("serial")
public abstract class AbstractController extends JPanel {
	
	protected AbstractModel abstractModel;
	
	public AbstractController(AbstractModel newAbstractModel) {
		this.abstractModel=newAbstractModel;
	}
	
	public abstract void actionPerformed(ActionEvent ae);

	public abstract JMenuBar makeMenuBar();

	public abstract Component makeEastBorder();

	public abstract Component makeWestBorder();
}