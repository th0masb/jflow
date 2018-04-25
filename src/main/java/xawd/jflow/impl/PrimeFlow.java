package xawd.jflow.impl;

import java.util.ArrayList;
import java.util.List;

import xawd.jflow.AbstractIntFlow;

/**
 * Not implemented yet
 *
 * @author ThomasB
 * @since 24 Apr 2018
 */
public class PrimeFlow extends AbstractIntFlow
{
	private static final int DEFAULT_ARRAY_SIZE = 20;

	private final List<int[]> cachedPrimes;

	public PrimeFlow()
	{
		cachedPrimes = new ArrayList<>();
		cachedPrimes.add(new int[DEFAULT_ARRAY_SIZE]);

	}

	@Override
	public int nextInt()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasNext()
	{
		return true;
	}

	@Override
	public void skip()
	{
		nextInt();
	}

}
