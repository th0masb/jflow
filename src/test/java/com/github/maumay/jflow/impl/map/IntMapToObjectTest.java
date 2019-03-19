/**
 * 
 */
package com.github.maumay.jflow.impl.map;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.testutilities.AbstractIntAdapterTest;

/**
 * @author t
 *
 */
public final class IntMapToObjectTest
		extends AbstractIntAdapterTest<AbstractRichIterator<String>>
{
	@Override
	protected List<IntCase<AbstractRichIterator<String>>> getTestCases()
	{
		IntAdapter<AbstractRichIterator<String>> adapter = iter -> iter
				.mapToObject(x -> "" + x);
		return list(new IntCase<>(list(), adapter, list()),
				new IntCase<>(list(1, 2, 3, 4), adapter, list("1", "2", "3", "4")));
	}
}
