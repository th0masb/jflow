/**
 *
 */
package com.github.maumay.jflow.iterators.impl.minmax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.OptionalDouble;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.testutilities.AbstractIterableDoubles;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;

/**
 * @author ThomasB
 *
 */
class AbstractDoubleIteratorMinMaxTest extends IteratorExampleProvider
{
	@Test
	void testMinOption()
	{
		AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		OptionalDouble minimum = populated.iter().minOption();
		assertTrue(minimum.isPresent());
		assertEquals(0, minimum.getAsDouble());

		AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();
		assertFalse(empty.iter().minOption().isPresent());
	}

	@Test
	void testMin()
	{
		AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		double minimum = populated.iter().min();
		assertEquals(0, minimum);

		AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();
		assertThrows(IllegalStateException.class, () -> empty.iter().min());
	}

	@Test
	void testMaxOption()
	{
		AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		OptionalDouble maximum = populated.iter().maxOption();
		assertTrue(maximum.isPresent());
		assertEquals(4, maximum.getAsDouble());

		AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();
		assertFalse(empty.iter().maxOption().isPresent());
	}

	@Test
	void testMax()
	{
		AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		double maximum = populated.iter().max();
		assertEquals(4, maximum);

		AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();
		assertThrows(IllegalStateException.class, () -> empty.iter().max());
	}
}
