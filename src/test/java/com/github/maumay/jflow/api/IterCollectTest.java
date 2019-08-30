/**
 * 
 */
package com.github.maumay.jflow.api;

import static com.github.maumay.jflow.vec.Vec.vec;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;
import java.util.OptionalDouble;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterator.Iter;
import com.github.maumay.jflow.iterator.collector.IterCollect;
import com.github.maumay.jflow.utils.Tup;
import com.github.maumay.jflow.vec.Vec;

/**
 * @author thomasb
 *
 */
public final class IterCollectTest
{
	@Test
	void averageTest()
	{
		assertEquals((Double) 5.0, Iter.doubles(0, 10).collect(IterCollect.average()));
		assertThrows(NoSuchElementException.class,
				() -> Iter.emptyDoubles().collect(IterCollect.average()));
	}

	@Test
	void averageOpTest()
	{
		assertEquals(OptionalDouble.of(5.0),
				Iter.doubles(0, 10).collect(IterCollect.averageOp()));
		assertEquals(OptionalDouble.empty(),
				Iter.emptyDoubles().collect(IterCollect.averageOp()));
	}

	@Test
	void testSplit()
	{
		Vec<Tup<Integer, Integer>> source = vec(Tup.of(1, 2), Tup.of(3, 4));
		assertEquals(Tup.of(vec(1, 3), vec(2, 4)), source.iter().collect(IterCollect.split()));
	}
}
