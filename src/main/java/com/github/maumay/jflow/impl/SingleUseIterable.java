/**
 * 
 */
package com.github.maumay.jflow.impl;

import com.github.maumay.jflow.iterables.RichIterable;
import com.github.maumay.jflow.iterators.RichIterator;
import com.github.maumay.jflow.utils.Exceptions;

/**
 * @author thomasb
 *
 */
public final class SingleUseIterable<E> implements RichIterable<E>
{
	private final AbstractRichIterator<E> source;
	private boolean consumed;

	public SingleUseIterable(AbstractRichIterator<E> source)
	{
		Exceptions.require(source.hasOwnership());
		this.source = source;
		this.consumed = false;
	}

	@Override
	public RichIterator<E> iter()
	{
		if (consumed) {
			throw new IllegalStateException();
		} else {
			consumed = true;
			return source;
		}
	}
}
