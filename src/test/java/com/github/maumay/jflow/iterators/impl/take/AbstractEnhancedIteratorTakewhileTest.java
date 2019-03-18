/**
 *
 */
package com.github.maumay.jflow.iterators.impl.take;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.testutilities.AbstractRichIterable;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;
import com.github.maumay.jflow.utils.Tup;

/**
 * @author t
 *
 */
public class AbstractEnhancedIteratorTakewhileTest extends IteratorExampleProvider
		implements IteratorTest
{
	@Test
	void test()
	{
		AbstractRichIterable<String> populated = getObjectTestIteratorProvider();
		AbstractRichIterable<String> empty = getEmptyObjectTestIteratorProvider();

		List<Tup<List<String>, Predicate<String>>> testData = asList(
				Tup.of(asList(), string -> !string.equals("0")),
				Tup.of(asList("0", "1", "2"), string -> !string.equals("3")),
				Tup.of(asList("0", "1", "2", "3", "4"), string -> !string.equals("5")));

		testData.stream().forEach(testCase -> {
			assertObjectIteratorAsExpected(testCase._1(),
					createTakewhileIteratorProviderFrom(populated, testCase._2()));
			assertObjectIteratorAsExpected(asList(),
					createTakewhileIteratorProviderFrom(empty, testCase._2()));
		});
	}

	private <T> AbstractRichIterable<T> createTakewhileIteratorProviderFrom(
			AbstractRichIterable<T> src, Predicate<T> predicate)
	{
		return new AbstractRichIterable<T>() {
			@Override
			public AbstractRichIterator<T> iter()
			{
				return src.iter().takeWhile(predicate);
			}
		};
	}

}
