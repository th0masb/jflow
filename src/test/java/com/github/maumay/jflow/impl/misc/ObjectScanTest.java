/**
 * 
 */
package com.github.maumay.jflow.impl.misc;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.testframework.AbstractObjectAdapterTest;

/**
 * @author thomasb
 *
 */
public final class ObjectScanTest
		extends AbstractObjectAdapterTest<String, AbstractRichIterator<String>>
{
	@Override
	protected List<Case<String, AbstractRichIterator<String>>> getTestCases()
	{
		Adapter<String, AbstractRichIterator<String>> adapter = iter -> iter.scan("",
				(a, b) -> a + b);
		return list(new Case<>(list(), adapter, list("")),
				new Case<>(list("a", "b", "c"), adapter, list("", "a", "ab", "abc")));
	}
}
