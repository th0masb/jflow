package com.github.maumay.jflow.iterators.impl.map;

import java.util.function.DoubleToLongFunction;
import java.util.function.IntToLongFunction;
import java.util.function.ToLongFunction;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.AbstractLongIterator;
import com.github.maumay.jflow.testutilities.AbstractEnhancedIterable;
import com.github.maumay.jflow.testutilities.AbstractIterableDoubles;
import com.github.maumay.jflow.testutilities.AbstractIterableInts;
import com.github.maumay.jflow.testutilities.AbstractIterableLongs;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

public class MapToLongTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	public void testAbstractFlowMapToLong()
	{
		AbstractEnhancedIterable<String> populated = getObjectTestIteratorProvider();
		AbstractEnhancedIterable<String> empty = getEmptyObjectTestIteratorProvider();
		ToLongFunction<String> mapper = Long::parseLong;

		assertLongIteratorAsExpected(new long[] { 0, 1, 2, 3, 4 },
				createMapToLongIteratorProviderFrom(populated, mapper));
		assertLongIteratorAsExpected(new long[] {},
				createMapToLongIteratorProviderFrom(empty, mapper));
	}

	private <T> AbstractIterableLongs createMapToLongIteratorProviderFrom(
			AbstractEnhancedIterable<T> src, ToLongFunction<T> mapper)
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractLongIterator iter()
			{
				return src.iterator().mapToLong(mapper);
			}
		};
	}

	@Test
	public void testAbstractIntFlowMapToLong()
	{
		AbstractIterableInts populated = getIntTestIteratorProvider();
		AbstractIterableInts empty = getEmptyIntTestIteratorProvider();
		IntToLongFunction mapper = x -> (long) x + 1;

		assertLongIteratorAsExpected(new long[] { 1, 2, 3, 4, 5 },
				createIntMapToLongIteratorProviderFrom(populated, mapper));
		assertLongIteratorAsExpected(new long[] {},
				createIntMapToLongIteratorProviderFrom(empty, mapper));
	}

	private AbstractIterableLongs createIntMapToLongIteratorProviderFrom(AbstractIterableInts src,
			IntToLongFunction mapper)
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractLongIterator iter()
			{
				return src.iter().mapToLong(mapper);
			}
		};
	}

	@Test
	public void testAbstractDoubleFlowMapToLong()
	{
		AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();
		DoubleToLongFunction mapper = x -> (long) (x + 1.6);

		assertLongIteratorAsExpected(new long[] { 1, 2, 3, 4, 5 },
				createDoubleMapToLongIteratorProviderFrom(populated, mapper));
		assertLongIteratorAsExpected(new long[] {},
				createDoubleMapToLongIteratorProviderFrom(empty, mapper));
	}

	private AbstractIterableLongs createDoubleMapToLongIteratorProviderFrom(
			AbstractIterableDoubles src, DoubleToLongFunction mapper)
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractLongIterator iter()
			{
				return src.iter().mapToLong(mapper);
			}
		};
	}
}
