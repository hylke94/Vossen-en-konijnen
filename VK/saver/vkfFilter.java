package VK.saver;

import java.io.File;
import javax.swing.filechooser.*;

/**
 * Write a description of class vkfFilter here.
 * 
 * @author Ole Vrijenhoek
 */
public class vkfFilter extends FileFilter {

    //Accept all directories and all vkf files.
    @Override
	public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = this.getExtension(f);
        if (extension != null) {
            if (extension.equals("vkf")){
                return true;
            }
			return false;
        }
        return false;
    }

    //The description of this filter
    @Override
	public String getDescription() {
        return "Vossen en Konijnen files";
    }
    
    @SuppressWarnings("static-method")
	private String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
 
        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}