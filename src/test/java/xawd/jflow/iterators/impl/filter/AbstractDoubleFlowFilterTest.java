/**
 *
 */
package xawd.jflow.iterators.impl.filter;

import java.util.function.DoublePredicate;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractDoubleFlow;
import xawd.jflow.testutilities.AbstractIterableDoubles;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractDoubleFlowFilterTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		final AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();

		final DoublePredicate allFilteredPredicate = x -> x < 0;
		assertDoubleIteratorAsExpected(new double[0], createFilterIteratorProviderFrom(populated, allFilteredPredicate));
		assertDoubleIteratorAsExpected(new double[0], createFilterIteratorProviderFrom(empty, allFilteredPredicate));

		final DoublePredicate someFilteredPredicate = x -> x < 3;
		assertDoubleIteratorAsExpected(new double[] {0, 1, 2}, createFilterIteratorProviderFrom(populated, someFilteredPredicate));
		assertDoubleIteratorAsExpected(new double[0], createFilterIteratorProviderFrom(empty, someFilteredPredicate));

		final DoublePredicate noneFilteredPredicate = x -> x < 5;
		assertDoubleIteratorAsExpected(new double[] {0, 1, 2, 3, 4}, createFilterIteratorProviderFrom(populated, noneFilteredPredicate));
		assertDoubleIteratorAsExpected(new double[0], createFilterIteratorProviderFrom(empty, noneFilteredPredicate));
	}

	private AbstractIterableDoubles createFilterIteratorProviderFrom(final AbstractIterableDoubles source, final DoublePredicate predicate)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleFlow iterator() {
				return source.iterator().filter(predicate);
			}
		};
	}
}
