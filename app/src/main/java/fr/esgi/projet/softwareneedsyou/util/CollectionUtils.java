/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

import lombok.NonNull;

/**
 * @author Tristan
 *
 */
public final class CollectionUtils {
	private CollectionUtils() {;}

	public static <TI, TO> List<TO> convert(@NonNull final List<TI> input, @NonNull final Function<TI, TO> convert) {
		return input.stream().map(convert).collect(Collectors.toList());
	}

	public static <TI, TO> Set<TO> convert(@NonNull final Set<TI> input, @NonNull final Function<TI, TO> convert) {
		return input.stream().map(convert).collect(Collectors.toSet());
	}

	/*public static <TIK, TIV, TOK, TOV> Map<TOK, TOV> convert(@NonNull final Map<TIK, TIV> input, @NonNull final Function<TI, TO> convert) {
		return input.stream().map(convert).collect(Collectors.toMap(keyMapper, valueMapper));
	}*/

	/*public static <L, C> Collection<C> convert(@NonNull final Collection<L> input, @NonNull final Function<L, C> convert) {
		return input.stream().map(convert).collect(Collectors.toCollection(Collection::new));
	}*/
	

	public static <TI, TO> TO[] convert(@NonNull final TI[] input, @NonNull final Function<TI, TO> convert, @NonNull final IntFunction<TO[]> generator) {
		return Arrays.stream(input).map(convert).toArray(generator);
	}
}
