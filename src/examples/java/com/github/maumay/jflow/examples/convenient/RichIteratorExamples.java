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
		// This vector is the source of the RichIterator instances used in the
		// following examples.
		Vec<String> strings = vec("a", "b");

		// *****************************************************************************************
		// Create other collections
		List<String> stringsList = asList("a", "b");

		assert strings.iter().toVec() == strings;
		assert strings.iter().toList() == stringsList;
		assert strings.iter().toSet() == new HashSet<>(stringsList);
		assert strings.iter().toCollection(ArrayList::new) == stringsList;

		// *****************************************************************************************
		// Create maps
		Map<String, String> expected = new HashMap<>();
		expected.put("a", "aa");
		expected.put("b", "bb");

		assert strings.iter().toMap(x -> x, x -> x + x) == expected;
		assert strings.iter().associate(x -> x + x) == expected;

		// *****************************************************************************************
		// Map
		assert strings.iter().map(s -> s.length()).toVec() == vec(1, 1);
		assert strings.iter().mapToInt(s -> s.length()).toVec() == IntVec.of(1, 1);
		assert strings.iter().mapToDouble(s -> s.length()).toVec() == DoubleVec.of(1, 1);
		assert strings.iter().mapToLong(s -> s.length()).toVec() == LongVec.of(1, 1);

		// *****************************************************************************************
		// Filter
		assert strings.iter().filter(s -> s.equals("a")).toVec() == vec("a");

		// *****************************************************************************************
		// Take, skip
		assert strings.iter().take(1).toVec() == vec("a");
		assert strings.iter().drop(1).toVec() == vec("b");

		assert strings.iter().takeWhile(s -> s.equals("a")).toVec() == vec("a");
		assert strings.iter().dropWhile(s -> s.equals("a")).toVec() == vec("b");

		// *****************************************************************************************
		// Predicate matching (these are terminal methods triggering the consumption of
		// the iterator).
		assert strings.iter().allMatch(s -> !s.equals("c"));
		assert strings.iter().anyMatch(s -> s.equals("b"));
		assert strings.iter().noneMatch(s -> s.equals("0"));

		// *****************************************************************************************
		// Fine grained control over consuming an iterator
		RichIterator<String> iter = strings.iter();
		assert iter.next() == "a";
		assert iter.nextOp() == Option.of("b");
		assert !iter.hasNext();
		assert !iter.nextOp().isPresent();
		try {
			System.out.println(iter.next());
		} catch (NoSuchElementException ex) {
		}

		// ...
		// with great power comes great responsibility

		// *****************************************************************************************
		// Append / insert
		assert strings.iter().append(strings.iterRev()).toVec() == vec("a", "b", "b", "a");
		assert strings.iter().insert(strings.iterRev()).toVec() == vec("b", "a", "a", "b");

		// *****************************************************************************************
		// Zipping
		assert strings.iter().zip(strings.iterRev()).map(pair -> pair._1 + pair._2)
				.toVec() == vec("ab", "ba");
	}

}
