/**
 * 
 */
package com.github.maumay.jflow.impl.misc;

import java.util.ArrayList;
import java.util.List;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.iterators.Iter;
import com.github.maumay.jflow.test.AbstractObjectAdapterTest;

/**
 * @author thomasb
 *
 */
public final class ObjectInterleaveTest
		extends AbstractObjectAdapterTest<Double, AbstractRichIterator<Double>>
{
	@Override
	protected List<Case<Double, AbstractRichIterator<Double>>> getTestCases()
	{
		Adapter<Double, AbstractRichIterator<Double>> emptyInterleave = i -> i
				.interleave(new ArrayList<Double>().iterator());
		Adapter<Double, AbstractRichIterator<Double>> populatedInterleave = i -> i
				.interleave(Iter.over(1.0, 2.0, 3.0));
		// .interleave(list(1.0, 2.0, 3.0).iterator());
		// return list(new Case<>(list(), emptyInterleave, list()),
		// new Case<>(list(1.0), emptyInterleave, list(1.0)),
		// new Case<>(list(), populatedInterleave, list()),
		// new Case<>(list(5.0), populatedInterleave, list(5.0, 1.0)),
		return list(new Case<>(list(5.0, 4.0, 6.0, 8.0, 9.0), populatedInterleave,
				list(5.0, 1.0, 4.0, 2.0, 6.0, 3.0, 8.0)));
	}
}
