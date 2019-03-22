/**
 * 
 */
package com.github.maumay.jflow.impl.dropwhile;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.test.AbstractObjectAdapterTest;

/**
 * @author t
 *
 */
public final class ObjectDropwhileTest
		extends AbstractObjectAdapterTest<Double, AbstractRichIterator<Double>>
{
	@Override
	protected List<Case<Double, AbstractRichIterator<Double>>> getTestCases()
	{
		Adapter<Double, AbstractRichIterator<Double>> adapter = i -> i
				.dropWhile(x -> x > 2);
		return list(new Case<>(list(), adapter, list()),
				new Case<>(list(1.0, 3.0, 0.0), adapter, list(1.0, 3.0, 0.0)),
				new Case<>(list(3.0, 1.0, 2.0), adapter, list(1.0, 2.0)),
				new Case<>(list(3.0, 3.0, 1.0), adapter, list(1.0)),
				new Case<>(list(3.0, 3.0, 3.0), adapter, list()));
	}
}
