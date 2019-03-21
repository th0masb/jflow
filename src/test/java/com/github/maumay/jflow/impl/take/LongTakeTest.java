/**
 * 
 */
package com.github.maumay.jflow.impl.take;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.testframework.AbstractLongAdapterTest;

/**
 * @author t
 *
 */
public final class LongTakeTest extends AbstractLongAdapterTest<AbstractLongIterator>
{
	@Override
	protected List<Case<AbstractLongIterator>> getTestCases()
	{
		List<Long> src = list(0L, 0L, 0L, 0L, 0L, 0L);
		return list(new Case<>(list(), i -> i.take(0), list()),
				new Case<>(list(), i -> i.take(3), list()),
				new Case<>(src, i -> i.take(0), list()),
				new Case<>(src, i -> i.take(3), list(0L, 0L, 0L)),
				new Case<>(src, i -> i.take(6), src));
	}
}
