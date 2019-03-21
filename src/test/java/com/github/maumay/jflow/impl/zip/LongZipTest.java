/**
 * 
 */
package com.github.maumay.jflow.impl.zip;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.test.AbstractDoubleAdapterTest;
import com.github.maumay.jflow.utils.DoubleTup;

/**
 * @author thomasb
 *
 */
public final class LongZipTest extends AbstractDoubleAdapterTest<AbstractRichIterator<DoubleTup>>
{
	@Override
	protected List<Case<AbstractRichIterator<DoubleTup>>> getTestCases()
	{

		return list(new Case<>(list(), i -> i.zip(iter(list())), list()),
				new Case<>(list(), i -> i.zip(iter(list(1.0))), list()),
				new Case<>(list(1.0), i -> i.zip(iter(list())), list()),
				new Case<>(list(1.0), i -> i.zip(iter(list(2.0))), list(tup(1.0, 2.0))),
				new Case<>(list(1.0, 2.0), i -> i.zip(iter(list(2.0))),
						list(tup(1.0, 2.0))),
				new Case<>(list(1.0), i -> i.zip(iter(list(2.0, 3.0))),
						list(tup(1.0, 2.0))));
	}

	private DoubleTup tup(double left, double right)
	{
		return DoubleTup.of(left, right);
	}
}
