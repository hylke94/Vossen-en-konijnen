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
	private final int cirkelSize = 350;

	/**
	* Constructor
	*/
	public PieView(Model newModel) {
		 super(newModel);
	}
	
	@Override
	public void paintComponent(Graphics gInput) {
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
		
		gInput.setColor(this.model.getColor(Rabbit.class));
		gInput.fillArc((this.getWidth()-this.cirkelSize)/2, (this.getHeight()-this.cirkelSize)/2, this.cirkelSize, this.cirkelSize, 0, rA);
		gInput.setColor(this.model.getColor(Fox.class));
		gInput.fillArc((this.getWidth()-this.cirkelSize)/2, (this.getHeight()-this.cirkelSize)/2, this.cirkelSize, this.cirkelSize, rA, fA);
		gInput.setColor(this.model.getColor(Bear.class));
		gInput.fillArc((this.getWidth()-this.cirkelSize)/2, (this.getHeight()-this.cirkelSize)/2, this.cirkelSize, this.cirkelSize, (fA + rA) , bA);
		gInput.setColor(this.model.getColor(Hunter.class));
		gInput.fillArc((this.getWidth()-this.cirkelSize)/2, (this.getHeight()-this.cirkelSize)/2, this.cirkelSize, this.cirkelSize, (fA + rA + bA) , hA);
		gInput.setColor(this.model.getColor(Grass.class));
		gInput.fillArc((this.getWidth()-this.cirkelSize)/2, (this.getHeight()-this.cirkelSize)/2, this.cirkelSize, this.cirkelSize, (fA + rA + bA + hA) , gA);
	}

	@Override
	public void preparePaint() {
		// TODO Auto-generated method stub
		
	}
}