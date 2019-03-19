/**
 *
 */
package com.github.maumay.jflow.iterators.impl.flatten;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.impl.KnownSize;
import com.github.maumay.jflow.iterators.RichIterator;
import com.github.maumay.jflow.testutilities.AbstractRichIterable;
import com.github.maumay.jflow.testutilities.IteratorExampleProviders;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 *
 */
class AbstractEnhancedIteratorFlattenTest extends IteratorExampleProviders implements IteratorTest
{
	@ParameterizedTest
	@MethodSource
	void newTest(Map<String, AbstractRichIterable<String>> testMapping,
			AbstractRichIterable<String> iteratorProvider, List<String> expectedOutput)
	{
		Function<String, AbstractRichIterator<String>> flattenMapping = string -> testMapping
				.getOrDefault(string, repeat("", 0)).iter();
		AbstractRichIterable<String> flattenedIteratorProvider = createFlattenIteratorProviderFrom(
				iteratorProvider, flattenMapping);
		assertObjectIteratorAsExpected(expectedOutput, flattenedIteratorProvider);
	}

	private static <E> AbstractRichIterable<E> repeat(E element, int nTimes)
	{
		return new AbstractRichIterable<E>() {

			@Override
			public AbstractRichIterator<E> iter()
			{
				return new AbstractRichIterator<E>(KnownSize.of(nTimes)) {
					int count = 0;

					@Override
					public boolean hasNext()
					{
						return count < nTimes;
					}

					@Override
					public E nextImpl()
					{
						if (count++ >= nTimes) {
							throw new NoSuchElementException();
						}
						return element;
					}

					@Override
					public void skipImpl()
					{
						next();
					}
				};
			}
		};
	}

	private <E, R> AbstractRichIterable<R> createFlattenIteratorProviderFrom(
			AbstractRichIterable<E> source,
			Function<? super E, ? extends RichIterator<R>> flattenMapping)
	{
		return new AbstractRichIterable<R>() {
			@Override
			public AbstractRichIterator<R> iter()
			{
				return source.iter().flatMap(flattenMapping);
			}
		};
	}

	static Stream<Arguments> newTest()
	{
		/*
		 * We test all combinations of left boundary, right boundary and interior being
		 * set.
		 */
		// ---
		// Case 0: All empty
		Map<String, AbstractRichIterable<String>> testMapping0 = new HashMap<>();
		AbstractRichIterable<String> iteratorProvider0 = getObjectIteratorProviders();
		List<String> expectedOutput0 = Arrays.asList();
		// ---
		// Case 1: Left boundary set
		Map<String, AbstractRichIterable<String>> testMapping1 = new HashMap<>();
		testMapping1.put("0", repeat("0", 2));
		AbstractRichIterable<String> iteratorProvider1 = getObjectIteratorProviders();
		List<String> expectedOutput1 = Arrays.asList("0", "0");
		// ---
		// Case 2: Interior set
		Map<String, AbstractRichIterable<String>> testMapping2 = new HashMap<>();
		testMapping2.put("1", repeat("1", 2));
		AbstractRichIterable<String> iteratorProvider2 = getObjectIteratorProviders();
		List<String> expectedOutput2 = Arrays.asList("1", "1");
		// ---
		// Case 3: Right boundary set
		Map<String, AbstractRichIterable<String>> testMapping3 = new HashMap<>();
		testMapping3.put("4", repeat("4", 3));
		AbstractRichIterable<String> iteratorProvider3 = getObjectIteratorProviders();
		List<String> expectedOutput3 = Arrays.asList("4", "4", "4");
		// ---
		// Case 4: Left boundary and interior set.
		Map<String, AbstractRichIterable<String>> testMapping4 = new HashMap<>();
		testMapping4.put("0", repeat("0", 1));
		testMapping4.put("1", repeat("1", 1));
		AbstractRichIterable<String> iteratorProvider4 = getObjectIteratorProviders();
		List<String> expectedOutput4 = Arrays.asList("0", "1");
		// ---
		// Case 5: Interior and right boundary set.
		Map<String, AbstractRichIterable<String>> testMapping5 = new HashMap<>();
		testMapping5.put("1", repeat("1", 1));
		testMapping5.put("4", repeat("4", 2));
		AbstractRichIterable<String> iteratorProvider5 = getObjectIteratorProviders();
		List<String> expectedOutput5 = Arrays.asList("1", "4", "4");
		// ---
		// Case 6: left and right boundary set.
		Map<String, AbstractRichIterable<String>> testMapping6 = new HashMap<>();
		testMapping6.put("0", repeat("0", 1));
		testMapping6.put("4", repeat("4", 2));
		AbstractRichIterable<String> iteratorProvider6 = getObjectIteratorProviders();
		List<String> expectedOutput6 = Arrays.asList("0", "4", "4");
		// ---
		// Case 7: all set.
		Map<String, AbstractRichIterable<String>> testMapping7 = new HashMap<>();
		testMapping7.put("0", repeat("0", 1));
		testMapping7.put("2", repeat("2", 1));
		testMapping7.put("4", repeat("4", 2));
		AbstractRichIterable<String> iteratorProvider7 = getObjectIteratorProviders();
		List<String> expectedOutput7 = Arrays.asList("0", "2", "4", "4");
		// ---
		// Case 8: Degenerate case.
		Map<String, AbstractRichIterable<String>> testMapping8 = new HashMap<>();
		testMapping8.put("0", repeat("0", 1));
		testMapping8.put("2", repeat("2", 1));
		testMapping8.put("4", repeat("4", 2));
		AbstractRichIterable<String> iteratorProvider8 = getEmptyObjectIteratorProvider();
		List<String> expectedOutput8 = Arrays.asList();

		return Stream.of(Arguments.of(testMapping0, iteratorProvider0, expectedOutput0),
				Arguments.of(testMapping1, iteratorProvider1, expectedOutput1),
				Arguments.of(testMapping2, iteratorProvider2, expectedOutput2),
				Arguments.of(testMapping3, iteratorProvider3, expectedOutput3),
				Arguments.of(testMapping4, iteratorProvider4, expectedOutput4),
				Arguments.of(testMapping5, iteratorProvider5, expectedOutput5),
				Arguments.of(testMapping6, iteratorProvider6, expectedOutput6),
				Arguments.of(testMapping7, iteratorProvider7, expectedOutput7),
				Arguments.of(testMapping8, iteratorProvider8, expectedOutput8));
	}
}
