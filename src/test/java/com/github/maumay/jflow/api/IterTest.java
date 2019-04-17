package com.github.maumay.jflow.api;

import static com.github.maumay.jflow.vec.Vec.vec;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.Iter;
import com.github.maumay.jflow.utils.Tup;
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
		fail("Not yet implemented");
	}

	@Test
	void testEmptyInts()
	{
		fail("Not yet implemented");
	}

	@Test
	void testInts()
	{
		fail("Not yet implemented");
	}

	@Test
	void testReverseInts()
	{
		fail("Not yet implemented");
	}

	@Test
	void testIntsByIndexing()
	{
		fail("Not yet implemented");
	}

	@Test
	void testEmptyDoubles()
	{
		fail("Not yet implemented");
	}

	@Test
	void testDoubles()
	{
		fail("Not yet implemented");
	}

	@Test
	void testReverseDoubles()
	{
		fail("Not yet implemented");
	}

	@Test
	void testDoublesByIndexing()
	{
		fail("Not yet implemented");
	}

	@Test
	void testEmptyLongs()
	{
		fail("Not yet implemented");
	}

	@Test
	void testLongs()
	{
		fail("Not yet implemented");
	}

	@Test
	void testReverseLongs()
	{
		fail("Not yet implemented");
	}

	@Test
	void testLongsByIndexing()
	{
		fail("Not yet implemented");
	}

	@Test
	void testWrapIterator()
	{
		fail("Not yet implemented");
	}

	@Test
	void testWrapIterable()
	{
		fail("Not yet implemented");
	}

	@Test
	void testUntil()
	{
		fail("Not yet implemented");
	}

	@Test
	void testBetweenIntInt()
	{
		fail("Not yet implemented");
	}

	@Test
	void testBetweenIntIntInt()
	{
		fail("Not yet implemented");
	}

	@Test
	void testPartition()
	{
		fail("Not yet implemented");
	}
}
