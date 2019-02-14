package maumay.jflow.iterators.impl.reversefromvalues;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import maumay.jflow.iterators.AbstractEnhancedIterator;
import maumay.jflow.iterators.impl.ReversedSourceIterator;
import maumay.jflow.testutilities.AbstractEnhancedIterable;
import maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractEnhancedIteratorReverseFromValuesTest implements IteratorTest
{
	@ParameterizedTest
	@MethodSource("creationTestDataProvider")
	void testCreationAsExpected(final List<String> source)
	{
		assertObjectIteratorAsExpected(reverse(source),
				getReverseFromValuesIteratorProvider(source));
	}

	static Stream<Arguments> creationTestDataProvider()
	{
		return Stream.of(Arguments.of(asList()), Arguments.of(asList("1", "2")));
	}

	<E> List<E> reverse(final List<E> toReverse)
	{
		final List<E> reversed = new ArrayList<>(toReverse.size());
		for (int i = toReverse.size() - 1; i > -1; i--) {
			reversed.add(toReverse.get(i));
		}
		return reversed;
	}

	<E> AbstractEnhancedIterable<E> getReverseFromValuesIteratorProvider(final List<E> source)
	{
		return new AbstractEnhancedIterable<E>() {
			@Override
			public AbstractEnhancedIterator<E> iter()
			{
				return new ReversedSourceIterator.OfObject<>(source);
			}
		};
	}
}
