package VK.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;
import VK.model.AbstractModel;
import VK.model.Model;

@SuppressWarnings("serial")
public abstract class AbstractView extends JPanel {
	
	protected Model model;
	public Graphics g;
	public int xScale, yScale;
	protected int gridWidth, gridHeight;
	protected final int GRID_VIEW_SCALING_FACTOR = 6;
	protected Dimension size;
	protected Image fieldImage;

	public AbstractView(Model newModel) {
		this.model=newModel;
		this.model.addView(this);
		
		this.size = new Dimension();
		this.gridWidth = 100;
		this.gridHeight = 100;
	}
	
	public AbstractModel getModel() {
		return this.model;
	}
	
	public void updateView(){
		repaint();
	}
	
	/**
	 * Prepare for a new round of painting. Since the component
	 * mey be resized, compute the scaling factor again.
	 */
	public abstract void preparePaint();
}