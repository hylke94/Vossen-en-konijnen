package VK.model;

import java.util.*;

import VK.main.RunException;
import VK.view.AbstractView;

public abstract class AbstractModel {
	
	private List<AbstractView> views;
	
	public AbstractModel() {
		this.views=new ArrayList<AbstractView>();
	}
	
	public void addView(AbstractView view) {
		this.views.add(view);
	}
	
	protected void notifyViews() {
		for(AbstractView v: this.views) v.updateView();
	}
}