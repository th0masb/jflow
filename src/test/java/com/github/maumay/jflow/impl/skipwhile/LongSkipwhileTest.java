/**
 * 
 */
package com.github.maumay.jflow.impl.skipwhile;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.testframework.AbstractLongAdapterTest;

/**
 * @author t
 *
 */
public final class LongSkipwhileTest extends AbstractLongAdapterTest<AbstractLongIterator>
{
	@Override
	protected List<Case<AbstractLongIterator>> getTestCases()
	{
		Adapter<AbstractLongIterator> adapter = i -> i.skipWhile(x -> x > 2);
		return list(new Case<>(list(), adapter, list()),
				new Case<>(list(1L, 3L, 0L), adapter, list(1L, 3L, 0L)),
				new Case<>(list(3L, 1L, 2L), adapter, list(1L, 2L)),
				new Case<>(list(3L, 3L, 1L), adapter, list(1L)),
				new Case<>(list(3L, 3L, 3L), adapter, list()));
	}
}
