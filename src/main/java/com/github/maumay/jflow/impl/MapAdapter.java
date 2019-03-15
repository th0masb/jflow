/**
 * 
 */
package com.github.maumay.jflow.impl;

import java.util.Objects;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.LongUnaryOperator;

/**
 * @author thomasb
 *
 */
public class MapAdapter
{
	private MapAdapter()
	{
	}

	public static final class OfObject<E, R>
			extends AbstractIteratorAdapter.OfObject<AbstractEnhancedIterator<E>, R>
	{
		private final Function<? super E, ? extends R> map;

		public OfObject(AbstractEnhancedIterator<E> source, Function<? super E, ? extends R> map)
		{
			super(source.getSize(), source);
			this.map = Objects.requireNonNull(map);
		}

		@Override
		public boolean hasNext()
		{
			return getSource().hasNext();
		}

		@Override
		public R nextImpl()
		{
			return map.apply(getSource().nextImpl());
		}

		@Override
		public void skipImpl()
		{
			getSource().skipImpl();
		}
	}

	public static final class OfInt extends AbstractIteratorAdapter.OfInt<AbstractIntIterator>
	{
		private final IntUnaryOperator map;

		public OfInt(AbstractIntIterator source, IntUnaryOperator map)
		{
			super(source.getSize(), source);
			this.map = Objects.requireNonNull(map);
		}

		@Override
		public boolean hasNext()
		{
			return getSource().hasNext();
		}

		@Override
		public int nextIntImpl()
		{
			return map.applyAsInt(getSource().nextIntImpl());
		}

		@Override
		public void skipImpl()
		{
			getSource().skipImpl();
		}
	}

	public static final class OfLong extends AbstractIteratorAdapter.OfLong<AbstractLongIterator>
	{
		private final LongUnaryOperator map;

		public OfLong(AbstractLongIterator source, LongUnaryOperator map)
		{
			super(source.getSize(), source);
			this.map = Objects.requireNonNull(map);
		}

		@Override
		public boolean hasNext()
		{
			return getSource().hasNext();
		}

		@Override
		public long nextLongImpl()
		{
			return map.applyAsLong(getSource().nextLongImpl());
		}

		@Override
		public void skipImpl()
		{
			getSource().skipImpl();
		}
	}

	public static final class OfDouble
			extends AbstractIteratorAdapter.OfDouble<AbstractDoubleIterator>
	{
		private final DoubleUnaryOperator map;

		public OfDouble(AbstractDoubleIterator source, DoubleUnaryOperator map)
		{
			super(source.getSize(), source);
			this.map = Objects.requireNonNull(map);
		}

		@Override
		public boolean hasNext()
		{
			return getSource().hasNext();
		}

		@Override
		public double nextDoubleImpl()
		{
			return map.applyAsDouble(getSource().nextDoubleImpl());
		}

		@Override
		public void skipImpl()
		{
			getSource().skipImpl();
		}
	}
}
