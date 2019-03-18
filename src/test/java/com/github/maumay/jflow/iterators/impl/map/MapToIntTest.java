package com.github.maumay.jflow.iterators.impl.map;

import java.util.function.DoubleToIntFunction;
import java.util.function.LongToIntFunction;
import java.util.function.ToIntFunction;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.testutilities.AbstractRichIterable;
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
		AbstractRichIterable<String> populated = getObjectTestIteratorProvider();
		AbstractRichIterable<String> empty = getEmptyObjectTestIteratorProvider();
		ToIntFunction<String> mapper = Integer::parseInt;

		assertIntIteratorAsExpected(new int[] { 0, 1, 2, 3, 4 },
				createMapToIntIteratorProviderFrom(populated, mapper));
		assertIntIteratorAsExpected(new int[] {},
				createMapToIntIteratorProviderFrom(empty, mapper));
	}

	private <T> AbstractIterableInts createMapToIntIteratorProviderFrom(
			AbstractRichIterable<T> src, ToIntFunction<T> mapper)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntIterator iter()
			{
				return src.iterator().mapToInt(mapper);
			}
		};
	}

	@Test
	public void testAbstractLongFlowMapToInt()
	{
		AbstractIterableLongs populated = getLongTestIteratorProvider();
		AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
		LongToIntFunction mapper = x -> (int) x + 1;

		assertIntIteratorAsExpected(new int[] { 1, 2, 3, 4, 5 },
				createLongMapToIntIteratorProviderFrom(populated, mapper));
		assertIntIteratorAsExpected(new int[] {},
				createLongMapToIntIteratorProviderFrom(empty, mapper));
	}

	private AbstractIterableInts createLongMapToIntIteratorProviderFrom(AbstractIterableLongs src,
			LongToIntFunction mapper)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntIterator iter()
			{
				return src.iter().mapToInt(mapper);
			}
		};
	}

	@Test
	public void testAbstractDoubleFlowMapToInt()
	{
		AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();
		DoubleToIntFunction mapper = x -> (int) (x + 1.6);

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
			public AbstractIntIterator iter()
			{
				return src.iter().mapToInt(mapper);
			}
		};
	}
}
