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
	public void test_PlanetExplorer_landing() {
		String status = pe.executeCommand("");
		
		assertEquals(status, "(0,0,N)");
	}
	
	@Test
	public void test_PlanetExplorer_turn_right() {
		String status = pe.executeCommand("r");
		assertEquals(status, "(0,0,E)");
	}
	
	@Test
	public void test_PlanetExplorer_turn_left() {
		String status = pe.executeCommand("l");
		assertEquals(status, "(0,0,W)");
	}
	
	@Test
	public void test_PlanetExplorer_turn_right_twice() {
		pe.executeCommand("r");
		String status = pe.executeCommand("r");
		assertEquals(status, "(0,0,S)");
	}

}
