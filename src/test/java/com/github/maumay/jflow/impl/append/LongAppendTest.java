/**
 * 
 */
package com.github.maumay.jflow.impl.append;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.testframework.AbstractLongAdapterTest;

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
		return list(new LongCase<>(empty, i -> i.append(iter(empty)), empty),
				new LongCase<>(empty, i -> i.append(iter(populated)), populated),
				new LongCase<>(populated, i -> i.append(iter(empty)), populated),
				new LongCase<>(populated, i -> i.append(iter(list(1L))), list(0L, 1L)));
	}
}
