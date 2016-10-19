import static org.junit.Assert.*;

import org.junit.Test;

public class TestPlanetExplorer {

	@Test
	public void test_PlanetExplorer_create_planet() {
		PlanetExplorer pe = new PlanetExplorer(100, 100);
		
		String planetSize = pe.getPlanetSize();
		
		assertEquals(planetSize, "100x100");
	}
}
