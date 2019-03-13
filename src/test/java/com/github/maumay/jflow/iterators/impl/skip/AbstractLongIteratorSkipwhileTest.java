package com.github.maumay.jflow.iterators.impl.skip;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.function.LongPredicate;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.impl2.AbstractLongIterator;
import com.github.maumay.jflow.testutilities.AbstractIterableLongs;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;
import com.github.maumay.jflow.utils.Tup;

/**
 * @author t
 */
class AbstractLongIteratorSkipwhileTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		AbstractIterableLongs populated = getLongTestIteratorProvider();
		AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();

		List<Tup<long[], LongPredicate>> testData = asList(
				Tup.of(new long[] { 0, 1, 2, 3, 4 }, x -> x < -0.1),
				Tup.of(new long[] { 4 }, x -> x < 3.1), Tup.of(new long[] {}, x -> x < 5));

		testData.stream().forEach(testCase -> {
			assertLongIteratorAsExpected(testCase._1(),
					createSkipwhileIteratorProviderFrom(populated, testCase._2()));
			assertLongIteratorAsExpected(new long[] {},
					createSkipwhileIteratorProviderFrom(empty, testCase._2()));
		});
	}

	private AbstractIterableLongs createSkipwhileIteratorProviderFrom(AbstractIterableLongs src,
			LongPredicate predicate)
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractLongIterator iter()
			{
				return src.iter().skipWhile(predicate);
			}
		};
	}
}
