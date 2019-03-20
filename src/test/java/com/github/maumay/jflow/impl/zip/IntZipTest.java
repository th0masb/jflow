/**
 * 
 */
package com.github.maumay.jflow.impl.zip;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.testframework.AbstractIntAdapterTest;
import com.github.maumay.jflow.utils.IntTup;

/**
 * @author thomasb
 *
 */
public final class IntZipTest extends AbstractIntAdapterTest<AbstractRichIterator<IntTup>>
{
	@Override
	protected List<IntCase<AbstractRichIterator<IntTup>>> getTestCases()
	{
		return list(new IntCase<>(list(), i -> i.zip(iter(list())), list()),
				new IntCase<>(list(), i -> i.zip(iter(list(1))), list()),
				new IntCase<>(list(1), i -> i.zip(iter(list())), list()),
				new IntCase<>(list(1), i -> i.zip(iter(list(2))), list(tup(1, 2))),
				new IntCase<>(list(1, 2), i -> i.zip(iter(list(2))), list(tup(1, 2))),
				new IntCase<>(list(1), i -> i.zip(iter(list(2, 3))), list(tup(1, 2))));
	}

	private IntTup tup(int left, int right)
	{
		return IntTup.of(left, right);
	}
}
