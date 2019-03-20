/**
 * 
 */
package com.github.maumay.jflow.impl.skipwhile;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.testutilities.AbstractIntAdapterTest;

/**
 * @author t
 *
 */
public final class IntSkipwhileTest extends AbstractIntAdapterTest<AbstractIntIterator>
{
	@Override
	protected List<IntCase<AbstractIntIterator>> getTestCases()
	{
		IntAdapter<AbstractIntIterator> adapter = i -> i.skipWhile(x -> x > 2);
		return list(new IntCase<>(list(), adapter, list()),
				new IntCase<>(list(1, 3, 0), adapter, list(1, 3, 0)),
				new IntCase<>(list(3, 1, 2), adapter, list(1, 2)),
				new IntCase<>(list(3, 3, 1), adapter, list(1)),
				new IntCase<>(list(3, 3, 3), adapter, list()));
	}
}
