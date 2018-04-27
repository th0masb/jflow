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
		final AbstractFlowIterable<String> populated = getPopulatedObjectTestIterator();
		final AbstractFlowIterable<String> empty = getEmptyObjectTestIterator();
		final Function<String, String> mapper = string -> string + string;

		assertObjectIteratorAsExpected(asList("00", "11", "22", "33", "44"), mapIteratorProvider(populated, mapper));
		assertObjectIteratorAsExpected(asList(), mapIteratorProvider(empty, mapper));
	}

	private <T, R> AbstractFlowIterable<R> mapIteratorProvider(final AbstractFlowIterable<T> src, final Function<T, R> mapper)
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
		final AbstractIterableLongs populated = getPopulatedLongTestIterator();
		final AbstractIterableLongs empty = getEmptyLongTestIterator();
		final LongUnaryOperator mapper = x -> 2*x;

		assertLongIteratorAsExpected(new long[] {0, 2, 4, 6, 8 }, mapLongIteratorProvider(populated, mapper));
		assertLongIteratorAsExpected(new long[] {}, mapLongIteratorProvider(empty, mapper));
	}

	private AbstractIterableLongs mapLongIteratorProvider(final AbstractIterableLongs src, final LongUnaryOperator mapper)
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
		final AbstractIterableDoubles populated = getPopulatedDoubleTestIterator();
		final AbstractIterableDoubles empty = getEmptyDoubleTestIterator();
		final DoubleUnaryOperator mapper = x -> 2*x;

		assertDoubleIteratorAsExpected(new double[] {0, 2, 4, 6, 8 }, mapDoubleIteratorProvider(populated, mapper));
		assertDoubleIteratorAsExpected(new double[] {}, mapDoubleIteratorProvider(empty, mapper));
	}

	private AbstractIterableDoubles mapDoubleIteratorProvider(final AbstractIterableDoubles src, final DoubleUnaryOperator mapper)
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
		final AbstractIterableInts populated = getPopulatedIntTestIterator();
		final AbstractIterableInts empty = getEmptyIntTestIterator();
		final IntUnaryOperator mapper = x -> 2*x;

		assertIntIteratorAsExpected(new int[] {0, 2, 4, 6, 8 }, mapIntIteratorProvider(populated, mapper));
		assertIntIteratorAsExpected(new int[] {}, mapIntIteratorProvider(empty, mapper));
	}

	private AbstractIterableInts mapIntIteratorProvider(final AbstractIterableInts src, final IntUnaryOperator mapper)
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
