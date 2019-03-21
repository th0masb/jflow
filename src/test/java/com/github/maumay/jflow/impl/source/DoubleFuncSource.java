/**
 * 
 */
package com.github.maumay.jflow.impl.source;

import java.util.List;
import java.util.function.IntToDoubleFunction;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.impl.FunctionSource;
import com.github.maumay.jflow.test.AbstractSourceTest;

/**
 * @author thomasb
 *
 */
public final class DoubleFuncSource extends AbstractSourceTest<AbstractDoubleIterator>
{
	@Override
	protected List<Case<AbstractDoubleIterator>> getTestCases()
	{
		IntToDoubleFunction src = i -> i;
		return list(new Case<>(() -> new FunctionSource.OfDouble(src, 0), list()),
				new Case<>(() -> new FunctionSource.OfDouble(src, 4), list(0.0, 1.0, 2.0, 3.0)));
	}
}
