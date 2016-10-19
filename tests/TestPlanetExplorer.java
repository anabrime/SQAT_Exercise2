import static org.junit.Assert.*;

import org.junit.Test;

public class TestPlanetExplorer {

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
		PlanetExplorer pe = new PlanetExplorer(100, 100);
		
		String status = pe.executeCommand("");
		
		assertEquals(status, "(0,0,N)");
	}

}
