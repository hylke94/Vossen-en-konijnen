package VK.view;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import VK.model.Model;

public class TextView extends AbstractView{

	private static final long serialVersionUID = 1L;
	
    // A statistics object computing and storing simulation information
    private JTextArea output;

    /**
    * Create a view of the given width and height.
    */
    public TextView(Model newModel) {
    	super(newModel);
    	
	    this.output = new JTextArea("Tekstview: \n\n", 35, 40);
	    this.output.setEditable(false);
	    this.output.setLineWrap(true);
	    JScrollPane scrollBar = new JScrollPane(this.output);
	    scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	
	    add(scrollBar, BorderLayout.CENTER);
    }
    
    public JTextArea getOutput() {
    	return this.output;
    }

	@Override
	public void preparePaint() {
		// TODO Auto-generated method stub
	}
}
