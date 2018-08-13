package xawd.jflow.iterators.impl.map;

import java.util.function.DoubleToIntFunction;
import java.util.function.LongToIntFunction;
import java.util.function.ToIntFunction;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractIntFlow;
import xawd.jflow.testutilities.AbstractFlowIterable;
import xawd.jflow.testutilities.AbstractIterableDoubles;
import xawd.jflow.testutilities.AbstractIterableInts;
import xawd.jflow.testutilities.AbstractIterableLongs;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
public class MapToIntTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	public void testAbstractFlowMapToInt() 
	{
		final AbstractFlowIterable<String> populated = getObjectTestIteratorProvider();
		final AbstractFlowIterable<String> empty = getEmptyObjectTestIteratorProvider();
		final ToIntFunction<String> mapper = Integer::parseInt;
		
		assertIntIteratorAsExpected(new int[] {0, 1, 2, 3, 4}, createMapToIntIteratorProviderFrom(populated, mapper));
		assertIntIteratorAsExpected(new int[] {}, createMapToIntIteratorProviderFrom(empty, mapper));
	}
	
	private <T> AbstractIterableInts createMapToIntIteratorProviderFrom(final AbstractFlowIterable<T> src, final ToIntFunction<T> mapper)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntFlow iterator() {
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
		
		assertIntIteratorAsExpected(new int[] {1, 2, 3, 4, 5}, createLongMapToIntIteratorProviderFrom(populated, mapper));
		assertIntIteratorAsExpected(new int[] {}, createLongMapToIntIteratorProviderFrom(empty, mapper));
	}
	
	private AbstractIterableInts createLongMapToIntIteratorProviderFrom(AbstractIterableLongs src, LongToIntFunction mapper)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntFlow iterator() {
				return src.iterator().mapToInt(mapper);
			}
		};
	}
	
	@Test
	public void testAbstractDoubleFlowMapToInt()
	{
		final AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		final AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();
		final DoubleToIntFunction mapper = x -> (int) (x + 1.6);
		
		assertIntIteratorAsExpected(new int[] {1, 2, 3, 4, 5}, createDoubleMapToIntIteratorProviderFrom(populated, mapper));
		assertIntIteratorAsExpected(new int[] {}, createDoubleMapToIntIteratorProviderFrom(empty, mapper));
	}
	
	private AbstractIterableInts createDoubleMapToIntIteratorProviderFrom(AbstractIterableDoubles src, DoubleToIntFunction mapper)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntFlow iterator() {
				return src.iterator().mapToInt(mapper);
			}
		};
	}
}
