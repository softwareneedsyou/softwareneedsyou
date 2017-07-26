/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api.compiler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Résultats d'un test
 * @author Tristan
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultTest {
	private TestState state;
	private String detail;
}
