/**
 * 
 */
package com.github.maumay.jflow.impl.map;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.testframework.AbstractIntAdapterTest;

/**
 * @author t
 *
 */
public class IntMapToLongTest extends AbstractIntAdapterTest<AbstractLongIterator>
{
	@Override
	protected List<IntCase<AbstractLongIterator>> getTestCases()
	{
		IntAdapter<AbstractLongIterator> adapter = iter -> iter.mapToLong(x -> 2 * x);
		return list(new IntCase<>(list(), adapter, list()),
				new IntCase<>(list(1, 2, 3), adapter, list(2L, 4L, 6L)));
	}
}
