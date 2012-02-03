package VK.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import VK.simulator.Field;

public class TextView extends JPanel implements SimulatorView{

	private static final long serialVersionUID = 1L;
	
	// A statistics object computing and storing simulation information
    private JTextArea output;

    /**
     * Create a view of the given width and height.
     */
    public TextView() {
        this.output = new JTextArea("", 40, 40);
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
		// TODO Auto-generated method stub
		
	}
}
