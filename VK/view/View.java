package VK.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

import VK.model.Model;

/**
 * @author Hylke de Vries
 * @version 0.0
 */

@SuppressWarnings("serial")
public class View extends AbstractView implements ActionListener {
	
	public AbstractView animatedView;
	private JPanel rightPanel = new JPanel();
	public AbstractView textView;
	public ArrayList<AbstractView> views;
	
    public View(Model newModel) {
		super(newModel);
		
		this.textView = new TextView(newModel);
		this.animatedView = new AnimatedView(newModel);
		
		this.rightPanel.setLayout(new GridLayout(0,1));
		this.rightPanel.add(this.textView);
		
		this.setLayout(new FlowLayout());
		this.add(this.animatedView);
		this.add(this.rightPanel);
		this.setVisible(true);
    }
	
	@Override
	public void actionPerformed(ActionEvent e){
		Object command = e.getActionCommand();
		
		if (command=="Afsluiten"){
			System.exit(0);
		}
		else{
			System.out.println("Geen actie voor command bekend!\n	Command = \""+command+"\";\n	"+e+"\n");
		}
	}

	@Override
	public void preparePaint() {
		// TODO Auto-generated method stub
		
	}
}
