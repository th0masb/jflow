package com.github.maumay.jflow.examples.convenient;

import static com.github.maumay.jflow.vec.Vec.vec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.github.maumay.jflow.iterators.termination.IteratorCollector;
import com.github.maumay.jflow.utils.Option;
import com.github.maumay.jflow.vec.Vec;

/**
 * 
 * @author thomasb
 *
 */
public class VecIsUseful
{
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Vec<Integer> ints = vec(1, 2, 3);

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
		// Predicate match
		assert ints.anyMatch(n -> n > 2);
		assert ints.allMatch(n -> n > 0);
		assert ints.noneMatch(n -> n > 3);

		// *****************************************************************************************
		// Safe indexing
		assert ints.headOption().equals(Option.of((Integer) 1));
		assert ints.lastOption().equals(Option.of((Integer) 3));
		assert !ints.getOption(6).isPresent();

		// *****************************************************************************************
		// unsafe indexing
		assert ints.head() == 1;
		assert ints.last() == 3;
		assert ints.get(1) == 2;

		// *****************************************************************************************
		// Easy type manipulation
		Vec<Number> nums = ints.cast(Number.class);

		// *****************************************************************************************
		// Easy to convert to/from other collection types
		assert ints.toList().equals(Arrays.asList(1, 2, 3));
		assert ints.toSet().equals(new HashSet<>(Arrays.asList(1, 2, 3)));
		assert ints.toCollection(ArrayList::new).equals(Arrays.asList(1, 2, 3));

		assert ints.equals(Vec.copy(Arrays.asList(1, 2, 3)));

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
		// Easy to transform arbitrarily using iterators
		assert "[1, 2, 3]".equals(ints.transform(join("[", ", ", "]")));
		assert "$$ 1 | 2 | 3 $$".equals(ints.transform(join("$$ ", " | ", " $$")));

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
