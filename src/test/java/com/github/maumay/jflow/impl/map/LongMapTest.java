/**
 * 
 */
package com.github.maumay.jflow.impl.map;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.testframework.AbstractLongAdapterTest;

/**
 * @author thomasb
 *
 */
public final class LongMapTest extends AbstractLongAdapterTest<AbstractLongIterator>
{
	@Override
	protected List<Case<AbstractLongIterator>> getTestCases()
	{
		Adapter<AbstractLongIterator> adapter = iter -> iter.map(n -> 2 * n);
		return list(new Case<>(list(), adapter, list()),
				new Case<>(list(1L, 2L, 3L), adapter, list(2L, 4L, 6L)));
	}
}
