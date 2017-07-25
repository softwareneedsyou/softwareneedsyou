/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api.compiler;

import java.util.Map;

/**
 * Résultat d'une compilation
 * 
 * @author Tristan
 */
public interface ResultCompiler {
	/**
	 * Indique si une compilation à réussie
	 * @return true si ok, false sinon
	 */
	boolean isCompileSuccess();

	/**
	 * Retourne le résultat du/des test(s)
	 * @return list of {test's id, test's result}
	 */
	Map<Integer, ResultTest> getTestsResults();
}
