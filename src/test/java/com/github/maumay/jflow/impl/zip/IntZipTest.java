/**
 * 
 */
package com.github.maumay.jflow.impl.zip;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.test.AbstractIntAdapterTest;
import com.github.maumay.jflow.utils.IntTup;

/**
 * @author thomasb
 *
 */
public final class IntZipTest extends AbstractIntAdapterTest<AbstractRichIterator<IntTup>>
{
	@Override
	protected List<Case<AbstractRichIterator<IntTup>>> getTestCases()
	{
		return list(new Case<>(list(), i -> i.zip(iter(list())), list()),
				new Case<>(list(), i -> i.zip(iter(list(1))), list()),
				new Case<>(list(1), i -> i.zip(iter(list())), list()),
				new Case<>(list(1), i -> i.zip(iter(list(2))), list(tup(1, 2))),
				new Case<>(list(1, 2), i -> i.zip(iter(list(2))), list(tup(1, 2))),
				new Case<>(list(1), i -> i.zip(iter(list(2, 3))), list(tup(1, 2))));
	}

	private IntTup tup(int left, int right)
	{
		return IntTup.of(left, right);
	}
}
