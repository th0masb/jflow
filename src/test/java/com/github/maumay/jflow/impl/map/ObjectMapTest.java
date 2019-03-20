/**
 * 
 */
package com.github.maumay.jflow.impl.map;

import java.util.ArrayList;
import java.util.List;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.testutilities.AbstractObjectAdapterTest;

/**
 * @author thomasb
 *
 */
public final class ObjectMapTest extends AbstractObjectAdapterTest<String, AbstractRichIterator<String>>
{
	@Override
	protected List<Case<String, AbstractRichIterator<String>>> getTestCases()
	{
		List<Case<String, AbstractRichIterator<String>>> dest = new ArrayList<>();
		Adapter<String, AbstractRichIterator<String>> adapter = iter -> iter.map(s -> s + s);
		dest.add(new Case<>(list(), adapter, list()));
		dest.add(new Case<>(list("1", "2"), adapter, list("11", "22")));
		return dest;
	}
}
