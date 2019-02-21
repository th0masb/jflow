package com.github.maumay.jflow.iterators.impl.map;

import java.util.function.DoubleToIntFunction;
import java.util.function.LongToIntFunction;
import java.util.function.ToIntFunction;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.AbstractEnhancedIntIterator;
import com.github.maumay.jflow.testutilities.AbstractEnhancedIterable;
import com.github.maumay.jflow.testutilities.AbstractIterableDoubles;
import com.github.maumay.jflow.testutilities.AbstractIterableInts;
import com.github.maumay.jflow.testutilities.AbstractIterableLongs;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
public class MapToIntTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	public void testAbstractFlowMapToInt()
	{
		final AbstractEnhancedIterable<String> populated = getObjectTestIteratorProvider();
		final AbstractEnhancedIterable<String> empty = getEmptyObjectTestIteratorProvider();
		final ToIntFunction<String> mapper = Integer::parseInt;

		assertIntIteratorAsExpected(new int[] { 0, 1, 2, 3, 4 },
				createMapToIntIteratorProviderFrom(populated, mapper));
		assertIntIteratorAsExpected(new int[] {},
				createMapToIntIteratorProviderFrom(empty, mapper));
	}

	private <T> AbstractIterableInts createMapToIntIteratorProviderFrom(
			final AbstractEnhancedIterable<T> src, final ToIntFunction<T> mapper)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractEnhancedIntIterator iter()
			{
				return src.iterator().mapToInt(mapper);
			}
		};
	}

	@Test
	public void testAbstractLongFlowMapToInt()
	{
		final AbstractIterableLongs populated = getLongTestIteratorProvider();
		AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
		final LongToIntFunction mapper = x -> (int) x + 1;

		assertIntIteratorAsExpected(new int[] { 1, 2, 3, 4, 5 },
				createLongMapToIntIteratorProviderFrom(populated, mapper));
		assertIntIteratorAsExpected(new int[] {},
				createLongMapToIntIteratorProviderFrom(empty, mapper));
	}

	private AbstractIterableInts createLongMapToIntIteratorProviderFrom(
			AbstractIterableLongs src, LongToIntFunction mapper)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractEnhancedIntIterator iter()
			{
				return src.iter().mapToInt(mapper);
			}
		};
	}

	@Test
	public void testAbstractDoubleFlowMapToInt()
	{
		final AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		final AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();
		final DoubleToIntFunction mapper = x -> (int) (x + 1.6);

		assertIntIteratorAsExpected(new int[] { 1, 2, 3, 4, 5 },
				createDoubleMapToIntIteratorProviderFrom(populated, mapper));
		assertIntIteratorAsExpected(new int[] {},
				createDoubleMapToIntIteratorProviderFrom(empty, mapper));
	}

	private AbstractIterableInts createDoubleMapToIntIteratorProviderFrom(
			AbstractIterableDoubles src, DoubleToIntFunction mapper)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractEnhancedIntIterator iter()
			{
				return src.iter().mapToInt(mapper);
			}
		};
	}
}
