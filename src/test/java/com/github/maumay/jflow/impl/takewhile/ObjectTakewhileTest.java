/**
 * 
 */
package com.github.maumay.jflow.impl.takewhile;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.test.AbstractObjectAdapterTest;

/**
 * @author t
 *
 */
public final class ObjectTakewhileTest
		extends AbstractObjectAdapterTest<Double, AbstractRichIterator<Double>>
{
	@Override
	protected List<Case<Double, AbstractRichIterator<Double>>> getTestCases()
	{
		Adapter<Double, AbstractRichIterator<Double>> adapter = i -> i
				.takeWhile(x -> x > 3);
		return list(new Case<>(list(), adapter, list()),
				new Case<>(list(3.0, 1.0, 5.0), adapter, list()),
				new Case<>(list(4.0, 4.0, 2.0, 4.0), adapter, list(4.0, 4.0)),
				new Case<>(list(4.0, 4.0, 1.0), adapter, list(4.0, 4.0)),
				new Case<>(list(4.0, 4.0, 4.0), adapter, list(4.0, 4.0, 4.0)));
	}
}
