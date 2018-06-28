/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.Iterator;
import java.util.PrimitiveIterator;

import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.misc.DoublePair;
import xawd.jflow.iterators.misc.DoubleWith;
import xawd.jflow.iterators.misc.DoubleWithLong;
import xawd.jflow.iterators.misc.IntPair;
import xawd.jflow.iterators.misc.IntWith;
import xawd.jflow.iterators.misc.IntWithDouble;
import xawd.jflow.iterators.misc.IntWithLong;
import xawd.jflow.iterators.misc.LongPair;
import xawd.jflow.iterators.misc.LongWith;
import xawd.jflow.iterators.misc.Pair;

/**
 * @author ThomasB
 */
public final class ZipFlow
{
	private ZipFlow() {}

	public static class OfObjects<E1, E2> extends AbstractFlow<Pair<E1, E2>>
	{
		private final Flow<? extends E1> firstSource;
		private final Iterator<? extends E2> secondSource;

		public OfObjects(final Flow<? extends E1> firstSource, final Iterator<? extends E2> secondSource)
		{
			super(ImplUtils.calculateNewSize(firstSource, secondSource));
			this.firstSource = firstSource;
			this.secondSource = secondSource;
		}

		@Override
		public boolean hasNext()
		{
			return firstSource.hasNext() && secondSource.hasNext();
		}

		@Override
		public Pair<E1, E2> next()
		{
			return Pair.of(firstSource.next(), secondSource.next());
		}

		@Override
		public void skip()
		{
			firstSource.skip();
			ImplUtils.skip(secondSource);
		}
	}

	public static class OfObjectAndLong<E> extends AbstractFlow<LongWith<E>>
	{
		private final Iterator<? extends E> objectSource;
		private final PrimitiveIterator.OfLong longSource;

		public OfObjectAndLong(final Iterator<? extends E> objectSource, final PrimitiveIterator.OfLong longSource)
		{
			super(ImplUtils.calculateNewSize(objectSource, longSource));
			this.objectSource = objectSource;
			this.longSource = longSource;
		}

		@Override
		public boolean hasNext()
		{
			return objectSource.hasNext() && longSource.hasNext();
		}

		@Override
		public LongWith<E> next()
		{
			return LongWith.of(longSource.nextLong(), objectSource.next());
		}

		@Override
		public void skip()
		{
			ImplUtils.skip(objectSource);
			ImplUtils.skip(longSource);
		}
	}

	public static class OfObjectAndDouble<E> extends AbstractFlow<DoubleWith<E>>
	{
		private final Iterator<? extends E> objectSource;
		private final PrimitiveIterator.OfDouble doubleSource;

		public OfObjectAndDouble(final Iterator<? extends E> objectSource, final PrimitiveIterator.OfDouble doubleSource)
		{
			super(ImplUtils.calculateNewSize(objectSource, doubleSource));
			this.objectSource = objectSource;
			this.doubleSource = doubleSource;
		}

		@Override
		public boolean hasNext()
		{
			return objectSource.hasNext() && doubleSource.hasNext();
		}

		@Override
		public DoubleWith<E> next()
		{
			return DoubleWith.of(doubleSource.nextDouble(), objectSource.next());
		}

		@Override
		public void skip()
		{
			ImplUtils.skip(objectSource);
			ImplUtils.skip(doubleSource);
		}
	}

	public static class OfObjectAndInt<E> extends AbstractFlow<IntWith<E>>
	{
		private final Iterator<? extends E> objectSource;
		private final PrimitiveIterator.OfInt intSource;

		public OfObjectAndInt(final Iterator<? extends E> objectSource, final PrimitiveIterator.OfInt intSource)
		{
			super(ImplUtils.calculateNewSize(objectSource, intSource));
			this.objectSource = objectSource;
			this.intSource = intSource;
		}

		@Override
		public boolean hasNext()
		{
			return objectSource.hasNext() && intSource.hasNext();
		}

		@Override
		public IntWith<E> next()
		{
			return IntWith.of(intSource.nextInt(), objectSource.next());
		}

		@Override
		public void skip()
		{
			ImplUtils.skip(objectSource);
			ImplUtils.skip(intSource);
		}
	}

	public static class OfDoubleWithLong extends AbstractFlow<DoubleWithLong>
	{
		private final PrimitiveIterator.OfDouble doubleSource;
		private final PrimitiveIterator.OfLong longSource;

		public OfDoubleWithLong(final PrimitiveIterator.OfDouble doubleSource, final PrimitiveIterator.OfLong longSource)
		{
			super(ImplUtils.calculateNewSize(doubleSource, longSource));
			this.doubleSource = doubleSource;
			this.longSource = longSource;
		}

		@Override
		public boolean hasNext()
		{
			return doubleSource.hasNext() && longSource.hasNext();
		}

		@Override
		public DoubleWithLong next()
		{
			return DoubleWithLong.of(doubleSource.nextDouble(), longSource.nextLong());
		}

		@Override
		public void skip()
		{
			ImplUtils.skip(doubleSource);
			ImplUtils.skip(longSource);
		}
	}

	public static class OfIntWithLong extends AbstractFlow<IntWithLong>
	{
		private final PrimitiveIterator.OfInt intSource;
		private final PrimitiveIterator.OfLong longSource;

		public OfIntWithLong(final PrimitiveIterator.OfInt intSource, final PrimitiveIterator.OfLong longSource)
		{
			super(ImplUtils.calculateNewSize(intSource, longSource));
			this.intSource = intSource;
			this.longSource = longSource;
		}

		@Override
		public boolean hasNext()
		{
			return intSource.hasNext() && longSource.hasNext();
		}

		@Override
		public IntWithLong next()
		{
			return IntWithLong.of(intSource.nextInt(), longSource.nextLong());
		}

		@Override
		public void skip()
		{
			ImplUtils.skip(intSource);
			ImplUtils.skip(longSource);
		}
	}

	public static class OfIntWithDouble extends AbstractFlow<IntWithDouble>
	{
		private final PrimitiveIterator.OfInt intSource;
		private final PrimitiveIterator.OfDouble doubleSource;

		public OfIntWithDouble(final PrimitiveIterator.OfInt intSource, final PrimitiveIterator.OfDouble doubleSource)
		{
			super(ImplUtils.calculateNewSize(intSource, doubleSource));
			this.intSource = intSource;
			this.doubleSource = doubleSource;
		}

		@Override
		public boolean hasNext()
		{
			return intSource.hasNext() && doubleSource.hasNext();
		}

		@Override
		public IntWithDouble next()
		{
			return IntWithDouble.of(intSource.nextInt(), doubleSource.nextDouble());
		}

		@Override
		public void skip()
		{
			ImplUtils.skip(intSource);
			ImplUtils.skip(doubleSource);
		}
	}

	public static class OfIntPair extends AbstractFlow<IntPair>
	{
		private final PrimitiveIterator.OfInt firstIntSource;
		private final PrimitiveIterator.OfInt secondIntSource;

		public OfIntPair(final PrimitiveIterator.OfInt firstIntSource, final PrimitiveIterator.OfInt secondIntSource)
		{
			super(ImplUtils.calculateNewSize(firstIntSource, secondIntSource));
			this.firstIntSource = firstIntSource;
			this.secondIntSource = secondIntSource;
		}

		@Override
		public boolean hasNext()
		{
			return firstIntSource.hasNext() && secondIntSource.hasNext();
		}

		@Override
		public IntPair next()
		{
			return IntPair.of(firstIntSource.nextInt(), secondIntSource.nextInt());
		}

		@Override
		public void skip()
		{
			ImplUtils.skip(firstIntSource);
			ImplUtils.skip(secondIntSource);
		}
	}

	public static class OfDoublePair extends AbstractFlow<DoublePair>
	{
		private final PrimitiveIterator.OfDouble firstDoubleSource;
		private final PrimitiveIterator.OfDouble secondDoubleSource;

		public OfDoublePair(final PrimitiveIterator.OfDouble firstDoubleSource, final PrimitiveIterator.OfDouble secondDoubleSource)
		{
			super(ImplUtils.calculateNewSize(firstDoubleSource, secondDoubleSource));
			this.firstDoubleSource = firstDoubleSource;
			this.secondDoubleSource = secondDoubleSource;
		}

		@Override
		public boolean hasNext()
		{
			return firstDoubleSource.hasNext() && secondDoubleSource.hasNext();
		}

		@Override
		public DoublePair next()
		{
			return DoublePair.of(firstDoubleSource.nextDouble(), secondDoubleSource.nextDouble());
		}

		@Override
		public void skip()
		{
			ImplUtils.skip(firstDoubleSource);
			ImplUtils.skip(secondDoubleSource);
		}
	}

	public static class OfLongPair extends AbstractFlow<LongPair>
	{
		private final PrimitiveIterator.OfLong firstLongSource;
		private final PrimitiveIterator.OfLong secondLongSource;

		public OfLongPair(final PrimitiveIterator.OfLong firstLongSource, final PrimitiveIterator.OfLong secondLongSource)
		{
			super(ImplUtils.calculateNewSize(firstLongSource, secondLongSource));
			this.firstLongSource = firstLongSource;
			this.secondLongSource = secondLongSource;
		}

		@Override
		public boolean hasNext()
		{
			return firstLongSource.hasNext() && secondLongSource.hasNext();
		}

		@Override
		public LongPair next()
		{
			return LongPair.of(firstLongSource.nextLong(), secondLongSource.nextLong());
		}

		@Override
		public void skip()
		{
			ImplUtils.skip(firstLongSource);
			ImplUtils.skip(secondLongSource);
		}
	}
}
