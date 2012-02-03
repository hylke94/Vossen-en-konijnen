package VK.main;

import static org.junit.Assert.*;
import org.junit.Test;

import VK.Starter;
import VK.simulator.Simulator;

@SuppressWarnings("unused")
public class JUnitTester {

	@SuppressWarnings("static-method")
	@Test
	public void test00() {
		String[] test = new String[] {};
		Starter.main(test);
		Simulator.runLongSimulation();
	}

	@SuppressWarnings("static-method")
	@Test
	public void test50() {
		String[] test = new String[] {"50","50"};
		Starter.main(test);
		Simulator.runLongSimulation();
	}

	@SuppressWarnings("static-method")
	@Test
	public void test100() {
		String[] test = new String[] {"100","100"};
		Starter.main(test);
		Simulator.runLongSimulation();
	}

	@SuppressWarnings("static-method")
	@Test
	public void test150() {
		String[] test = new String[] {"150","150"};
		Starter.main(test);
		Simulator.runLongSimulation();
	}
}
