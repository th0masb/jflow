/**
 * 
 */
package com.github.maumay.jflow.impl.skip;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.testframework.AbstractIntAdapterTest;

/**
 * @author t
 *
 */
public final class IntSkipTest extends AbstractIntAdapterTest<AbstractIntIterator>
{
	@Override
	protected List<IntCase<AbstractIntIterator>> getTestCases()
	{
		List<Integer> src = list(0, 0, 0, 0, 0, 0);
		return list(new IntCase<>(list(), i -> i.skip(2), list()),
				new IntCase<>(src, i -> i.skip(0), src),
				new IntCase<>(src, i -> i.skip(3), list(0, 0, 0)),
				new IntCase<>(src, i -> i.skip(6), list()),
				new IntCase<>(src, i -> i.skip(7), list()));
	}
}
