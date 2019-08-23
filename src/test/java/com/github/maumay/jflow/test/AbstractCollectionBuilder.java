/**
 * 
 */
package com.github.maumay.jflow.test;

import java.util.Arrays;
import java.util.List;

import com.github.maumay.jflow.vec.DoubleVec;
import com.github.maumay.jflow.vec.Vec;

/**
 * @author thomasb
 *
 */
public abstract class AbstractCollectionBuilder
{
	@SafeVarargs
	protected final <T> List<T> list(T... ts)
	{
		return Arrays.asList(ts);
	}

	// @SafeVarargs
	// protected final <T> Set<T> set(T... ts)
	// {
	// return new HashSet<>(list(ts));
	// }

	@SafeVarargs
	protected final <T> Vec<T> vec(T... ts)
	{
		return Vec.of(ts);
	}

	protected final DoubleVec dvec(double... xs)
	{
		return DoubleVec.of(xs);
	}
}
