/**
 * 
 */
package com.github.maumay.jflow.impl.slice;

import static java.lang.Math.max;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.testframework.AbstractLongAdapterTest;

/**
 * @author thomasb
 *
 */
public final class LongSliceTest extends AbstractLongAdapterTest<AbstractLongIterator>
{
	@Override
	protected List<LongCase<AbstractLongIterator>> getTestCases()
	{
		List<Long> src = list(0L, 1L, 2L, 3L, 4L, 5L);
		return list(new LongCase<>(list(), i -> i.slice(n -> n), list()),
				new LongCase<>(src, i -> i.slice(n -> n), src),
				new LongCase<>(src, i -> i.slice(n -> n + 1), list(1L, 2L, 3L, 4L, 5L)),
				new LongCase<>(src, i -> i.slice(n -> 2 * n), list(0L, 2L, 4L)),
				new LongCase<>(src, i -> i.slice(n -> max(0, 3 * n - 1)), list(0L, 2L, 5L)));
	}
}
