/**
 *
 */
package com.github.maumay.jflow.iterators.impl.map;

import java.util.function.IntToDoubleFunction;
import java.util.function.LongToDoubleFunction;
import java.util.function.ToDoubleFunction;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.impl2.AbstractDoubleIterator;
import com.github.maumay.jflow.testutilities.AbstractEnhancedIterable;
import com.github.maumay.jflow.testutilities.AbstractIterableDoubles;
import com.github.maumay.jflow.testutilities.AbstractIterableInts;
import com.github.maumay.jflow.testutilities.AbstractIterableLongs;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;

/**
 * @author t
 */
public class MapToDoubleTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	public void testAbstractFlowMapToDouble()
	{
		AbstractEnhancedIterable<String> populated = getObjectTestIteratorProvider();
		AbstractEnhancedIterable<String> empty = getEmptyObjectTestIteratorProvider();
		ToDoubleFunction<String> mapper = Double::parseDouble;

		assertDoubleIteratorAsExpected(new double[] { 0, 1, 2, 3, 4 },
				createMapToDoubleIteratorProviderFrom(populated, mapper));
		assertDoubleIteratorAsExpected(new double[] {},
				createMapToDoubleIteratorProviderFrom(empty, mapper));
	}

	private <T> AbstractIterableDoubles createMapToDoubleIteratorProviderFrom(
			AbstractEnhancedIterable<T> src, ToDoubleFunction<T> mapper)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleIterator iter()
			{
				return src.iterator().mapToDouble(mapper);
			}
		};
	}

	@Test
	public void testAbstractLongFlowMapToDouble()
	{
		AbstractIterableLongs populated = getLongTestIteratorProvider();
		AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
		LongToDoubleFunction mapper = x -> x + 3.2;

		assertDoubleIteratorAsExpected(new double[] { 3.2, 4.2, 5.2, 6.2, 7.2 },
				createLongMapToDoubleIteratorProviderFrom(populated, mapper));
		assertDoubleIteratorAsExpected(new double[] {},
				createLongMapToDoubleIteratorProviderFrom(empty, mapper));
	}

	private AbstractIterableDoubles createLongMapToDoubleIteratorProviderFrom(
			AbstractIterableLongs src, LongToDoubleFunction mapper)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleIterator iter()
			{
				return src.iter().mapToDouble(mapper);
			}
		};
	}

	@Test
	public void testAbstractIntFlowMapToDouble()
	{
		AbstractIterableInts populated = getIntTestIteratorProvider();
		AbstractIterableInts empty = getEmptyIntTestIteratorProvider();
		IntToDoubleFunction mapper = x -> x + 1.6;

		assertDoubleIteratorAsExpected(new double[] { 1.6, 2.6, 3.6, 4.6, 5.6 },
				createIntMapToDoubleIteratorProviderFrom(populated, mapper));
		assertDoubleIteratorAsExpected(new double[] {},
				createIntMapToDoubleIteratorProviderFrom(empty, mapper));
	}

	private AbstractIterableDoubles createIntMapToDoubleIteratorProviderFrom(
			AbstractIterableInts src, IntToDoubleFunction mapper)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleIterator iter()
			{
				return src.iter().mapToDouble(mapper);
			}
		};
	}
}
