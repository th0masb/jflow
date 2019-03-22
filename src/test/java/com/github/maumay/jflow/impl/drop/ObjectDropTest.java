/**
 * 
 */
package com.github.maumay.jflow.impl.drop;

import java.util.List;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.test.AbstractObjectAdapterTest;

/**
 * @author t
 *
 */
public final class ObjectDropTest
		extends AbstractObjectAdapterTest<String, AbstractRichIterator<String>>
{
	@Override
	protected List<Case<String, AbstractRichIterator<String>>> getTestCases()
	{
		List<String> src = list("", "", "", "", "", "");
		return list(new Case<>(list(), i -> i.drop(2), list()),
				new Case<>(src, i -> i.drop(0), src),
				new Case<>(src, i -> i.drop(3), list("", "", "")),
				new Case<>(src, i -> i.drop(6), list()),
				new Case<>(src, i -> i.drop(7), list()));
	}
}
