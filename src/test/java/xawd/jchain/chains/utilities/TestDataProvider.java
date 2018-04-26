package xawd.jchain.chains.utilities;


import static xawd.jchain.chains.utilities.CollectionUtil.asList;
import static xawd.jchain.chains.utilities.CollectionUtil.len;
import static xawd.jchain.chains.utilities.PrimitiveUtil.isZero;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.primitives.ImmutableDoubleArray;
import com.google.common.primitives.ImmutableIntArray;
import com.google.common.primitives.ImmutableLongArray;

import xawd.jchain.chains.Chain;
import xawd.jchain.chains.DoubleChain;
import xawd.jchain.chains.IntChain;
import xawd.jchain.chains.LongChain;
import xawd.jchain.chains.rangedfunction.RangedDoubleFunction;
import xawd.jchain.chains.rangedfunction.RangedFunction;
import xawd.jchain.chains.rangedfunction.RangedIntFunction;
import xawd.jchain.chains.rangedfunction.RangedLongFunction;

/**
 * @author ThomasB
 * @since 14 Feb 2018
 */
@SuppressWarnings("unused")
public abstract class TestDataProvider
{
	static final int ONE = 1, TWO = 2, THREE = 3, EXTRA_VAL = 4;

	static {
		if (!( ONE < TWO && TWO < THREE && THREE < EXTRA_VAL)) {
			throw new AssertionError();
		}
	}

	static final int[] XS_INT_ARR = {ONE, TWO, THREE};
	static final ImmutableIntArray XS_INT_IMARR = ImmutableIntArray.copyOf(XS_INT_ARR);
	static final IntChain XS_INT_GEN = RangedIntFunction.from(XS_INT_ARR);

	static final double[] XS_DOUBLE_ARR = {ONE, TWO, THREE};
	static final ImmutableDoubleArray XS_DOUBLE_IMARR = ImmutableDoubleArray.copyOf(XS_DOUBLE_ARR);
	static final DoubleChain XS_DOUBLE_GEN = RangedDoubleFunction.from(XS_DOUBLE_ARR);

	static final long[] XS_LONG_ARR = {ONE, TWO, THREE};
	static final ImmutableLongArray XS_LONG_IMARR = ImmutableLongArray.copyOf(XS_LONG_ARR);
	static final LongChain XS_LONG_GEN = RangedLongFunction.from(XS_LONG_ARR);

	static final Integer[] XS_OBJ_ARR = {ONE, TWO, THREE};
	static final List<Integer> XS_OBJ_LIST = new ArrayList<>(asList(XS_OBJ_ARR));
	static final List<Integer> XS_OBJ_IMLIST = ImmutableList.copyOf(XS_OBJ_LIST);
	static final Chain<Integer> XS_OBJ_GEN = RangedFunction.from(XS_OBJ_ARR);


	boolean areEqual(final int[] constraint, final int[] other)
	{
		if (len(constraint) != len(other)) {
			return false;
		}
		for (int i = 0; i < len(constraint); i++) {
			if (constraint[i] != other[i]) {
				return false;
			}
		}
		return true;
	}

	boolean areEqual(final int[] constraint, final ImmutableIntArray other)
	{
		return other.equals(ImmutableIntArray.copyOf(constraint));
	}

	boolean areEqual(final int[] constraint, final IntChain other)
	{
		if (len(constraint) != len(other)) {
			return false;
		}
		for (int i = 0; i < len(constraint); i++) {
			if (constraint[i] != other.elementAt(i)) {
				return false;
			}
		}
		return true;
	}

	boolean areEqual(final int[] constraint, final double[] other)
	{
		if (len(constraint) != len(other)) {
			return false;
		}
		for (int i = 0; i < len(constraint); i++) {
			if (!isZero(constraint[i] - other[i])) {
				return false;
			}
		}
		return true;
	}

	boolean areEqual(final int[] constraint, final ImmutableDoubleArray other)
	{
		if (len(constraint) != len(other)) {
			return false;
		}
		for (int i = 0; i < len(constraint); i++) {
			if (!isZero(constraint[i] - other.get(i))) {
				return false;
			}
		}
		return true;
	}

	boolean areEqual(final int[] constraint, final DoubleChain other)
	{
		if (len(constraint) != len(other)) {
			return false;
		}
		for (int i = 0; i < len(constraint); i++) {
			if (!isZero(constraint[i] - other.elementAt(i))) {
				return false;
			}
		}
		return true;
	}

	boolean areEqual(final int[] constraint, final long[] other)
	{
		if (len(constraint) != len(other)) {
			return false;
		}
		for (int i = 0; i < len(constraint); i++) {
			if (constraint[i] != other[i]) {
				return false;
			}
		}
		return true;
	}

	boolean areEqual(final int[] constraint, final ImmutableLongArray other)
	{
		if (len(constraint) != len(other)) {
			return false;
		}
		for (int i = 0; i < len(constraint); i++) {
			if (constraint[i] != other.get(i)) {
				return false;
			}
		}
		return true;
	}

	boolean areEqual(final int[] constraint, final LongChain other)
	{
		if (len(constraint) != len(other)) {
			return false;
		}
		for (int i = 0; i < len(constraint); i++) {
			if (constraint[i] != other.elementAt(i)) {
				return false;
			}
		}
		return true;
	}

	<T> boolean areEqual(final int[] constraint, final T[] other)
	{
		if (len(constraint) != len(other)) {
			return false;
		}
		for (int i = 0; i < len(constraint); i++) {
			if (!other[i].equals(constraint[i])) {

				return false;
			}
		}
		return true;
	}

	<T> boolean areEqual(final int[] constraint, final List<T> other)
	{
		if (len(constraint) != len(other)) {
			return false;
		}
		for (int i = 0; i < len(constraint); i++) {
			if (!other.get(i).equals(constraint[i])) {
				return false;
			}
		}
		return true;
	}

	<T> boolean areEqual(final int[] constraint, final Chain<T> other)
	{
		if (len(constraint) != len(other)) {
			return false;
		}
		for (int i = 0; i < len(constraint); i++) {
			if (!other.elementAt(i).equals(constraint[i])) {
				return false;
			}
		}
		return true;
	}

	public static void main(final String[] args)
	{
		System.out.println(new Integer(1).equals(1));
	}
}