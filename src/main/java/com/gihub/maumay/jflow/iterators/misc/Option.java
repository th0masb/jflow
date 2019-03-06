/**
 *
 */
package com.gihub.maumay.jflow.iterators.misc;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.LongUnaryOperator;

/**
 * Static utility methods for working with optional values.
 *
 * @author ThomasB
 */
public final class Option
{
	private Option()
	{
	}

	/**
	 * Boxes this primitive optional value.
	 * 
	 * @return a boxed version of the wrapped primitive if it exists, nothing
	 *         otherwise.
	 */
	public static Optional<Double> box(OptionalDouble x)
	{
		if (x.isPresent()) {
			return Optional.of(x.getAsDouble());
		} else {
			return Optional.empty();
		}
	}

	/**
	 * Boxes this primitive optional value.
	 * 
	 * @return a boxed version of the wrapped primitive if it exists, nothing
	 *         otherwise.
	 */
	public static Optional<Integer> box(OptionalInt x)
	{
		if (x.isPresent()) {
			return Optional.of(x.getAsInt());
		} else {
			return Optional.empty();
		}
	}

	/**
	 * Boxes this primitive optional value.
	 * 
	 * @return a boxed version of the wrapped primitive if it exists, nothing
	 *         otherwise.
	 */
	public static Optional<Long> box(OptionalLong x)
	{
		if (x.isPresent()) {
			return Optional.of(x.getAsLong());
		} else {
			return Optional.empty();
		}
	}

	/**
	 * Performs a 'safe-cast' of a source object to a type specified by the given
	 * class parameter.
	 * 
	 * @param            <R> The type which we will attempt to cast the object
	 *                   reference to.
	 * @param src        The object to cast.
	 * @param targetType The type to attempt to cast to.
	 * @return The result of the cast if the given object is an instance of the
	 *         target type. Nothing otherwise.
	 */
	@SuppressWarnings("unchecked")
	public static <R> Optional<R> cast(Object src, Class<R> targetType)
	{
		if (targetType.isInstance(src)) {
			return Optional.of((R) src);
		} else {
			return Optional.empty();
		}
	}

	/**
	 * Wraps a value in an optional.
	 *
	 * @param   <T> The type of the wrapped object reference.
	 * @param t The value to wrap in an optional.
	 * @return An optional wrapping the parameter value.
	 */
	public static <T> Optional<T> of(T t)
	{
		return Optional.of(t);
	}

	/**
	 * Creates an empty optional.
	 * 
	 * @param <T> The inferred type parameterisation of the returned optional.
	 * @return an empty optional.
	 */
	public static <T> Optional<T> empty()
	{
		return Optional.empty();
	}

	/**
	 * Wraps a value in an optional.
	 *
	 * @param x The value to wrap in an optional.
	 * @return An optional wrapping the parameter value.
	 */
	public static OptionalInt ofInt(int x)
	{
		return OptionalInt.of(x);
	}

	/**
	 * Creates an empty optional.
	 * 
	 * @return an empty optional.
	 */
	public static OptionalInt emptyInt()
	{
		return OptionalInt.empty();
	}

	/**
	 * Wraps a value in an optional.
	 *
	 * @param x The value to wrap in an optional.
	 * @return An optional wrapping the parameter value.
	 */
	public static OptionalDouble ofDouble(double x)
	{
		return OptionalDouble.of(x);
	}

	/**
	 * Creates an empty optional.
	 * 
	 * @return an empty optional.
	 */
	public static OptionalDouble emptyDouble()
	{
		return OptionalDouble.empty();
	}

	/**
	 * Wraps a value in an optional.
	 *
	 * @param x The value to wrap in an optional.
	 * @return An optional wrapping the parameter value.
	 */
	public static OptionalLong ofLong(int x)
	{
		return OptionalLong.of(x);
	}

	/**
	 * Creates an empty optional.
	 * 
	 * @return an empty optional.
	 */
	public static OptionalLong emptyLong()
	{
		return OptionalLong.empty();
	}

	/**
	 * Applies a function to a given optional value if it exists.
	 *
	 * @param f The mapping function.
	 * @param x The optional value.
	 * @return An optional wrapping the mapped value if the original value existed.
	 *         Otherwise nothing is returned.
	 */
	public static OptionalInt map(IntUnaryOperator f, OptionalInt x)
	{
		return x.isPresent() ? OptionalInt.of(f.applyAsInt(x.getAsInt())) : OptionalInt.empty();
	}

	/**
	 * Applies a function to a given optional value if it exists.
	 *
	 * @param f The mapping function.
	 * @param x The optional value.
	 * @return An optional wrapping the mapped value if the original value existed.
	 *         Otherwise nothing is returned.
	 */
	public static OptionalDouble map(DoubleUnaryOperator f, OptionalDouble x)
	{
		return x.isPresent() ? OptionalDouble.of(f.applyAsDouble(x.getAsDouble()))
				: OptionalDouble.empty();
	}

	/**
	 * Applies a function to a given optional value if it exists.
	 *
	 * @param f The mapping function.
	 * @param x The optional value.
	 * @return An optional wrapping the mapped value if the original value existed.
	 *         Otherwise nothing is returned.
	 */
	public static OptionalLong map(LongUnaryOperator f, OptionalLong x)
	{
		return x.isPresent() ? OptionalLong.of(f.applyAsLong(x.getAsLong())) : OptionalLong.empty();
	}

	/**
	 * Given an optional value this method attempts to retrieve the value contained
	 * inside, if the optional is empty an {@linkplain AssertionError} is thrown.
	 *
	 * @param   <T> The type of the value wrapped by the passed optional.
	 * @param x The optional to extract a value from.
	 * @return The value inside the optional if it exists.
	 */
	public static <T> T extract(Optional<? extends T> x, String errorMessage)
	{
		return x.orElseThrow(() -> new NoSuchElementException(errorMessage));
	}

	/**
	 * Given an optional value this method attempts to retrieve the value contained
	 * inside, if the optional is empty an {@linkplain AssertionError} is thrown.
	 *
	 * @param x The optional to extract a value from.
	 * @return The value inside the optional if it exists.
	 */
	public static int extract(OptionalInt x, String errorMessage)
	{
		return x.orElseThrow(() -> new NoSuchElementException(errorMessage));
	}

	/**
	 * Given an optional value this method attempts to retrieve the value contained
	 * inside, if the optional is empty an {@linkplain AssertionError} is thrown.
	 *
	 * @param x The optional to extract a value from.
	 * @return The value inside the optional if it exists.
	 */
	public static double extract(OptionalDouble x, String errorMessage)
	{
		return x.orElseThrow(() -> new NoSuchElementException(errorMessage));
	}

	/**
	 * Given an optional value this method attempts to retrieve the value contained
	 * inside, if the optional is empty an {@linkplain AssertionError} is thrown.
	 *
	 * @param x The optional to extract a value from.
	 * @return The value inside the optional if it exists.
	 */
	public static double extract(OptionalLong x, String errorMessage)
	{
		return x.orElseThrow(() -> new NoSuchElementException(errorMessage));
	}

	/**
	 * An addition function acting on optional values.
	 *
	 * @param first  An optional value
	 * @param second An optional value
	 * @return An optional wrapping the result of adding the numbers wrapped by the
	 *         provided optionals if they both exist, nothing otherwise.
	 */
	public static OptionalInt add(OptionalInt first, OptionalInt second)
	{
		return first.isPresent() && second.isPresent()
				? OptionalInt.of(first.getAsInt() + second.getAsInt())
				: OptionalInt.empty();
	}

	/**
	 * An addition function acting on optional values.
	 *
	 * @param first  An optional value
	 * @param second An optional value
	 * @return An optional wrapping the result of adding the numbers wrapped by the
	 *         provided optionals if they both exist, nothing otherwise.
	 */
	public static OptionalDouble add(OptionalDouble first, OptionalDouble second)
	{
		return first.isPresent() && second.isPresent()
				? OptionalDouble.of(first.getAsDouble() + second.getAsDouble())
				: OptionalDouble.empty();
	}

	/**
	 * An addition function acting on optional values.
	 *
	 * @param first  An optional value
	 * @param second An optional value
	 * @return An optional wrapping the result of adding the numbers wrapped by the
	 *         provided optionals if they both exist, nothing otherwise.
	 */
	public static OptionalLong add(OptionalLong first, OptionalLong second)
	{
		return first.isPresent() && second.isPresent()
				? OptionalLong.of(first.getAsLong() + second.getAsLong())
				: OptionalLong.empty();
	}

	/**
	 * Runs a given procedure if the given optional is empty.
	 */
	public static void ifAbsent(Optional<?> optional, Runnable procedure)
	{
		if (!optional.isPresent()) {
			procedure.run();
		}
	}
}
