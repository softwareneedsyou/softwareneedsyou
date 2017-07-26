/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api.compiler;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * Résultat d'une compilation
 * 
 * @author Tristan
 */
@EqualsAndHashCode
@ToString
@Getter
@Setter(AccessLevel.PROTECTED)
public class ResultCompiler {
	/**
	 * @param compileSuccess
	 */
	public ResultCompiler(final boolean compileSuccess) {
		this(false, Collections.emptyMap());
	}

	/**
	 * @param compileSuccess
	 * @param testsResults
	 */
	public ResultCompiler(final boolean compileSuccess, @NonNull final Map<Integer, ResultTest> testsResults) {
		super();
		this.compileSuccess = compileSuccess;
		this.testsResults = testsResults;
	}

	/**
	 * @param compileSuccess
	 * @param testsResults
	 */
	public ResultCompiler(final boolean compileSuccess, @NonNull final ResultTest... testsResults) {
		this(compileSuccess,  Arrays.asList(testsResults));
	}

	/**
	 * @param compileSuccess
	 * @param testsResults
	 */
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private int count;
	public ResultCompiler(final boolean compileSuccess, @NonNull final Collection<ResultTest> testsResults) {
		super();
		this.compileSuccess = compileSuccess;
		count = 0;
		this.testsResults = testsResults.stream().collect(Collectors.toMap(t -> Integer.valueOf(count++), t -> t));
	}

	/**
	 * Indique si une compilation à réussie
	 * @return true si ok, false sinon
	 */
	private final boolean compileSuccess;

	/**
	 * Retourne le résultat du/des test(s)
	 * @return list of {test's id, test's result}
	 */
	@NonNull private final Map<Integer, ResultTest> testsResults;

	/**
	 * Retourne le résultat du/des test(s)
	 * @return list of {test's id, test's result}
	 */
	public Map<Integer, ResultTest> getTestsResults() {
		return Collections.unmodifiableMap(this.testsResults);
	}
	
	public void addResultTest(final int id, @NonNull final ResultTest rt) {
		this.testsResults.put(id, rt);
	}
}
