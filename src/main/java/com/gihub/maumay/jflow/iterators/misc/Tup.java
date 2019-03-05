package com.gihub.maumay.jflow.iterators.misc;

import java.util.Objects;
import java.util.function.Function;

/**
 * Compact tuple (pair).
 * 
 * @author ThomasB
 */
public final class Tup<T, U>
{
	/** First element of the pair. */
	public final T _1;

	/** Second element of the pair. */
	public final U _2;

	public Tup(T first, U second)
	{
		this._1 = Objects.requireNonNull(first);
		this._2 = Objects.requireNonNull(second);
	}

	/*
	 * I provide getters so that e.g Pair::_1 can be used instead of a lambda x ->
	 * x._1 (mainly for compatibility with existing code, there is only one
	 * character difference).
	 */
	/**
	 * First element of the pair. I use the Scala naming convention.
	 */
	public T _1()
	{
		return _1;
	}

	/**
	 * Second element of the pair. I use the Scala naming convention.
	 */
	public U _2()
	{
		return _2;
	}

	public <S, R> Tup<S, R> map(Function<? super T, ? extends S> fArg1,
			Function<? super U, ? extends R> fArg2)
	{
		return new Tup<>(fArg1.apply(_1), fArg2.apply(_2));
	}

	public static <T, U> Tup<T, U> of(T t, U u)
	{
		return new Tup<>(t, u);
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
