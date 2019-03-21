/**
 * 
 */
package com.github.maumay.jflow.impl.source;

import java.util.List;
import java.util.function.IntToLongFunction;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.impl.FunctionSource;
import com.github.maumay.jflow.test.AbstractSourceTest;

/**
 * @author thomasb
 *
 */
public final class LongFuncSource extends AbstractSourceTest<AbstractLongIterator>
{
	@Override
	protected List<Case<AbstractLongIterator>> getTestCases()
	{
		IntToLongFunction src = i -> i;
		return list(new Case<>(() -> new FunctionSource.OfLong(src, 0), list()),
				new Case<>(() -> new FunctionSource.OfLong(src, 4), list(0L, 1L, 2L, 3L)));
	}
}
