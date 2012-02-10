package VK.utils.pictures;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class SplashScreen extends JWindow {

	private int duration;

	public SplashScreen(int d) {
		this.duration = d;
	}

	// A simple little method to show a title screen in the center
	// of the screen for the amount of time given in the constructor
	public void showSplash() {

		JPanel panel = (JPanel)getContentPane();
		panel.setBackground(Color.white);

		// Build the splash screen
		JLabel image = new JLabel(new ImageIcon("src/VK/utils/pictures/vos_konijn.jpg"));
		JLabel info = new JLabel("Project \"Vossen & konijnen\"", SwingConstants.CENTER);
		JLabel info2 = new JLabel("Gemaakt door Freddy, Hylke, Pim en Ole", SwingConstants.CENTER);
		info.setFont(new Font("Sans-Serif", Font.BOLD, 12));
		panel.add(info, BorderLayout.NORTH);
		panel.add(image, BorderLayout.CENTER);
		panel.add(info2, BorderLayout.SOUTH);
		
		Color oraRed = new Color(156, 20, 20, 255);
		panel.setBorder(BorderFactory.createLineBorder(oraRed, 2));

		// Set the window's bounds, centering the window
		int width = 450;
		int height =350;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width-width)/2;
		int y = (screen.height-height)/2;
		setBounds(x,y,width,height);

		// Display it
		setVisible(true);

		// Wait a little while, maybe while loading resources
		try {
			Thread.sleep(this.duration);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		setVisible(false);
	}

	public void showSplashAndExit() {

		showSplash();

	}
}