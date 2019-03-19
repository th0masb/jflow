/**
 * 
 */
package com.github.maumay.jflow.iterators.impl.map;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.testutilities.AbstractLongAdapterTest;

/**
 * @author thomasb
 *
 */
public final class LongMapTest extends AbstractLongAdapterTest<AbstractLongIterator>
{
	@Override
	protected List<LongCase<AbstractLongIterator>> getLongTestCases()
	{
		LongAdapter<AbstractLongIterator> adapter = iter -> iter.map(n -> 2 * n);
		return list(new LongCase<>(list(), adapter, list()),
				new LongCase<>(list(1L, 2L, 3L), adapter, list(2L, 4L, 6L)));
	}
}
