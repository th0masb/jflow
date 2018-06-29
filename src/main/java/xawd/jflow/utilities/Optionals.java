/**
 *
 */
package xawd.jflow.utilities;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.LongUnaryOperator;

/**
 * Provides methods for working with optional values.
 *
 * @author ThomasB
 */
public final class Optionals
{
	private Optionals()
	{
	}

	/**
	 * Static factory for wrapping values in optional instances.
	 *
	 * @param t
	 *            The value to wrap in an optional.
	 * @return An optional wrapping the parameter value.
	 */
	public static <T> Optional<T> of(T t)
	{
		return Optional.of(t);
	}

	/**
	 * Static factory for wrapping values in optional instances.
	 *
	 * @param x
	 *            The value to wrap in an optional.
	 * @return An optional wrapping the parameter value.
	 */
	public static OptionalInt ofInt(int x)
	{
		return OptionalInt.of(x);
	}

	/**
	 * Static factory for wrapping values in optional instances.
	 *
	 * @param x
	 *            The value to wrap in an optional.
	 * @return An optional wrapping the parameter value.
	 */
	public static OptionalDouble ofInt(double x)
	{
		return OptionalDouble.of(x);
	}

	/**
	 * Static factory for wrapping values in optional instances.
	 *
	 * @param x
	 *            The value to wrap in an optional.
	 * @return An optional wrapping the parameter value.
	 */
	public static OptionalLong ofLong(int x)
	{
		return OptionalLong.of(x);
	}

	/**
	 * Applies a function to a given optional value if it exists.
	 *
	 * @param f
	 *            The mapping function.
	 * @param x
	 *            The optional value.
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
	 * @param f
	 *            The mapping function.
	 * @param x
	 *            The optional value.
	 * @return An optional wrapping the mapped value if the original value existed.
	 *         Otherwise nothing is returned.
	 */
	public static OptionalDouble map(DoubleUnaryOperator f, OptionalDouble x)
	{
		return x.isPresent() ? OptionalDouble.of(f.applyAsDouble(x.getAsDouble())) : OptionalDouble.empty();
	}

	/**
	 * Applies a function to a given optional value if it exists.
	 *
	 * @param f
	 *            The mapping function.
	 * @param x
	 *            The optional value.
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
	 * @param x
	 *            The optional to extract a value from.
	 * @return The value inside the optional if it exists.
	 */
	public static <T> T getOrError(Optional<? extends T> x)
	{
		return x.orElseThrow(() -> new AssertionError("Attempted to retrieve value from empty optional."));
	}

	/**
	 * Given an optional value this method attempts to retrieve the value contained
	 * inside, if the optional is empty an {@linkplain AssertionError} is thrown.
	 *
	 * @param x
	 *            The optional to extract a value from.
	 * @return The value inside the optional if it exists.
	 */
	public static int getOrError(OptionalInt x)
	{
		return x.orElseThrow(() -> new AssertionError("Attempted to retrieve value from empty optional."));
	}

	/**
	 * Given an optional value this method attempts to retrieve the value contained
	 * inside, if the optional is empty an {@linkplain AssertionError} is thrown.
	 *
	 * @param x
	 *            The optional to extract a value from.
	 * @return The value inside the optional if it exists.
	 */
	public static double getOrError(OptionalDouble x)
	{
		return x.orElseThrow(() -> new AssertionError("Attempted to retrieve value from empty optional."));
	}

	/**
	 * Given an optional value this method attempts to retrieve the value contained
	 * inside, if the optional is empty an {@linkplain AssertionError} is thrown.
	 *
	 * @param x
	 *            The optional to extract a value from.
	 * @return The value inside the optional if it exists.
	 */
	public static double getOrError(OptionalLong x)
	{
		return x.orElseThrow(() -> new AssertionError("Attempted to retrieve value from empty optional."));
	}

	/**
	 * An addition function acting on optional values.
	 *
	 * @param first
	 *            An optional value
	 * @param second
	 *            An optional value
	 * @return An optional wrapping the result of adding the numbers wrapped by the
	 *         provided optionals if they both exist, nothing otherwise.
	 */
	public static OptionalInt add(OptionalInt first, OptionalInt second)
	{
		return first.isPresent() && second.isPresent()
				? OptionalInt.of(first.getAsInt() + second.getAsInt()) : OptionalInt.empty();
	}

	/**
	 * An addition function acting on optional values.
	 *
	 * @param first
	 *            An optional value
	 * @param second
	 *            An optional value
	 * @return An optional wrapping the result of adding the numbers wrapped by the
	 *         provided optionals if they both exist, nothing otherwise.
	 */
	public static OptionalDouble add(OptionalDouble first, OptionalDouble second)
	{
		return first.isPresent() && second.isPresent()
				? OptionalDouble.of(first.getAsDouble() + second.getAsDouble()) : OptionalDouble.empty();
	}

	/**
	 * An addition function acting on optional values.
	 *
	 * @param first
	 *            An optional value
	 * @param second
	 *            An optional value
	 * @return An optional wrapping the result of adding the numbers wrapped by the
	 *         provided optionals if they both exist, nothing otherwise.
	 */
	public static OptionalLong add(OptionalLong first, OptionalLong second)
	{
		return first.isPresent() && second.isPresent()
				? OptionalLong.of(first.getAsLong() + second.getAsLong()) : OptionalLong.empty();
	}
}
