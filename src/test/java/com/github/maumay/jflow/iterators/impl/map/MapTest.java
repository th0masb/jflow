package com.github.maumay.jflow.iterators.impl.map;

import static java.util.Arrays.asList;

import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.LongUnaryOperator;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.testutilities.AbstractIterableDoubles;
import com.github.maumay.jflow.testutilities.AbstractIterableInts;
import com.github.maumay.jflow.testutilities.AbstractIterableLongs;
import com.github.maumay.jflow.testutilities.AbstractRichIterable;
import com.github.maumay.jflow.testutilities.IteratorExampleProviders;
import com.github.maumay.jflow.testutilities.FiniteIteratorTest;

/**
 * 
 * @author thomasb
 *
 */
public class MapTest extends IteratorExampleProviders implements FiniteIteratorTest
{
	@Test
	public void testAbstractObjectFlowMap()
	{
		AbstractRichIterable<String> populated = getObjectIteratorProviders();
		AbstractRichIterable<String> empty = getEmptyObjectIteratorProvider();
		Function<String, String> mapper = string -> string + string;

		assertIteratorAsExpected(asList("00", "11", "22", "33", "44"),
				createMapIteratorProviderFrom(populated, mapper));
		assertIteratorAsExpected(asList(), createMapIteratorProviderFrom(empty, mapper));
	}

	private <T, R> AbstractRichIterable<R> createMapIteratorProviderFrom(
			AbstractRichIterable<T> src, Function<T, R> mapper)
	{
		return new AbstractRichIterable<R>() {
			@Override
			public AbstractRichIterator<R> iter()
			{
				return src.iterator().map(mapper);
			}
		};
	}

	@Test
	public void testAbstractLongFlowMap()
	{
		AbstractIterableLongs populated = getLongTestIteratorProvider();
		AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
		LongUnaryOperator mapper = x -> 2 * x;

		assertLongIteratorAsExpected(new long[] { 0, 2, 4, 6, 8 },
				createLongMapIteratorProviderFrom(populated, mapper));
		assertLongIteratorAsExpected(new long[] {},
				createLongMapIteratorProviderFrom(empty, mapper));
	}

	private AbstractIterableLongs createLongMapIteratorProviderFrom(AbstractIterableLongs src,
			LongUnaryOperator mapper)
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
		AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();
		DoubleUnaryOperator mapper = x -> 2 * x;

		assertDoubleIteratorAsExpected(new double[] { 0, 2, 4, 6, 8 },
				createDoubleMapIteratorProviderFrom(populated, mapper));
		assertDoubleIteratorAsExpected(new double[] {},
				createDoubleMapIteratorProviderFrom(empty, mapper));
	}

	private AbstractIterableDoubles createDoubleMapIteratorProviderFrom(AbstractIterableDoubles src,
			DoubleUnaryOperator mapper)
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
		AbstractIterableInts populated = getIntTestIteratorProvider();
		AbstractIterableInts empty = getEmptyIntTestIteratorProvider();
		IntUnaryOperator mapper = x -> 2 * x;

		assertIntIteratorAsExpected(new int[] { 0, 2, 4, 6, 8 },
				createIntMapIteratorProvider(populated, mapper));
		assertIntIteratorAsExpected(new int[] {}, createIntMapIteratorProvider(empty, mapper));
	}

	private AbstractIterableInts createIntMapIteratorProvider(AbstractIterableInts src,
			IntUnaryOperator mapper)
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
