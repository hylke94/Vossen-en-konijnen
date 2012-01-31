package VK.controller;
import javax.swing.*;

import VK.model.Model;

import java.awt.event.*;

/**
 * 
 * @author Hylke de Vries
 * @version 0.0
 */

@SuppressWarnings("serial")
public class Controller extends JPanel implements ActionListener {
	private Model model;
	private JButton mineen;
	private JButton pluseen;
	private JButton start;
	private JButton stop;
	
	public Controller(Model newModel) {
		this.model=newModel;
		
		setSize(450, 50);
		this.mineen=new JButton("-1");
		this.mineen.addActionListener(this);
		this.pluseen=new JButton("+1");
		this.pluseen.addActionListener(this);
		this.start=new JButton("Start");
		this.start.addActionListener(this);
		this.stop=new JButton("Stop");
		this.stop.addActionListener(this);
		
		this.setLayout(null);
		add(this.mineen);
		add(this.pluseen);
		add(this.start);
		add(this.stop);
		this.mineen.setBounds(50, 10, 70, 30);
		this.pluseen.setBounds(140, 10, 70, 30);
		this.start.setBounds(229, 10, 70, 30);
		this.stop.setBounds(319, 10, 70, 30);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==this.mineen) {
			this.model.setAantal(this.model.getAantal()-1);
		}
		
		if (e.getSource()==this.pluseen) {
			this.model.setAantal(this.model.getAantal()+1);
		}
		
		if (e.getSource()==this.start) {
			this.model.start();
		}
		
		if (e.getSource()==this.stop) {
			this.model.stop();
		}
	}
}
