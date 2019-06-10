/**
 * 
 */
package com.github.maumay.jflow.impl.append;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.test.AbstractIntAdapterTest;

/**
 * @author thomasb
 *
 */
public final class IntChainTest extends AbstractIntAdapterTest<AbstractIntIterator>
{
	@Override
	protected List<Case<AbstractIntIterator>> getTestCases()
	{
		List<Integer> empty = list(), populated = list(0);
		return list(new Case<>(empty, i -> i.chain(iter(empty)), empty),
				new Case<>(empty, i -> i.chain(iter(populated)), populated),
				new Case<>(populated, i -> i.chain(iter(empty)), populated),
				new Case<>(populated, i -> i.chain(iter(list(1))), list(0, 1)));
	}
}
