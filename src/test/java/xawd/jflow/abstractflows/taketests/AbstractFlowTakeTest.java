package xawd.jflow.abstractflows.taketests;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import xawd.jflow.AbstractFlow;
import xawd.jflow.abstractiterables.AbstractFlowIterable;
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
		
		AbstractFlowIterable<String> populated = getPopulatedObjectTestIteratorProvider();
		AbstractFlowIterable<String> empty = getEmptyObjectTestIteratorProvider();
		
		IntStream.range(0, nArgs).forEach(i -> 
		{
			assertObjectIteratorAsExpected(expectedOutcomesForDifferentIndexArguments.get(i), createTakeIteratorProviderFrom(populated, i));
			assertObjectIteratorAsExpected(asList(), createTakeIteratorProviderFrom(empty, i));
		});
		
		IntStream.range(Constants.NEGATIVE_LOWER_BOUND, 0).forEach(i -> 
		{
			assertThrows(IllegalArgumentException.class, () -> populated.iter().take(i));
			assertThrows(IllegalArgumentException.class, () -> empty.iter().take(i));
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
			public AbstractFlow<T> iter() {
				return src.iter().take(takeCount);
			}
		};
	}
}
