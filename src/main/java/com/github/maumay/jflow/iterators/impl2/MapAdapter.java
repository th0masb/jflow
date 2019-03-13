/**
 * 
 */
package com.github.maumay.jflow.iterators.impl2;

import java.util.function.Function;

/**
 * @author thomasb
 */
public final class MapAdapter<E, R> extends AbstractAdapterIterator<E, R>
{
	private final Function<? super E, ? extends R> map;

	public MapAdapter(AbstractEnhancedIterator<E> source, Function<? super E, ? extends R> map)
	{
		super(source.getSize(), source);
		this.map = map;
	}

	@Override
	public boolean hasNext()
	{
		return getSource().hasNext();
	}

	@Override
	protected R nextImpl()
	{
		return map.apply(getSource().nextImpl());
	}

	@Override
	protected void skipImpl()
	{
		getSource().skipImpl();
	}
}
