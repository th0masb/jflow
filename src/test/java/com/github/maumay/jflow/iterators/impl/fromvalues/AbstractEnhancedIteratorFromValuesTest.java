package com.github.maumay.jflow.iterators.impl.fromvalues;

import static java.util.Arrays.asList;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.impl.ArraySource;
import com.github.maumay.jflow.testframework.AbstractRichIterable;
import com.github.maumay.jflow.testframework.FiniteIteratorTest;

/**
 * @author ThomasB
 */
class AbstractEnhancedIteratorFromValuesTest implements FiniteIteratorTest
{
	@ParameterizedTest
	@MethodSource("creationTestDataProvider")
	void testCreationAsExpected(String[] source)
	{
		assertIteratorAsExpected(asList(source),
				getCreationFromValuesIteratorProvider(source));
	}

	static Stream<Arguments> creationTestDataProvider()
	{
		return Stream.of(Arguments.of(new Object[] { new String[0] }),
				Arguments.of(new Object[] { new String[] { "1", "1" } }));
	}

	<E> AbstractRichIterable<E> getCreationFromValuesIteratorProvider(E[] source)
	{
		return new AbstractRichIterable<E>() {
			@Override
			public AbstractRichIterator<E> iter()
			{
				return new ArraySource.OfObject<>(source);
			}
		};
	}
}
