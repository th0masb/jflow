/**
 * 
 */
package com.github.maumay.jflow.impl.slice;

import static java.lang.Math.max;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.test.AbstractIntAdapterTest;

/**
 * @author thomasb
 *
 */
public final class IntSliceTest extends AbstractIntAdapterTest<AbstractIntIterator>
{
	@Override
	protected List<Case<AbstractIntIterator>> getTestCases()
	{
		List<Integer> src = list(0, 1, 2, 3, 4, 5);
		return list(new Case<>(list(), i -> i.slice(n -> n), list()),
				new Case<>(src, i -> i.slice(n -> n), src),
				new Case<>(src, i -> i.slice(n -> n + 1), list(1, 2, 3, 4, 5)),
				new Case<>(src, i -> i.slice(n -> 2 * n), list(0, 2, 4)),
				new Case<>(src, i -> i.slice(n -> max(0, 3 * n - 1)), list(0, 2, 5)));
	}
}
