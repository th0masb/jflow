package com.github.maumay.jflow.iterators.impl.fromvalues;

import static java.util.Arrays.asList;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.maumay.jflow.iterators.AbstractEnhancedIterator;
import com.github.maumay.jflow.iterators.impl.SourceIterator;
import com.github.maumay.jflow.testutilities.AbstractEnhancedIterable;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractEnhancedIteratorFromValuesTest implements IteratorTest
{
	@ParameterizedTest
	@MethodSource("creationTestDataProvider")
	void testCreationAsExpected(final String[] source)
	{
		assertObjectIteratorAsExpected(asList(source),
				getCreationFromValuesIteratorProvider(source));
	}

	static Stream<Arguments> creationTestDataProvider()
	{
		return Stream.of(Arguments.of(new Object[] { new String[0] }),
				Arguments.of(new Object[] { new String[] { "1", "1" } }));
	}

	<E> AbstractEnhancedIterable<E> getCreationFromValuesIteratorProvider(final E[] source)
	{
		return new AbstractEnhancedIterable<E>() {
			@Override
			public AbstractEnhancedIterator<E> iter()
			{
				return new SourceIterator.OfObject<>(source);
			}
		};
	}
}
