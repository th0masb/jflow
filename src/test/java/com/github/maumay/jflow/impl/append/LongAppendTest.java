/**
 * 
 */
package com.github.maumay.jflow.impl.append;

import java.util.Iterator;
import java.util.List;
import java.util.PrimitiveIterator;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.testutilities.AbstractLongAdapterTest;

/**
 * @author thomasb
 *
 */
public final class LongAppendTest extends AbstractLongAdapterTest<AbstractLongIterator>
{
	@Override
	protected List<LongCase<AbstractLongIterator>> getTestCases()
	{
		List<Long> empty = list(), populated = list(0L);
		return list(new LongCase<>(empty, i -> i.append(unbox(empty.iterator())), empty),
				new LongCase<>(empty, i -> i.append(unbox(populated.iterator())), populated),
				new LongCase<>(populated, i -> i.append(unbox(empty.iterator())), populated),
				new LongCase<>(populated, i -> i.append(unbox(populated.iterator())),
						list(0L, 0L)));
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
