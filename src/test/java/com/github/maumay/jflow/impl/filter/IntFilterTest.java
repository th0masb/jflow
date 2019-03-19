/**
 * 
 */
package com.github.maumay.jflow.impl.filter;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.testutilities.AbstractIntAdapterTest;

/**
 * @author t
 *
 */
public final class IntFilterTest extends AbstractIntAdapterTest<AbstractIntIterator>
{
	@Override
	protected List<IntCase<AbstractIntIterator>> getTestCases()
	{
		IntAdapter<AbstractIntIterator> adapter = iter -> iter.filter(x -> x > 1);
		return list(new IntCase<>(list(), adapter, list()),
				new IntCase<>(list(0, 2), adapter, list(2)),
				new IntCase<>(list(0, 1), adapter, list()),
				new IntCase<>(list(2, 3), adapter, list(2, 3)));
	}
}
