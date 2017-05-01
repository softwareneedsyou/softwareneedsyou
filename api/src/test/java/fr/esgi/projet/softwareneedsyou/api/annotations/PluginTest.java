/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api.annotations;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ServiceLoader;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Blixel
 *
 * Test de la génération du META-INF/service
 */
@Plugin(PluginTestInterf.class)
public class PluginTest implements PluginTestInterf {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	//TODO @Test
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

	//TODO @Test
	public final void testPluginMetaInf() {
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("/META-INF/services/" + PluginTestInterf.class.getName())))) {
			assertTrue( reader.lines().distinct().filter((l) -> l.equalsIgnoreCase(PluginTest.class.getName())).count() > Long.valueOf(0) );
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
		fail();
	}

	@Override
	public void test() {
		; //
	}

}
