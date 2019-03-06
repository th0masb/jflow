package com.gihub.maumay.jflow.iterators.misc;

import java.util.Objects;
import java.util.function.Function;

/**
 * Compact two element tuple (pair) inspired by Scala.
 * 
 * @author ThomasB
 */
public final class Tup<T, U>
{
	/**
	 * First element of the pair. Note this is the Scala naming convention for
	 * tuples
	 */
	public final T _1;

	/**
	 * Second element of the pair. Note this is the Scala naming convention for
	 * tuples.
	 */
	public final U _2;

	public Tup(T first, U second)
	{
		this._1 = Objects.requireNonNull(first);
		this._2 = Objects.requireNonNull(second);
	}

	/*
	 * I provide getters so that e.g Tup::_1 can be used instead of a lambda x ->
	 * x._1 (mainly for compatibility with existing code, there is only two
	 * character difference).
	 */
	/**
	 * Retrieve the first (left) element of the pair. Note this is the Scala naming
	 * convention for tuples.
	 * 
	 * @return The first element in this pair.
	 */
	public T _1()
	{
		return _1;
	}

	/**
	 * Retrieve the second (right) element of the pair. Note this is the Scala
	 * naming convention for tuples.
	 * 
	 * @return The second element in this pair.
	 */
	public U _2()
	{
		return _2;
	}

	/**
	 * Maps this tuple to a new instance by applying the two mapping functions to
	 * the left and right elements respectively.
	 * 
	 * @param           <S> The left type of the new tuple.
	 * @param           <R> The right type of the new tuple.
	 * @param leftFunc  The function to apply to the first (left) element of this
	 *                  tuple.
	 * @param rightFunc The function to apply to the second (right) element of this
	 *                  tuple.
	 * @return A new tuple whose first/second element is the image of the
	 *         first/second element of this tuple under the left/right function.
	 */
	public <S, R> Tup<S, R> map(Function<? super T, ? extends S> leftFunc,
			Function<? super U, ? extends R> rightFunc)
	{
		return new Tup<>(leftFunc.apply(_1), rightFunc.apply(_2));
	}

	/**
	 * Maps this tuple to a new instance by applying the given function to the
	 * second (right) element of this tuple whilst retaining the first (left)
	 * element.
	 * 
	 * @param      <R> The right type of the new tuple.
	 * @param func The function to apply to the second (right) element of this
	 *             tuple.
	 * @return A new tuple whose left element is the same as this tuple and whose
	 *         right element is the image of the right element of this tuple under
	 *         the supplied function.
	 */
	public <R> Tup<T, R> rmap(Function<? super U, ? extends R> func)
	{
		return map(Function.identity(), func);
	}

	/**
	 * Maps this tuple to a new instance by applying the given function to the
	 * second (right) element of this tuple whilst retaining the first (left)
	 * element.
	 * 
	 * @param      <S> The left type of the new tuple.
	 * @param func The function to apply to the first (left) element of this tuple.
	 * @return A new tuple whose right element is the same as this tuple and whose
	 *         left element is the image of the left element of this tuple under the
	 *         supplied function.
	 */
	public <S> Tup<S, U> lmap(Function<? super T, ? extends S> func)
	{
		return map(func, Function.identity());
	}

	/**
	 * Creates a new tuple whose type is inferred by the arguments.
	 * 
	 * @param first  The first (left) value of the new tuple.
	 * @param second The second (right) value of the new tuple.
	 * @return A new tuple with the given left and right values.
	 */
	public static <T, U> Tup<T, U> of(T first, U second)
	{
		return new Tup<>(first, second);
	}

	@Override
	public String toString()
	{
		return new StringBuilder("(").append(_1.toString()).append(", ").append(_2.toString())
				.append(")").toString();
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(_1, _2);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Tup<?, ?>) {
			Tup<?, ?> p = (Tup<?, ?>) obj;
			return _1.equals(p._1) && _2.equals(p._2);
		} else {
			return false;
		}
	}
}
