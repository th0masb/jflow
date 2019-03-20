/**
 * 
 */
package com.github.maumay.jflow.impl.map;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.testframework.AbstractLongAdapterTest;

/**
 * @author t
 *
 */
public class LongMapToDoubleTest extends AbstractLongAdapterTest<AbstractDoubleIterator>
{
	@Override
	protected List<LongCase<AbstractDoubleIterator>> getTestCases()
	{
		LongAdapter<AbstractDoubleIterator> adapter = iter -> iter
				.mapToDouble(x -> 2 * x);
		return list(new LongCase<>(list(), adapter, list()),
				new LongCase<>(list(1L, 2L, 3L), adapter, list(2.0, 4.0, 6.0)));
	}
}
