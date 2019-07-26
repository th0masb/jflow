/**
 * 
 */
package com.github.maumay.jflow.impl;

import static com.github.maumay.jflow.vec.Vec.vec;
import static java.util.Comparator.naturalOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.test.AbstractListBuilder;
import com.github.maumay.jflow.utils.Tup;
import com.github.maumay.jflow.vec.Vec;

/**
 * @author thomasb
 *
 */
class VecImplTest extends AbstractListBuilder
{
	@Test
	void testEquals()
	{
		assertEquals(new VecImpl<>(), new VecImpl<>());
		assertEquals(new VecImpl<>().hashCode(), new VecImpl<>().hashCode());

		assertEquals(new VecImpl<>("a", "b", "c"), new VecImpl<>("a", "b", "c"));
		assertEquals(new VecImpl<>("a", "b", "c").hashCode(),
				new VecImpl<>("a", "b", "c").hashCode());

		assertNotEquals(new VecImpl<>("a"), new VecImpl<>("a", "b"));
		assertNotEquals(new VecImpl<>("a"), new Object());
	}

	@Test
	void testToString()
	{
		assertEquals("Vec[a, b, c]", new VecImpl<>("a", "b", "c").toString());
	}

	@Test
	void testStreamCollection()
	{
		Vec<String> empty = new VecImpl<>();
		assertEquals(empty, empty.stream().collect(Vec.collector()));

		Vec<String> vector = new VecImpl<>("a", "b", "c");
		assertEquals(vector, vector.stream().collect(Vec.collector()));

		// Vec<Long> numbers = Iter.until(1000).mapToLong(x -> x).boxed().toVec();
		// assertEquals((Long) 499500L, numbers.parstream().reduce((a, b) -> a +
		// b).get());

	}

	@Test
	void testPartition()
	{
		Vec<Integer> vector = new VecImpl<>(1, 2, 3, 4, 5, 6);
		assertEquals(Tup.of(vec(1, 2, 3), vec(4, 5, 6)), vector.partition(n -> n < 4));
		assertEquals(Tup.of(Vec.empty(), vec(1, 2, 3, 4, 5, 6)), vector.partition(n -> n < 1));
		assertEquals(Tup.of(vec(1, 2, 3, 4, 5, 6), Vec.empty()), vector.partition(n -> n < 7));
	}

	@Test
	void testReverseIteration()
	{
		assertEquals(new VecImpl<>("a", "b"), new VecImpl<>("b", "a").iterRev().toVec());
	}

	@Test
	void testMap()
	{
		assertEquals(new VecImpl<>(), new VecImpl<>().map(x -> x.toString()));
		assertEquals(new VecImpl<>("aa", "bb"), new VecImpl<String>("a", "b").map(x -> x + x));
	}

	@Test
	void testFlatMap()
	{
		assertEquals(new VecImpl<>("a", "b"),
				new VecImpl<List<String>>(list("a"), list(), list("b")).flatMap(List::iterator));
	}

	@Test
	void testFilter()
	{
		assertEquals(new VecImpl<Integer>(1), new VecImpl<Integer>(1, 2, 3).filter(x -> x < 2));
	}

	@Test
	void testAppend()
	{
		assertEquals(new VecImpl<>("a", "b"), new VecImpl<>("a").append("b"));
		assertEquals(new VecImpl<>("a", "b"), new VecImpl<>("a").append(list("b")));
		assertEquals(new VecImpl<>("a", "b"),
				new VecImpl<>("a").append((Iterable<String>) list("b")));
	}

	@Test
	void testInsert()
	{
		assertEquals(new VecImpl<>("b", "a"), new VecImpl<>("a").insert("b"));
		assertEquals(new VecImpl<>("b", "a"), new VecImpl<>("a").insert(list("b")));
		assertEquals(new VecImpl<>("b", "a"),
				new VecImpl<>("a").insert((Iterable<String>) list("b")));
	}

	@Test
	void testTake()
	{
		assertEquals(new VecImpl<>("a", "b"), new VecImpl<>("a", "b").take(3));
		assertEquals(new VecImpl<>("a", "b"), new VecImpl<>("a", "b").take(2));
		assertEquals(new VecImpl<>("a"), new VecImpl<>("a", "b").take(1));
		assertEquals(new VecImpl<>(), new VecImpl<>("a", "b").take(0));
		assertThrows(IllegalArgumentException.class, () -> new VecImpl<>("a").take(-1));
	}

	@Test
	void testDrop()
	{
		assertEquals(new VecImpl<>("a", "b"), new VecImpl<>("a", "b").drop(0));
		assertEquals(new VecImpl<>("b"), new VecImpl<>("a", "b").drop(1));
		assertEquals(new VecImpl<>(), new VecImpl<>("a", "b").drop(2));
		assertEquals(new VecImpl<>(), new VecImpl<>("a", "b").drop(3));
		assertThrows(IllegalArgumentException.class, () -> new VecImpl<>("a").drop(-1));
	}

	@Test
	void testSpan()
	{
		Vec<Integer> spanTestCase = new VecImpl<>(1, 2, 3, 4, 1);
		assertEquals(Tup.of(new VecImpl<>(), spanTestCase), spanTestCase.span(x -> x < 1));
		assertEquals(Tup.of(new VecImpl<>(1, 2, 3), new VecImpl<>(4, 1)),
				spanTestCase.span(x -> x < 4));
	}

	@Test
	void testToSet()
	{
		assertEquals(new HashSet<>(list("a", "b")), new VecImpl<>("a", "a", "b").toSet());
	}

	@Test
	void testToList()
	{
		assertEquals(list("a", "a", "b"), new VecImpl<>("a", "a", "b").toList());
	}

	@Test
	void testSorted()
	{
		assertEquals(new VecImpl<>(1, 2, 3), new VecImpl<Integer>(1, 3, 2).sorted(naturalOrder()));
	}
}
