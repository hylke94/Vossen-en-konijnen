package VK.main;

import static org.junit.Assert.*;
import org.junit.Test;

import VK.Starter;
import VK.model.Model;

@SuppressWarnings("unused")
public class JUnitTester {
	
	private Model model;

	@Test
	public void test00() {
		String[] test = new String[] {};
		Starter.main(test);
		this.model.runLongSimulation();
	}

	@Test
	public void test50() {
		String[] test = new String[] {"50","50"};
		Starter.main(test);
		this.model.runLongSimulation();
	}

	@Test
	public void test100() {
		String[] test = new String[] {"100","100"};
		Starter.main(test);
		this.model.runLongSimulation();
	}

	@Test
	public void test150() {
		String[] test = new String[] {"150","150"};
		Starter.main(test);
		this.model.runLongSimulation();
	}
}
