/**
 * 
 */
package com.github.maumay.jflow.impl.map;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.testframework.AbstractIntAdapterTest;

/**
 * @author t
 *
 */
public class IntMapToDoubleTest extends AbstractIntAdapterTest<AbstractDoubleIterator>
{
	@Override
	protected List<IntCase<AbstractDoubleIterator>> getTestCases()
	{
		IntAdapter<AbstractDoubleIterator> adapter = iter -> iter.mapToDouble(x -> 2 * x);
		return list(new IntCase<>(list(), adapter, list()),
				new IntCase<>(list(1, 2, 3), adapter, list(2.0, 4.0, 6.0)));
	}
}
