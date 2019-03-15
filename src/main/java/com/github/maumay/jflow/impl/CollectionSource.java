/**
 * 
 */
package com.github.maumay.jflow.impl;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author thomasb
 *
 */
public final class CollectionSource<E> extends AbstractEnhancedIterator<E>
{
	private final Iterator<? extends E> source;

	public CollectionSource(Collection<? extends E> source)
	{
		// We can use the size safe in the knowledge it won't change
		// because of the built in iterator behaviour which is early
		// binding and checks for concurrent modification.
		super(new KnownSize(source.size()));
		this.source = source.iterator();
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
