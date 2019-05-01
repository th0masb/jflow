package com.github.maumay.jflow.api;

import static com.github.maumay.jflow.vec.Vec.vec;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.Iter;
import com.github.maumay.jflow.utils.Tup;
import com.github.maumay.jflow.vec.DoubleVec;
import com.github.maumay.jflow.vec.IntVec;
import com.github.maumay.jflow.vec.LongVec;
import com.github.maumay.jflow.vec.Vec;

/**
 * Some simple tests on the {@link Iter} api.
 * 
 * @author thomasb
 */
class IterTest
{
	@Test
	void testEmpty()
	{
		Vec<Object> expected = Vec.empty(), actual = Iter.empty().toVec();
		assertEquals(expected, actual);
	}

	@Test
	void testOverCollection()
	{
		assertEquals(vec("a", "b"), Iter.over(Arrays.asList("a", "b")).toVec());
	}

	@Test
	void testOverArray()
	{
		assertEquals(vec("a", "b"), Iter.over("a", "b").toVec());
	}

	@Test
	void testOverEnum()
	{
		assertEquals(vec(Locale.Category.DISPLAY, Locale.Category.FORMAT),
				Iter.over(Locale.Category.class).toVec());
	}

	@Test
	void testReverse()
	{
		assertEquals(vec("b", "a"), Iter.reverse("a", "b").toVec());
	}

	@Test
	void testOption()
	{
		Optional<String> emptyOp = Optional.empty();
		assertEquals(Vec.empty(), Iter.option(emptyOp).toVec());
		assertEquals(vec("a"), Iter.option(Optional.of("a")).toVec());
	}

	@Test
	void testValues()
	{
		Map<String, String> map = Iter.over("a").associate(x -> x + x);
		assertEquals(vec("aa"), Iter.values(map).toVec());
	}

	@Test
	void testKeys()
	{
		Map<String, String> map = Iter.over("a").associate(x -> x + x);
		assertEquals(vec("a"), Iter.keys(map).toVec());
	}

	@Test
	void testEntries()
	{
		Map<String, String> map = Iter.over("a").associate(x -> x + x);
		assertEquals(vec(Tup.of("a", "aa")), Iter.entries(map).toVec());
	}

	@Test
	void testFlatten()
	{
		assertEquals(vec("a", "b", "c"),
				Iter.flatten(vec(vec(), vec("a"), vec(), vec("b", "c"))).toVec());
	}

	@Test
	void testEmptyInts()
	{
		assertEquals(IntVec.empty(), Iter.emptyInts().toVec());
	}

	@Test
	void testInts()
	{
		assertEquals(IntVec.of(1, 2), Iter.ints(1, 2).toVec());
	}

	@Test
	void testReverseInts()
	{
		assertEquals(IntVec.of(2, 1), Iter.reverseInts(1, 2).toVec());
	}

	@Test
	void testEmptyDoubles()
	{
		assertEquals(DoubleVec.empty(), Iter.emptyDoubles().toVec());
	}

	@Test
	void testDoubles()
	{
		assertEquals(DoubleVec.of(1, 2), Iter.doubles(1, 2).toVec());
	}

	@Test
	void testReverseDoubles()
	{
		assertEquals(DoubleVec.of(2, 1), Iter.reverseDoubles(1, 2).toVec());
	}

	@Test
	void testEmptyLongs()
	{
		assertEquals(LongVec.empty(), Iter.emptyLongs().toVec());
	}

	@Test
	void testLongs()
	{
		assertEquals(LongVec.of(1, 2), Iter.longs(1, 2).toVec());
	}

	@Test
	void testReverseLongs()
	{
		assertEquals(LongVec.of(2, 1), Iter.reverseLongs(1, 2).toVec());
	}

	@Test
	void testWrapIterator()
	{
		assertEquals(vec("a"), Iter.wrap(Arrays.asList("a").iterator()).toVec());
	}

	@Test
	void testWrapIterable()
	{
		assertEquals(vec("a"), Iter.wrap(Arrays.asList("a")).toVec());
	}

	@Test
	void testUntil()
	{
		assertEquals(IntVec.of(0, 1, 2, 3, 4), Iter.until(5).toVec());
	}

	@Test
	void testBetweenIntInt()
	{
		assertEquals(IntVec.of(1, 2, 3, 4), Iter.between(1, 5).toVec());
	}

	@Test
	void testBetweenIntIntInt()
	{
		assertEquals(IntVec.of(1, 2, 3, 4), Iter.between(1, 5, 1).toVec());
		assertEquals(IntVec.of(1, -1, -3), Iter.between(1, -5, -2).toVec());
	}

	@Test
	void testPartition()
	{
		assertArrayEquals(new double[] { 1.0, 1.5, 2.0, 2.5, 3.0 },
				Iter.partition(1.0, 3.0, 4).toArray(), 1e-7);
	}
}
