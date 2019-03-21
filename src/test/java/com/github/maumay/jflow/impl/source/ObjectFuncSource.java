/**
 * 
 */
package com.github.maumay.jflow.impl.source;

import java.util.List;
import java.util.function.IntFunction;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.impl.FunctionSource;
import com.github.maumay.jflow.test.AbstractSourceTest;

/**
 * @author thomasb
 *
 */
public final class ObjectFuncSource extends AbstractSourceTest<AbstractRichIterator<Double>>
{
	@Override
	protected List<Case<AbstractRichIterator<Double>>> getTestCases()
	{
		IntFunction<Double> src = i -> (double) i;
		return list(new Case<>(() -> new FunctionSource.OfObject<>(src, 0), list()),
				new Case<>(() -> new FunctionSource.OfObject<>(src, 4), list(0.0, 1.0, 2.0, 3.0)));
	}
}
