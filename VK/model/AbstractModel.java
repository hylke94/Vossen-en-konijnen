package model;

import nl.hanze.t12.life.view.*;
import java.util.*;

import View.AbstractView;

public abstract class AbstractModel {
	private List<AbstractView> views;
	
	public AbstractModel() {
		views=new ArrayList<AbstractView>();
	}
	
	public void addView(AbstractView view) {
		views.add(view);
	}
	
	public void notifyViews() {
		for(AbstractView v: views) v.updateView();
	}
}