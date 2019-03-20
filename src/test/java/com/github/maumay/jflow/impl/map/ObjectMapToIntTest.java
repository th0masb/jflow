/**
 * 
 */
package com.github.maumay.jflow.impl.map;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.testutilities.AbstractObjectAdapterTest;

/**
 * @author thomasb
 *
 */
public final class ObjectMapToIntTest extends AbstractObjectAdapterTest<String, AbstractIntIterator>
{
	@Override
	protected List<Case<String, AbstractIntIterator>> getTestCases()
	{
		Adapter<String, AbstractIntIterator> adapter = iter -> iter.mapToInt(Integer::parseInt);
		return list(new Case<>(list(), adapter, list()),
				new Case<>(list("0", "1"), adapter, list(0, 1)));
	}
}
