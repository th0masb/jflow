/**
 * Copyright � 2018 Lhasa Limited
 * File created: 23 Apr 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package xawd.jflow;

import java.util.Iterator;
import java.util.OptionalInt;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntUnaryOperator;

import xawd.jflow.zippedpairs.IntPair;
import xawd.jflow.zippedpairs.IntWith;
import xawd.jflow.zippedpairs.IntWithDouble;
import xawd.jflow.zippedpairs.IntWithLong;

/**
 * @author ThomasB
 * @since 23 Apr 2018
 */
public abstract class AbstractIntFlow implements IntFlow
{
	@Override
	public IntFlow map(final IntUnaryOperator f)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Flow<T> mapToObject(final IntFunction<T> f)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleFlow mapToDouble(final IntToDoubleFunction f)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntFlow mapToInt(final IntUnaryOperator f)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Flow<IntWith<T>> zipWith(final Iterator<T> other)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flow<IntPair> zipWith(final OfInt other)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flow<IntWithDouble> zipWith(final OfDouble other)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flow<IntWithLong> zipWith(final OfLong other)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flow<IntPair> enumerate()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntFlow take(final int n)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntFlow takeWhile(final IntPredicate p)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntFlow drop(final int n)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntFlow dropWhile(final IntPredicate p)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntFlow filter(final IntPredicate p)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntFlow append(final int... xs)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntFlow append(final OfInt other)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntFlow insert(final OfInt other)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntFlow insert(final int... xs)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt min()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int min(final int defaultValue)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int minByKey(final int defaultValue, final IntToDoubleFunction key)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OptionalInt minByKey(final IntToDoubleFunction key)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <C extends Comparable<C>> OptionalInt minByObjectKey(final IntFunction<C> key)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalInt max()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int max(final int defaultValue)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int maxByKey(final int defaultValue, final IntToDoubleFunction key)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OptionalInt maxByKey(final IntToDoubleFunction key)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <C extends Comparable<C>> OptionalInt maxByObjectKey(final IntFunction<C> key)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean allMatch(final IntPredicate predicate)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean anyMatch(final IntPredicate predicate)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean noneMatch(final IntPredicate predicate)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int count()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int reduce(final int id, final IntBinaryOperator reducer)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OptionalInt reduce(final IntBinaryOperator reducer)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntFlow accumulate(final IntBinaryOperator accumulator)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntFlow accumulate(final int id, final IntBinaryOperator accumulator)
	{
		// TODO Auto-generated method stub
		return null;
	}

}

/* ---------------------------------------------------------------------*
 * This software is the confidential and proprietary
 * information of Lhasa Limited
 * Granary Wharf House, 2 Canal Wharf, Leeds, LS11 5PS
 * ---
 * No part of this confidential information shall be disclosed
 * and it shall be used only in accordance with the terms of a
 * written license agreement entered into by holder of the information
 * with LHASA Ltd.
 * --------------------------------------------------------------------- */