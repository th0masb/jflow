/**
 * 
 */
package com.github.maumay.jflow.impl.append;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.test.AbstractDoubleAdapterTest;

/**
 * @author thomasb
 *
 */
public final class DoubleAppendTest extends AbstractDoubleAdapterTest<AbstractDoubleIterator>
{
	@Override
	protected List<Case<AbstractDoubleIterator>> getTestCases()
	{
		List<Double> empty = list(), populated = list(0.0);
		return list(new Case<>(empty, i -> i.append(iter(empty)), empty),
				new Case<>(empty, i -> i.append(iter(populated)), populated),
				new Case<>(populated, i -> i.append(iter(empty)), populated),
				new Case<>(populated, i -> i.append(iter(list(1.0))), list(0.0, 1.0)));
	}
}
