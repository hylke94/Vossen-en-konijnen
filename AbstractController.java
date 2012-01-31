package nl.hanze.t12.life.controller;

import javax.swing.*;
import nl.hanze.t12.life.logic.*;

public abstract class AbstractController extends JPanel {
	protected LifeLogic life;
	
	public AbstractController(LifeLogic life) {
		this.life=life;
	}
}