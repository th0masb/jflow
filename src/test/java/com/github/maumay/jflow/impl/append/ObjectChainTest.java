/**
 * 
 */
package com.github.maumay.jflow.impl.append;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.test.AbstractObjectAdapterTest;

/**
 * @author thomasb
 *
 */
public final class ObjectChainTest
		extends AbstractObjectAdapterTest<Double, AbstractRichIterator<Double>>
{
	@Override
	protected List<Case<Double, AbstractRichIterator<Double>>> getTestCases()
	{
		List<Double> empty = list(), populated = list(0.0);
		return list(new Case<>(empty, i -> i.chain(empty.iterator()), empty),
				new Case<>(empty, i -> i.chain(populated.iterator()), populated),
				new Case<>(populated, i -> i.chain(empty.iterator()), populated),
				new Case<>(populated, i -> i.chain(list(1.0).iterator()), list(0.0, 1.0)));
	}
}
