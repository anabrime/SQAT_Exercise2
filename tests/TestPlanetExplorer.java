import static org.junit.Assert.*;

import org.junit.Test;

public class TestPlanetExplorer {
	
	private PlanetExplorer pe = new PlanetExplorer(100, 100);

	@Test
	public void test_PlanetExplorer_create_planet() throws PlanetExplorerException {
		PlanetExplorer pe = new PlanetExplorer(100, 100);
		
		String planetSize = pe.getPlanetSize();
		
		assertEquals("100x100", planetSize);
	}
	
	@Test
	public void test_PlanetExplorer_create_planet_size2() throws PlanetExplorerException {
		PlanetExplorer pe = new PlanetExplorer(100, 200);
		
		String planetSize = pe.getPlanetSize();
		
		assertEquals("100x200", planetSize);
	}
	
	@Test
	public void test_executeCommand_landing() {
		String status = pe.executeCommand("");
		
		assertEquals("(0,0,N)", status);
	}
	
	@Test
	public void test_executeCommand_turn_right() {
		String status = pe.executeCommand("r");
		assertEquals("(0,0,E)", status);
	}
	
	@Test
	public void test_executeCommand_turn_left() {
		String status = pe.executeCommand("l");
		assertEquals("(0,0,W)", status);
	}
	
	@Test
	public void test_executeCommand_turn_right_twice() {
		pe.executeCommand("r");
		String status = pe.executeCommand("r");
		assertEquals("(0,0,S)", status);
	}
	
	@Test
	public void test_executeCommand_turn_left_twice() {
		pe.executeCommand("l");
		String status = pe.executeCommand("l");
		assertEquals("(0,0,S)", status);
	}
	
	@Test
	public void test_executeCommand_move_forward() {
		String status = pe.executeCommand("f");
		assertEquals("(0,1,N)", status);
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
		assertEquals("(4,8,E)", status);
	}
	
	@Test
	public void test_executeCommand_moving_and_turning_combined() {
		String status = pe.executeCommand("ffrff");
		assertEquals("(2,2,E)", status);
	}
	
	@Test
	public void test_executeCommand_wrapping() {
		String status = pe.executeCommand("b");
		assertEquals("(0,99,N)", status);
	}
	
	@Test (expected = PlanetExplorerException.class)
	public void test_PlanetExplorer_placing_obstacle_out_of_bounds() throws PlanetExplorerException {
		new PlanetExplorer(5, 5, "(4,5)");
	}
	
	@Test
	public void test_executeCommand_locate_obstacle() throws PlanetExplorerException {
		PlanetExplorer pe = new PlanetExplorer(100, 100, "(2,2)");
		String status = pe.executeCommand("ffrfff");
		assertEquals("(1,2,E)(2,2)", status);
	}
	
	@Test
	public void test_executeCommand_locate_multiple_obstacles() throws PlanetExplorerException {
		PlanetExplorer pe = new PlanetExplorer(100, 100, "(2,2)(2,1)");
		String status = pe.executeCommand("ffrfffrflf");
		assertEquals("(1,1,E)(2,2)(2,1)", status);
	}

}
