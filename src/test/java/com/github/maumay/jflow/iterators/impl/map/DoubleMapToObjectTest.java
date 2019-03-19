/**
 * 
 */
package com.github.maumay.jflow.iterators.impl.map;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.testutilities.AbstractDoubleAdapterTest;

/**
 * @author t
 *
 */
public final class DoubleMapToObjectTest
		extends AbstractDoubleAdapterTest<AbstractRichIterator<String>>
{
	@Override
	protected List<DoubleCase<AbstractRichIterator<String>>> getTestCases()
	{
		DoubleAdapter<AbstractRichIterator<String>> adapter = iter -> iter
				.mapToObject(x -> "" + (int) x);
		return list(new DoubleCase<>(list(), adapter, list()), new DoubleCase<>(
				list(1.0, 2.0, 3.0, 4.0), adapter, list("1", "2", "3", "4")));
	}
}
