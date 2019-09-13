/**
 * 
 */
package com.github.maumay.jflow.impl;

import static java.util.Comparator.naturalOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.test.AbstractCollectionBuilder;
import com.github.maumay.jflow.utils.Tup;
import com.github.maumay.jflow.vec.Vec;

/**
 * @author thomasb
 *
 */
class VecImplTest extends AbstractCollectionBuilder
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
	}

	@Test
	void testPartition()
	{
		Vec<Integer> vector = new VecImpl<>(1, 2, 3, 4, 5, 6);
		assertEquals(Tup.of(vec(1, 2, 3), vec(4, 5, 6)), vector.partition(n -> n < 4));
		assertEquals(Tup.of(Vec.empty(), vec(1, 2, 3, 4, 5, 6)),
				vector.partition(n -> n < 1));
		assertEquals(Tup.of(vec(1, 2, 3, 4, 5, 6), Vec.empty()),
				vector.partition(n -> n < 7));
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
		assertEquals(new VecImpl<>("aa", "bb"),
				new VecImpl<String>("a", "b").map(x -> x + x));
	}

	@Test
	void testFlatMap()
	{
		assertEquals(new VecImpl<>("a", "b"),
				new VecImpl<List<String>>(list("a"), list(), list("b"))
						.flatMap(List::iterator));
	}

	@Test
	void testFilter()
	{
		assertEquals(new VecImpl<Integer>(1),
				new VecImpl<Integer>(1, 2, 3).filter(x -> x < 2));
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
		assertEquals(new VecImpl<>("a", "b"), new VecImpl<>("a", "b").skip(0));
		assertEquals(new VecImpl<>("b"), new VecImpl<>("a", "b").skip(1));
		assertEquals(new VecImpl<>(), new VecImpl<>("a", "b").skip(2));
		assertEquals(new VecImpl<>(), new VecImpl<>("a", "b").skip(3));
		assertThrows(IllegalArgumentException.class, () -> new VecImpl<>("a").skip(-1));
	}

	@Test
	void testSpan()
	{
		Vec<Integer> spanTestCase = new VecImpl<>(1, 2, 3, 4, 1);
		assertEquals(Tup.of(new VecImpl<>(), spanTestCase),
				spanTestCase.span(x -> x < 1));
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
		assertEquals(new VecImpl<>(1, 2, 3),
				new VecImpl<Integer>(1, 3, 2).sorted(naturalOrder()));
	}

	@Test
	void testFindOp()
	{
		assertEquals(Optional.of(3),
				new VecImpl<Integer>(1, 2, 2, 3, 5).findOp(n -> n > 2));
	}

	@Test
	void testMinOp()
	{
		assertEquals(Optional.of(1),
				new VecImpl<Integer>(2, 3, 1).minOp(Comparator.naturalOrder()));
	}

	@Test
	void testMin()
	{
		assertEquals((Integer) 1,
				new VecImpl<Integer>(2, 3, 1).min(Comparator.naturalOrder()));
	}

	@Test
	void testMaxOp()
	{
		assertEquals(Optional.of(3),
				new VecImpl<Integer>(2, 3, 1).maxOp(Comparator.naturalOrder()));
	}

	@Test
	void testMax()
	{
		assertEquals((Integer) 3,
				new VecImpl<Integer>(2, 3, 1).max(Comparator.naturalOrder()));
	}

	@Test
	void testAll()
	{
		assertEquals(Boolean.TRUE, new VecImpl<Integer>(1, 2, 3).all(n -> n < 4));
		assertEquals(Boolean.FALSE, new VecImpl<Integer>(1, 2, 3).all(n -> n < 3));
	}

	@Test
	void testAny()
	{
		assertEquals(Boolean.TRUE, new VecImpl<Integer>(1, 2, 3).any(n -> n > 2));
		assertEquals(Boolean.FALSE, new VecImpl<Integer>(1, 2, 3).any(n -> n > 3));
	}

	@Test
	void testNone()
	{
		assertEquals(Boolean.FALSE, new VecImpl<Integer>(1, 2, 3).none(n -> n > 2));
		assertEquals(Boolean.TRUE, new VecImpl<Integer>(1, 2, 3).none(n -> n > 3));
	}

	@Test
	void testSize()
	{
		Vec<Integer> empty = new VecImpl<>();
		Vec<Integer> populated = new VecImpl<>(1, 2, 3);
		assertEquals(0, empty.size());
		assertEquals(3, populated.size());
		assertTrue(empty.isEmpty());
		assertFalse(empty.isPopulated());
		assertTrue(populated.isPopulated());
		assertFalse(populated.isEmpty());
	}

	@Test
	void testGet()
	{
		Vec<Integer> empty = new VecImpl<>();
		Vec<Integer> populated = new VecImpl<>(1, 2, 3);
		assertEquals(Optional.empty(), empty.headOp());
		assertEquals(Optional.of(1), populated.headOp());
		assertEquals(Optional.empty(), empty.lastOp());
		assertEquals(Optional.of(3), populated.lastOp());
		assertEquals((Integer) 1, populated.head());
		assertEquals((Integer) 3, populated.last());

		assertEquals(Optional.empty(), empty.getOp(2));
		assertEquals(Optional.empty(), populated.getOp(5));
		assertEquals(Optional.of(2), populated.getOp(1));
		assertEquals((Integer) 2, populated.get(1));
	}
}
