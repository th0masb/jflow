/**
 * 
 */
package com.github.maumay.jflow.impl.append;

import java.util.Iterator;
import java.util.List;
import java.util.PrimitiveIterator;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.testutilities.AbstractDoubleAdapterTest;

/**
 * @author thomasb
 *
 */
public final class DoubleAppendTest extends AbstractDoubleAdapterTest<AbstractDoubleIterator>
{
	@Override
	protected List<DoubleCase<AbstractDoubleIterator>> getTestCases()
	{
		List<Double> empty = list(), populated = list(0.0);
		return list(new DoubleCase<>(empty, i -> i.append(unbox(empty.iterator())), empty),
				new DoubleCase<>(empty, i -> i.append(unbox(populated.iterator())), populated),
				new DoubleCase<>(populated, i -> i.append(unbox(empty.iterator())), populated),
				new DoubleCase<>(populated, i -> i.append(unbox(populated.iterator())),
						list(0.0, 0.0)));
	}

	private PrimitiveIterator.OfDouble unbox(Iterator<Double> source)
	{
		return new PrimitiveIterator.OfDouble() {
			@Override
			public boolean hasNext()
			{
				return source.hasNext();
			}

			@Override
			public double nextDouble()
			{
				return source.next();
			}
		};
	}
}
