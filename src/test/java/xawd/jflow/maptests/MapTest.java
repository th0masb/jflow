package xawd.jflow.maptests;

import static java.util.Arrays.asList;

import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.LongUnaryOperator;

import org.junit.jupiter.api.Test;

import xawd.jflow.AbstractDoubleFlow;
import xawd.jflow.AbstractFlow;
import xawd.jflow.AbstractIntFlow;
import xawd.jflow.AbstractLongFlow;
import xawd.jflow.iterables.AbstractFlowIterable;
import xawd.jflow.iterables.AbstractIterableDoubles;
import xawd.jflow.iterables.AbstractIterableInts;
import xawd.jflow.iterables.AbstractIterableLongs;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;


public class MapTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	public void testAbstractObjectFlowMap()
	{
		final AbstractFlowIterable<String> populated = getPopulatedObjectTestIteratorProvider();
		final AbstractFlowIterable<String> empty = getEmptyObjectTestIteratorProvider();
		final Function<String, String> mapper = string -> string + string;

		assertObjectIteratorAsExpected(asList("00", "11", "22", "33", "44"), createMapIteratorProviderFrom(populated, mapper));
		assertObjectIteratorAsExpected(asList(), createMapIteratorProviderFrom(empty, mapper));
	}

	private <T, R> AbstractFlowIterable<R> createMapIteratorProviderFrom(final AbstractFlowIterable<T> src, final Function<T, R> mapper)
	{
		return new AbstractFlowIterable<R>()
		{
			@Override
			public AbstractFlow<R> iterator()
			{
				return src.iterator().map(mapper);
			}
		};
	}

	@Test
	public void testAbstractLongFlowMap()
	{
		final AbstractIterableLongs populated = getPopulatedLongTestIteratorProvider();
		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
		final LongUnaryOperator mapper = x -> 2*x;

		assertLongIteratorAsExpected(new long[] {0, 2, 4, 6, 8 }, createLongMapIteratorProviderFrom(populated, mapper));
		assertLongIteratorAsExpected(new long[] {}, createLongMapIteratorProviderFrom(empty, mapper));
	}

	private AbstractIterableLongs createLongMapIteratorProviderFrom(final AbstractIterableLongs src, final LongUnaryOperator mapper)
	{
		return new AbstractIterableLongs()
		{
			@Override
			public AbstractLongFlow iterator()
			{
				return src.iterator().map(mapper);
			}
		};
	}

	@Test
	public void testAbstractDoubleFlowMap()
	{
		final AbstractIterableDoubles populated = getPopulatedDoubleTestIteratorProvider();
		final AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();
		final DoubleUnaryOperator mapper = x -> 2*x;

		assertDoubleIteratorAsExpected(new double[] {0, 2, 4, 6, 8 }, createDoubleMapIteratorProviderFrom(populated, mapper));
		assertDoubleIteratorAsExpected(new double[] {}, createDoubleMapIteratorProviderFrom(empty, mapper));
	}

	private AbstractIterableDoubles createDoubleMapIteratorProviderFrom(final AbstractIterableDoubles src, final DoubleUnaryOperator mapper)
	{
		return new AbstractIterableDoubles()
		{
			@Override
			public AbstractDoubleFlow iterator()
			{
				return src.iterator().map(mapper);
			}
		};
	}

	@Test
	public void testAbstractIntFlowMap()
	{
		final AbstractIterableInts populated = getPopulatedIntTestIteratorProvider();
		final AbstractIterableInts empty = getEmptyIntTestIteratorProvider();
		final IntUnaryOperator mapper = x -> 2*x;

		assertIntIteratorAsExpected(new int[] {0, 2, 4, 6, 8 }, createIntMapIteratorProvider(populated, mapper));
		assertIntIteratorAsExpected(new int[] {}, createIntMapIteratorProvider(empty, mapper));
	}

	private AbstractIterableInts createIntMapIteratorProvider(final AbstractIterableInts src, final IntUnaryOperator mapper)
	{
		return new AbstractIterableInts()
		{
			@Override
			public AbstractIntFlow iterator()
			{
				return src.iterator().map(mapper);
			}
		};
	}
}
