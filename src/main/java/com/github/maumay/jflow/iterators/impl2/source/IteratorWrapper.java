/**
 * 
 */
package com.github.maumay.jflow.iterators.impl2.source;

import java.util.Iterator;

import com.github.maumay.jflow.iterators.impl.AbstractSourceIterator;
import com.github.maumay.jflow.iterators.impl.UnknownSize;

/**
 * @author thomasb
 */
public class IteratorWrapper<E> extends AbstractSourceIterator<E>
{
	private final Iterator<? extends E> source;

	public IteratorWrapper(Iterator<? extends E> source)
	{
		super(UnknownSize.instance());
		this.source = source;
	}

	@Override
	public boolean hasNext()
	{
		return source.hasNext();
	}

	@Override
	public E nextImpl()
	{
		return source.next();
	}

	@Override
	public void skipImpl()
	{
		source.next();
	}
}
