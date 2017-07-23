/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api.history;

import java.util.Optional;

/**
 * Description d'un test
 * 
 * @author Tristan
 */
public interface Test {
	/**
	 * ID du test.
	 * Permet de la distinguer dans les résultats des tests
	 * @return id
	 */
	short getId();
	
	/**
	 * Nom (quelconque)
	 * @return nom du test
	 */
	String getName();
	
	/**
	 * Courte description du test
	 * @return courte description (info-bulle)
	 */
	Optional<String> getResume();
}
