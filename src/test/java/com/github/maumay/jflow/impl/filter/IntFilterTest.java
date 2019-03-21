/**
 * 
 */
package com.github.maumay.jflow.impl.filter;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.test.AbstractIntAdapterTest;

/**
 * @author t
 *
 */
public final class IntFilterTest extends AbstractIntAdapterTest<AbstractIntIterator>
{
	@Override
	protected List<Case<AbstractIntIterator>> getTestCases()
	{
		Adapter<AbstractIntIterator> adapter = iter -> iter.filter(x -> x > 1);
		return list(new Case<>(list(), adapter, list()),
				new Case<>(list(0, 2), adapter, list(2)),
				new Case<>(list(0, 1), adapter, list()),
				new Case<>(list(2, 3), adapter, list(2, 3)));
	}
}
