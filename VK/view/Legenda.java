package VK.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Legenda extends JDialog implements ActionListener {

	@SuppressWarnings("null")
	public Legenda(JFrame parent, String title) {
		super(parent, title, true);
		if (parent != null) {
			Dimension parentSize = parent.getSize();
			Point p = parent.getLocation();
			setLocation(p.x + parentSize.width / 4, p.y + parentSize.height / 4);
		}
		int width = 320;
		int height = 200;
        int[] location = centerFrame(width, height);
		this.setBounds(location[0], location[1], width, height);
		
		String vos = "Blauw: vos.";
		
		JTextArea textarea = new JTextArea("De volgende kleuren staan voor de volgende dingen: \n\n" +
		"Blauw: Vos. \n" +
		"Rood: Beer. \n" +
		"Oranje: Konijn. \n" +
		"Zwart: Jager.");
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		
		textarea.setEditable(false);
		
		getContentPane().add(textarea);
		JPanel buttonPane = new JPanel();
		JButton button = new JButton("OK");
		buttonPane.add(button);
		button.addActionListener(this);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
        
		parent.pack();
	}
	
	public static int[] centerFrame(int width, int height){
		int[] place = new int[2];
        int frameHeight = height;
        int frameWidth = width;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = dim.height;
        int screenWidth = dim.width;

        place[0] = ((screenWidth-frameWidth)/2);
        place[1] = ((screenHeight-frameHeight)/2);
        
        return place;
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		setVisible(false);
		dispose();
	}
}