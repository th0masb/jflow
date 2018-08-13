package xawd.jflow.iterators.impl.take;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractDoubleFlow;
import xawd.jflow.testutilities.AbstractIterableDoubles;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author t
 */
public class AbstractDoubleFlowTakeTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test() 
	{
		double[][] expectedOutcomesForDifferentIndexArguments = {
				{},
				{0},
				{0, 1},
				{0, 1, 2},
				{0, 1, 2, 3},
				{0, 1, 2, 3, 4}
		};
		
		int nArgs = expectedOutcomesForDifferentIndexArguments.length;
		
		AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();
		
		IntStream.range(0, nArgs).forEach(i -> 
		{
			assertDoubleIteratorAsExpected(expectedOutcomesForDifferentIndexArguments[i], createTakeIteratorProviderFrom(populated, i));
			assertDoubleIteratorAsExpected(new double[] {}, createTakeIteratorProviderFrom(empty, i));
		});
		
		IntStream.range(Constants.NEGATIVE_LOWER_BOUND, 0).forEach(i -> 
		{
			assertThrows(IllegalArgumentException.class, () -> populated.iterator().take(i));
			assertThrows(IllegalArgumentException.class, () -> empty.iterator().take(i));
		});
		
		IntStream.range(nArgs, Constants.POSITIVE_UPPER_BOUND).forEach(i -> 
		{
			assertDoubleIteratorAsExpected(expectedOutcomesForDifferentIndexArguments[nArgs - 1], createTakeIteratorProviderFrom(populated, i));
			assertDoubleIteratorAsExpected(new double[] {}, createTakeIteratorProviderFrom(empty, i));
		});
	}
	
	private AbstractIterableDoubles createTakeIteratorProviderFrom(AbstractIterableDoubles src, int takeCount)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleFlow iterator() {
				return src.iterator().take(takeCount);
			}
		};
	}
}
