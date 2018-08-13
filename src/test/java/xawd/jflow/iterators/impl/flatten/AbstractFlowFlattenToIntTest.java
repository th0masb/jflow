/**
 *
 */
package xawd.jflow.iterators.impl.flatten;

import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractIntFlow;
import xawd.jflow.iterators.IntFlow;
import xawd.jflow.testutilities.AbstractFlowIterable;
import xawd.jflow.testutilities.AbstractIterableInts;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractFlowFlattenToIntTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractFlowIterable<String> populated = getObjectTestIteratorProvider();
		final AbstractFlowIterable<String> empty = getEmptyObjectTestIteratorProvider();

		final Function<String, AbstractIntFlow> flattenMapping1 = string -> repeat(Integer.parseInt(string), 2*(Integer.parseInt(string)%2));
		assertIntIteratorAsExpected(new int[] {1, 1, 3, 3}, createFlattenToIntsIteratorProviderFrom(populated, flattenMapping1));
		assertIntIteratorAsExpected(new int[0], createFlattenToIntsIteratorProviderFrom(empty, flattenMapping1));

		final Function<String, AbstractIntFlow> flattenMapping2 = string -> repeat(Integer.parseInt(string), 2*((Integer.parseInt(string) + 1)%2));
		assertIntIteratorAsExpected(new int[] {0, 0, 2, 2, 4, 4}, createFlattenToIntsIteratorProviderFrom(populated, flattenMapping2));
		assertIntIteratorAsExpected(new int[0], createFlattenToIntsIteratorProviderFrom(empty, flattenMapping2));
	}

	private AbstractIntFlow repeat(final int element, final int nTimes)
	{
		return new AbstractIntFlow(OptionalInt.of(nTimes)) {
			int count = 0;
			@Override
			public boolean hasNext() {
				return count < nTimes;
			}
			@Override
			public int nextInt() {
				if (count++ >= nTimes) {
					throw new NoSuchElementException();
				}
				return element;
			}
			@Override
			public void skip() {
				nextInt();
			}
		};
	}

	private <E> AbstractIterableInts createFlattenToIntsIteratorProviderFrom(final AbstractFlowIterable<E> source, final Function<? super E, ? extends IntFlow> flattenMapping)
	{
		return new AbstractIterableInts() {
			@Override
			public AbstractIntFlow iterator() {
				return source.iterator().flattenToInts(flattenMapping);
			}
		};
	}
}
