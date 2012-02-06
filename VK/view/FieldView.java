package VK.view;

import java.awt.Dimension;
import java.awt.Graphics;
import VK.model.AbstractModel;

@SuppressWarnings("serial")
public class FieldView extends AbstractView
{
	
	/**
     * Create a new FieldView component.
     */
    public FieldView(AbstractModel abstractModel) {
     super(abstractModel);
     setSize(200,200);
     setVisible(true);
    }

   
    /**
    * The field view component needs to be redisplayed. Copy the
	* internal image to screen.
	*/
    @Override
	public void paintComponent(Graphics gInput) {
        if(this.fieldImage != null) {
            Dimension currentSize = getSize();
            if(this.size.equals(currentSize)) {
                gInput.drawImage(this.fieldImage, 0, 0, null);
            }
            else {
                // Rescale the previous image.
                gInput.drawImage(this.fieldImage, 0, 0, currentSize.width, currentSize.height, null);
            }
        }
    }
    
    /**
     * Prepare for a new round of painting. Since the component
	* may be resized, compute the scaling factor again.
	*/
    public void preparePaint() {
        if(!this.size.equals(getSize())) { // if the size has changed...
            this.size = getSize();
            this.fieldImage = this.createImage(this.size.width, this.size.height);
            this.g = this.fieldImage.getGraphics();

            this.xScale = this.size.width / this.gridWidth;
            if(this.xScale < 1) {
                this.xScale = this.GRID_VIEW_SCALING_FACTOR;
            }
            this.yScale = this.size.height / this.gridHeight;
            if(this.yScale < 1) {
                this.yScale = this.GRID_VIEW_SCALING_FACTOR;
            }
        }
    }
    
    /**
     * Tell the GUI manager how big we would like to be.
	*/
    @Override
	public Dimension getPreferredSize() {
        return new Dimension(this.gridWidth * this.GRID_VIEW_SCALING_FACTOR,
                             this.gridHeight * this.GRID_VIEW_SCALING_FACTOR);
    }
}