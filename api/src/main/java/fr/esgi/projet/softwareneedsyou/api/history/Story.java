/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api.history;

import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;

/**
 * Niveau d'une histoire
 * 
 * @author Tristan
 * @see PluginHistory
 */
public interface Story {
	/**
	 * Titre du niveau
	 * @return titre
	 */
	String getTitle();
	
	/**
	 * Description courte du niveau
	 * @return courte description
	 */
	Optional<String> getResume();
	
	/**
	 * Contenu du niveau
	 * @return texte explicatif
	 */
	String getContent();
	
	/**
	 * Contenu des tests (dépendant du compilateur)
	 * @return fichier contenant les tests
	 */
	byte[] getFileTest();
	
	/**
	 * Liste des tests du niveau
	 * @return collection de test
	 */
	Set<Test> getTests();
}
