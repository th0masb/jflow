package com.github.maumay.jflow.iterators.impl.take;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.function.LongPredicate;

import org.junit.jupiter.api.Test;

import com.gihub.maumay.jflow.iterators.misc.Tup;
import com.github.maumay.jflow.iterators.AbstractLongIterator;
import com.github.maumay.jflow.testutilities.AbstractIterableLongs;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author t
 */
class AbstractLongIteratorTakewhileTest extends IteratorExampleProvider
		implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractIterableLongs populated = getLongTestIteratorProvider();
		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();

		final List<Tup<long[], LongPredicate>> testData = asList(
				Tup.of(new long[] {}, x -> x != 0),
				Tup.of(new long[] { 0, 1, 2 }, x -> x != 3),
				Tup.of(new long[] { 0, 1, 2, 3, 4 }, x -> x != 5));

		testData.stream().forEach(testCase -> {
			assertLongIteratorAsExpected(testCase._1(),
					createTakewhileIteratorProviderFrom(populated, testCase._2()));
			assertLongIteratorAsExpected(new long[] {},
					createTakewhileIteratorProviderFrom(empty, testCase._2()));
		});
	}

	private AbstractIterableLongs createTakewhileIteratorProviderFrom(
			AbstractIterableLongs src, LongPredicate predicate)
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractLongIterator iter()
			{
				return src.iter().takeWhile(predicate);
			}
		};
	}
}
