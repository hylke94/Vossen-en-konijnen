package VK.controller;
import javax.swing.*;

import VK.model.Model;
import VK.simulator.Simulator;
import java.awt.GridLayout;
import java.awt.event.*;

/**
 * 
 * @author Hylke de Vries
 * @version 0.0
 */

@SuppressWarnings("serial")
public class Controller extends AbstractController implements ActionListener {
	
	private JButton btnStart1;
	private JButton btnStart100;
	private JTextField aantalStappen;
	private JButton btnSimuleer;
	private JButton start;
	private JButton stop;
	private JButton btnReset;

	public Controller(Model newModel) {
		super(newModel);
		
		this.add(makeWestBorder());
	}
	
	/**
	 * Makes a JPanel with a BoxLayout.
	 * Contains buttons
	 * 
	 * @return JPanel
	 */
	private static JPanel makeWestBorder() {
		JPanel panel2 = new JPanel();
		JPanel westborder = new JPanel();
		
		//Make buttons
		JButton btnStart1 = new JButton("Step 1");
		btnStart1.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						if (Simulator.getRun()) Simulator.stop();
						Simulator.simulateOneStep();
					}	
				});
		JButton btnStart100 = new JButton("Step 100");
		btnStart100.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						if (Simulator.getRun()) Simulator.stop();
						Simulator.simulate(100);
					}	
				});
		final JTextField aantalStappen = new JTextField();
		JButton btnSimuleer = new JButton("Simuleer");
		btnSimuleer.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						try{
							String aantalstappen = aantalStappen.getText();
							int aantal = Integer.parseInt(aantalstappen);
							
							if (aantal<=0)
								System.out.println("Aantal dagen mag geen 0 zijn!");
							else{
								if (Simulator.getRun()) Simulator.stop();
								Simulator.simulate(aantal);
							}
						}
						catch (Exception exc){
							System.out.println("Voer een positief getal in!");
						}
					}
				});
		final JButton btnStart = new JButton("Start");
		btnStart.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){
						if (!Simulator.getRun()) Simulator.start();
					}
				});
		final JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){
						if (Simulator.getRun()) Simulator.stop();
					}
				});
		final JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){
						if (Simulator.getRun()) Simulator.stop();
						Simulator.reset();
					}
				});
		
		//An empty label, to create an empty line in the buttonmenu
		JLabel emptyLabel1 = new JLabel();
		JLabel emptyLabel2 = new JLabel();
		JLabel emptyLabel3 = new JLabel();
		
		//Make frames
		panel2.add(btnStart1);
		panel2.add(btnStart100);
		
		panel2.add(emptyLabel1);
		panel2.add(aantalStappen);
		panel2.add(btnSimuleer);
		
		panel2.add(emptyLabel2);
		panel2.add(btnStart);
		panel2.add(btnStop);
		
		panel2.add(emptyLabel3);
		panel2.add(btnReset);
		
		panel2.setLayout(new GridLayout(0,1));
		
		
		westborder.add(panel2);
		westborder.setVisible(true);
		
		return westborder;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==this.btnStart1) {
			model.simulateOneStep();
		}
		
		if (e.getSource()==this.btnStart100) {
			model.simulate(100);
		}
		
		if (e.getSource()==this.btnSimuleer) {
			this.model.setSteps(this.model.getSteps()+1);
			
			try{
				String aantalstappen = this.aantalStappen.getText();
				int aantal = Integer.parseInt(aantalstappen);
				
				if (aantal<=0)
					System.out.println("Aantal dagen mag geen 0 zijn!");
				else{
					if (this.model.getRun()) this.model.stop();
					model.simulate(aantal);
				}
			}
			catch (Exception exc){
				System.out.println("Voer een positief getal in!");
			}
		}
		
		if (e.getSource()==this.start) {
			this.model.start();
		}
		
		if (e.getSource()==this.stop) {
			this.model.stop();
		}
		
		if (e.getSource()==this.btnReset) {
			model.reset();
		}
	}
}
