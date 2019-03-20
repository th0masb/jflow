/**
 * 
 */
package com.github.maumay.jflow.impl.zip;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.testutilities.AbstractDoubleAdapterTest;
import com.github.maumay.jflow.utils.DoubleTup;

/**
 * @author thomasb
 *
 */
public final class DoubleZipTest extends AbstractDoubleAdapterTest<AbstractRichIterator<DoubleTup>>
{
	@Override
	protected List<DoubleCase<AbstractRichIterator<DoubleTup>>> getTestCases()
	{

		return list(new DoubleCase<>(list(), i -> i.zipWith(iter(list())), list()),
				new DoubleCase<>(list(), i -> i.zipWith(iter(list(1.0))), list()),
				new DoubleCase<>(list(1.0), i -> i.zipWith(iter(list())), list()),
				new DoubleCase<>(list(1.0), i -> i.zipWith(iter(list(2.0))), list(tup(1.0, 2.0))),
				new DoubleCase<>(list(1.0, 2.0), i -> i.zipWith(iter(list(2.0))),
						list(tup(1.0, 2.0))),
				new DoubleCase<>(list(1.0), i -> i.zipWith(iter(list(2.0, 3.0))),
						list(tup(1.0, 2.0))));
	}

	private DoubleTup tup(double left, double right)
	{
		return DoubleTup.of(left, right);
	}
}
