/**
 * 
 */
package com.github.maumay.jflow.impl;

/**
 * @author thomasb
 */
public final class FibonnaciIterator extends AbstractLongIterator
{
	private long count = 0;
	private long x1, x2;

	public FibonnaciIterator(int first, int second)
	{
		super(InfiniteSize.instance());
		x1 = first;
		x2 = second;
	}

	@Override
	public long nextLongImpl()
	{
		if (count < 2) {
			count++;
			return count == 1 ? x1 : x2;
		} else {
			long nextFib = Math.addExact(x1, x2);
			x1 = x2;
			x2 = nextFib;
			return nextFib;
		}
	}

	@Override
	public void forwardImpl()
	{
		nextLongImpl();
	}

	@Override
	public boolean hasNext()
	{
		return true;
	}
}
