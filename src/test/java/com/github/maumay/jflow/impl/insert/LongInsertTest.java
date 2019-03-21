/**
 * 
 */
package com.github.maumay.jflow.impl.insert;

import java.util.Iterator;
import java.util.List;
import java.util.PrimitiveIterator;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.test.AbstractLongAdapterTest;

/**
 * @author thomasb
 *
 */
public final class LongInsertTest extends AbstractLongAdapterTest<AbstractLongIterator>
{
	@Override
	protected List<Case<AbstractLongIterator>> getTestCases()
	{
		List<Long> empty = list(), populated = list(0L);
		return list(new Case<>(empty, i -> i.insert(unbox(empty.iterator())), empty),
				new Case<>(empty, i -> i.insert(unbox(populated.iterator())), populated),
				new Case<>(populated, i -> i.insert(unbox(empty.iterator())), populated),
				new Case<>(populated, i -> i.insert(unbox(list(1L).iterator())), list(1L, 0L)));
	}

	private PrimitiveIterator.OfLong unbox(Iterator<Long> source)
	{
		return new PrimitiveIterator.OfLong() {
			@Override
			public boolean hasNext()
			{
				return source.hasNext();
			}

			@Override
			public long nextLong()
			{
				return source.next();
			}
		};
	}
}
