package VK.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import VK.model.Model;

public class TextView extends AbstractView implements SimulatorView{

	private static final long serialVersionUID = 1L;
	
	// A statistics object computing and storing simulation information
    private JTextArea output;

    /**
     * Create a view of the given width and height.
     */
    public TextView(Model newModel) {
    	super(newModel);
    	
        this.output = new JTextArea("", 10, 40);
        this.output.setEditable(false);
        this.output.setLineWrap(true);
        JScrollPane scrollBar = new JScrollPane(this.output);
        scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollBar, BorderLayout.CENTER);
    }

    /**
     * Determine whether the simulation should continue to run.
     * @return true If there is more than one species alive.
     */
    public JTextArea getOutput() {
        return this.output;

    }

	@SuppressWarnings("rawtypes")
	@Override
	public void setColor(Class cl, Color color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isViable(Field field) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void showStatus(int step, Field field) {
		System.out.println("Poplation:");
		System.out.println("Bear: "+", Fox: "+", Rabbit: "+", Hunter: ");
	}

	@Override
	public void preparePaint() {
		// TODO Auto-generated method stub
		
	}
}
