package com.github.maumay.jflow.examples.convenient;

import static com.github.maumay.jflow.vec.Vec.vec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.github.maumay.jflow.utils.Tup;
import com.github.maumay.jflow.vec.Vec;

/**
 * 
 * @author thomasb
 *
 */
public class VecExamples
{
	@SuppressWarnings("unused")
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
		// Can map directly
		assert ints.map(n -> 2 * n).equals(vec(2, 4, 6));
		assert ints.map(n -> 3 * n).equals(vec(3, 6, 9));

		// *****************************************************************************************
		// Filter
		assert ints.filter(n -> n > 3).equals(vec());

		// *****************************************************************************************
		// Take, skip
		assert ints.take(1).equals(vec(1));
		assert ints.drop(2).equals(vec(3));

		assert ints.takeWhile(n -> n % 2 == 1).equals(vec(1));
		assert ints.dropWhile(n -> n % 2 == 1).equals(vec(2, 3));

		// *****************************************************************************************
		// Predicate matching
		assert ints.anyMatch(n -> n > 2);
		assert ints.allMatch(n -> n > 0);
		assert ints.noneMatch(n -> n > 3);
		assert ints.find(n -> n > 3).equals(Optional.empty());

		// *****************************************************************************************
		// Min/max

		// Safe versions (returns nothing if vector is empty)
		assert ints.minOption(Comparator.naturalOrder()).equals(Optional.of(1));
		assert ints.maxOption(Comparator.naturalOrder()).equals(Optional.of(3));

		// Unsafe versions (throws exception if the vector is empty)
		assert ints.min(Comparator.naturalOrder()).equals(1);
		assert ints.max(Comparator.naturalOrder()).equals(3);

		// *****************************************************************************************
		// Safe indexing
		assert ints.headOption().get().equals(1);
		assert ints.lastOption().get().equals(3);
		assert !ints.getOption(6).isPresent();

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
		// Vec splitting
		Tup<Vec<Integer>, Vec<Integer>> partitioned = ints.partition(n -> n % 2 == 0);

		// note the Scala inspired tuple.
		assert partitioned._1.equals(vec(2));
		assert partitioned._2.equals(vec(1, 3));

		Tup<Integer, Vec<Integer>> headTail = Tup.of(ints.head(), ints.drop(1));

		assert headTail._1.equals(1);
		assert headTail._2.equals(vec(2, 3));

		// *****************************************************************************************
		// Fold vectors
		assert ints.fold((a, b) -> a | b).equals(0b11);

		System.out.println("All assertions passed.");
	}

	// /**
	// * Creates a collector which consumes an iterator by joining all the elements
	// * together in a string with the given prefix, join string and postfix.
	// */
	// static IteratorCollector<Object, String> join(String prefix, String joiner,
	// String postfix)
	// {
	// return iter -> {
	// StringBuilder builder = new StringBuilder(prefix);
	// while (iter.hasNext()) {
	// builder.append(iter.next());
	// if (iter.hasNext()) {
	// builder.append(joiner);
	// }
	// }
	// builder.append(postfix);
	// return builder.toString();
	// };
	// }
}
