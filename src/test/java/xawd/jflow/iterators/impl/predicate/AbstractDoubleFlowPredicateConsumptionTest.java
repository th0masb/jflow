/**
 *
 */
package xawd.jflow.iterators.impl.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NoSuchElementException;
import java.util.OptionalInt;
import java.util.function.DoublePredicate;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import xawd.jflow.iterators.AbstractDoubleFlow;
import xawd.jflow.iterators.misc.DoublePredicatePartition;
import xawd.jflow.testutilities.IteratorExampleProvider;

/**
 * @author ThomasB
 */
class AbstractDoubleFlowPredicateConsumptionTest extends IteratorExampleProvider
{
	@ParameterizedTest
	@MethodSource("allEqualTestDataProvider")
	void testAllEqual(final AbstractDoubleFlow iterator, final Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.areAllEqual());
	}

	static Stream<Arguments> allEqualTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getAllEqualFlow(), Boolean.TRUE),
				Arguments.of(getDoubleTestIteratorProvider().iterator(), Boolean.FALSE),
				Arguments.of(getEmptyDoubleTestIteratorProvider().iterator(), Boolean.TRUE)
				);
	}

	private static AbstractDoubleFlow getAllEqualFlow()
	{
		return new AbstractDoubleFlow(OptionalInt.of(3))
		{
			int count = 0;
			@Override
			public boolean hasNext() {
				return count < 3;
			}
			@Override
			public double nextDouble() {
				if (count++ >= 3) {
					throw new NoSuchElementException();
				}
				return 0;
			}
			@Override
			public void skip() {
				nextDouble();
			}
		};
	}

	@ParameterizedTest
	@MethodSource("allMatchTestDataProvider")
	void testAllMatch(final AbstractDoubleFlow iterator, final DoublePredicate predicate, final Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.allMatch(predicate));
	}

	static Stream<Arguments> allMatchTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getDoubleTestIteratorProvider().iterator(), (DoublePredicate) x -> x < 3, Boolean.FALSE),
				Arguments.of(getDoubleTestIteratorProvider().iterator(), (DoublePredicate) x -> x > -1, Boolean.TRUE),
				Arguments.of(getEmptyDoubleTestIteratorProvider().iterator(), (DoublePredicate) x -> x < 3, Boolean.TRUE),
				Arguments.of(getEmptyDoubleTestIteratorProvider().iterator(), (DoublePredicate) x -> x > -1, Boolean.TRUE)
				);
	}

	@ParameterizedTest
	@MethodSource("anyMatchTestDataProvider")
	void testAnyMatch(final AbstractDoubleFlow iterator, final DoublePredicate predicate, final Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.anyMatch(predicate));
	}

	static Stream<Arguments> anyMatchTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getDoubleTestIteratorProvider().iterator(), (DoublePredicate) x -> x < -1, Boolean.FALSE),
				Arguments.of(getDoubleTestIteratorProvider().iterator(), (DoublePredicate) x -> x > 3, Boolean.TRUE),
				Arguments.of(getEmptyDoubleTestIteratorProvider().iterator(), (DoublePredicate) x -> x < 3, Boolean.FALSE),
				Arguments.of(getEmptyDoubleTestIteratorProvider().iterator(), (DoublePredicate) x -> x > -1, Boolean.FALSE)
				);
	}

	@ParameterizedTest
	@MethodSource("noneMatchTestDataProvider")
	void testNoneMatch(final AbstractDoubleFlow iterator, final DoublePredicate predicate, final Boolean expectedResult)
	{
		assertEquals(expectedResult.booleanValue(), iterator.noneMatch(predicate));
	}

	static Stream<Arguments> noneMatchTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getDoubleTestIteratorProvider().iterator(), (DoublePredicate) x -> x < -1, Boolean.TRUE),
				Arguments.of(getDoubleTestIteratorProvider().iterator(), (DoublePredicate) x -> x > 3, Boolean.FALSE),
				Arguments.of(getEmptyDoubleTestIteratorProvider().iterator(), (DoublePredicate) x -> x < 3, Boolean.TRUE),
				Arguments.of(getEmptyDoubleTestIteratorProvider().iterator(), (DoublePredicate) x -> x > -1, Boolean.TRUE)
				);
	}

	@ParameterizedTest
	@MethodSource("predicatePartitioningTestDataProvider")
	void testPredicatePartitioning(final AbstractDoubleFlow iterator, final DoublePredicate predicate, final DoublePredicatePartition expectedResult)
	{
		assertEquals(expectedResult, iterator.partition(predicate));
	}

	static Stream<Arguments> predicatePartitioningTestDataProvider()
	{
		return Stream.of(
				Arguments.of(getDoubleTestIteratorProvider().iterator(), (DoublePredicate) x -> x < -1, new DoublePredicatePartition(new double[0], new double[] {0, 1, 2, 3, 4})),
				Arguments.of(getDoubleTestIteratorProvider().iterator(), (DoublePredicate) x -> x > 2, new DoublePredicatePartition(new double[] {3, 4}, new double[] {0, 1, 2})),
				Arguments.of(getDoubleTestIteratorProvider().iterator(), (DoublePredicate) x -> x > -1, new DoublePredicatePartition(new double[] {0, 1, 2, 3, 4}, new double[0])),
				Arguments.of(getEmptyDoubleTestIteratorProvider().iterator(), (DoublePredicate) x -> x < -1, new DoublePredicatePartition(new double[0], new double[0])),
				Arguments.of(getEmptyDoubleTestIteratorProvider().iterator(), (DoublePredicate) x -> x > 3, new DoublePredicatePartition(new double[0], new double[0])),
				Arguments.of(getEmptyDoubleTestIteratorProvider().iterator(), (DoublePredicate) x -> x > -1, new DoublePredicatePartition(new double[0], new double[0]))
				);
	}
}
