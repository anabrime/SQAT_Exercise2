import static org.junit.Assert.*;

import org.junit.Test;

public class TestPlanetExplorer {
	
	private PlanetExplorer pe = new PlanetExplorer(100, 100);

	@Test
	public void test_PlanetExplorer_create_planet() {
		PlanetExplorer pe = new PlanetExplorer(100, 100);
		
		String planetSize = pe.getPlanetSize();
		
		assertEquals(planetSize, "100x100");
	}
	
	@Test
	public void test_PlanetExplorer_create_planet_size2() {
		PlanetExplorer pe = new PlanetExplorer(100, 200);
		
		String planetSize = pe.getPlanetSize();
		
		assertEquals(planetSize, "100x200");
	}
	
	@Test
	public void test_executeCommand_landing() {
		String status = pe.executeCommand("");
		
		assertEquals(status, "(0,0,N)");
	}
	
	@Test
	public void test_executeCommand_turn_right() {
		String status = pe.executeCommand("r");
		assertEquals(status, "(0,0,E)");
	}
	
	@Test
	public void test_executeCommand_turn_left() {
		String status = pe.executeCommand("l");
		assertEquals(status, "(0,0,W)");
	}
	
	@Test
	public void test_executeCommand_turn_right_twice() {
		pe.executeCommand("r");
		String status = pe.executeCommand("r");
		assertEquals(status, "(0,0,S)");
	}
	
	@Test
	public void test_executeCommand_turn_left_twice() {
		pe.executeCommand("l");
		String status = pe.executeCommand("l");
		assertEquals(status, "(0,0,S)");
	}
	
	@Test
	public void test_executeCommand_move_forward() {
		String status = pe.executeCommand("f");
		assertEquals(status, "(0,1,N)");
	}
	
	@Test
	public void test_executeCommand_move_backward() {
		// Move to (0,8)
		for (int i = 0; i < 8; i++) {
			pe.executeCommand("f");
		}
		
		// Move to (5,8)
		pe.executeCommand("r");
		for (int i = 0; i < 5; i++) {
			pe.executeCommand("f");
		}
		
		// Move backwards
		String status = pe.executeCommand("b");
		assertEquals(status, "(4,8,E)");
	}
	
	@Test
	public void test_executeCommand_moving_and_turning_combined() {
		String status = pe.executeCommand("ffrff");
		assertEquals("(2,2,E)", status);
	}

}
