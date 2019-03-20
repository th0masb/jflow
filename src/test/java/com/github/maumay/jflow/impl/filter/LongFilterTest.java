/**
 * 
 */
package com.github.maumay.jflow.impl.filter;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.testframework.AbstractLongAdapterTest;

/**
 * @author t
 *
 */
public final class LongFilterTest extends AbstractLongAdapterTest<AbstractLongIterator>
{
	@Override
	protected List<LongCase<AbstractLongIterator>> getTestCases()
	{
		LongAdapter<AbstractLongIterator> adapter = iter -> iter.filter(x -> x > 1);
		return list(new LongCase<>(list(), adapter, list()),
				new LongCase<>(list(0L, 2L), adapter, list(2L)),
				new LongCase<>(list(0L, 1L), adapter, list()),
				new LongCase<>(list(2L, 3L), adapter, list(2L, 3L)));
	}
}
