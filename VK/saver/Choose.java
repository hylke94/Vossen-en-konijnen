package VK.saver;

import java.io.*;
import javax.swing.*;

import VK.Starter;

/**
 * Write a description of class Choose here.
 * 
 * @author Ole Vrijenhoek
 */
public class Choose
{
    private static JFileChooser fc = null;
    
    public Choose()
    {
        Choose.fc = new JFileChooser();
        Choose.fc.addChoosableFileFilter(new vkfFilter());
        Choose.fc.setAcceptAllFileFilterUsed(false);
    }
   
    public static int[] open() {
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            Values val = new Values();
            val.load(file.getPath(), true);
            return val.getCountArray();
        }
		System.out.println("Bewerking afgebroken");
		return new int[] {0};
    }
    
    @SuppressWarnings("static-method")
	public void save(int v, int k, int b, int h) {
        int returnVal = fc.showSaveDialog(Starter.main);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            new Values(v,k,b,h).save(file.getPath());
        } else {
            System.out.println("Bewerking afgebroken");
        }
    }
}
