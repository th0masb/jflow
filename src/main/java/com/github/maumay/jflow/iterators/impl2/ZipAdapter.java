/**
 * 
 */
package com.github.maumay.jflow.iterators.impl2;

import com.github.maumay.jflow.utils.Tup;

/**
 * @author thomasb
 *
 */
public final class ZipAdapter
{
	private ZipAdapter()
	{
	}

	public static class OfObjects<E1, E2>
			extends AbstractIteratorBiAdapters.OfObject<E1, E2, Tup<E1, E2>>
	{
		public OfObjects(AbstractEnhancedIterator<? extends E1> sourceOne,
				AbstractEnhancedIterator<? extends E2> sourceTwo)
		{
			super(IteratorSizeUtils.min(sourceOne.getSize(), sourceTwo.getSize()), sourceOne,
					sourceTwo);
		}

		@Override
		public boolean hasNext()
		{
			throw new RuntimeException("Not yet implemented");
		}

		@Override
		public Tup<E1, E2> nextImpl()
		{
			throw new RuntimeException("Not yet implemented");
		}

		@Override
		public void skipImpl()
		{
			throw new RuntimeException("Not yet implemented");
		}
	}
}
