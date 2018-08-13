/**
 *
 */
package xawd.jflow.iterators.impl.flatten;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.Flow;
import xawd.jflow.testutilities.AbstractFlowIterable;
import xawd.jflow.testutilities.IteratorExampleProvider;
import xawd.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 *
 */
class AbstractFlowFlattenTest extends IteratorExampleProvider implements IteratorTest
{
	@ParameterizedTest
	@MethodSource
	void newTest(final Map<String, AbstractFlowIterable<String>> testMapping, final AbstractFlowIterable<String> iteratorProvider, final List<String> expectedOutput)
	{
		final Function<String, AbstractFlow<String>> flattenMapping = string -> testMapping.getOrDefault(string, repeat("", 0)).iterator();
		final AbstractFlowIterable<String> flattenedIteratorProvider = createFlattenIteratorProviderFrom(iteratorProvider, flattenMapping);
		assertObjectIteratorAsExpected(expectedOutput, flattenedIteratorProvider);
	}

	private static <E> AbstractFlowIterable<E> repeat(final E element, final int nTimes)
	{
		return new AbstractFlowIterable<E>() {

			@Override
			public AbstractFlow<E> iterator()
			{
				return new AbstractFlow<E>(OptionalInt.of(nTimes)) {
					int count = 0;
					@Override
					public boolean hasNext() {
						return count < nTimes;
					}
					@Override
					public E next() {
						if (count++ >= nTimes) {
							throw new NoSuchElementException();
						}
						return element;
					}
					@Override
					public void skip() {
						next();
					}
				};
			}
		};
	}

	private <E, R> AbstractFlowIterable<R> createFlattenIteratorProviderFrom(final AbstractFlowIterable<E> source, final Function<? super E, ? extends Flow<R>> flattenMapping)
	{
		return new AbstractFlowIterable<R>() {
			@Override
			public AbstractFlow<R> iterator() {
				return source.iterator().flatten(flattenMapping);
			}
		};
	}

	static Stream<Arguments> newTest()
	{
		/*
		 * We test all combinations of left boundary, right boundary and interior being set.
		 */
		// ---
		// Case 0: All empty
		final Map<String, AbstractFlowIterable<String>> testMapping0 = new HashMap<>();
		final AbstractFlowIterable<String> iteratorProvider0 = getObjectTestIteratorProvider();
		final List<String> expectedOutput0 = Arrays.asList();
		// ---
		// Case 1: Left boundary set
		final Map<String, AbstractFlowIterable<String>> testMapping1 = new HashMap<>();
		testMapping1.put("0", repeat("0", 2));
		final AbstractFlowIterable<String> iteratorProvider1 = getObjectTestIteratorProvider();
		final List<String> expectedOutput1 = Arrays.asList("0", "0");
		// ---
		// Case 2: Interior set
		final Map<String, AbstractFlowIterable<String>> testMapping2 = new HashMap<>();
		testMapping2.put("1", repeat("1", 2));
		final AbstractFlowIterable<String> iteratorProvider2 = getObjectTestIteratorProvider();
		final List<String> expectedOutput2 = Arrays.asList("1", "1");
		// ---
		// Case 3: Right boundary set
		final Map<String, AbstractFlowIterable<String>> testMapping3 = new HashMap<>();
		testMapping3.put("4", repeat("4", 3));
		final AbstractFlowIterable<String> iteratorProvider3 = getObjectTestIteratorProvider();
		final List<String> expectedOutput3 = Arrays.asList("4", "4", "4");
		// ---
		// Case 4: Left boundary and interior set.
		final Map<String, AbstractFlowIterable<String>> testMapping4 = new HashMap<>();
		testMapping4.put("0", repeat("0", 1));
		testMapping4.put("1", repeat("1", 1));
		final AbstractFlowIterable<String> iteratorProvider4 = getObjectTestIteratorProvider();
		final List<String> expectedOutput4 = Arrays.asList("0", "1");
		// ---
		// Case 5: Interior and right boundary set.
		final Map<String, AbstractFlowIterable<String>> testMapping5 = new HashMap<>();
		testMapping5.put("1", repeat("1", 1));
		testMapping5.put("4", repeat("4", 2));
		final AbstractFlowIterable<String> iteratorProvider5 = getObjectTestIteratorProvider();
		final List<String> expectedOutput5 = Arrays.asList("1", "4", "4");
		// ---
		// Case 6: left and right boundary set.
		final Map<String, AbstractFlowIterable<String>> testMapping6 = new HashMap<>();
		testMapping6.put("0", repeat("0", 1));
		testMapping6.put("4", repeat("4", 2));
		final AbstractFlowIterable<String> iteratorProvider6 = getObjectTestIteratorProvider();
		final List<String> expectedOutput6 = Arrays.asList("0", "4", "4");
		// ---
		// Case 7: all set.
		final Map<String, AbstractFlowIterable<String>> testMapping7 = new HashMap<>();
		testMapping7.put("0", repeat("0", 1));
		testMapping7.put("2", repeat("2", 1));
		testMapping7.put("4", repeat("4", 2));
		final AbstractFlowIterable<String> iteratorProvider7 = getObjectTestIteratorProvider();
		final List<String> expectedOutput7 = Arrays.asList("0", "2", "4", "4");
		// ---
		// Case 8: Degenerate case.
		final Map<String, AbstractFlowIterable<String>> testMapping8 = new HashMap<>();
		testMapping8.put("0", repeat("0", 1));
		testMapping8.put("2", repeat("2", 1));
		testMapping8.put("4", repeat("4", 2));
		final AbstractFlowIterable<String> iteratorProvider8 = getEmptyObjectTestIteratorProvider();
		final List<String> expectedOutput8 = Arrays.asList();

		return Stream.of(
				Arguments.of(testMapping0, iteratorProvider0, expectedOutput0),
				Arguments.of(testMapping1, iteratorProvider1, expectedOutput1),
				Arguments.of(testMapping2, iteratorProvider2, expectedOutput2),
				Arguments.of(testMapping3, iteratorProvider3, expectedOutput3),
				Arguments.of(testMapping4, iteratorProvider4, expectedOutput4),
				Arguments.of(testMapping5, iteratorProvider5, expectedOutput5),
				Arguments.of(testMapping6, iteratorProvider6, expectedOutput6),
				Arguments.of(testMapping7, iteratorProvider7, expectedOutput7),
				Arguments.of(testMapping8, iteratorProvider8, expectedOutput8)
				);
	}
}
