/**
 * 
 */
package com.github.maumay.jflow.impl.map;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.test.AbstractDoubleAdapterTest;

/**
 * @author t
 *
 */
public class DoubleMapToIntTest extends AbstractDoubleAdapterTest<AbstractIntIterator>
{
	@Override
	protected List<Case<AbstractIntIterator>> getTestCases()
	{
		Adapter<AbstractIntIterator> adapter = iter -> iter
				.mapToInt(x -> (int) (2 * x));
		return list(new Case<>(list(), adapter, list()),
				new Case<>(list(1.0, 2.0), adapter, list(2, 4)));
	}
}
