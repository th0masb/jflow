/**
 * 
 */
package com.github.maumay.jflow.impl.map;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.test.AbstractLongAdapterTest;

/**
 * @author t
 *
 */
public class LongMapToIntTest extends AbstractLongAdapterTest<AbstractIntIterator>
{
	@Override
	protected List<Case<AbstractIntIterator>> getTestCases()
	{
		Adapter<AbstractIntIterator> adapter = iter -> iter.mapToInt(x -> (int) (2 * x));
		return list(new Case<>(list(), adapter, list()),
				new Case<>(list(1L, 2L, 3L), adapter, list(2, 4, 6)));
	}
}
