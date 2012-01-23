import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * @author Hylke de Vries
 * @version 1.0
 */
public class View implements ActionListener {
	
	static Simulator sim;
	
	public View(int depth, int width){
		JFrame frame = new JFrame("Foxes and rabbits");
		
		frame.setJMenuBar(makeMenuBar());
		
		frame.add(makeSouthBorder(), BorderLayout.SOUTH);
		frame.add(makeWestBorder(), BorderLayout.WEST);
		frame.add(makeCenterBorder(depth, width), BorderLayout.CENTER);
		
		frame.setLocation(50, 25);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public View(){
		JFrame frame = new JFrame("Foxes and rabbits");
		
		frame.setJMenuBar(makeMenuBar());
		
		frame.add(makeWestBorder(), BorderLayout.WEST);
		frame.add(makeSouthBorder(), BorderLayout.SOUTH);
		frame.add(makeCenterBorder(), BorderLayout.CENTER);
		
		frame.setLocation(50, 25);
		
		frame.pack();
		frame.setVisible(true);
	}

	private JMenuBar makeMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menuMenu1 = new JMenu("Menu1");
			JMenuItem openItem = new JMenuItem("Openen");
			openItem.addActionListener(this);
			menuMenu1.add(openItem);
			JMenuItem quitItem = new JMenuItem("Afsluiten");
			quitItem.addActionListener(this);
			menuMenu1.add(quitItem);
				menuBar.add(menuMenu1);
		JMenu menuMenu2 = new JMenu("Menu2");
			JMenuItem menu2Item = new JMenuItem("Menu2Item");
			menuMenu2.add(menu2Item);
				menuBar.add(menuMenu2);
		JMenu menuHelp = new JMenu("Help");
			JMenuItem helpItem = new JMenuItem("Help");
			menuHelp.add(helpItem);
				menuBar.add(menuHelp);
		
		return menuBar;
	}
	
	private static JPanel makeCenterBorder(int depth, int width) {
		JPanel centerborder = new JPanel();
		
		View.sim = new Simulator(depth, width);
		
		centerborder.add(View.sim.getSimulatorView());

		centerborder.setVisible(true);
		
		return centerborder;
	}
	
	private static JPanel makeCenterBorder() {
		JPanel centerborder = new JPanel();
		
		sim = new Simulator();
		
		centerborder.add(View.sim.getSimulatorView());

		centerborder.setLayout(new GridLayout(0, 1));
		
		centerborder.setVisible(true);
		
		return centerborder;
	}
	
	private static JPanel makeWestBorder() {
		JPanel westborder = new JPanel();
		
		JButton button1 = new JButton("Step 1");
		//button1.setPreferredSize(new Dimension(100, 30));
		//button1.setSize(100,30);
		//button1.setAlignmentY(100);
		button1.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						View.sim.simulateOneStep();
					}	
				}
		);
		//button1.addActionListener(this);
		JButton button2 = new JButton("Step 100");
		button2.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						View.sim.simulate(100);
					}	
				}
		);
		
		westborder.add(button1);
		westborder.add(button2);
		
		BoxLayout bl = new BoxLayout(westborder, 1);
		westborder.setLayout(bl);
		
		westborder.setVisible(true);
		
		return westborder;
	}

	private static JPanel makeSouthBorder() {
		JPanel southborder = new JPanel();
		
		@SuppressWarnings("static-access")
		JLabel labelBottom = new JLabel("Version 0.0", JLabel.LEFT);
		
		southborder.add(labelBottom, BorderLayout.SOUTH);
		
		southborder.setVisible(true);
		
		return southborder;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object command = e.getActionCommand();
		
		if (command!=null){
			System.out.println("Geen acte voor command ingevoer!\n	Command = \""+command+"\";\n	"+e+"\n");
		}
		else{
			System.out.println("Command is 'null'!\n	"+e);
		}
	}
}
