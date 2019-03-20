/**
 * 
 */
package com.github.maumay.jflow.impl.append;

import java.util.Iterator;
import java.util.List;
import java.util.PrimitiveIterator;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.testutilities.AbstractIntAdapterTest;

/**
 * @author thomasb
 *
 */
public final class IntAppendTest extends AbstractIntAdapterTest<AbstractIntIterator>
{
	@Override
	protected List<IntCase<AbstractIntIterator>> getTestCases()
	{
		List<Integer> empty = list(), populated = list(0);
		return list(new IntCase<>(empty, i -> i.append(unbox(empty.iterator())), empty),
				new IntCase<>(empty, i -> i.append(unbox(populated.iterator())), populated),
				new IntCase<>(populated, i -> i.append(unbox(empty.iterator())), populated),
				new IntCase<>(populated, i -> i.append(unbox(populated.iterator())), list(0, 0)));
	}

	private PrimitiveIterator.OfInt unbox(Iterator<Integer> source)
	{
		return new PrimitiveIterator.OfInt() {
			@Override
			public boolean hasNext()
			{
				return source.hasNext();
			}

			@Override
			public int nextInt()
			{
				return source.next();
			}
		};
	}
}
