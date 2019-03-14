/**
 * 
 */
package com.github.maumay.jflow.iterators.impl2.source;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.github.maumay.jflow.iterators.impl.AbstractEnhancedIterator;
import com.github.maumay.jflow.iterators.impl.AbstractSourceIterator;
import com.github.maumay.jflow.iterators.impl.KnownSize;
import com.github.maumay.jflow.utils.Option;

/**
 * @author thomasb
 *
 */
public final class CollectionSourceIterator<E> extends AbstractSourceIterator<E>
{
	private final Iterator<? extends E> source;

	public CollectionSourceIterator(Collection<? extends E> source)
	{
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

	public static void main(String[] args)
	{
		List<String> strings = Arrays.asList("s");

		CollectionSourceIterator<String> start = new CollectionSourceIterator<>(strings);

		AbstractEnhancedIterator<String> adapted = start.map(s -> "");

		// assert start.next().equals("s");
		// assert start.getSize().getSize() == 0;

		assert adapted.next().equals("");
		assert adapted.getSize().getExactSize().equals(Option.of(0));

		System.out.println("Good");
	}
}
