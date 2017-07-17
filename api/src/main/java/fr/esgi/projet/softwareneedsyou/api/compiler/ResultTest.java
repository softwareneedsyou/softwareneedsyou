/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api.compiler;

import lombok.Data;

/**
 * Résultats d'un test
 * @author Tristan
 */
@Data
public class ResultTest {
	private TestState state;
	private String detail;
}
