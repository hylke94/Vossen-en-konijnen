package VK.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import VK.model.Model;
import VK.model.actors.Bear;
import VK.model.actors.Fox;
import VK.model.actors.Grass;
import VK.model.actors.Hunter;
import VK.model.actors.Rabbit;

public class TextView extends AbstractView implements ActionListener{

	private static final long serialVersionUID = 1L;
	
    // A statistics object computing and storing simulation information
    private JTextArea output;
    private JButton clear;

    /**
    * Create a view of the given width and height.
    */
    public TextView(Model newModel) {
super(newModel);
        
        JPanel panel = new JPanel();
        this.clear = new JButton("Clear");
        this.clear.addActionListener(this);
        panel.add(this.clear);
        
        this.output = new JTextArea("Tekstview: \n\n", 40, 40);
        this.output.setEditable(false);
        this.output.setLineWrap(true);
        JScrollPane scrollBar = new JScrollPane(this.output);
        scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    
        add(scrollBar, BorderLayout.CENTER);
        add(panel);
        
        setVisible(true);

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
        int steps = this.model.getSteps();

        this.output.append("Step: " + steps + "\n");
        this.output.append("Rabbit: " + rC + "\n");
        this.output.append("Fox: " + fC + "\n");
        this.output.append("Bear: " + bC + "\n");
        this.output.append("Hunter: " + hC + "\n");
        this.output.append("Grass: " + gC + "\n");
        this.output.append("-----------------------\n");

        
        this.output.setCaretPosition(this.output.getDocument().getLength());    
    }

    
    public JTextArea getOutput() {
    	return this.output;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Clear"){
            this.output.setText("");
        }
    }

	@Override
	public void preparePaint() {
		// TODO Auto-generated method stub
	}
}
