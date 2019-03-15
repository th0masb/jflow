/**
 *
 */
package com.github.maumay.jflow.impl;

import java.util.NoSuchElementException;
import java.util.OptionalLong;
import java.util.function.LongBinaryOperator;

import com.github.maumay.jflow.utils.Exceptions;

/**
 * @author ThomasB
 */
public final class LongReductionConsumption
{
	private LongReductionConsumption()
	{
	}

	public static OptionalLong foldOption(AbstractLongIterator source, LongBinaryOperator reducer)
	{
		Exceptions.require(source.hasOwnership());
		boolean reductionUninitialised = true;
		long reduction = -1;
		while (source.hasNext()) {
			if (reductionUninitialised) {
				reduction = source.nextLongImpl();
				reductionUninitialised = false;
			} else {
				reduction = reducer.applyAsLong(reduction, source.nextLongImpl());
			}
		}
		return reductionUninitialised ? OptionalLong.empty() : OptionalLong.of(reduction);
	}

	public static long fold(AbstractLongIterator source, LongBinaryOperator reducer)
	{
		Exceptions.require(source.hasOwnership());
		boolean reductionUninitialised = true;
		long reduction = -1;
		while (source.hasNext()) {
			if (reductionUninitialised) {
				reduction = source.nextLongImpl();
				reductionUninitialised = false;
			} else {
				reduction = reducer.applyAsLong(reduction, source.nextLongImpl());
			}
		}
		if (reductionUninitialised) {
			throw new NoSuchElementException("Attempted fold on empty iterator");
		} else {
			return reduction;
		}
	}

	public static long fold(AbstractLongIterator source, long id, LongBinaryOperator reducer)
	{
		Exceptions.require(source.hasOwnership());
		long reduction = id;
		while (source.hasNext()) {
			reduction = reducer.applyAsLong(reduction, source.nextLongImpl());
		}
		return reduction;
	}

	public static long count(AbstractLongIterator source)
	{
		Exceptions.require(source.hasOwnership());
		long count = 0;
		while (source.hasNext()) {
			source.skipImpl();
			count++;
		}
		return count;
	}
}
