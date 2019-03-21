/**
 * 
 */
package com.github.maumay.jflow.impl.source;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.impl.ArraySource;
import com.github.maumay.jflow.test.AbstractSourceTest;

/**
 * @author thomasb
 *
 */
public final class LongArraySourceTest extends AbstractSourceTest<AbstractLongIterator>
{
	@Override
	protected List<Case<AbstractLongIterator>> getTestCases()
	{
		return list(new Case<>(() -> new ArraySource.OfLong(), list()),
				new Case<>(() -> new ArraySource.OfLong(0L, 1L, 2L), list(0L, 1L, 2L)));
	}
}
