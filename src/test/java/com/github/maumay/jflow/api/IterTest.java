package com.github.maumay.jflow.api;

import static com.github.maumay.jflow.vec.Vec.vec;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterator.Iter;
import com.github.maumay.jflow.utils.Strings;
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
	void testArgs()
	{
		assertEquals(vec("a", "b"), Iter.args("a", "b").toVec());
	}

	@Test
	void testOverEnum()
	{
		assertEquals(vec(Locale.Category.DISPLAY, Locale.Category.FORMAT),
				Iter.enums(Locale.Category.class).toVec());
	}

	@Test
	void testOverOption()
	{
		Optional<String> emptyOp = Optional.empty();
		assertEquals(Vec.empty(), Iter.over(emptyOp).toVec());
		assertEquals(vec("a"), Iter.over(Optional.of("a")).toVec());
	}

	@Test
	void testValues()
	{
		Map<String, String> map = Iter.args("a").associate(x -> x + x);
		assertEquals(vec("aa"), Iter.values(map).toVec());
	}

	@Test
	void testKeys()
	{
		Map<String, String> map = Iter.args("a").associate(x -> x + x);
		assertEquals(vec("a"), Iter.keys(map).toVec());
	}

	@Test
	void testOverMap()
	{
		Map<String, String> map = Iter.args("a").associate(x -> x + x);
		assertEquals(vec(Tup.of("a", "aa")), Iter.over(map).toVec());
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
	void testOverIterable()
	{
		assertEquals(vec("a"), Iter.over(() -> Arrays.asList("a").iterator()).toVec());
	}

	@Test
	void testUntil()
	{
		assertEquals(IntVec.of(0, 1, 2, 3, 4), Iter.until(5).toVec());
	}

	@Test
	void testBetweenIntInt()
	{
		assertEquals(IntVec.empty(), Iter.between(1, 0).toVec());
		assertEquals(IntVec.of(1, 2, 3, 4), Iter.between(1, 5).toVec());
	}

	@Test
	void testBetweenIntIntInt()
	{
		assertEquals(IntVec.of(1, 2, 3, 4), Iter.between(1, 5, 1).toVec());
		assertEquals(IntVec.of(1, -1, -3), Iter.between(1, -5, -2).toVec());
		assertEquals(IntVec.empty(), Iter.between(1, -5, 2).toVec());
	}

	@Test
	void testPartition()
	{
		assertArrayEquals(new double[] { 1.0, 1.5, 2.0, 2.5, 3.0 },
				Iter.partition(1.0, 3.0, 4).toArray(), 1e-7);
		assertThrows(IllegalArgumentException.class, () -> Iter.partition(0, 1, 0));
	}

	@Test
	void testApply()
	{
		assertEquals(vec("a", "aa", "aaaa"), Iter.apply(x -> x + x, "a").take(3).toVec());
	}

	@Test
	void testRepeatAndCall()
	{
		assertEquals(vec("a", "a", "a"), Iter.repeat("a").take(3).toVec());
		assertEquals(vec("a", "a", "a"), Iter.call(() -> "a").take(3).toVec());
		assertEquals(IntVec.of(1, 1, 1), Iter.callInts(() -> 1).skip(3).toVec());
		assertEquals(LongVec.of(1, 1, 1), Iter.callLongs(() -> 1).take(3).toVec());
		assertEquals(DoubleVec.of(1, 1, 1), Iter.callDoubles(() -> 1).take(3).toVec());
	}

	@Test
	void testIndex()
	{
		assertEquals(vec("0", "1", "2"),
				Iter.index(n -> Strings.convert(n)).take(3).toVec());
		assertEquals(IntVec.of(0, 2, 4), Iter.indexInts(n -> 2 * n).skip(3).toVec());
		assertEquals(LongVec.of(0, 2, 4), Iter.indexLongs(n -> 2 * n).take(3).toVec());
		assertEquals(DoubleVec.of(0, 2, 4),
				Iter.indexDoubles(x -> 2 * x).take(3).toVec());
	}
}
