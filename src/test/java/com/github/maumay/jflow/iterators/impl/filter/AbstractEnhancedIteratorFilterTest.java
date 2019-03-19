package com.github.maumay.jflow.iterators.impl.filter;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.testutilities.AbstractRichIterable;
import com.github.maumay.jflow.testutilities.IteratorExampleProviders;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractEnhancedIteratorFilterTest extends IteratorExampleProviders implements IteratorTest
{
	@Test
	void test()
	{
		AbstractRichIterable<String> populated = getObjectIteratorProviders();
		AbstractRichIterable<String> empty = getEmptyObjectIteratorProvider();

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

	private <E> AbstractRichIterable<E> createFilterIteratorProviderFrom(
			AbstractRichIterable<E> source, Predicate<? super E> predicate)
	{
		return new AbstractRichIterable<E>() {
			@Override
			public AbstractRichIterator<E> iter()
			{
				return source.iter().filter(predicate);
			}
		};
	}
}
