/**
 *
 */
package com.github.maumay.jflow.iterators.impl.minmax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.OptionalLong;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.testutilities.AbstractIterableLongs;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;

/**
 * @author ThomasB
 *
 */
class AbstractLongIteratorMinMaxTest extends IteratorExampleProvider
{
	@Test
	void testMin()
	{
		final AbstractIterableLongs populated = getLongTestIteratorProvider();
		final OptionalLong minimum = populated.iter().min();
		assertTrue(minimum.isPresent());
		assertEquals(0, minimum.getAsLong());

		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
		assertFalse(empty.iter().min().isPresent());
	}

	@Test
	void testMinWithDefaultValue()
	{
		final AbstractIterableLongs populated = getLongTestIteratorProvider();
		final double minimum = populated.iter().min(-1);
		assertEquals(0, minimum);

		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
		assertEquals(-1, empty.iter().min(-1));
	}

	@Test
	void testMax()
	{
		final AbstractIterableLongs populated = getLongTestIteratorProvider();
		final OptionalLong maximum = populated.iter().max();
		assertTrue(maximum.isPresent());
		assertEquals(4, maximum.getAsLong());

		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
		assertFalse(empty.iter().max().isPresent());
	}

	@Test
	void testMaxWithDefaultValue()
	{
		final AbstractIterableLongs populated = getLongTestIteratorProvider();
		final double maximum = populated.iter().max(-1);
		assertEquals(4, maximum);

		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
		assertEquals(-1, empty.iter().max(-1));
	}
}
