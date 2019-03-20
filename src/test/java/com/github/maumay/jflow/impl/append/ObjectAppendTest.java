/**
 * 
 */
package com.github.maumay.jflow.impl.append;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.testutilities.AbstractAdapterTest;

/**
 * @author thomasb
 *
 */
public final class ObjectAppendTest
		extends AbstractAdapterTest<Double, AbstractRichIterator<Double>>
{
	@Override
	protected List<Case<Double, AbstractRichIterator<Double>>> getTestCases()
	{
		List<Double> empty = list(), populated = list(0.0);
		return list(new Case<>(empty, i -> i.append(empty.iterator()), empty),
				new Case<>(empty, i -> i.append(populated.iterator()), populated),
				new Case<>(populated, i -> i.append(empty.iterator()), populated),
				new Case<>(populated, i -> i.append(populated.iterator()), list(0.0, 0.0)));
	}
}
