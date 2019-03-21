/**
 * 
 */
package com.github.maumay.jflow.impl.filter;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.test.AbstractLongAdapterTest;

/**
 * @author t
 *
 */
public final class LongFilterTest extends AbstractLongAdapterTest<AbstractLongIterator>
{
	@Override
	protected List<Case<AbstractLongIterator>> getTestCases()
	{
		Adapter<AbstractLongIterator> adapter = iter -> iter.filter(x -> x > 1);
		return list(new Case<>(list(), adapter, list()),
				new Case<>(list(0L, 2L), adapter, list(2L)),
				new Case<>(list(0L, 1L), adapter, list()),
				new Case<>(list(2L, 3L), adapter, list(2L, 3L)));
	}
}
