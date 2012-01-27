import java.util.*;

/**
 * 
 * @author Hylke de Vries
 * @version 0.0
 */

public class Model implements Runnable {
	private int aantal;
	private List<View> views;
	private boolean run;
	
	public Model() {
		this.views=new ArrayList<View>();
	}
	
	public int getAantal() {
		return this.aantal;
	}
	
	public void setAantal(int newAantal) {
		if (newAantal>=0 && newAantal <=360) {
			this.aantal=newAantal;
			notifyViews();
		}
	}
	
	public void start() {
		new Thread(this).start();
	}
	
	public void stop() {
		this.run=false;
	}
	
	public void addView(View view) {
		this.views.add(view);
	}
	
	private void notifyViews() {
		for(View v: this.views) v.updateView();
	}

	@Override
	public void run() {
		this.run=true;
		while(this.run) {
			setAantal(getAantal()+1);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				System.out.println("Thread error!");
			} 
		}
	}
}