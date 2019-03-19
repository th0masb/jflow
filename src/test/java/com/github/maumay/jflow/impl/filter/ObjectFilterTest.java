/**
 * 
 */
package com.github.maumay.jflow.impl.filter;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.testutilities.AbstractAdapterTest;

/**
 * @author t
 *
 */
public final class ObjectFilterTest
		extends AbstractAdapterTest<String, AbstractRichIterator<String>>
{
	@Override
	protected List<Case<String, AbstractRichIterator<String>>> getTestCases()
	{
		Adapter<String, AbstractRichIterator<String>> adapter = iter -> iter
				.filter(s -> s.startsWith("0"));
		return list(new Case<>(list(), adapter, list()),
				new Case<>(list("0", "1"), adapter, list("0")),
				new Case<>(list("1", "2"), adapter, list()),
				new Case<>(list("0", "00"), adapter, list("0", "00")));
	}
}
