/**
 *
 */
package xawd.jflow.iterators.impl.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.function.LongPredicate;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import xawd.jflow.iterators.AbstractLongFlow;
import xawd.jflow.iterators.misc.LongPredicatePartition;
import xawd.jflow.testutilities.IteratorExampleProvider;

/**
 * @author ThomasB
 */
class AbstractLongFlowPredicateConsumptionTest extends IteratorExampleProvider
{
	@ParameterizedTest
	@MethodSource("allEqualTestDataProvider")
	void testAllEqual(final AbstractLongFlow iterator, final Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.areAllEqual());
	}

	static Stream<Arguments> allEqualTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getAllEqualFlow(), Boolean.TRUE),
				Arguments.of(getLongTestIteratorProvider().iterator(), Boolean.FALSE),
				Arguments.of(getEmptyLongTestIteratorProvider().iterator(), Boolean.TRUE)
				);
	}

	private static AbstractLongFlow getAllEqualFlow()
	{
		return new AbstractLongFlow(OptionalInt.of(3))
		{
			int count = 0;
			@Override
			public boolean hasNext() {
				return count < 3;
			}
			@Override
			public long nextLong() {
				if (count++ >= 3) {
					throw new NoSuchElementException();
				}
				return 0;
			}
			@Override
			public void skip() {
				nextLong();
			}
		};
	}

	@ParameterizedTest
	@MethodSource("allMatchTestDataProvider")
	void testAllMatch(final AbstractLongFlow iterator, final LongPredicate predicate, final Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.allMatch(predicate));
	}

	static Stream<Arguments> allMatchTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getLongTestIteratorProvider().iterator(), (LongPredicate) x -> x < 3, Boolean.FALSE),
				Arguments.of(getLongTestIteratorProvider().iterator(), (LongPredicate) x -> x > -1, Boolean.TRUE),
				Arguments.of(getEmptyLongTestIteratorProvider().iterator(), (LongPredicate) x -> x < 3, Boolean.TRUE),
				Arguments.of(getEmptyLongTestIteratorProvider().iterator(), (LongPredicate) x -> x > -1, Boolean.TRUE)
				);
	}

	@ParameterizedTest
	@MethodSource("anyMatchTestDataProvider")
	void testAnyMatch(final AbstractLongFlow iterator, final LongPredicate predicate, final Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.anyMatch(predicate));
	}

	static Stream<Arguments> anyMatchTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getLongTestIteratorProvider().iterator(), (LongPredicate) x -> x < -1, Boolean.FALSE),
				Arguments.of(getLongTestIteratorProvider().iterator(), (LongPredicate) x -> x > 3, Boolean.TRUE),
				Arguments.of(getEmptyLongTestIteratorProvider().iterator(), (LongPredicate) x -> x < 3, Boolean.FALSE),
				Arguments.of(getEmptyLongTestIteratorProvider().iterator(), (LongPredicate) x -> x > -1, Boolean.FALSE)
				);
	}

	@ParameterizedTest
	@MethodSource("noneMatchTestDataProvider")
	void testNoneMatch(final AbstractLongFlow iterator, final LongPredicate predicate, final Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.noneMatch(predicate));
	}

	static Stream<Arguments> noneMatchTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getLongTestIteratorProvider().iterator(), (LongPredicate) x -> x < -1, Boolean.TRUE),
				Arguments.of(getLongTestIteratorProvider().iterator(), (LongPredicate) x -> x > 3, Boolean.FALSE),
				Arguments.of(getEmptyLongTestIteratorProvider().iterator(), (LongPredicate) x -> x < 3, Boolean.TRUE),
				Arguments.of(getEmptyLongTestIteratorProvider().iterator(), (LongPredicate) x -> x > -1, Boolean.TRUE)
				);
	}

	@ParameterizedTest
	@MethodSource("predicatePartitioningTestDataProvider")
	void testPredicatePartitioning(final AbstractLongFlow iterator, final LongPredicate predicate, final LongPredicatePartition expectedResult)
	{
		assertEquals(expectedResult, iterator.partition(predicate));
	}

	static Stream<Arguments> predicatePartitioningTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getLongTestIteratorProvider().iterator(), (LongPredicate) x -> x < -1, new LongPredicatePartition(new long[0], new long[] {0, 1, 2, 3, 4})),
				Arguments.of(getLongTestIteratorProvider().iterator(), (LongPredicate) x -> x > 2, new LongPredicatePartition(new long[] {3, 4}, new long[] {0, 1, 2})),
				Arguments.of(getLongTestIteratorProvider().iterator(), (LongPredicate) x -> x > -1, new LongPredicatePartition(new long[] {0, 1, 2, 3, 4}, new long[0])),
				Arguments.of(getEmptyLongTestIteratorProvider().iterator(), (LongPredicate) x -> x < -1, new LongPredicatePartition(new long[0], new long[0])),
				Arguments.of(getEmptyLongTestIteratorProvider().iterator(), (LongPredicate) x -> x > 3, new LongPredicatePartition(new long[0], new long[0])),
				Arguments.of(getEmptyLongTestIteratorProvider().iterator(), (LongPredicate) x -> x > -1, new LongPredicatePartition(new long[0], new long[0]))
				);
	}
}
