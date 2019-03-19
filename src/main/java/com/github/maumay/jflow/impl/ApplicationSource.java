/**
 * 
 */
package com.github.maumay.jflow.impl;

import java.util.function.UnaryOperator;

/**
 * @author thomasb
 *
 */
public final class ApplicationSource
{
	private ApplicationSource()
	{
	}

	public static class OfObject<E> extends AbstractRichIterator<E>
	{
		private final UnaryOperator<E> applicationOperator;
		private E currentValue;

		public OfObject(UnaryOperator<E> applicationOperator, E initialValue)
		{
			super(InfiniteSize.instance());
			this.applicationOperator = applicationOperator;
			this.currentValue = initialValue;
		}

		@Override
		public boolean hasNext()
		{
			return true;
		}

		@Override
		public E nextImpl()
		{
			E prev = currentValue;
			currentValue = applicationOperator.apply(currentValue);
			return prev;
		}

		@Override
		public void skipImpl()
		{
			currentValue = applicationOperator.apply(currentValue);
		}
	}
}
