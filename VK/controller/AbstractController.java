package controller;

import javax.swing.*;
import nl.hanze.t12.life.logic.*;

public abstract class AbstractController extends JPanel {

	private static final long serialVersionUID = 1L;
	
	protected LifeLogic life;
	
	public AbstractController(LifeLogic life) {
		this.life=life;
	}
}