package VK;

import static org.junit.Assert.*;
import org.junit.Test;

@SuppressWarnings("unused")
public class JUnitTester {

	@SuppressWarnings("static-method")
	@Test
	public void test00() {
		String[] test = new String[] {};
		Starter.main(test);
	}

	@SuppressWarnings("static-method")
	@Test
	public void test50() {
		String[] test = new String[] {"50","50"};
		Starter.main(test);
	}

	@SuppressWarnings("static-method")
	@Test
	public void test100() {
		String[] test = new String[] {"100","100"};
		Starter.main(test);
	}

	@SuppressWarnings("static-method")
	@Test
	public void test150() {
		String[] test = new String[] {"150","150"};
		Starter.main(test);
	}
}
