package VK.view;

import java.awt.Color;
import java.awt.Graphics;

import VK.actors.Bear;
import VK.actors.Fox;
import VK.actors.Hunter;
import VK.actors.Rabbit;
import VK.model.Model;

@SuppressWarnings("serial")
public class HistogramView extends AbstractView{
	
	private final int SCALE = 3;
	
	public HistogramView(Model newModel){
		super(newModel);
	}
	
	@Override
	public void paintComponent(Graphics gInput)
	{
		gInput.setColor(Color.WHITE);
		gInput.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		int height = this.getHeight();
		
		int rH = (Math.round(this.model.getCount(Rabbit.class)))/this.SCALE;
		int fH = (Math.round(this.model.getCount(Fox.class)))/this.SCALE;
		int bH = (Math.round(this.model.getCount(Bear.class)))/this.SCALE;
		int hH = (Math.round(this.model.getCount(Hunter.class)))/this.SCALE;
		
		
		gInput.setColor(this.model.getColor(Rabbit.class));
		gInput.fillRect(10, 10, 50, (rH));
		gInput.setColor(this.model.getColor(Fox.class));
		gInput.fillRect(70, 10, 50, (fH));
		gInput.setColor(this.model.getColor(Bear.class));
		gInput.fillRect(130, 10, 50, (bH));
		gInput.setColor(this.model.getColor(Hunter.class));
		gInput.fillRect(190, 10, 50, (hH));
	}

	@Override
	public void preparePaint()
	{
		// TODO Auto-generated method stub
	}
}