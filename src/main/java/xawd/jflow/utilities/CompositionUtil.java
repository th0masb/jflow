/**
 *
 */
package xawd.jflow.utilities;

import java.util.function.DoubleFunction;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongFunction;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * @author ThomasB
 * @since 26 Jan 2018
 */
public final class CompositionUtil
{

	private CompositionUtil()
	{
	}

	/**
	 * Performs a standard composition of functions. Note that use of lambdas will often result in
	 * compilation errors since the type system isn't sophisticated enough to handle the ambiguities.
	 *
	 * @param f - The function to apply last
	 * @param g - the function to apply first
	 * @return the composed function f.g
	 */
	public static IntToDoubleFunction compose(final IntToDoubleFunction f, final IntUnaryOperator g)
	{
		return i -> f.applyAsDouble(g.applyAsInt(i));
	}

	/**
	 * Performs a standard composition of functions. Note that use of lambdas will often result in
	 * compilation errors since the type system isn't sophisticated enough to handle the ambiguities.
	 *
	 * @param f - The function to apply last
	 * @param g - the function to apply first
	 * @return the composed function f.g
	 */
	public static IntToDoubleFunction compose(final DoubleUnaryOperator f, final IntToDoubleFunction g)
	{
		return i -> f.applyAsDouble(g.applyAsDouble(i));
	}

	/**
	 * Performs a standard composition of functions. Note that use of lambdas will often result in
	 * compilation errors since the type system isn't sophisticated enough to handle the ambiguities.
	 *
	 * @param f - The function to apply last
	 * @param g - the function to apply first
	 * @return the composed function f.g
	 */
	public static IntUnaryOperator compose(final DoubleToIntFunction f, final IntToDoubleFunction g)
	{
		return i -> f.applyAsInt(g.applyAsDouble(i));
	}

	/**
	 * Performs a standard composition of functions. Note that use of lambdas will often result in
	 * compilation errors since the type system isn't sophisticated enough to handle the ambiguities.
	 *
	 * @param f - The function to apply last
	 * @param g - the function to apply first
	 * @return the composed function f.g
	 */
	public static IntToLongFunction compose(final DoubleToLongFunction f, final IntToDoubleFunction g)
	{
		return i -> f.applyAsLong(g.applyAsDouble(i));
	}

	/**
	 * Performs a standard composition of functions. Note that use of lambdas will often result in
	 * compilation errors since the type system isn't sophisticated enough to handle the ambiguities.
	 *
	 * @param f - The function to apply last
	 * @param g - the function to apply first
	 * @return the composed function f.g
	 */
	public static IntUnaryOperator compose(final LongToIntFunction f, final IntToLongFunction g)
	{
		return i -> f.applyAsInt(g.applyAsLong(i));
	}

	/**
	 * Performs a standard composition of functions. Note that use of lambdas will often result in
	 * compilation errors since the type system isn't sophisticated enough to handle the ambiguities.
	 *
	 * @param f - The function to apply last
	 * @param g - the function to apply first
	 * @return the composed function f.g
	 */
	public static IntToLongFunction compose(final IntToLongFunction f, final IntUnaryOperator g)
	{
		return i -> f.applyAsLong(g.applyAsInt(i));
	}

	/**
	 * Performs a standard composition of functions. Note that use of lambdas will often result in
	 * compilation errors since the type system isn't sophisticated enough to handle the ambiguities.
	 *
	 * @param f - The function to apply last
	 * @param g - the function to apply first
	 * @return the composed function f.g
	 */
	public static IntToDoubleFunction compose(final LongToDoubleFunction f, final IntToLongFunction g)
	{
		return i -> f.applyAsDouble(g.applyAsLong(i));
	}

	/**
	 * Performs a standard composition of functions. Note that use of lambdas will often result in
	 * compilation errors since the type system isn't sophisticated enough to handle the ambiguities.
	 *
	 * @param f - The function to apply last
	 * @param g - the function to apply first
	 * @return the composed function f.g
	 */
	public static IntToLongFunction compose(final LongUnaryOperator f, final IntToLongFunction g)
	{
		return i -> f.applyAsLong(g.applyAsLong(i));
	}

	/**
	 * Performs a standard composition of functions. Note that use of lambdas will often result in
	 * compilation errors since the type system isn't sophisticated enough to handle the ambiguities.
	 *
	 * @param f - The function to apply last
	 * @param g - the function to apply first
	 * @return the composed function f.g
	 */
	public static <T> IntFunction<T> compose(final DoubleFunction<T> f, final IntToDoubleFunction g)
	{
		return i -> f.apply(g.applyAsDouble(i));
	}

	/**
	 * Performs a standard composition of functions. Note that use of lambdas will often result in
	 * compilation errors since the type system isn't sophisticated enough to handle the ambiguities.
	 *
	 * @param f - The function to apply last
	 * @param g - the function to apply first
	 * @return the composed function f.g
	 */
	public static <T> IntFunction<T> compose(final LongFunction<T> f, final IntToLongFunction g)
	{
		return i -> f.apply(g.applyAsLong(i));
	}

	/**
	 * Performs a standard composition of functions. Note that use of lambdas will often result in
	 * compilation errors since the type system isn't sophisticated enough to handle the ambiguities.
	 *
	 * @param f - The function to apply last
	 * @param g - the function to apply first
	 * @return the composed function f.g
	 */
	public static <T> IntFunction<T> compose(final IntFunction<T> f, final IntUnaryOperator g)
	{
		return i -> f.apply(g.applyAsInt(i));
	}

	/**
	 * Performs a standard composition of functions. Note that use of lambdas will often result in
	 * compilation errors since the type system isn't sophisticated enough to handle the ambiguities.
	 *
	 * @param f - The function to apply last
	 * @param g - the function to apply first
	 * @return the composed function f.g
	 */
	public static <T> IntUnaryOperator compose(final ToIntFunction<T> f, final IntFunction<? extends T> g)
	{
		return i -> f.applyAsInt(g.apply(i));
	}

	/**
	 * Performs a standard composition of functions. Note that use of lambdas will often result in
	 * compilation errors since the type system isn't sophisticated enough to handle the ambiguities.
	 *
	 * @param f - The function to apply last
	 * @param g - the function to apply first
	 * @return the composed function f.g
	 */
	public static <T> IntToDoubleFunction compose(final ToDoubleFunction<T> f, final IntFunction<? extends T> g)
	{
		return i -> f.applyAsDouble(g.apply(i));
	}

	/**
	 * Performs a standard composition of functions. Note that use of lambdas will often result in
	 * compilation errors since the type system isn't sophisticated enough to handle the ambiguities.
	 *
	 * @param f - The function to apply last
	 * @param g - the function to apply first
	 * @return the composed function f.g
	 */
	public static <T> IntToLongFunction compose(final ToLongFunction<T> f, final IntFunction<? extends T> g)
	{
		return i -> f.applyAsLong(g.apply(i));
	}

	/**
	 * Performs a standard composition of functions. Note that use of lambdas will often result in
	 * compilation errors since the type system isn't sophisticated enough to handle the ambiguities.
	 *
	 * @param f - The function to apply last
	 * @param g - the function to apply first
	 * @return the composed function f.g
	 */
	public static <T, R> IntFunction<R> compose(final Function<T, R> f, final IntFunction<? extends T> g)
	{
		return i -> f.apply(g.apply(i));
	}

	/**
	 * Performs a standard composition of functions. Note that use of lambdas will often result in
	 * compilation errors since the type system isn't sophisticated enough to handle the ambiguities.
	 *
	 * @param f - The function to apply last
	 * @param g - the function to apply first
	 * @return the composed function f.g
	 */
	public static <T, S, R> Function<T, R> compose(final Function<? super S, R> f, final Function<T, ? extends S> g)
	{
		return g.andThen(f);
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args)
	{
		// final IntToDoubleFunction x = compose(i -> 5 * i, i -> i + 5);
	}
}
