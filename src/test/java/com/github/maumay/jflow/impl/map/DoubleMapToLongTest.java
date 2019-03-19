/**
 * 
 */
package com.github.maumay.jflow.impl.map;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.testutilities.AbstractDoubleAdapterTest;

/**
 * @author t
 *
 */
public class DoubleMapToLongTest extends AbstractDoubleAdapterTest<AbstractLongIterator>
{
	@Override
	protected List<DoubleCase<AbstractLongIterator>> getTestCases()
	{
		DoubleAdapter<AbstractLongIterator> adapter = iter -> iter
				.mapToLong(x -> (int) (2 * x));
		return list(new DoubleCase<>(list(), adapter, list()),
				new DoubleCase<>(list(1.0, 2.0), adapter, list(2L, 4L)));
	}
}
