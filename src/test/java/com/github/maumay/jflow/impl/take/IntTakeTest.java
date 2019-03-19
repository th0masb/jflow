/**
 * 
 */
package com.github.maumay.jflow.impl.take;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.testutilities.AbstractIntAdapterTest;

/**
 * @author t
 *
 */
public final class IntTakeTest extends AbstractIntAdapterTest<AbstractIntIterator>
{
	@Override
	protected List<IntCase<AbstractIntIterator>> getTestCases()
	{
		List<Integer> src = list(0, 0, 0, 0, 0, 0);
		return list(new IntCase<>(list(), i -> i.take(0), list()),
				new IntCase<>(list(), i -> i.take(3), list()),
				new IntCase<>(src, i -> i.take(0), list()),
				new IntCase<>(src, i -> i.take(3), list(0, 0, 0)),
				new IntCase<>(src, i -> i.take(6), src));
	}
}
