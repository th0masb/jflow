/**
 *
 */
package xawd.jflow.iterators.impl.flatten;

import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractDoubleFlow;
import xawd.jflow.iterators.DoubleFlow;
import xawd.jflow.testutilities.AbstractFlowIterable;
import xawd.jflow.testutilities.AbstractIterableDoubles;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractFlowFlattenToDoubleTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractFlowIterable<String> populated = getObjectTestIteratorProvider();
		final AbstractFlowIterable<String> empty = getEmptyObjectTestIteratorProvider();

		final Function<String, AbstractDoubleFlow> flattenMapping1 = string -> repeat(Double.parseDouble(string), 2*(Integer.parseInt(string)%2));
		assertDoubleIteratorAsExpected(new double[] {1, 1, 3, 3}, createFlattenToDoublesIteratorProviderFrom(populated, flattenMapping1));
		assertDoubleIteratorAsExpected(new double[0], createFlattenToDoublesIteratorProviderFrom(empty, flattenMapping1));

		final Function<String, AbstractDoubleFlow> flattenMapping2 = string -> repeat(Double.parseDouble(string), 2*((Integer.parseInt(string) + 1)%2));
		assertDoubleIteratorAsExpected(new double[] {0, 0, 2, 2, 4, 4}, createFlattenToDoublesIteratorProviderFrom(populated, flattenMapping2));
		assertDoubleIteratorAsExpected(new double[0], createFlattenToDoublesIteratorProviderFrom(empty, flattenMapping2));
	}

	private AbstractDoubleFlow repeat(final double element, final int nTimes)
	{
		return new AbstractDoubleFlow(OptionalInt.of(nTimes)) {
			int count = 0;
			@Override
			public boolean hasNext() {
				return count < nTimes;
			}
			@Override
			public double nextDouble() {
				if (count++ >= nTimes) {
					throw new NoSuchElementException();
				}
				return element;
			}
			@Override
			public void skip() {
				nextDouble();
			}
		};
	}

	private <E> AbstractIterableDoubles createFlattenToDoublesIteratorProviderFrom(final AbstractFlowIterable<E> source, final Function<? super E, ? extends DoubleFlow> flattenMapping)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleFlow iterator() {
				return source.iterator().flattenToDoubles(flattenMapping);
			}
		};
	}
}
