/**
 * 
 */
package com.github.maumay.jflow.impl.insert;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.test.AbstractObjectAdapterTest;

/**
 * @author thomasb
 *
 */
public final class ObjectInsertTest
		extends AbstractObjectAdapterTest<Double, AbstractRichIterator<Double>>
{
	@Override
	protected List<Case<Double, AbstractRichIterator<Double>>> getTestCases()
	{
		List<Double> empty = list(), populated = list(0.0);
		return list(new Case<>(empty, i -> i.insert(empty.iterator()), empty),
				new Case<>(empty, i -> i.insert(populated.iterator()), populated),
				new Case<>(populated, i -> i.insert(empty.iterator()), populated),
				new Case<>(populated, i -> i.insert(list(1.0).iterator()), list(1.0, 0.0)));
	}
}
