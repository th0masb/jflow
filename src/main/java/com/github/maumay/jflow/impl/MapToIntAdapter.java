/**
 * 
 */
package com.github.maumay.jflow.impl;

import java.util.function.DoubleToIntFunction;
import java.util.function.LongToIntFunction;
import java.util.function.ToIntFunction;

/**
 * @author thomasb
 *
 */
public final class MapToIntAdapter
{
	private MapToIntAdapter()
	{
	}

	public static final class FromObject<E>
			extends AbstractIteratorAdapter.OfInt<AbstractRichIterator<E>>
	{
		private final ToIntFunction<? super E> map;

		public FromObject(AbstractRichIterator<E> source, ToIntFunction<? super E> map)
		{
			super(source.getSize().copy(), source);
			this.map = map;
		}

		@Override
		public boolean hasNext()
		{
			return getSource().hasNext();
		}

		@Override
		public int nextIntImpl()
		{
			return map.applyAsInt(getSource().nextImpl());
		}

		@Override
		public void skipImpl()
		{
			getSource().skipImpl();
		}
	}

	public static final class FromDouble
			extends AbstractIteratorAdapter.OfInt<AbstractDoubleIterator>
	{
		private final DoubleToIntFunction map;

		public FromDouble(AbstractDoubleIterator source, DoubleToIntFunction map)
		{
			super(source.getSize().copy(), source);
			this.map = map;
		}

		@Override
		public boolean hasNext()
		{
			return getSource().hasNext();
		}

		@Override
		public int nextIntImpl()
		{
			return map.applyAsInt(getSource().nextDoubleImpl());
		}

		@Override
		public void skipImpl()
		{
			getSource().skipImpl();
		}
	}

	public static final class FromLong extends AbstractIteratorAdapter.OfInt<AbstractLongIterator>
	{
		private final LongToIntFunction map;

		public FromLong(AbstractLongIterator source, LongToIntFunction map)
		{
			super(source.getSize().copy(), source);
			this.map = map;
		}

		@Override
		public boolean hasNext()
		{
			return getSource().hasNext();
		}

		@Override
		public int nextIntImpl()
		{
			return map.applyAsInt(getSource().nextLongImpl());
		}

		@Override
		public void skipImpl()
		{
			getSource().skipImpl();
		}
	}
}
