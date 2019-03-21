/**
 * 
 */
package com.github.maumay.jflow.impl.source;

import java.util.List;
import java.util.function.IntUnaryOperator;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.impl.FunctionSource;
import com.github.maumay.jflow.test.AbstractSourceTest;

/**
 * @author thomasb
 *
 */
public final class IntFuncSource extends AbstractSourceTest<AbstractIntIterator>
{
	@Override
	protected List<Case<AbstractIntIterator>> getTestCases()
	{
		IntUnaryOperator src = i -> i;
		return list(new Case<>(() -> new FunctionSource.OfInt(src, 0), list()),
				new Case<>(() -> new FunctionSource.OfInt(src, 4), list(0, 1, 2, 3)));
	}
}
