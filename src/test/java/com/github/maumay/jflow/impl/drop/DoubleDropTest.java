/**
 * 
 */
package com.github.maumay.jflow.impl.drop;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.test.AbstractDoubleAdapterTest;

/**
 * @author t
 *
 */
public final class DoubleDropTest
		extends AbstractDoubleAdapterTest<AbstractDoubleIterator>
{
	@Override
	protected List<Case<AbstractDoubleIterator>> getTestCases()
	{
		List<Double> src = list(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		return list(new Case<>(list(), i -> i.drop(2), list()),
				new Case<>(src, i -> i.drop(0), src),
				new Case<>(src, i -> i.drop(3), list(0.0, 0.0, 0.0)),
				new Case<>(src, i -> i.drop(6), list()),
				new Case<>(src, i -> i.drop(7), list()));
	}
}
