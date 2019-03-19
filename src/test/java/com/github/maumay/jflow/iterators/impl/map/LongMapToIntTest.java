/**
 * 
 */
package com.github.maumay.jflow.iterators.impl.map;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.testutilities.AbstractLongAdapterTest;

/**
 * @author t
 *
 */
public class LongMapToIntTest extends AbstractLongAdapterTest<AbstractIntIterator>
{
	@Override
	protected List<LongCase<AbstractIntIterator>> getTestCases()
	{
		LongAdapter<AbstractIntIterator> adapter = iter -> iter
				.mapToInt(x -> (int) (2 * x));
		return list(new LongCase<>(list(), adapter, list()),
				new LongCase<>(list(1L, 2L, 3L), adapter, list(2L, 4L, 6L)));
	}
}
