/**
 * 
 */
package com.github.maumay.jflow.iterators.impl.map;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.testutilities.AbstractIntAdapterTest;

/**
 * @author thomasb
 *
 */
public final class IntMapTest extends AbstractIntAdapterTest<AbstractIntIterator>
{
	@Override
	protected List<IntCase<AbstractIntIterator>> getIntTestCases()
	{
		IntAdapter<AbstractIntIterator> adapter = iter -> iter.map(n -> 2 * n);
		return list(new IntCase<>(list(), adapter, list()),
				new IntCase<>(list(1, 2, 3), adapter, list(2, 4, 6)));
	}

}
