/**
 * Copyright © 2018 Lhasa Limited
 * File created: 23 Apr 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package xawd.jflow;

import java.util.Iterator;
import java.util.OptionalDouble;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;

import xawd.jflow.zippedpairs.DoublePair;
import xawd.jflow.zippedpairs.DoubleWith;
import xawd.jflow.zippedpairs.DoubleWithLong;
import xawd.jflow.zippedpairs.IntWithDouble;

/**
 * @author ThomasB
 * @since 23 Apr 2018
 */
public abstract class AbstractDoubleFlow implements DoubleFlow
{
	@Override
	public DoubleFlow map(final DoubleUnaryOperator f)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Flow<T> mapToObject(final DoubleFunction<T> f)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LongFlow mapToLong(final DoubleToLongFunction f)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleFlow mapToInt(final DoubleToIntFunction f)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Flow<DoubleWith<T>> zipWith(final Iterator<T> other)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flow<DoublePair> zipWith(final OfDouble other)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flow<DoubleWithLong> zipWith(final OfLong other)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flow<IntWithDouble> zipWith(final OfInt other)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flow<IntWithDouble> enumerate()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleFlow take(final int n)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleFlow takeWhile(final DoublePredicate p)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleFlow drop(final int n)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleFlow dropWhile(final DoublePredicate p)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleFlow filter(final DoublePredicate p)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleFlow append(final double x)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleFlow append(final OfDouble other)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleFlow insert(final OfDouble other)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleFlow insert(final double x)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalDouble min()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double min(final double defaultValue)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double minByKey(final double defaultValue, final DoubleUnaryOperator key)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OptionalDouble minByKey(final DoubleUnaryOperator key)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <C extends Comparable<C>> OptionalDouble minByObjectKey(final DoubleFunction<C> key)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OptionalDouble max()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double max(final double defaultValue)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double maxByKey(final double defaultValue, final DoubleUnaryOperator key)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OptionalDouble maxByKey(final DoubleUnaryOperator key)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <C extends Comparable<C>> OptionalDouble maxByObjectKey(final DoubleFunction<C> key)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean allMatch(final DoublePredicate predicate)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean anyMatch(final DoublePredicate predicate)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean noneMatch(final DoublePredicate predicate)
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
	public double reduce(final double id, final DoubleBinaryOperator reducer)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OptionalDouble reduce(final DoubleBinaryOperator reducer)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleFlow accumulate(final DoubleBinaryOperator accumulator)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DoubleFlow accumulate(final double id, final DoubleBinaryOperator accumulator)
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