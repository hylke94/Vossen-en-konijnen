package VK.sound;

import javax.sound.sampled.*;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineEvent.Type;
/**
 * Class to play sounds.
 * @author Mart de Graaf
 *
 */
public class SoundPlayer {
	
	public SoundPlayer (String fileName, int aantalPlays){
		try {
			int loops = aantalPlays-1;
			playClip(fileName, loops);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void playClip(String file, int loops) throws IOException, 
	UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
		
		File clipFile = new File(file);
		
		class AudioListener implements LineListener {
			
			private boolean done = false;
			
			@Override public synchronized void update(LineEvent event) {
				Type eventType = event.getType();
				if (eventType == Type.STOP || eventType == Type.CLOSE) {
					this.done = true;
					notifyAll();
				}
			}
			public synchronized void waitUntilDone() throws InterruptedException {
				while (!this.done) { wait(); }
			}
		}
		AudioListener listener = new AudioListener();
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(clipFile);
		try {
			Clip clip = AudioSystem.getClip();
			clip.addLineListener(listener);
			clip.open(audioInputStream);
			try {
				clip.loop(loops);
				clip.start();
				listener.waitUntilDone();
			} finally {
				clip.close();
			}
		} finally {
			audioInputStream.close();
		}
	}
}