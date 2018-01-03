/**
 * 
 */
package io.xyz.common.funcutils;

import java.util.function.DoubleFunction;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;

/**
 * @author t
 *
 */
public final class CompositionUtil {

	private CompositionUtil() {
	}

	public static IntToDoubleFunction compose(final IntToDoubleFunction f, final IntUnaryOperator g) {
		return i -> f.applyAsDouble(g.applyAsInt(i));
	}

	public static IntToDoubleFunction compose(final DoubleUnaryOperator f, final IntToDoubleFunction g) {
		return i -> f.applyAsDouble(g.applyAsDouble(i));
	}

	public static IntUnaryOperator compose(final DoubleToIntFunction f, final IntToDoubleFunction g) {
		return i -> f.applyAsInt(g.applyAsDouble(i));
	}

	public static <T> IntFunction<T> compose(final DoubleFunction<T> f, final IntToDoubleFunction g) {
		return i -> f.apply(g.applyAsDouble(i));
	}

	public static <T> IntFunction<T> compose(final IntFunction<T> f, final IntUnaryOperator g) {
		return i -> f.apply(g.applyAsInt(i));
	}

	public static <T> IntUnaryOperator compose(final ToIntFunction<T> f, final IntFunction<T> g) {
		return i -> f.applyAsInt(g.apply(i));
	}

	public static <T> IntToDoubleFunction compose(final ToDoubleFunction<T> f, final IntFunction<T> g) {
		return i -> f.applyAsDouble(g.apply(i));
	}

	public static <T> IntFunction<T> compose(final UnaryOperator<T> f, final IntFunction<T> g) {
		return i -> f.apply(g.apply(i));
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		// TODO Auto-generated method stub
	}
}
