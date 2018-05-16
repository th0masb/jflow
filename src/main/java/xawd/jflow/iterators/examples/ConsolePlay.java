/**
 *
 */
package xawd.jflow.iterators.examples;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;

import java.util.HashSet;
import java.util.List;

import xawd.jflow.iterators.construction.IterRange;
import xawd.jflow.iterators.construction.Iterate;
import xawd.jflow.iterators.construction.Numbers;
import xawd.jflow.iterators.misc.Pair;

/**
 * @author ThomasB
 *
 */
public final class ConsolePlay {

	@SuppressWarnings("unused")
	public static void main(final String[] args)
	{
		/* Mapping */

//		final Flow<String> strings = Iterate.over("a", "b", "c");
//		strings.map(s -> s + s); //---> aa, bb cc

		/* Skip/takeWhile  */

		Numbers.natural().takeWhile(n -> n < 5); // --> 0, 1, 2, 3, 4

		Numbers.fibonacci().skipWhile(n -> n < 10).take(5); // --> 13, 21, 34, 55, 89

		Iterate.over("1", "2", "3").takeWhile(s -> parseInt(s) < 3); // --> "1", "2"

		/* Zipping /combining */

		final List<String> strings = asList("a", "b", "c"), otherStrings = asList("d", "e");

		Iterate.over(strings).enumerate(); // --> (0, "a"), (1, "b"), (2, "c")

		Iterate.over(strings).zipWith(otherStrings); // --> ("a", "d"), ("b", "e")

		Iterate.over(strings).combineWith(otherStrings, (s1, s2) -> s1 + s2); // --> "ad", "be"

		/* Min/Max by key */

		Iterate.over("1", "-1", "3").minByKey(Integer::parseInt); // --> "-1"

		final List<Pair<Integer, Integer>> intPairs = asList(Pair.of(0, 5), Pair.of(5, 0));

		Iterate.over(intPairs).maxByKey(Pair::first); // --> Pair.of(5, 0)
		Iterate.over(intPairs).maxByKey(Pair::second); // --> Pair.of(0, 5)

		/* Flattening */

		final List<String> first = asList("a", "b"), second = asList("c"), third = asList("d", "e");

		Iterate.over(first, second, third).flatten(list -> Iterate.over(list)); // --> a, b, c, d, e

		Iterate.over(first, second, third).flatten(Iterate::over); // is equivalent

		/* Slicing */

		IterRange.to(10); // --> 0, 1, 2, 3, 4, 5, 6, 7, 8, 9

		IterRange.to(10).slice(i -> 2*i + 1); // --> 1, 3, 5, 7, 9

		IterRange.to(10).slice(i -> 3*i); // --> 0, 3, 6, 9

		Iterate.over("0", "1", "2").slice(i -> 2*i); // --> "0", "2"

		/* Predicate matching */

		IterRange.between(10, 15); // --> 10, 11, 12, 13, 14

		IterRange.between(10, 15).areAllEqual(); // --> false

		IterRange.between(10, 15).allMatch(n -> n > 9); // --> true

		IterRange.between(10, 15).anyMatch(n -> n > 14); // --> false

		IterRange.between(10, 15).partition(n -> n % 2 == 0); // --> {[10, 12, 14], [11, 13]}

		/* Collecting */

		Iterate.over("a", "b", "c").toList(); // --> ["a", "b", "c"]

		Iterate.over("a", "b", "c").toSet(); // --> {"a", "b", "c"}

		Iterate.over("a", "b", "c").toCollection(HashSet::new); // --> HashSet{"a", "b", "c"}

		Iterate.over("a", "b").toMap(s -> s + s, s -> s + s + s); // --> {"aa": "aaa", "bb": "bbb"}

		Iterate.over("0", "1", "2").groupBy(s -> parseInt(s) % 2); // --> {0: ["0", "2"], 1: ["1]}

	}
}
