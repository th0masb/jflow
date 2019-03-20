/**
 * 
 */
package com.github.maumay.jflow.impl.append;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.testframework.AbstractDoubleAdapterTest;

/**
 * @author thomasb
 *
 */
public final class DoubleAppendTest extends AbstractDoubleAdapterTest<AbstractDoubleIterator>
{
	@Override
	protected List<DoubleCase<AbstractDoubleIterator>> getTestCases()
	{
		List<Double> empty = list(), populated = list(0.0);
		return list(new DoubleCase<>(empty, i -> i.append(iter(empty)), empty),
				new DoubleCase<>(empty, i -> i.append(iter(populated)), populated),
				new DoubleCase<>(populated, i -> i.append(iter(empty)), populated),
				new DoubleCase<>(populated, i -> i.append(iter(list(1.0))), list(0.0, 1.0)));
	}
}
