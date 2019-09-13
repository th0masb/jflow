/**
 * 
 */
package com.github.maumay.jflow.impl.skip;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.test.AbstractLongAdapterTest;

/**
 * @author t
 *
 */
public final class LongSkipTest extends AbstractLongAdapterTest<AbstractLongIterator>
{
	@Override
	protected List<Case<AbstractLongIterator>> getTestCases()
	{
		List<Long> src = list(0L, 0L, 0L, 0L, 0L, 0L);
		return list(new Case<>(list(), i -> i.skip(2), list()),
				new Case<>(src, i -> i.skip(0), src),
				new Case<>(src, i -> i.skip(3), list(0L, 0L, 0L)),
				new Case<>(src, i -> i.skip(6), list()),
				new Case<>(src, i -> i.skip(7), list()));
	}
}
