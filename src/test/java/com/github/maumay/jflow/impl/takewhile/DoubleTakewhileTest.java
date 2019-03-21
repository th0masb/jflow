/**
 * 
 */
package com.github.maumay.jflow.impl.takewhile;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.testframework.AbstractDoubleAdapterTest;

/**
 * @author t
 *
 */
public final class DoubleTakewhileTest
		extends AbstractDoubleAdapterTest<AbstractDoubleIterator>
{
	@Override
	protected List<Case<AbstractDoubleIterator>> getTestCases()
	{
		Adapter<AbstractDoubleIterator> adapter = i -> i.takeWhile(x -> x > 3);
		return list(new Case<>(list(), adapter, list()),
				new Case<>(list(3.0, 1.0, 5.0), adapter, list()),
				new Case<>(list(4.0, 4.0, 2.0, 4.0), adapter, list(4.0, 4.0)),
				new Case<>(list(4.0, 4.0, 1.0), adapter, list(4.0, 4.0)),
				new Case<>(list(4.0, 4.0, 4.0), adapter, list(4.0, 4.0, 4.0)));
	}
}
