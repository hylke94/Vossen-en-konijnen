package VK.saver;

import java.io.FileInputStream;
import java.io.File;

/**
 * Write a description of class Read here.
 * 
 * @author Ole Vrijenhoek
 */

public class Read
{
    private FileInputStream fis;
    private byte[] data;
    
    public Read(String path) throws Exception
    {
        File vkBestand = new File(path);
        if(!vkBestand.exists()) {
            throw new Exception("Opgegeven bestandsnaam bestaat niet.");
        }
        
        try {
            this.fis = new FileInputStream(path);
        } catch(Exception f) {
            System.out.println(f.getMessage());
        }
        
        this.data = new byte[(int) vkBestand.length()];
    }
    
    public void readFromFile() throws Exception
    {
        this.fis.read(this.data);
    }
    
    public byte[] getData()
    {
        return this.data;
    }
}
