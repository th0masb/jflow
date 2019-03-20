/**
 * 
 */
package com.github.maumay.jflow.impl.takewhile;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.testframework.AbstractLongAdapterTest;

/**
 * @author t
 *
 */
public final class LongTakewhileTest extends AbstractLongAdapterTest<AbstractLongIterator>
{
	@Override
	protected List<LongCase<AbstractLongIterator>> getTestCases()
	{
		LongAdapter<AbstractLongIterator> adapter = i -> i.takeWhile(x -> x > 3);
		return list(new LongCase<>(list(), adapter, list()),
				new LongCase<>(list(3L, 1L, 5L), adapter, list()),
				new LongCase<>(list(4L, 4L, 2L, 4L), adapter, list(4L, 4L)),
				new LongCase<>(list(4L, 4L, 1L), adapter, list(4L, 4L)),
				new LongCase<>(list(4L, 4L, 4L), adapter, list(4L, 4L, 4L)));
	}
}
