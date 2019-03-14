/**
 * 
 */
package com.github.maumay.jflow.iterators.impl2.source;

import java.util.Iterator;
import java.util.PrimitiveIterator;

import com.github.maumay.jflow.iterators.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.iterators.impl.AbstractEnhancedIterator;
import com.github.maumay.jflow.iterators.impl.AbstractIntIterator;
import com.github.maumay.jflow.iterators.impl.AbstractLongIterator;
import com.github.maumay.jflow.iterators.impl.AbstractSourceIterator;
import com.github.maumay.jflow.iterators.impl.UnknownSize;

/**
 * @author thomasb
 *
 */
public final class IteratorWrappers
{
	private IteratorWrappers()
	{
	}

	public static <E> AbstractEnhancedIterator<? extends E> wrap(Iterator<? extends E> src)
	{
		if (src instanceof AbstractEnhancedIterator<?>) {
			return (AbstractEnhancedIterator<? extends E>) src;
		} else {
			return new OfObject<>(src);
		}
	}

	public static AbstractIntIterator wrap(PrimitiveIterator.OfInt src)
	{
		if (src instanceof AbstractIntIterator) {
			return (AbstractIntIterator) src;
		} else {
			return new OfInt(src);
		}
	}

	public static AbstractLongIterator wrap(PrimitiveIterator.OfLong src)
	{
		if (src instanceof AbstractLongIterator) {
			return (AbstractLongIterator) src;
		} else {
			return new OfLong(src);
		}
	}

	public static AbstractDoubleIterator wrap(PrimitiveIterator.OfDouble src)
	{
		if (src instanceof AbstractDoubleIterator) {
			return (AbstractDoubleIterator) src;
		} else {
			return new OfDouble(src);
		}
	}

	private static class OfObject<E> extends AbstractSourceIterator<E>
	{
		private final Iterator<? extends E> source;

		public OfObject(Iterator<? extends E> source)
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

	private static class OfLong extends AbstractLongIterator
	{
		private final PrimitiveIterator.OfLong source;

		public OfLong(PrimitiveIterator.OfLong source)
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
		public long nextLongImpl()
		{
			return source.nextLong();
		}

		@Override
		public void skipImpl()
		{
			source.nextLong();
		}
	}

	private static class OfInt extends AbstractIntIterator
	{
		private final PrimitiveIterator.OfInt source;

		public OfInt(PrimitiveIterator.OfInt source)
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
		public int nextIntImpl()
		{
			return source.nextInt();
		}

		@Override
		public void skipImpl()
		{
			source.nextInt();
		}
	}

	private static class OfDouble extends AbstractDoubleIterator
	{
		private final PrimitiveIterator.OfDouble source;

		public OfDouble(PrimitiveIterator.OfDouble source)
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
		public double nextDoubleImpl()
		{
			return source.nextDouble();
		}

		@Override
		public void skipImpl()
		{
			source.nextDouble();
		}
	}
}
