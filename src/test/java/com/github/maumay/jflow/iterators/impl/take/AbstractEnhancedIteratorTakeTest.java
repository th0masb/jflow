package com.github.maumay.jflow.iterators.impl.take;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractEnhancedIterator;
import com.github.maumay.jflow.testutilities.AbstractEnhancedIterable;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
public class AbstractEnhancedIteratorTakeTest extends IteratorExampleProvider
		implements IteratorTest
{
	@Test
	void test()
	{
		List<List<String>> expectedOutcomesForDifferentIndexArguments = asList(asList(),
				asList("0"), asList("0", "1"), asList("0", "1", "2"), asList("0", "1", "2", "3"),
				asList("0", "1", "2", "3", "4"));

		int nArgs = expectedOutcomesForDifferentIndexArguments.size();

		AbstractEnhancedIterable<String> populated = getObjectTestIteratorProvider();
		AbstractEnhancedIterable<String> empty = getEmptyObjectTestIteratorProvider();

		IntStream.range(0, nArgs).forEach(i -> {
			assertObjectIteratorAsExpected(expectedOutcomesForDifferentIndexArguments.get(i),
					createTakeIteratorProviderFrom(populated, i));
			assertObjectIteratorAsExpected(asList(), createTakeIteratorProviderFrom(empty, i));
		});

		IntStream.range(Constants.NEGATIVE_LOWER_BOUND, 0).forEach(i -> {
			assertThrows(IllegalArgumentException.class, () -> populated.iter().take(i));
			assertThrows(IllegalArgumentException.class, () -> empty.iter().take(i));
		});

		IntStream.range(nArgs, Constants.POSITIVE_UPPER_BOUND).forEach(i -> {
			assertObjectIteratorAsExpected(
					expectedOutcomesForDifferentIndexArguments.get(nArgs - 1),
					createTakeIteratorProviderFrom(populated, i));
			assertObjectIteratorAsExpected(asList(), createTakeIteratorProviderFrom(empty, i));
		});
	}

	private <T> AbstractEnhancedIterable<T> createTakeIteratorProviderFrom(
			AbstractEnhancedIterable<T> src, int takeCount)
	{
		return new AbstractEnhancedIterable<T>() {
			@Override
			public AbstractEnhancedIterator<T> iter()
			{
				return src.iter().take(takeCount);
			}
		};
	}
}
