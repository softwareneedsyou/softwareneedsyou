/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api.annotations;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ServiceLoader;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kohsuke.MetaInfServices;

/**
 * @author Tristan
 *
 * Test de la génération du META-INF/service
 */
@MetaInfServices
public class PluginTest implements PluginTestInterf {

	/**
	 * 
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
	}

	/**
	 * 
	 */
	@AfterClass
	public static void tearDownAfterClass() {
	}

	/**
	 * 
	 */
	@Before
	public void setUp() {
	}

	/**
	 * 
	 */
	@After
	public void tearDown() {
	}

	/**
	 * Test le bon fonctionnement du SPI
	 */
	@Test
	public final void testPluginSPI() {
		ServiceLoader<PluginTestInterf> loader = ServiceLoader.load(PluginTestInterf.class);
		boolean found = false;
		for(PluginTestInterf pti : loader) {
			found |= pti.getClass().getName().equalsIgnoreCase(this.getClass().getName());
			if(found)
				break; //pas la peine de continuer, on a trouver ce que l'on veut
		}
		assertTrue(found);
	}

	/**
	 * Test le bon fonctionnement de l'annotation pour le META-INF/services
	 */
	@Test
	public final void testPluginMetaInf() {
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("META-INF/services/" + PluginTestInterf.class.getName())))) {
			assertTrue( reader.lines().distinct().map(s -> s.trim()).filter((l) -> l.equalsIgnoreCase(PluginTest.class.getName())).count() > Long.valueOf(0) );
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Override
	public void test() {
		; //
	}

}
