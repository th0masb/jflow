/**
 *
 */
package xawd.jflow.iterators.impl.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.function.IntPredicate;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import xawd.jflow.iterators.AbstractIntFlow;
import xawd.jflow.iterators.misc.IntPredicatePartition;
import xawd.jflow.testutilities.IteratorExampleProvider;

/**
 * @author ThomasB
 */
class AbstractIntFlowPredicateConsumptionTest extends IteratorExampleProvider
{
	@ParameterizedTest
	@MethodSource("allEqualTestDataProvider")
	void testAllEqual(final AbstractIntFlow iterator, final Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.areAllEqual());
	}

	static Stream<Arguments> allEqualTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getAllEqualFlow(), Boolean.TRUE),
				Arguments.of(getIntTestIteratorProvider().iterator(), Boolean.FALSE),
				Arguments.of(getEmptyIntTestIteratorProvider().iterator(), Boolean.TRUE)
				);
	}

	private static AbstractIntFlow getAllEqualFlow()
	{
		return new AbstractIntFlow(OptionalInt.of(3))
		{
			int count = 0;
			@Override
			public boolean hasNext() {
				return count < 3;
			}
			@Override
			public int nextInt() {
				if (count++ >= 3) {
					throw new NoSuchElementException();
				}
				return 0;
			}
			@Override
			public void skip() {
				nextInt();
			}
		};
	}

	@ParameterizedTest
	@MethodSource("allMatchTestDataProvider")
	void testAllMatch(final AbstractIntFlow iterator, final IntPredicate predicate, final Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.allMatch(predicate));
	}

	static Stream<Arguments> allMatchTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getIntTestIteratorProvider().iterator(), (IntPredicate) x -> x < 3, Boolean.FALSE),
				Arguments.of(getIntTestIteratorProvider().iterator(), (IntPredicate) x -> x > -1, Boolean.TRUE),
				Arguments.of(getEmptyIntTestIteratorProvider().iterator(), (IntPredicate) x -> x < 3, Boolean.TRUE),
				Arguments.of(getEmptyIntTestIteratorProvider().iterator(), (IntPredicate) x -> x > -1, Boolean.TRUE)
				);
	}

	@ParameterizedTest
	@MethodSource("anyMatchTestDataProvider")
	void testAnyMatch(final AbstractIntFlow iterator, final IntPredicate predicate, final Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.anyMatch(predicate));
	}

	static Stream<Arguments> anyMatchTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getIntTestIteratorProvider().iterator(), (IntPredicate) x -> x < -1, Boolean.FALSE),
				Arguments.of(getIntTestIteratorProvider().iterator(), (IntPredicate) x -> x > 3, Boolean.TRUE),
				Arguments.of(getEmptyIntTestIteratorProvider().iterator(), (IntPredicate) x -> x < 3, Boolean.FALSE),
				Arguments.of(getEmptyIntTestIteratorProvider().iterator(), (IntPredicate) x -> x > -1, Boolean.FALSE)
				);
	}

	@ParameterizedTest
	@MethodSource("noneMatchTestDataProvider")
	void testNoneMatch(final AbstractIntFlow iterator, final IntPredicate predicate, final Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.noneMatch(predicate));
	}

	static Stream<Arguments> noneMatchTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getIntTestIteratorProvider().iterator(), (IntPredicate) x -> x < -1, Boolean.TRUE),
				Arguments.of(getIntTestIteratorProvider().iterator(), (IntPredicate) x -> x > 3, Boolean.FALSE),
				Arguments.of(getEmptyIntTestIteratorProvider().iterator(), (IntPredicate) x -> x < 3, Boolean.TRUE),
				Arguments.of(getEmptyIntTestIteratorProvider().iterator(), (IntPredicate) x -> x > -1, Boolean.TRUE)
				);
	}

	@ParameterizedTest
	@MethodSource("predicatePartitioningTestDataProvider")
	void testPredicatePartitioning(final AbstractIntFlow iterator, final IntPredicate predicate, final IntPredicatePartition expectedResult)
	{
		assertEquals(expectedResult, iterator.partition(predicate));
	}

	static Stream<Arguments> predicatePartitioningTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getIntTestIteratorProvider().iterator(), (IntPredicate) x -> x < -1, new IntPredicatePartition(new int[0], new int[] {0, 1, 2, 3, 4})),
				Arguments.of(getIntTestIteratorProvider().iterator(), (IntPredicate) x -> x > 2, new IntPredicatePartition(new int[] {3, 4}, new int[] {0, 1, 2})),
				Arguments.of(getIntTestIteratorProvider().iterator(), (IntPredicate) x -> x > -1, new IntPredicatePartition(new int[] {0, 1, 2, 3, 4}, new int[0])),
				Arguments.of(getEmptyIntTestIteratorProvider().iterator(), (IntPredicate) x -> x < -1, new IntPredicatePartition(new int[0], new int[0])),
				Arguments.of(getEmptyIntTestIteratorProvider().iterator(), (IntPredicate) x -> x > 3, new IntPredicatePartition(new int[0], new int[0])),
				Arguments.of(getEmptyIntTestIteratorProvider().iterator(), (IntPredicate) x -> x > -1, new IntPredicatePartition(new int[0], new int[0]))
				);
	}
}
