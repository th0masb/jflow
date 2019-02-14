package maumay.jflow.iterators.misc;

import java.util.Objects;
import java.util.function.Function;

/**
 * Compact pair.
 * 
 * @author ThomasB
 */
public final class Pair<T, U>
{
	/** First element of the pair. */
	public final T _1;

	/** Second element of the pair. */
	public final U _2;

	public Pair(T first, U second)
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

	public <T0, U0> Pair<T0, U0> map(Function<? super T, ? extends T0> fArg1,
			Function<? super U, ? extends U0> fArg2)
	{
		return new Pair<>(fArg1.apply(_1), fArg2.apply(_2));
	}

	public static <T, U> Pair<T, U> of(T t, U u)
	{
		return new Pair<>(t, u);
	}

	@Override
	public String toString()
	{
		return new StringBuilder("(").append(_1.toString()).append(", ")
				.append(_2.toString()).append(")").toString();
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(_1, _2);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Pair<?, ?>) {
			Pair<?, ?> p = (Pair<?, ?>) obj;
			return _1.equals(p._1) && _2.equals(p._2);
		} else {
			return false;
		}
	}
}
