/**
 * 
 */
package com.github.maumay.jflow.impl.append;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.testframework.AbstractIntAdapterTest;

/**
 * @author thomasb
 *
 */
public final class IntAppendTest extends AbstractIntAdapterTest<AbstractIntIterator>
{
	@Override
	protected List<Case<AbstractIntIterator>> getTestCases()
	{
		List<Integer> empty = list(), populated = list(0);
		return list(new Case<>(empty, i -> i.append(iter(empty)), empty),
				new Case<>(empty, i -> i.append(iter(populated)), populated),
				new Case<>(populated, i -> i.append(iter(empty)), populated),
				new Case<>(populated, i -> i.append(iter(list(1))), list(0, 1)));
	}
}
