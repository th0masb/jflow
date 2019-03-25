/**
 * 
 */
package com.github.maumay.jflow.examples.convenient;

import static com.github.maumay.jflow.vec.Vec.vec;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.github.maumay.jflow.iterators.RichIterator;
import com.github.maumay.jflow.utils.Option;
import com.github.maumay.jflow.vec.DoubleVec;
import com.github.maumay.jflow.vec.IntVec;
import com.github.maumay.jflow.vec.LongVec;
import com.github.maumay.jflow.vec.Vec;

/**
 * @author thomasb
 *
 */
public class RichIteratorExamples
{

	public static void main(String[] args)
	{
		// This vector is the source of the EnhancedIterator instances used in the
		// following examples.
		Vec<String> strings = vec("a", "b");

		// *****************************************************************************************
		// Create other collections
		List<String> stringsList = asList("a", "b");

		assert strings.iter().toVec().equals(strings);
		assert strings.iter().toList().equals(stringsList);
		assert strings.iter().toSet().equals(new HashSet<>(stringsList));
		assert strings.iter().toCollection(ArrayList::new).equals(stringsList);

		// *****************************************************************************************
		// Create maps
		Map<String, String> expected = new HashMap<>();
		expected.put("a", "aa");
		expected.put("b", "bb");

		assert strings.iter().toMap(x -> x, x -> x + x).equals(expected);
		assert strings.iter().associate(x -> x + x).equals(expected);

		// *****************************************************************************************
		// Map
		assert strings.iter().map(s -> s.length()).toVec().equals(vec(1, 1));
		assert strings.iter().mapToInt(s -> s.length()).toVec().equals(IntVec.of(1, 1));
		assert strings.iter().mapToDouble(s -> s.length()).toVec()
				.equals(DoubleVec.of(1, 1));
		assert strings.iter().mapToLong(s -> s.length()).toVec().equals(LongVec.of(1, 1));

		// *****************************************************************************************
		// Filter
		assert strings.iter().filter(s -> s.equals("a")).toVec().equals(vec("a"));

		// *****************************************************************************************
		// Take, skip
		assert strings.iter().take(1).toVec().equals(vec("a"));
		assert strings.iter().drop(1).toVec().equals(vec("b"));

		assert strings.iter().takeWhile(s -> s.equals("a")).toVec().equals(vec("a"));
		assert strings.iter().dropWhile(s -> s.equals("a")).toVec().equals(vec("b"));

		// *****************************************************************************************
		// Predicate matching (these are terminal methods triggering the consumption of
		// the iterator).
		assert strings.iter().allMatch(s -> !s.equals("c"));
		assert strings.iter().anyMatch(s -> s.equals("b"));
		assert strings.iter().noneMatch(s -> s.equals("0"));

		// *****************************************************************************************
		// Fine grained control over consuming an iterator
		RichIterator<String> iter = strings.iter();
		assert iter.next().equals("a");
		assert iter.nextOp().equals(Option.of("b"));
		assert !iter.hasNext();
		assert iter.nextOp().equals(Option.empty());
		try {
			System.out.println(iter.next());
		} catch (NoSuchElementException ex) {
		}

		// ...
		// with great power comes great responsibility

		// *****************************************************************************************
		// Append / insert
		assert strings.iter().append(strings.revIter()).toVec()
				.equals(vec("a", "b", "b", "a"));
		assert strings.iter().insert(strings.revIter()).toVec()
				.equals(vec("b", "a", "a", "b"));

		// *****************************************************************************************
		// Zipping
		assert strings.iter().zip(strings.revIter()).map(pair -> pair._1 + pair._2)
				.toVec().equals(vec("ab", "ba"));

		System.out.println("All assertions passed");
	}

}
