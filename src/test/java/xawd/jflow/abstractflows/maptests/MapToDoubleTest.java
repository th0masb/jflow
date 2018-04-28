/**
 *
 */
package xawd.jflow.abstractflows.maptests;

import java.util.function.IntToDoubleFunction;
import java.util.function.LongToDoubleFunction;
import java.util.function.ToDoubleFunction;

import org.junit.jupiter.api.Test;

import xawd.jflow.AbstractDoubleFlow;
import xawd.jflow.abstractiterables.AbstractFlowIterable;
import xawd.jflow.abstractiterables.AbstractIterableDoubles;
import xawd.jflow.abstractiterables.AbstractIterableInts;
import xawd.jflow.abstractiterables.AbstractIterableLongs;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author t
 */
public class MapToDoubleTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	public void testAbstractFlowMapToDouble()
	{
		final AbstractFlowIterable<String> populated = getPopulatedObjectTestIteratorProvider();
		final AbstractFlowIterable<String> empty = getEmptyObjectTestIteratorProvider();
		final ToDoubleFunction<String> mapper = Double::parseDouble;

		assertDoubleIteratorAsExpected(new double[] {0, 1, 2, 3, 4}, createMapToDoubleIteratorProviderFrom(populated, mapper));
		assertDoubleIteratorAsExpected(new double[] {}, createMapToDoubleIteratorProviderFrom(empty, mapper));
	}

	private <T> AbstractIterableDoubles createMapToDoubleIteratorProviderFrom(final AbstractFlowIterable<T> src, final ToDoubleFunction<T> mapper)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleFlow iter() {
				return src.iter().mapToDouble(mapper);
			}
		};
	}

	@Test
	public void testAbstractLongFlowMapToDouble()
	{
		final AbstractIterableLongs populated = getPopulatedLongTestIteratorProvider();
		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
		final LongToDoubleFunction mapper = x -> x + 3.2;

		assertDoubleIteratorAsExpected(new double[] {3.2, 4.2, 5.2, 6.2, 7.2}, createLongMapToDoubleIteratorProviderFrom(populated, mapper));
		assertDoubleIteratorAsExpected(new double[] {}, createLongMapToDoubleIteratorProviderFrom(empty, mapper));
	}

	private AbstractIterableDoubles createLongMapToDoubleIteratorProviderFrom(AbstractIterableLongs src, LongToDoubleFunction mapper)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleFlow iter() {
				return src.iter().mapToDouble(mapper);
			}
		};
	}

	@Test
	public void testAbstractIntFlowMapToDouble()
	{
		final AbstractIterableInts populated = getPopulatedIntTestIteratorProvider();
		final AbstractIterableInts empty = getEmptyIntTestIteratorProvider();
		final IntToDoubleFunction mapper = x -> x + 1.6;

		assertDoubleIteratorAsExpected(new double[] {1.6, 2.6, 3.6, 4.6, 5.6}, createIntMapToDoubleIteratorProviderFrom(populated, mapper));
		assertDoubleIteratorAsExpected(new double[] {}, createIntMapToDoubleIteratorProviderFrom(empty, mapper));
	}

	private AbstractIterableDoubles createIntMapToDoubleIteratorProviderFrom(AbstractIterableInts src, IntToDoubleFunction mapper)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleFlow iter() {
				return src.iter().mapToDouble(mapper);
			}
		};
	}
}
