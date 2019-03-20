/**
 * 
 */
package com.github.maumay.jflow.impl.map;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.testframework.AbstractDoubleAdapterTest;

/**
 * @author t
 *
 */
public class DoubleMapToIntTest extends AbstractDoubleAdapterTest<AbstractIntIterator>
{
	@Override
	protected List<DoubleCase<AbstractIntIterator>> getTestCases()
	{
		DoubleAdapter<AbstractIntIterator> adapter = iter -> iter
				.mapToInt(x -> (int) (2 * x));
		return list(new DoubleCase<>(list(), adapter, list()),
				new DoubleCase<>(list(1.0, 2.0), adapter, list(2, 4)));
	}
}
