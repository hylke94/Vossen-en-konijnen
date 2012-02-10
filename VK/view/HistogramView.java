package VK.view;

import java.awt.Color;
import java.awt.Graphics;

import VK.model.Model;
import VK.model.actors.Bear;
import VK.model.actors.Fox;
import VK.model.actors.Grass;
import VK.model.actors.Hunter;
import VK.model.actors.Rabbit;

@SuppressWarnings("serial")
public class HistogramView extends AbstractView{
	
	private int spaceBetween = 30;
	private int rectWidth = 100;
	private int spaceAround = 10;
	private int maxRectHeight;
	
	public HistogramView(Model newModel){
		super(newModel);
	}
	
	@Override
	public void paintComponent(Graphics gInput)
	{
		this.maxRectHeight = (this.getHeight()-(2*this.spaceAround));
		
		// Teken de achtergrond
		gInput.setColor(Color.WHITE);
		gInput.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// Haal het aantal per actor op
		double[] aantalActors = new double[5];
		aantalActors[0] = (Math.round(this.model.getCount(Rabbit.class)));
		aantalActors[1] = (Math.round(this.model.getCount(Fox.class)));
		aantalActors[2] = (Math.round(this.model.getCount(Bear.class)));
		aantalActors[3] = (Math.round(this.model.getCount(Hunter.class)));
		aantalActors[4] = (Math.round(this.model.getCount(Grass.class)));
		
		// Zoek het maximum aantal
		double maxAantal = 0;
		for (int i=0; i<5; i++){
			if (aantalActors[i]>maxAantal) maxAantal = aantalActors[i];
		}
		System.out.println();
		
		// Bereken percentage (aantal actors - hoogte scherm)
		double[] perc = new double[5];
		perc[0] = (aantalActors[0]/maxAantal)*100;
		perc[1] = (aantalActors[1]/maxAantal)*100;
		perc[2] = (aantalActors[2]/maxAantal)*100;
		perc[3] = (aantalActors[3]/maxAantal)*100;
		perc[4] = (aantalActors[4]/maxAantal)*100;
		
		// Bereken de hoogte van de balkjes
		double[] rectHeight = new double[5];
		rectHeight[0] = (int) (this.maxRectHeight*(perc[0]/100));
		rectHeight[1] = (int) (this.maxRectHeight*(perc[1]/100));
		rectHeight[2] = (int) (this.maxRectHeight*(perc[2]/100));
		rectHeight[3] = (int) (this.maxRectHeight*(perc[3]/100));
		rectHeight[4] = (int) (this.maxRectHeight*(perc[4]/100));
		
		// Centreer het frame
		int rxr = ((this.getWidth()-(5*this.rectWidth+4*this.spaceBetween))/2);
		int ryr = this.spaceAround;
		int rxf = rxr + this.spaceBetween + this.rectWidth;
		int ryf = this.spaceAround;
		int rxb = rxf + this.spaceBetween + this.rectWidth;
		int ryb = this.spaceAround;
		int rxh = rxb + this.spaceBetween + this.rectWidth;
		int ryh = this.spaceAround;
		int rxg = rxh + this.spaceBetween + this.rectWidth;
		int ryg = this.spaceAround;
		
		// Teken het histogram
		gInput.setColor(Model.getColor(Rabbit.class));
		gInput.fillRect(rxr, ryr, this.rectWidth, (int) (rectHeight[0]));
		gInput.setColor(Model.getColor(Fox.class));
		gInput.fillRect(rxf, ryf, this.rectWidth, (int) (rectHeight[1]));
		gInput.setColor(Model.getColor(Bear.class));
		gInput.fillRect(rxb, ryb, this.rectWidth, (int) (rectHeight[2]));
		gInput.setColor(Model.getColor(Hunter.class));
		gInput.fillRect(rxh, ryh, this.rectWidth, (int) (rectHeight[3]));
		gInput.setColor(Model.getColor(Grass.class));
		gInput.fillRect(rxg, ryg, this.rectWidth, (int) (rectHeight[4]));
	}

	@Override
	public void preparePaint()
	{
		// TODO Auto-generated method stub
	}
}