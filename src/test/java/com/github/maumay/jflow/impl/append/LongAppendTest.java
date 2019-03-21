/**
 * 
 */
package com.github.maumay.jflow.impl.append;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.test.AbstractLongAdapterTest;

/**
 * @author thomasb
 *
 */
public final class LongAppendTest extends AbstractLongAdapterTest<AbstractLongIterator>
{
	@Override
	protected List<Case<AbstractLongIterator>> getTestCases()
	{
		List<Long> empty = list(), populated = list(0L);
		return list(new Case<>(empty, i -> i.append(iter(empty)), empty),
				new Case<>(empty, i -> i.append(iter(populated)), populated),
				new Case<>(populated, i -> i.append(iter(empty)), populated),
				new Case<>(populated, i -> i.append(iter(list(1L))), list(0L, 1L)));
	}
}
