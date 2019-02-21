package com.github.maumay.jflow.iterators.impl.take;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.AbstractEnhancedDoubleIterator;
import com.github.maumay.jflow.testutilities.AbstractIterableDoubles;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author t
 */
public class AbstractEnhancedDoubleIteratorTakeTest extends IteratorExampleProvider
		implements IteratorTest
{
	@Test
	void test()
	{
		double[][] expectedOutcomesForDifferentIndexArguments = { {}, { 0 }, { 0, 1 },
				{ 0, 1, 2 }, { 0, 1, 2, 3 }, { 0, 1, 2, 3, 4 } };

		int nArgs = expectedOutcomesForDifferentIndexArguments.length;

		AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();

		IntStream.range(0, nArgs).forEach(i -> {
			assertDoubleIteratorAsExpected(expectedOutcomesForDifferentIndexArguments[i],
					createTakeIteratorProviderFrom(populated, i));
			assertDoubleIteratorAsExpected(new double[] {},
					createTakeIteratorProviderFrom(empty, i));
		});

		IntStream.range(Constants.NEGATIVE_LOWER_BOUND, 0).forEach(i -> {
			assertThrows(IllegalArgumentException.class,
					() -> populated.iter().take(i));
			assertThrows(IllegalArgumentException.class, () -> empty.iter().take(i));
		});

		IntStream.range(nArgs, Constants.POSITIVE_UPPER_BOUND).forEach(i -> {
			assertDoubleIteratorAsExpected(
					expectedOutcomesForDifferentIndexArguments[nArgs - 1],
					createTakeIteratorProviderFrom(populated, i));
			assertDoubleIteratorAsExpected(new double[] {},
					createTakeIteratorProviderFrom(empty, i));
		});
	}

	private AbstractIterableDoubles createTakeIteratorProviderFrom(
			AbstractIterableDoubles src, int takeCount)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractEnhancedDoubleIterator iter()
			{
				return src.iter().take(takeCount);
			}
		};
	}
}
