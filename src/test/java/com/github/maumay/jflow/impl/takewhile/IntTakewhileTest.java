/**
 * 
 */
package com.github.maumay.jflow.impl.takewhile;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.testframework.AbstractIntAdapterTest;

/**
 * @author t
 *
 */
public final class IntTakewhileTest extends AbstractIntAdapterTest<AbstractIntIterator>
{
	@Override
	protected List<Case<AbstractIntIterator>> getTestCases()
	{
		Adapter<AbstractIntIterator> adapter = i -> i.takeWhile(x -> x > 3);
		return list(new Case<>(list(), adapter, list()),
				new Case<>(list(3, 1, 5), adapter, list()),
				new Case<>(list(4, 4, 2, 4), adapter, list(4, 4)),
				new Case<>(list(4, 4, 1), adapter, list(4, 4)),
				new Case<>(list(4, 4, 4), adapter, list(4, 4, 4)));
	}
}
