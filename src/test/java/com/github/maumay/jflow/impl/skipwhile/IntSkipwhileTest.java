/**
 * 
 */
package com.github.maumay.jflow.impl.skipwhile;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.testframework.AbstractIntAdapterTest;

/**
 * @author t
 *
 */
public final class IntSkipwhileTest extends AbstractIntAdapterTest<AbstractIntIterator>
{
	@Override
	protected List<Case<AbstractIntIterator>> getTestCases()
	{
		Adapter<AbstractIntIterator> adapter = i -> i.skipWhile(x -> x > 2);
		return list(new Case<>(list(), adapter, list()),
				new Case<>(list(1, 3, 0), adapter, list(1, 3, 0)),
				new Case<>(list(3, 1, 2), adapter, list(1, 2)),
				new Case<>(list(3, 3, 1), adapter, list(1)),
				new Case<>(list(3, 3, 3), adapter, list()));
	}
}
