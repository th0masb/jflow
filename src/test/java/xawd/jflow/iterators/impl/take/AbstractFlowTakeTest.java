package xawd.jflow.iterators.impl.take;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.testutilities.AbstractFlowIterable;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
public class AbstractFlowTakeTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test() 
	{
		List<List<String>> expectedOutcomesForDifferentIndexArguments = asList(
				asList(),
				asList("0"),
				asList("0", "1"),
				asList("0", "1", "2"),
				asList("0", "1", "2", "3"),
				asList("0", "1", "2", "3", "4")
				);
		
		int nArgs = expectedOutcomesForDifferentIndexArguments.size();
		
		AbstractFlowIterable<String> populated = getObjectTestIteratorProvider();
		AbstractFlowIterable<String> empty = getEmptyObjectTestIteratorProvider();
		
		IntStream.range(0, nArgs).forEach(i -> 
		{
			assertObjectIteratorAsExpected(expectedOutcomesForDifferentIndexArguments.get(i), createTakeIteratorProviderFrom(populated, i));
			assertObjectIteratorAsExpected(asList(), createTakeIteratorProviderFrom(empty, i));
		});
		
		IntStream.range(Constants.NEGATIVE_LOWER_BOUND, 0).forEach(i -> 
		{
			assertThrows(IllegalArgumentException.class, () -> populated.iterator().take(i));
			assertThrows(IllegalArgumentException.class, () -> empty.iterator().take(i));
		});
		
		IntStream.range(nArgs, Constants.POSITIVE_UPPER_BOUND).forEach(i -> 
		{
			assertObjectIteratorAsExpected(expectedOutcomesForDifferentIndexArguments.get(nArgs - 1), createTakeIteratorProviderFrom(populated, i));
			assertObjectIteratorAsExpected(asList(), createTakeIteratorProviderFrom(empty, i));
		});
	}
	
	private <T> AbstractFlowIterable<T> createTakeIteratorProviderFrom(AbstractFlowIterable<T> src, int takeCount)
	{
		return new AbstractFlowIterable<T>() {
			@Override
			public AbstractFlow<T> iterator() {
				return src.iterator().take(takeCount);
			}
		};
	}
}
