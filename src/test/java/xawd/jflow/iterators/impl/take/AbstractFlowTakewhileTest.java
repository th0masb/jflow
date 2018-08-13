/**
 *
 */
package xawd.jflow.iterators.impl.take;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.misc.Pair;
import xawd.jflow.testutilities.AbstractFlowIterable;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author t
 *
 */
public class AbstractFlowTakewhileTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractFlowIterable<String> populated = getObjectTestIteratorProvider();
		final AbstractFlowIterable<String> empty = getEmptyObjectTestIteratorProvider();

		final List<Pair<List<String>, Predicate<String>>> testData = asList(
				Pair.of(asList(), string -> !string.equals("0")),
				Pair.of(asList("0", "1", "2"), string -> !string.equals("3")),
				Pair.of(asList("0", "1", "2", "3", "4"), string -> !string.equals("5"))
				);

		testData.stream().forEach(testCase ->
		{
			assertObjectIteratorAsExpected(testCase.first(), createTakewhileIteratorProviderFrom(populated, testCase.second()));
			assertObjectIteratorAsExpected(asList(), createTakewhileIteratorProviderFrom(empty, testCase.second()));
		});
	}

	private <T> AbstractFlowIterable<T> createTakewhileIteratorProviderFrom(AbstractFlowIterable<T> src, Predicate<T> predicate)
	{
		return new AbstractFlowIterable<T>()
		{
			@Override
			public AbstractFlow<T> iterator()
			{
				return src.iterator().takeWhile(predicate);
			}
		};
	}

}
