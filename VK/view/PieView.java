package VK.view;

import java.awt.Color;
import java.awt.Graphics;

import VK.actors.Bear;
import VK.actors.Fox;
import VK.actors.Grass;
import VK.actors.Hunter;
import VK.actors.Rabbit;
import VK.model.Model;

@SuppressWarnings("serial")
public class PieView extends AbstractView {
	
	// The size of the cirkel
	private int cirkelSize;
	// The distance between the pie and the end of the panel
	private final int distance = 100;

	/**
	* Constructor
	*/
	public PieView(Model newModel) {
		 super(newModel);
	}
	
	@Override
	public void paintComponent(Graphics gInput) {
		if (this.getWidth()<this.getHeight()) this.cirkelSize = this.getWidth()-this.distance;
		else this.cirkelSize=this.getHeight()-this.distance;
		
		gInput.setColor(Color.WHITE);
		gInput.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		float rC=this.model.getCount(Rabbit.class);
		float fC=this.model.getCount(Fox.class);
		float bC=this.model.getCount(Bear.class);
		float hC=this.model.getCount(Hunter.class);
		float gC=this.model.getCount(Grass.class);
	  
		float total = rC + fC + bC + hC + gC;
	  
		float temp = 0.0f;
	  
		temp = (3.6f * ((rC/total)*100));
		int rA = Math.round(temp);
	
		temp = (3.6f * ((fC/total)*100));
		int fA = Math.round(temp);
	  
		temp = (3.6f * ((bC/total)*100));
		int bA = Math.round(temp);
	  
		temp = (3.6f * ((hC/total)*100));
		int hA = Math.round(temp);
		  
		temp = (3.6f * ((gC/total)*100));
		int gA = Math.round(temp);
		
		gInput.setColor(Model.getColor(Rabbit.class));
		gInput.fillArc((this.getWidth()-this.cirkelSize)/2, (this.getHeight()-this.cirkelSize)/2, this.cirkelSize, this.cirkelSize, 0, rA);
		gInput.setColor(Model.getColor(Fox.class));
		gInput.fillArc((this.getWidth()-this.cirkelSize)/2, (this.getHeight()-this.cirkelSize)/2, this.cirkelSize, this.cirkelSize, rA, fA);
		gInput.setColor(Model.getColor(Bear.class));
		gInput.fillArc((this.getWidth()-this.cirkelSize)/2, (this.getHeight()-this.cirkelSize)/2, this.cirkelSize, this.cirkelSize, (fA + rA) , bA);
		gInput.setColor(Model.getColor(Hunter.class));
		gInput.fillArc((this.getWidth()-this.cirkelSize)/2, (this.getHeight()-this.cirkelSize)/2, this.cirkelSize, this.cirkelSize, (fA + rA + bA) , hA);
		gInput.setColor(Model.getColor(Grass.class));
		gInput.fillArc((this.getWidth()-this.cirkelSize)/2, (this.getHeight()-this.cirkelSize)/2, this.cirkelSize, this.cirkelSize, (fA + rA + bA + hA) , gA);
	}

	@Override
	public void preparePaint() {
		// TODO Auto-generated method stub
		
	}
}