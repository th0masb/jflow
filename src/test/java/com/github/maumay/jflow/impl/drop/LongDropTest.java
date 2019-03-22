/**
 * 
 */
package com.github.maumay.jflow.impl.drop;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.test.AbstractLongAdapterTest;

/**
 * @author t
 *
 */
public final class LongDropTest extends AbstractLongAdapterTest<AbstractLongIterator>
{
	@Override
	protected List<Case<AbstractLongIterator>> getTestCases()
	{
		List<Long> src = list(0L, 0L, 0L, 0L, 0L, 0L);
		return list(new Case<>(list(), i -> i.drop(2), list()),
				new Case<>(src, i -> i.drop(0), src),
				new Case<>(src, i -> i.drop(3), list(0L, 0L, 0L)),
				new Case<>(src, i -> i.drop(6), list()),
				new Case<>(src, i -> i.drop(7), list()));
	}
}
