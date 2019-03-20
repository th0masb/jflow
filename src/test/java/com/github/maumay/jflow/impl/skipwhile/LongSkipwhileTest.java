/**
 * 
 */
package com.github.maumay.jflow.impl.skipwhile;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.testutilities.AbstractLongAdapterTest;

/**
 * @author t
 *
 */
public final class LongSkipwhileTest extends AbstractLongAdapterTest<AbstractLongIterator>
{
	@Override
	protected List<LongCase<AbstractLongIterator>> getTestCases()
	{
		LongAdapter<AbstractLongIterator> adapter = i -> i.skipWhile(x -> x > 2);
		return list(new LongCase<>(list(), adapter, list()),
				new LongCase<>(list(1L, 3L, 0L), adapter, list(1L, 3L, 0L)),
				new LongCase<>(list(3L, 1L, 2L), adapter, list(1L, 2L)),
				new LongCase<>(list(3L, 3L, 1L), adapter, list(1L)),
				new LongCase<>(list(3L, 3L, 3L), adapter, list()));
	}
}
