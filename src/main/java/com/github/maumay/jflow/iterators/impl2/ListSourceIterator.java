/**
 * 
 */
package com.github.maumay.jflow.iterators.impl2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.github.maumay.jflow.iterators.size.KnownSize;
import com.github.maumay.jflow.utils.Option;

/**
 * @author thomasb
 *
 */
public final class ListSourceIterator<E> extends AbstractSourceIterator<E>
{
	private final Iterator<? extends E> source;

	public ListSourceIterator(List<? extends E> source)
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
	protected E nextImpl()
	{
		return source.next();
	}

	@Override
	protected void skipImpl()
	{
		source.next();
	}

	public static void main(String[] args)
	{
		List<String> strings = Arrays.asList("s");

		ListSourceIterator<String> start = new ListSourceIterator<>(strings);

		AbstractEnhancedIterator<String> adapted = start.map(s -> "");

		// assert start.next().equals("s");
		// assert start.getSize().getSize() == 0;

		assert adapted.next().equals("");
		assert adapted.getSize().getExactSize().equals(Option.of(0));

		System.out.println("Good");
	}
}
