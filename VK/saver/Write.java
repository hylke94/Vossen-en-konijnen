package VK.saver;

import java.io.FileOutputStream;

/**
 * Write a description of class Write here.
 * 
 * @author Ole Vrijenhoek
 */

public class Write
{
    private final byte[] VKF = new byte[] {86, 75, 70};
    private boolean identifier = false;
    @SuppressWarnings("unused")
	private String path;
    private FileOutputStream fos = null;

    public Write(String pathInput)
    {
        this.path = pathInput;
        
        try {
            this.fos = new FileOutputStream(pathInput);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    public void writeToFile(byte[][] data) throws Exception
    {
        if(!this.identifier) {
            this.fos.write(this.VKF);
            this.identifier = true;
        }
        for(int i=0;i<data.length;i++) {
            for(int j=0;j<data[i].length;j++) {
                this.fos.write(data[i][j]);
            }
        }
    }
}
