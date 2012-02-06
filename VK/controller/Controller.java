package VK.controller;

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

import VK.model.AbstractModel;
import VK.model.Model;

/**
 * 
 * @author Hylke de Vries
 * @version 0.0
 */

@SuppressWarnings("serial")
public class Controller extends AbstractController implements ActionListener {
	
	private JButton btnStart1, btnStart100, btnSimuleer, btnStart, btnStop, btnReset;
	private JTextField aantalStappen;
	protected Model model;

	public Controller(AbstractModel newModel) {
		super(newModel);

		this.add(makeMenuBar());
		this.add(makeWestBorder());
		this.add(makeEastBorder());
	}
	
	/**
	 * Makes a JPanel with a BoxLayout.
	 * Contains buttons
	 * 
	 * @return JPanel
	 */
	@Override
	public JPanel makeWestBorder() {
		JPanel panel2 = new JPanel();
		JPanel westborder = new JPanel();
		
		//Make buttons
		this.btnStart1 = new JButton("Step 1");
		this.btnStart1.addActionListener(this);
		
		this.btnStart100 = new JButton("Step 100");
		this.btnStart100.addActionListener(this);
		
		this.aantalStappen = new JTextField();
		
		this.btnSimuleer = new JButton("Simuleer");
		this.btnSimuleer.addActionListener(this);
		
		this.btnStart = new JButton("Start");
		this.btnStart.addActionListener(this);
		
		this.btnStop = new JButton("Stop");
		this.btnStop.addActionListener(this);
		
		this.btnReset = new JButton("Reset");
		this.btnReset.addActionListener(this);
		
		//An empty label, to create an empty line in the buttonmenu
		JLabel emptyLabel1 = new JLabel();
		JLabel emptyLabel2 = new JLabel();
		JLabel emptyLabel3 = new JLabel();
		
		//Make frames
		panel2.add(this.btnStart1);
		panel2.add(this.btnStart100);
		
		panel2.add(emptyLabel1);
		panel2.add(this.aantalStappen);
		panel2.add(this.btnSimuleer);
		
		panel2.add(emptyLabel2);
		panel2.add(this.btnStart);
		panel2.add(this.btnStop);
		
		panel2.add(emptyLabel3);
		panel2.add(this.btnReset);
		
		panel2.setLayout(new GridLayout(0,1));
		
		westborder.add(panel2);
		westborder.setVisible(true);
		
		return westborder;
	}
    
	/**
	* Makes the menubar
	*
	* @return JMenuBar
	*/
	@Override
	public JMenuBar makeMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menuMenu1 = new JMenu("Bestand");
		final JMenuItem quitItem = new JMenuItem("Afsluiten");
		quitItem.addActionListener(this);
		menuMenu1.add(quitItem);
		menuBar.add(menuMenu1);
	
		JMenu menuHelp = new JMenu("Help");
		final JMenuItem legendaItem = new JMenuItem("Legenda");
		menuHelp.add(legendaItem);
		legendaItem.addActionListener(this);
		menuBar.add(menuHelp);
	
		return menuBar;
	}
    
    /**
    * Creates a JPanel with a BoxLayout to facilitate the Graphics
    * @return
    */

    @Override
	public JPanel makeEastBorder(){
	    JPanel panel = new JPanel();
	    JPanel eastborder = new JPanel();
	
	    final JButton btnTest = new JButton("Test");
	    btnTest.addActionListener(this);
	
	    panel.add(btnTest);
	    panel.setLayout(new GridLayout(0,3));
	
	    eastborder.add(panel);
	    eastborder.setVisible(true);
	
	    return eastborder;
    }

	@Override
	public void actionPerformed(ActionEvent ae) {
		Object e = ae.getSource();
		if (e==this.btnStart1) {
			this.model.simulateOneStep();
		}
		
		if (e==this.btnStart100) {
			this.model.simulate(100);
		}
		
		if (e==this.btnSimuleer) {
			try{
				String aantalstappen = this.aantalStappen.getText();
				int aantal = Integer.parseInt(aantalstappen);
				
				if (aantal<=0)
					System.out.println("Aantal dagen mag geen 0 zijn!");
				else{
					if (this.model.run) this.model.stop();
					this.model.simulate(aantal);
				}
			}
			catch (Exception exc){
				System.out.println("Voer een positief getal in!");
			}
		}
		
		if (e==this.btnStart) {
			this.model.start();
		}
		
		if (e==this.btnStop) {
			this.model.stop();
		}
		
		if (e==this.btnReset) {
			this.model.reset();
		}
	}
}
