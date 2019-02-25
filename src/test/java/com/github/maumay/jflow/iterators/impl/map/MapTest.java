package com.github.maumay.jflow.iterators.impl.map;

import static java.util.Arrays.asList;

import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.LongUnaryOperator;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.AbstractDoubleIterator;
import com.github.maumay.jflow.iterators.AbstractIntIterator;
import com.github.maumay.jflow.iterators.AbstractEnhancedIterator;
import com.github.maumay.jflow.iterators.AbstractLongIterator;
import com.github.maumay.jflow.testutilities.AbstractEnhancedIterable;
import com.github.maumay.jflow.testutilities.AbstractIterableDoubles;
import com.github.maumay.jflow.testutilities.AbstractIterableInts;
import com.github.maumay.jflow.testutilities.AbstractIterableLongs;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

public class MapTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	public void testAbstractObjectFlowMap()
	{
		final AbstractEnhancedIterable<String> populated = getObjectTestIteratorProvider();
		final AbstractEnhancedIterable<String> empty = getEmptyObjectTestIteratorProvider();
		final Function<String, String> mapper = string -> string + string;

		assertObjectIteratorAsExpected(asList("00", "11", "22", "33", "44"),
				createMapIteratorProviderFrom(populated, mapper));
		assertObjectIteratorAsExpected(asList(),
				createMapIteratorProviderFrom(empty, mapper));
	}

	private <T, R> AbstractEnhancedIterable<R> createMapIteratorProviderFrom(
			final AbstractEnhancedIterable<T> src, final Function<T, R> mapper)
	{
		return new AbstractEnhancedIterable<R>() {
			@Override
			public AbstractEnhancedIterator<R> iter()
			{
				return src.iterator().map(mapper);
			}
		};
	}

	@Test
	public void testAbstractLongFlowMap()
	{
		final AbstractIterableLongs populated = getLongTestIteratorProvider();
		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
		final LongUnaryOperator mapper = x -> 2 * x;

		assertLongIteratorAsExpected(new long[] { 0, 2, 4, 6, 8 },
				createLongMapIteratorProviderFrom(populated, mapper));
		assertLongIteratorAsExpected(new long[] {},
				createLongMapIteratorProviderFrom(empty, mapper));
	}

	private AbstractIterableLongs createLongMapIteratorProviderFrom(
			final AbstractIterableLongs src, final LongUnaryOperator mapper)
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractLongIterator iter()
			{
				return src.iter().map(mapper);
			}
		};
	}

	@Test
	public void testAbstractDoubleFlowMap()
	{
		final AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		final AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();
		final DoubleUnaryOperator mapper = x -> 2 * x;

		assertDoubleIteratorAsExpected(new double[] { 0, 2, 4, 6, 8 },
				createDoubleMapIteratorProviderFrom(populated, mapper));
		assertDoubleIteratorAsExpected(new double[] {},
				createDoubleMapIteratorProviderFrom(empty, mapper));
	}

	private AbstractIterableDoubles createDoubleMapIteratorProviderFrom(
			final AbstractIterableDoubles src, final DoubleUnaryOperator mapper)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleIterator iter()
			{
				return src.iter().map(mapper);
			}
		};
	}

	@Test
	public void testAbstractIntFlowMap()
	{
		final AbstractIterableInts populated = getIntTestIteratorProvider();
		final AbstractIterableInts empty = getEmptyIntTestIteratorProvider();
		final IntUnaryOperator mapper = x -> 2 * x;

		assertIntIteratorAsExpected(new int[] { 0, 2, 4, 6, 8 },
				createIntMapIteratorProvider(populated, mapper));
		assertIntIteratorAsExpected(new int[] {},
				createIntMapIteratorProvider(empty, mapper));
	}

	private AbstractIterableInts createIntMapIteratorProvider(
			final AbstractIterableInts src, final IntUnaryOperator mapper)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntIterator iter()
			{
				return src.iter().map(mapper);
			}
		};
	}
}
