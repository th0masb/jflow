/**
 * 
 */
package com.github.maumay.jflow.impl.skip;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.testutilities.AbstractLongAdapterTest;

/**
 * @author t
 *
 */
public final class LongSkipTest extends AbstractLongAdapterTest<AbstractLongIterator>
{
	@Override
	protected List<LongCase<AbstractLongIterator>> getTestCases()
	{
		List<Long> src = list(0L, 0L, 0L, 0L, 0L, 0L);
		return list(new LongCase<>(list(), i -> i.skip(2), list()),
				new LongCase<>(src, i -> i.skip(0), src),
				new LongCase<>(src, i -> i.skip(3), list(0L, 0L, 0L)),
				new LongCase<>(src, i -> i.skip(6), list()),
				new LongCase<>(src, i -> i.skip(7), list()));
	}
}
