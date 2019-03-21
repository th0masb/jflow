/**
 * 
 */
package com.github.maumay.jflow.impl.takewhile;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.test.AbstractLongAdapterTest;

/**
 * @author t
 *
 */
public final class LongTakewhileTest extends AbstractLongAdapterTest<AbstractLongIterator>
{
	@Override
	protected List<Case<AbstractLongIterator>> getTestCases()
	{
		Adapter<AbstractLongIterator> adapter = i -> i.takeWhile(x -> x > 3);
		return list(new Case<>(list(), adapter, list()),
				new Case<>(list(3L, 1L, 5L), adapter, list()),
				new Case<>(list(4L, 4L, 2L, 4L), adapter, list(4L, 4L)),
				new Case<>(list(4L, 4L, 1L), adapter, list(4L, 4L)),
				new Case<>(list(4L, 4L, 4L), adapter, list(4L, 4L, 4L)));
	}
}
