/**
 * 
 */
package com.github.maumay.jflow.vec;

import static com.github.maumay.jflow.vec.Vec.vec;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.factories.Iter;
import com.github.maumay.jflow.utils.Tup;

/**
 * @author thomasb
 *
 */
class VecTest
{
	@Test
	void testEquals()
	{
		assertEquals(Vec.empty(), Vec.empty());
		assertEquals(Vec.empty().hashCode(), Vec.empty().hashCode());

		assertEquals(vec("a", "b", "c"), vec("a", "b", "c"));
		assertEquals(vec("a", "b", "c").hashCode(), vec("a", "b", "c").hashCode());

		assertNotEquals(vec("a"), vec("a", "b"));
		assertNotEquals(vec("a").hashCode(), vec("a", "b").hashCode());
	}

	@Test
	void testToString()
	{
		assertEquals("Vec[a, b, c]", vec("a", "b", "c").toString());
	}

	@Test
	void testStreamCollection()
	{
		Vec<String> empty = Vec.empty();
		assertEquals(empty, empty.stream().collect(Vec.collector()));

		Vec<String> vector = vec("a", "b", "c");
		assertEquals(vector, vector.stream().collect(Vec.collector()));

		Vec<Integer> intVector = Iter.until(5000).boxed().toVec();
		assertEquals(intVector, intVector.stream().collect(Vec.collector()));
	}

	@Test
	void testPartition()
	{
		Vec<Integer> vector = vec(1, 2, 3, 4, 5, 6);
		assertEquals(Tup.of(vec(1, 2, 3), vec(4, 5, 6)), vector.partition(n -> n < 4));
		assertEquals(Tup.of(Vec.empty(), vec(1, 2, 3, 4, 5, 6)), vector.partition(n -> n < 1));
		assertEquals(Tup.of(vec(1, 2, 3, 4, 5, 6), Vec.empty()), vector.partition(n -> n < 7));
	}
}
