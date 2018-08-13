/**
 *
 */
package xawd.jflow.iterators.impl.slice;


import static java.util.Arrays.asList;

import java.util.function.IntUnaryOperator;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.testutilities.AbstractFlowIterable;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 *
 */
class AbstractFlowSliceTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractFlowIterable<String> populated = getObjectTestIteratorProvider();
		final AbstractFlowIterable<String> empty = getEmptyObjectTestIteratorProvider();

		final IntUnaryOperator allSlicedOperator = i -> i;
		assertObjectIteratorAsExpected(asList("0", "1", "2", "3", "4"), createSlicedIteratorProviderFrom(populated, allSlicedOperator));
		assertObjectIteratorAsExpected(asList(), createSlicedIteratorProviderFrom(empty, allSlicedOperator));

		final IntUnaryOperator someSlicedOperator = i -> 2*i;
		assertObjectIteratorAsExpected(asList("0", "2", "4"), createSlicedIteratorProviderFrom(populated, someSlicedOperator));
		assertObjectIteratorAsExpected(asList(), createSlicedIteratorProviderFrom(empty, someSlicedOperator));

		final IntUnaryOperator noneSlicedOperator = i -> i + 5;
		assertObjectIteratorAsExpected(asList(), createSlicedIteratorProviderFrom(populated, noneSlicedOperator));
		assertObjectIteratorAsExpected(asList(), createSlicedIteratorProviderFrom(empty, noneSlicedOperator));
	}

	private <E> AbstractFlowIterable<E> createSlicedIteratorProviderFrom(final AbstractFlowIterable<E> source, final IntUnaryOperator slicemap)
	{
		return new AbstractFlowIterable<E>() {
			@Override
			public AbstractFlow<E> iterator() {
				return source.iterator().slice(slicemap);
			}
		};
	}
}
