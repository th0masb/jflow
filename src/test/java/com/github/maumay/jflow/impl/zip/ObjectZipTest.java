/**
 * 
 */
package com.github.maumay.jflow.impl.zip;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.testutilities.AbstractObjectAdapterTest;
import com.github.maumay.jflow.utils.Tup;

/**
 * @author thomasb
 *
 */
public final class ObjectZipTest
		extends AbstractObjectAdapterTest<Double, AbstractRichIterator<Tup<Double, Double>>>
{
	@Override
	protected List<Case<Double, AbstractRichIterator<Tup<Double, Double>>>> getTestCases()
	{

		return list(new Case<>(list(), i -> i.zip(list()), list()),
				new Case<>(list(), i -> i.zip(list(1.0)), list()),
				new Case<>(list(1.0), i -> i.zip(list()), list()),
				new Case<>(list(1.0), i -> i.zip(list(2.0)), list(tup(1.0, 2.0))),
				new Case<>(list(1.0, 2.0), i -> i.zip(list(2.0)), list(tup(1.0, 2.0))),
				new Case<>(list(1.0), i -> i.zip(list(2.0, 3.0)), list(tup(1.0, 2.0))));
	}

	private <L, R> Tup<L, R> tup(L left, R right)
	{
		return Tup.of(left, right);
	}
}
