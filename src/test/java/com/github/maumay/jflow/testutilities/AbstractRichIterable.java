package com.github.maumay.jflow.testutilities;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.iterables.RichIterable;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public abstract class AbstractRichIterable<T> implements RichIterable<T>
{
	@Override
	public AbstractRichIterator<T> iterator()
	{
		return iter();
	}

	@Override
	public abstract AbstractRichIterator<T> iter();
}
