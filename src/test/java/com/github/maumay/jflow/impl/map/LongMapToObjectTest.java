/**
 * 
 */
package com.github.maumay.jflow.impl.map;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.testframework.AbstractLongAdapterTest;

/**
 * @author t
 *
 */
public final class LongMapToObjectTest
		extends AbstractLongAdapterTest<AbstractRichIterator<String>>
{
	@Override
	protected List<Case<AbstractRichIterator<String>>> getTestCases()
	{
		Adapter<AbstractRichIterator<String>> adapter = iter -> iter
				.mapToObject(x -> "" + x);
		return list(new Case<>(list(), adapter, list()),
				new Case<>(list(1L, 2L, 3L, 4L), adapter, list("1", "2", "3", "4")));
	}
}
