/**
 * 
 */
package com.github.maumay.jflow.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.impl.FibonnaciIterator;
import com.github.maumay.jflow.iterator.Numbers;
import com.github.maumay.jflow.test.AbstractSourceTest;
import com.github.maumay.jflow.vec.IntVec;
import com.github.maumay.jflow.vec.LongVec;

/**
 * @author thomasb
 *
 */
public class NumbersTest extends AbstractSourceTest<AbstractLongIterator>
{
	@Test
	void testNaturalNumbers()
	{
		assertEquals(IntVec.of(0, 1, 2, 3, 4), Numbers.natural().take(5).toVec());
	}

	@Test
	void testFibonnaciNumbers()
	{
		assertEquals(LongVec.of(1, 1, 2, 3, 5), Numbers.fibonacci().take(5).toVec());
		assertEquals(LongVec.of(2, 2, 4, 6, 10), Numbers.fibonacci(2, 2).take(5).toVec());
	}

	@Override
	protected List<Case<AbstractLongIterator>> getTestCases()
	{
		return list(
				new Case<>(() -> new FibonnaciIterator(1, 1).take(6),
						list(1L, 1L, 2L, 3L, 5L, 8L)),
				new Case<>(() -> new FibonnaciIterator(2, 2).take(6),
						list(2L, 2L, 4L, 6L, 10L, 16L)));
	}
}
