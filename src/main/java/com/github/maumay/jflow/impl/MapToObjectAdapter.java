/**
 * 
 */
package com.github.maumay.jflow.impl;

import java.util.function.DoubleFunction;
import java.util.function.IntFunction;
import java.util.function.LongFunction;

/**
 * @author thomasb
 *
 */
public class MapToObjectAdapter
{
	private MapToObjectAdapter()
	{
	}

	public static final class FromInt<R>
			extends AbstractIteratorAdapter.OfObject<AbstractIntIterator, R>
	{
		private final IntFunction<? extends R> map;

		public FromInt(AbstractIntIterator source, IntFunction<? extends R> map)
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
		public R nextImpl()
		{
			return map.apply(getSource().nextIntImpl());
		}

		@Override
		public void skipImpl()
		{
			getSource().skipImpl();
		}
	}

	public static final class FromLong<R>
			extends AbstractIteratorAdapter.OfObject<AbstractLongIterator, R>
	{
		private final LongFunction<? extends R> map;

		public FromLong(AbstractLongIterator source, LongFunction<? extends R> map)
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
		public R nextImpl()
		{
			return map.apply(getSource().nextLongImpl());
		}

		@Override
		public void skipImpl()
		{
			getSource().skipImpl();
		}
	}

	public static final class FromDouble<R>
			extends AbstractIteratorAdapter.OfObject<AbstractDoubleIterator, R>
	{
		private final DoubleFunction<? extends R> map;

		public FromDouble(AbstractDoubleIterator source, DoubleFunction<? extends R> map)
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
		public R nextImpl()
		{
			return map.apply(getSource().nextDoubleImpl());
		}

		@Override
		public void skipImpl()
		{
			getSource().skipImpl();
		}
	}
}
