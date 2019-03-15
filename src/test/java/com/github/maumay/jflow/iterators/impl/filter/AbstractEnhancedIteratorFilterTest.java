package com.github.maumay.jflow.iterators.impl.filter;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractEnhancedIterator;
import com.github.maumay.jflow.testutilities.AbstractEnhancedIterable;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractEnhancedIteratorFilterTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		AbstractEnhancedIterable<String> populated = getObjectTestIteratorProvider();
		AbstractEnhancedIterable<String> empty = getEmptyObjectTestIteratorProvider();

		Predicate<String> allFilteredPredicate = string -> parseInt(string) < 0;
		assertObjectIteratorAsExpected(asList(),
				createFilterIteratorProviderFrom(populated, allFilteredPredicate));
		assertObjectIteratorAsExpected(asList(),
				createFilterIteratorProviderFrom(empty, allFilteredPredicate));

		Predicate<String> someFilteredPredicate = string -> parseInt(string) < 3;
		assertObjectIteratorAsExpected(asList("0", "1", "2"),
				createFilterIteratorProviderFrom(populated, someFilteredPredicate));
		assertObjectIteratorAsExpected(asList(),
				createFilterIteratorProviderFrom(empty, someFilteredPredicate));

		Predicate<String> noneFilteredPredicate = string -> parseInt(string) < 5;
		assertObjectIteratorAsExpected(asList("0", "1", "2", "3", "4"),
				createFilterIteratorProviderFrom(populated, noneFilteredPredicate));
		assertObjectIteratorAsExpected(asList(),
				createFilterIteratorProviderFrom(empty, noneFilteredPredicate));
	}

	private <E> AbstractEnhancedIterable<E> createFilterIteratorProviderFrom(
			AbstractEnhancedIterable<E> source, Predicate<? super E> predicate)
	{
		return new AbstractEnhancedIterable<E>() {
			@Override
			public AbstractEnhancedIterator<E> iter()
			{
				return source.iter().filter(predicate);
			}
		};
	}
}
