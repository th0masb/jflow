package com.github.maumay.jflow.examples.vec;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.maumay.jflow.iterators.termination.IteratorCollector;
import com.github.maumay.jflow.vec.Vec;

/**
 * 
 * @author thomasb
 *
 */
public class FunToWorkWith
{
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Vec<Integer> ints = Vec.of(1, 2, 3, 4);

		// Easy to make enhanced iterators and streams
		ints.iter();
		ints.stream();

		// Streams still work
		Vec<Integer> doubled = ints.stream().map(n -> 2 * n).collect(Vec.collector());
		Vec<Integer> tripled = ints.parstream().map(n -> 3 * n).collect(Vec.collector());

		// Can map directly
		Vec<Integer> doubled2 = ints.map(n -> 2 * n);
		Vec<Integer> tripled2 = ints.map(n -> 3 * n);

		// Filter
		Vec<Integer> filtered = ints.filter(n -> n > 3);

		// Predicate match
		ints.anyMatch(n -> n > 3);
		ints.allMatch(n -> n > 3);
		ints.noneMatch(n -> n > 3);

		// Safe indexing
		ints.headOption().ifPresent(System.out::println);
		ints.lastOption().ifPresent(System.out::println);
		Integer x = ints.getOption(6).orElse(0);

		// unsafe indexing
		ints.head();
		ints.last();
		ints.get(3);

		// Easy type manipulation
		Vec<Number> nums = ints.cast(Number.class);

		// Easy to convert to other collections
		List<Integer> listInts = ints.toList();
		Set<Integer> setInts = ints.toSet();
		ArrayDeque<Integer> dequeInts = ints.toCollection(ArrayDeque::new);

		// Easy to convert to maps
		Map<Integer, Integer> map1 = ints.toMap(n -> n, n -> 2 * n);
		Map<Integer, Integer> map2 = ints.associate(n -> 2 * n);
		assert map1.equals(map2);

		// Easy to transform arbitrarily using iterators
		String joined = ints.transform(join("[", ", ", "]"));
		assert "[1, 2, 3, 4]".equals(joined);
	}

	static IteratorCollector<Integer, String> join(String prefix, String joiner, String postfix)
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
