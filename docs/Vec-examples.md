# Vec API examples
---
Below is a snippet of Java code demonstrating the main functionality provided with the `Vec` interface. The simplest way to think of them is as an immutable array which does not allow null-references and has many useful methods. The last two assertions and the static function at the bottom of the snippet give a taste of the iterator collection mechanism which will be treated in more detail in other documents.

```java
// Some lines omitted..
import static com.github.maumay.jflow.vec.Vec.vec;

import com.github.maumay.jflow.iterators.termination.IteratorCollector;
import com.github.maumay.jflow.utils.Option;
import com.github.maumay.jflow.utils.Tup;
import com.github.maumay.jflow.vec.Vec;

public class VecExamples
{
	public static void main(String[] args)
	{
		// Factory method designed for importing statically
		Vec<Integer> ints = vec(1, 2, 3);
		// or can use ints = Vec.of(1, 2, 3);

		// *****************************************************************************************
		// Easy to make enhanced iterators and streams
		ints.iter();
		ints.stream();

		// *****************************************************************************************
		// Streams still work
		assert ints.stream().map(n -> 2 * n).collect(Vec.collector()).equals(vec(2, 4, 6));
		assert ints.parstream().map(n -> 3 * n).collect(Vec.collector()).equals(vec(3, 6, 9));

		// *****************************************************************************************
		// Map directly
		assert ints.map(n -> 2 * n).equals(vec(2, 4, 6));

		// *****************************************************************************************
		// Filter directly
		assert ints.filter(n -> n > 3).equals(vec());

		// *****************************************************************************************
		// Take, skip directly (note that the stream api uses 'limit' instead of 'take')
		assert ints.take(1).equals(vec(1));
		assert ints.skip(2).equals(vec(3));

		assert ints.takeWhile(n -> n % 2 == 1).equals(vec(1));
		assert ints.skipWhile(n -> n % 2 == 1).equals(vec(2, 3));

		// *****************************************************************************************
		// Predicate match directly
		assert ints.anyMatch(n -> n > 2);
		assert ints.allMatch(n -> n > 0);
		assert ints.noneMatch(n -> n > 3);
		assert ints.find(n -> n > 3).equals(Optional.empty());
		assert ints.find(n -> n > 1).equals(Optional.of(2));
		
		// *****************************************************************************************
		// Min/max

		// Safe versions (returns nothing if the vector is empty)
		assert ints.minOption(Comparator.naturalOrder()).equals(Optional.of(1));
		assert ints.maxOption(Comparator.naturalOrder()).equals(Optional.of(3));

		// Unsafe versions (throws exception if the vector is empty)
		assert ints.min(Comparator.naturalOrder()).equals(1);
		assert ints.max(Comparator.naturalOrder()).equals(3);

		// *****************************************************************************************
		// Safe indexing
		assert ints.headOption().equals(Optional.of(1));
		assert ints.lastOption().equals(Optional.of(3));
		assert ints.getOption(6).equals(Optional.empty());

		// *****************************************************************************************
		// Unsafe indexing
		assert ints.head() == 1;
		assert ints.last() == 3;
		assert ints.get(1) == 2;

		try {
			Integer seventh = ints.get(7);
			assert false;
		} catch (IndexOutOfBoundsException ex) {
		}

		// *****************************************************************************************
		// Easy type manipulation
		Vec<Number> nums = ints.cast(Number.class);

		// *****************************************************************************************
		// Easy to convert to/from other collection types
		List<Integer> intsInList = Arrays.asList(1, 2, 3);

		assert ints.toList().equals(intsInList);
		assert ints.toSet().equals(new HashSet<>(intsInList));
		assert ints.toCollection(ArrayList::new).equals(intsInList);

		assert ints.equals(Vec.copy(intsInList));

		// *****************************************************************************************
		// Easy to convert to maps
		Map<Integer, Integer> expected = new HashMap<>();
		expected.put(1, 2);
		expected.put(2, 4);
		expected.put(3, 6);

		Map<Integer, Integer> map1 = ints.toMap(n -> n, n -> 2 * n);
		Map<Integer, Integer> map2 = ints.associate(n -> 2 * n);

		assert map1.equals(expected) && map2.equals(expected);

		// *****************************************************************************************
		// Split vectors
		Tup<Vec<Integer>, Vec<Integer>> partitioned = ints.partition(n -> n % 2 == 0);

		// note the Scala inspired tuple.
		assert partitioned._1.equals(vec(2));
		assert partitioned._2.equals(vec(1, 3));
		
		// *****************************************************************************************
		// Fold vectors
		assert ints.fold((a, b) -> a | b).equals(0b11);

		// *****************************************************************************************
		// Can transform arbitrarily using iterators
		assert ints.transform(join("[", ", ", "]")).equals("[1, 2, 3]");
		assert ints.transform(join("$$ ", " | ", " $$")).equals("$$ 1 | 2 | 3 $$");

		System.out.println("All assertions passed.");
	}

	/**
	 * Creates a collector which consumes an iterator by joining all the elements
	 * together in a string with the given prefix, join string and postfix.
	 */
	static IteratorCollector<Object, String> join(String prefix, String joiner, String postfix)
	{
		return iter -> {
			StringBuilder builder = new StringBuilder(prefix);
			while (iter.hasNext()) {
				builder.append(iter.next());
				if (iter.hasNext()) {
					builder.append(joiner);
				}
			}
			builder.append(postfix);
			return builder.toString();
		};
	}
}
```