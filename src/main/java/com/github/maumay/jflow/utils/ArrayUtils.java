/**
 * 
 */
package com.github.maumay.jflow.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import com.github.maumay.jflow.iterators.EnhancedIterator;
import com.github.maumay.jflow.iterators.factories.Iter;
import com.github.maumay.jflow.vec.Vec;

/**
 * @author ThomasB
 *
 */
public final class ArrayUtils
{
	private ArrayUtils()
	{
	}

	// Combining
	/**
	 * Combines two arrays into a single array via a binary operator.
	 *
	 * @param combiner The operator describing how elements are combined.
	 * @param xs       The first array.
	 * @param ys       The second array.
	 * @return Let {@code f} denote the combiner operator. Then we return an array
	 *         {@code zs} defined by:
	 *         <ul>
	 *         <li>{@code zs[i] = f(xs[i], ys[i])}</li>
	 *         <li>{@code sizeOf(zs) = min(xs.length, ys.length)}</li>
	 *         </ul>
	 */
	public static int[] combine(IntBinaryOperator combiner, int[] xs, int[] ys)
	{
		int newLength = Math.min(xs.length, ys.length);
		int[] combined = new int[newLength];
		for (int i = 0; i < newLength; i++) {
			combined[i] = combiner.applyAsInt(xs[i], ys[i]);
		}
		return combined;
	}

	/**
	 * Combines two arrays into a single array via a binary operator.
	 *
	 * @param combiner The operator describing how elements are combined.
	 * @param xs       The first array.
	 * @param ys       The second array.
	 * @return Let {@code f} denote the combiner operator. Then we return an array
	 *         {@code zs} defined by:
	 *         <ul>
	 *         <li>{@code zs[i] = f(xs[i], ys[i])}</li>
	 *         <li>{@code sizeOf(zs) = min(xs.length, ys.length)}</li>
	 *         </ul>
	 */
	public static double[] combine(DoubleBinaryOperator combiner, double[] xs, double[] ys)
	{
		int newLength = Math.min(xs.length, ys.length);
		double[] combined = new double[newLength];
		for (int i = 0; i < newLength; i++) {
			combined[i] = combiner.applyAsDouble(xs[i], ys[i]);
		}
		return combined;
	}

	/**
	 * Combines two arrays into a single array via a binary operator.
	 *
	 * @param combiner The operator describing how elements are combined.
	 * @param xs       The first array.
	 * @param ys       The second array.
	 * @return Let {@code f} denote the combiner operator. Then we return an array
	 *         {@code zs} defined by:
	 *         <ul>
	 *         <li>{@code zs[i] = f(xs[i], ys[i])}</li>
	 *         <li>{@code sizeOf(zs) = min(xs.length, ys.length)}</li>
	 *         </ul>
	 */
	public static long[] combine(LongBinaryOperator combiner, long[] xs, long[] ys)
	{
		int newLength = Math.min(xs.length, ys.length);
		long[] combined = new long[newLength];
		for (int i = 0; i < newLength; i++) {
			combined[i] = combiner.applyAsLong(xs[i], ys[i]);
		}
		return combined;
	}

	// Filtering
	/**
	 * Filters an array according to some predicate.
	 *
	 * @param p  The predicate.
	 * @param xs The source.
	 * @return An array which contains only those elements in the source who pass
	 *         the predicate test.
	 */
	public static double[] filter(DoublePredicate p, double[] xs)
	{
		int arrcount = 0;
		double[] filtered = new double[xs.length];
		for (double x : xs) {
			if (p.test(x)) {
				filtered[arrcount++] = x;
			}
		}
		return Arrays.copyOf(filtered, arrcount);
	}

	/**
	 * Filters an array according to some predicate.
	 *
	 * @param p  The predicate.
	 * @param xs The source.
	 * @return An array which contains only those elements in the source who pass
	 *         the predicate test.
	 */
	public static int[] filter(IntPredicate p, int[] xs)
	{
		int arrcount = 0;
		int[] filtered = new int[xs.length];
		for (int x : xs) {
			if (p.test(x)) {
				filtered[arrcount++] = x;
			}
		}
		return Arrays.copyOf(filtered, arrcount);
	}

	/**
	 * Filters an array according to some predicate.
	 *
	 * @param p  The predicate.
	 * @param xs The source.
	 * @return An array which contains only those elements in the source who pass
	 *         the predicate test.
	 */
	public static long[] filter(LongPredicate p, long[] xs)
	{
		int arrcount = 0;
		long[] filtered = new long[xs.length];
		for (long x : xs) {
			if (p.test(x)) {
				filtered[arrcount++] = x;
			}
		}
		return Arrays.copyOf(filtered, arrcount);
	}

	// Folding
	/**
	 * Reduces an array to a single value via some operator in a right associative
	 * manner.
	 *
	 * @param foldFunction The binary operator describing how elements fold together
	 * @param id           The initial value of the fold
	 * @param xs           The array to fold
	 * @return Let us denote the fold operator by {@code *} and the array by
	 *         {@code [xs[0], ..., xs[n]]}. Then the result is:<br>
	 *         <br>
	 *         {@code x[0] * (x[1] * (... * (x[n-1] * (x[n] * id))...)}
	 */
	public static int foldr(IntBinaryOperator foldFunction, int id, int[] xs)
	{
		int cumulativeFold = id;
		for (int i = xs.length - 1; i > -1; i--) {
			cumulativeFold = foldFunction.applyAsInt(xs[i], cumulativeFold);
		}
		return cumulativeFold;
	}

	/**
	 * Reduces an array to a single value via some operator in a right associative
	 * manner.
	 *
	 * @param foldFunction The binary operator describing how elements fold together
	 * @param id           The initial value of the fold
	 * @param xs           The array to fold
	 * @return Let us denote the fold operator by {@code *} and the array by
	 *         {@code [xs[0], ..., xs[n]]}. Then the result is:<br>
	 *         <br>
	 *         {@code x[0] * (x[1] * (... * (x[n-1] * (x[n] * id))...)}
	 */
	public static double foldr(DoubleBinaryOperator foldFunction, double id, double[] xs)
	{
		double cumulativeFold = id;
		for (int i = xs.length - 1; i > -1; i--) {
			cumulativeFold = foldFunction.applyAsDouble(xs[i], cumulativeFold);
		}
		return cumulativeFold;
	}

	/**
	 * Reduces an array to a single value via some operator in a right associative
	 * manner.
	 *
	 * @param foldFunction The binary operator describing how elements fold together
	 * @param id           The initial value of the fold
	 * @param xs           The array to fold
	 * @return Let us denote the fold operator by {@code *} and the array by
	 *         {@code [xs[0], ..., xs[n]]}. Then the result is:<br>
	 *         <br>
	 *         {@code x[0] * (x[1] * (... * (x[n-1] * (x[n] * id))...)}
	 */
	public static long foldr(LongBinaryOperator foldFunction, long id, long[] xs)
	{
		long cumulativeFold = id;
		for (int i = xs.length - 1; i > -1; i--) {
			cumulativeFold = foldFunction.applyAsLong(xs[i], cumulativeFold);
		}
		return cumulativeFold;
	}

	// Mapping
	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce a double array.
	 *
	 * @param f  The function defining the mapping.
	 * @param xs The source array.
	 * @return A double array of the same length as the source containing the mapped
	 *         values of the source elements with order retained.
	 */
	public static double[] doubleMap(DoubleUnaryOperator f, double[] xs)
	{
		double[] mapped = new double[xs.length];
		for (int i = 0; i < xs.length; i++) {
			mapped[i] = f.applyAsDouble(xs[i]);
		}
		return mapped;
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce a double array.
	 *
	 * @param f  The function defining the mapping.
	 * @param xs The source array.
	 * @return A double array of the same length as the source containing the mapped
	 *         values of the source elements with order retained.
	 */
	public static double[] doubleMap(IntToDoubleFunction f, int[] xs)
	{
		double[] mapped = new double[xs.length];
		for (int i = 0; i < xs.length; i++) {
			mapped[i] = f.applyAsDouble(xs[i]);
		}
		return mapped;
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce a double array.
	 *
	 * @param f  The function defining the mapping.
	 * @param xs The source array.
	 * @return A double array of the same length as the source containing the mapped
	 *         values of the source elements with order retained.
	 */
	public static double[] doubleMap(LongToDoubleFunction f, long[] xs)
	{
		double[] mapped = new double[xs.length];
		for (int i = 0; i < xs.length; i++) {
			mapped[i] = f.applyAsDouble(xs[i]);
		}
		return mapped;
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite input
	 * collection to produce a double array.
	 *
	 * @param    <E> The source element type.
	 * @param f  The function defining the mapping.
	 * @param xs The source collection.
	 * @return An double array of the same length as the source containing the
	 *         mapped values of the source elements with order retained if the input
	 *         has a defined order.
	 */
	public static <E> double[] doubleMap(ToDoubleFunction<? super E> f, Collection<? extends E> xs)
	{
		double[] dest = new double[xs.size()];
		int counter = 0;
		for (Iterator<? extends E> itr = xs.iterator(); itr.hasNext();) {
			dest[counter++] = f.applyAsDouble(itr.next());
		}
		return dest;
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce an int array.
	 *
	 * @param f  The function defining the mapping.
	 * @param xs The source array.
	 * @return An int array of the same length as the source containing the mapped
	 *         values of the source elements with order retained.
	 */
	public static int[] intMap(IntUnaryOperator f, int[] xs)
	{
		int[] mapped = new int[xs.length];
		for (int i = 0; i < xs.length; i++) {
			mapped[i] = f.applyAsInt(xs[i]);
		}
		return mapped;
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce an int array.
	 *
	 * @param f  The function defining the mapping.
	 * @param xs The source array.
	 * @return An int array of the same length as the source containing the mapped
	 *         values of the source elements with order retained.
	 */
	public static int[] intMap(DoubleToIntFunction f, double[] xs)
	{
		int[] mapped = new int[xs.length];
		for (int i = 0; i < xs.length; i++) {
			mapped[i] = f.applyAsInt(xs[i]);
		}
		return mapped;
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce an int array.
	 *
	 * @param f  The function defining the mapping.
	 * @param xs The source array.
	 * @return An int array of the same length as the source containing the mapped
	 *         values of the source elements with order retained.
	 */
	public static int[] intMap(LongToIntFunction f, long[] xs)
	{
		int[] mapped = new int[xs.length];
		for (int i = 0; i < xs.length; i++) {
			mapped[i] = f.applyAsInt(xs[i]);
		}
		return mapped;
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite input
	 * collection to produce a int array.
	 *
	 * @param    <E> The source element type.
	 * @param f  The function defining the mapping.
	 * @param xs The source collection.
	 * @return An int array of the same length as the source containing the mapped
	 *         values of the source elements with order retained if the input has a
	 *         defined order.
	 */
	public static <E> int[] intMap(ToIntFunction<? super E> f, Collection<? extends E> xs)
	{
		int[] dest = new int[xs.size()];
		int counter = 0;
		for (Iterator<? extends E> itr = xs.iterator(); itr.hasNext();) {
			dest[counter++] = f.applyAsInt(itr.next());
		}
		return dest;
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce an long array.
	 *
	 * @param f  The function defining the mapping.
	 * @param xs The source array.
	 * @return A long array of the same length as the source containing the mapped
	 *         values of the source elements with order retained.
	 */
	public static long[] longMap(LongUnaryOperator f, long[] xs)
	{
		long[] mapped = new long[xs.length];
		for (int i = 0; i < xs.length; i++) {
			mapped[i] = f.applyAsLong(xs[i]);
		}
		return mapped;
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce an long array.
	 *
	 * @param f  The function defining the mapping.
	 * @param xs The source array.
	 * @return A long array of the same length as the source containing the mapped
	 *         values of the source elements with order retained.
	 */
	public static long[] longMap(IntToLongFunction f, int[] xs)
	{
		long[] mapped = new long[xs.length];
		for (int i = 0; i < xs.length; i++) {
			mapped[i] = f.applyAsLong(xs[i]);
		}
		return mapped;
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce an long array.
	 *
	 * @param f  The function defining the mapping.
	 * @param xs The source array.
	 * @return A long array of the same length as the source containing the mapped
	 *         values of the source elements with order retained.
	 */
	public static long[] longMap(DoubleToLongFunction f, double[] xs)
	{
		long[] mapped = new long[xs.length];
		for (int i = 0; i < xs.length; i++) {
			mapped[i] = f.applyAsLong(xs[i]);
		}
		return mapped;
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite input
	 * collection to produce a long array.
	 *
	 * @param    <E> The source element type.
	 * @param f  The function defining the mapping.
	 * @param xs The source collection.
	 * @return An long array of the same length as the source containing the mapped
	 *         values of the source elements with order retained if the input has a
	 *         defined order.
	 */
	public static <E> long[] longMap(ToLongFunction<? super E> f, Collection<? extends E> xs)
	{
		long[] dest = new long[xs.size()];
		int counter = 0;
		for (Iterator<? extends E> itr = xs.iterator(); itr.hasNext();) {
			dest[counter++] = f.applyAsLong(itr.next());
		}
		return dest;
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce a {@linkplain Vec}.
	 *
	 * @param    <R> The target type of the mapping operation.
	 * @param f  The function defining the mapping.
	 * @param xs The source array.
	 *
	 * @return A Seq of the same length as the source containing the mapped values
	 *         of the source elements with order retained.
	 */
	public static <R> Vec<R> objMap(IntFunction<? extends R> f, int[] xs)
	{
		EnhancedIterator<R> intermediate = Iter.ints(xs).mapToObject(f);
		return intermediate.toVec();
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce a {@linkplain Vec}.
	 *
	 * @param    <R> The target type of the mapping operation.
	 * @param f  The function defining the mapping.
	 * @param xs The source array.
	 *
	 * @return A Seq of the same length as the source containing the mapped values
	 *         of the source elements with order retained.
	 */
	public static <R> Vec<R> objMap(DoubleFunction<? extends R> f, double[] xs)
	{
		EnhancedIterator<R> intermediate = Iter.doubles(xs).mapToObject(f);
		return intermediate.toVec();
	}

	/**
	 * Sequentially applies a mapping function elementwise to an finite, ordered
	 * input sequence to produce a {@linkplain Vec}.
	 *
	 * @param    <R> The target type of the mapping operation.
	 * @param f  The function defining the mapping.
	 * @param xs The source array.
	 *
	 * @return A Seq of the same length as the source containing the mapped values
	 *         of the source elements with order retained.
	 */
	public static <R> Vec<R> objMap(LongFunction<? extends R> f, long[] xs)
	{
		EnhancedIterator<R> intermediate = Iter.longs(xs).mapToObject(f);
		return intermediate.toVec();
	}

	// Predicate checking
	/**
	 * Checks whether every element in a sequence satisfies a given predicate.
	 *
	 * @param predicate The predicate test.
	 * @param xs        The source sequence.
	 * @return True if every element in the source passes the predicate test, false
	 *         otherwise.
	 */
	public static boolean allMatch(IntPredicate predicate, int[] xs)
	{
		for (int x : xs) {
			if (!predicate.test(x)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks whether every element in a sequence satisfies a given predicate.
	 *
	 * @param predicate The predicate test.
	 * @param xs        The source sequence.
	 * @return True if every element in the source passes the predicate test, false
	 *         otherwise.
	 */
	public static boolean allMatch(DoublePredicate predicate, double[] xs)
	{
		for (double x : xs) {
			if (!predicate.test(x)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks whether every element in a sequence satisfies a given predicate.
	 *
	 * @param predicate The predicate test.
	 * @param xs        The source sequence.
	 * @return True if every element in the source passes the predicate test, false
	 *         otherwise.
	 */
	public static boolean allMatch(LongPredicate predicate, long[] xs)
	{
		for (long x : xs) {
			if (!predicate.test(x)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks whether any element in a sequence satisfies a given predicate.
	 *
	 * @param predicate The predicate test.
	 * @param xs        The source sequence.
	 * @return True if any element in the source passes the predicate test, false
	 *         otherwise.
	 */
	public static boolean anyMatch(IntPredicate predicate, int[] xs)
	{
		for (int x : xs) {
			if (predicate.test(x)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks whether any element in a sequence satisfies a given predicate.
	 *
	 * @param predicate The predicate test.
	 * @param xs        The source sequence.
	 * @return True if any element in the source passes the predicate test, false
	 *         otherwise.
	 */
	public static boolean anyMatch(DoublePredicate predicate, double[] xs)
	{
		for (double x : xs) {
			if (predicate.test(x)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks whether any element in a sequence satisfies a given predicate.
	 *
	 * @param predicate The predicate test.
	 * @param xs        The source sequence.
	 * @return True if any element in the source passes the predicate test, false
	 *         otherwise.
	 */
	public static boolean anyMatch(LongPredicate predicate, long[] xs)
	{
		for (long x : xs) {
			if (predicate.test(x)) {
				return true;
			}
		}
		return false;
	}

	// Sum/product/min/max
	/**
	 * Calculates the sum of the parameter values.
	 * 
	 * @param xs The values to sum
	 * 
	 * @return The sum of the values if non-empty, zero otherwise.
	 */
	public static double sum(double... xs)
	{
		return foldr((a, b) -> a + b, 0, xs);
	}

	/**
	 * Calculates the sum of the parameter values. It is fail-fast on numerical
	 * overflow.
	 * 
	 * @param xs The values to sum
	 * 
	 * @return The sum of the values if non-empty, zero otherwise.
	 */
	public static int sum(int... xs)
	{
		return foldr(Math::addExact, 0, xs);
	}

	/**
	 * Calculates the sum of the parameter values. It is fail-fast on numerical
	 * overflow.
	 * 
	 * @param xs The values to sum
	 * 
	 * @return The sum of the values if non-empty, zero otherwise.
	 */
	public static long sum(long... xs)
	{
		return foldr(Math::addExact, 0, xs);
	}

	/**
	 * Calculates the product of the parameter values.
	 * 
	 * @param xs The values to multiply
	 * 
	 * @return The product of the values if non-empty, one otherwise.
	 */
	public static double product(double... xs)
	{
		return foldr((a, b) -> a * b, 1, xs);
	}

	/**
	 * Calculates the product of the parameter values. It is fail-fast on numerical
	 * overflow.
	 * 
	 * @param xs The values to multiply
	 * 
	 * @return The product of the values if non-empty, one otherwise.
	 */
	public static int product(int... xs)
	{
		return foldr(Math::multiplyExact, 1, xs);
	}

	/**
	 * Calculates the product of the parameter values. It is fail-fast on numerical
	 * overflow.
	 * 
	 * @param xs The values to multiply
	 * 
	 * @return The product of the values if non-empty, one otherwise.
	 */
	public static long product(long... xs)
	{
		return foldr(Math::multiplyExact, 1, xs);
	}

	/**
	 * Calculates the minimum of a non-empty sequence of primitive values. If an
	 * empty sequence is passed an exception will be thrown.
	 * 
	 * @param xs The values to find the minimum of.
	 * 
	 * @return The minimum value in the sequence.
	 */
	public static int min(int... xs)
	{
		Exceptions.requireArg(xs.length > 0);
		return foldr(Math::min, Integer.MAX_VALUE, xs);
	}

	/**
	 * Calculates the minimum of a non-empty sequence of primitive values. If an
	 * empty sequence is passed an exception will be thrown.
	 * 
	 * @param xs The values to find the minimum of.
	 * 
	 * @return The minimum value in the sequence.
	 */
	public static double min(double... xs)
	{
		return foldr(Math::min, Double.POSITIVE_INFINITY, xs);
	}

	/**
	 * Calculates the minimum of a non-empty sequence of primitive values. If an
	 * empty sequence is passed an exception will be thrown.
	 * 
	 * @param xs The values to find the minimum of.
	 * 
	 * @return The minimum value in the sequence.
	 */
	public static long min(long... xs)
	{
		return foldr(Math::min, Long.MAX_VALUE, xs);
	}

	/**
	 * Calculates the maximum of a non-empty sequence of primitive values. If an
	 * empty sequence is passed an exception will be thrown.
	 * 
	 * @param xs The values to find the maximum of.
	 * 
	 * @return The maximum value in the sequence.
	 */
	public static int max(int... xs)
	{
		Exceptions.requireArg(xs.length > 0);
		return foldr(Math::max, Integer.MIN_VALUE, xs);
	}

	/**
	 * Calculates the maximum of a non-empty sequence of primitive values. If an
	 * empty sequence is passed an exception will be thrown.
	 * 
	 * @param xs The values to find the maximum of.
	 * 
	 * @return The maximum value in the sequence.
	 */
	public static double max(double... xs)
	{
		return foldr(Math::max, Double.NEGATIVE_INFINITY, xs);
	}

	/**
	 * Calculates the maximum of a non-empty sequence of primitive values. If an
	 * empty sequence is passed an exception will be thrown.
	 * 
	 * @param xs The values to find the maximum of.
	 * 
	 * @return The maximum value in the sequence.
	 */
	public static long max(long... xs)
	{
		return foldr(Math::max, Long.MIN_VALUE, xs);
	}

	// General manipulation
	/**
	 * Reverses an int array.
	 *
	 * @param xs An int array reference.
	 * @return An int array containing the same values as the parameter but with a
	 *         reversed order.
	 */
	public static int[] reverse(int[] xs)
	{
		int n = xs.length;
		int[] reversed = new int[n];
		for (int i = 0; i < n; i++) {
			reversed[i] = xs[n - (i + 1)];
		}
		return reversed;
	}

	/**
	 * Reverses a double array.
	 *
	 * @param xs A double array reference.
	 * @return A double array containing the same values as the parameter but with a
	 *         reversed order.
	 */
	public static double[] reverse(double[] xs)
	{
		int n = xs.length;
		double[] reversed = new double[n];
		for (int i = 0; i < n; i++) {
			reversed[i] = xs[n - (i + 1)];
		}
		return reversed;
	}

	/**
	 * Reverses a long array.
	 *
	 * @param xs A long array reference.
	 * @return A long array containing the same values as the parameter but with a
	 *         reversed order.
	 */
	public static long[] reverse(long[] xs)
	{
		int n = xs.length;
		long[] reversed = new long[n];
		for (int i = 0; i < n; i++) {
			reversed[i] = xs[n - (i + 1)];
		}
		return reversed;
	}

	/**
	 * Reverses a char array.
	 *
	 * @param xs A char array reference.
	 * @return A char array containing the same values as the parameter but with a
	 *         reversed order.
	 */
	public static char[] reverse(char[] xs)
	{
		int n = xs.length;
		char[] reversed = new char[n];
		for (int i = 0; i < n; i++) {
			reversed[i] = xs[n - (i + 1)];
		}
		return reversed;
	}

	/**
	 * Take the first n elements from the beginning of an array retaining their
	 * order.
	 *
	 * @param n  The number of elements to take.
	 * @param xs The array to take elements from.
	 * @throws IllegalArgumentException If the take count is negative.
	 * @return An array containing the taken elements.
	 */
	public static int[] take(int n, int[] xs)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		int newSize = Math.min(xs.length, n);
		return Arrays.copyOf(xs, newSize);
	}

	/**
	 * Take the first n elements from the beginning of an array retaining their
	 * order.
	 *
	 * @param n  The number of elements to take.
	 * @param xs The array to take elements from.
	 * @throws IllegalArgumentException If the take count is negative.
	 * @return An array containing the taken elements.
	 */
	public static double[] take(int n, double[] xs)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		int newSize = Math.min(xs.length, n);
		return Arrays.copyOf(xs, newSize);
	}

	/**
	 * Take the first n elements from the beginning of an array retaining their
	 * order.
	 *
	 * @param n  The number of elements to take.
	 * @param xs The array to take elements from.
	 * @throws IllegalArgumentException If the take count is negative.
	 * @return An array containing the taken elements.
	 */
	public static long[] take(int n, long[] xs)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		int newSize = Math.min(xs.length, n);
		return Arrays.copyOf(xs, newSize);
	}

	/**
	 * Take the first n elements from the beginning of an array retaining their
	 * order.
	 *
	 * @param n  The number of elements to take.
	 * @param xs The array to take elements from.
	 * @throws IllegalArgumentException If the take count is negative.
	 * @return An array containing the taken elements.
	 */
	public static char[] take(int n, char[] xs)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		int newSize = Math.min(xs.length, n);
		return Arrays.copyOf(xs, newSize);
	}

	/**
	 * Drop the first n elements from the beginning of an array retaining the order
	 * of the remaining elements.
	 *
	 * @param n  The number of elements to drop.
	 * @param xs The array to drop elements from.
	 * @throws IllegalArgumentException If the drop count is negative.
	 * @return An array containing the elements which were not dropped from the
	 *         source.
	 */
	public static int[] drop(int n, int[] xs)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		int dropPos = Math.min(xs.length, n);
		int newSize = xs.length - dropPos;
		int[] newArr = new int[newSize];
		System.arraycopy(xs, dropPos, newArr, 0, newSize);
		return newArr;
	}

	/**
	 * Drop the first n elements from the beginning of an array retaining the order
	 * of the remaining elements.
	 *
	 * @param n  The number of elements to drop.
	 * @param xs The array to drop elements from.
	 * @throws IllegalArgumentException If the drop count is negative.
	 * @return An array containing the elements which were not dropped from the
	 *         source.
	 */
	public static double[] drop(int n, double[] xs)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		int dropPos = Math.min(xs.length, n);
		int newSize = xs.length - dropPos;
		double[] newArr = new double[newSize];
		System.arraycopy(xs, dropPos, newArr, 0, newSize);
		return newArr;
	}

	/**
	 * Drop the first n elements from the beginning of an array retaining the order
	 * of the remaining elements.
	 *
	 * @param n  The number of elements to drop.
	 * @param xs The array to drop elements from.
	 * @throws IllegalArgumentException If the drop count is negative.
	 * @return An array containing the elements which were not dropped from the
	 *         source.
	 */
	public static long[] drop(int n, long[] xs)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		int dropPos = Math.min(xs.length, n);
		int newSize = xs.length - dropPos;
		long[] newArr = new long[newSize];
		System.arraycopy(xs, dropPos, newArr, 0, newSize);
		return newArr;
	}

	/**
	 * Drop the first n elements from the beginning of an array retaining the order
	 * of the remaining elements.
	 *
	 * @param n  The number of elements to drop.
	 * @param xs The array to drop elements from.
	 * @throws IllegalArgumentException If the drop count is negative.
	 * @return An array containing the elements which were not dropped from the
	 *         source.
	 */
	public static char[] drop(int n, char[] xs)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		int dropPos = Math.min(xs.length, n);
		int newSize = xs.length - dropPos;
		char[] newArr = new char[newSize];
		System.arraycopy(xs, dropPos, newArr, 0, newSize);
		return newArr;
	}

	/**
	 * Append an element to the end of an array.
	 *
	 * @param x  The element to append
	 * @param xs The source array
	 * @return A new array containing the elements from the source with the
	 *         parameter appended to the end.
	 */
	public static char[] append(char x, char[] xs)
	{
		int n = xs.length;
		char[] newArr = new char[n + 1];
		newArr[n] = x;
		System.arraycopy(xs, 0, newArr, 0, n);
		return newArr;
	}

	/**
	 * Append an element to the end of an array.
	 *
	 * @param x  The element to append
	 * @param xs The source array
	 * @return A new array containing the elements from the source with the
	 *         parameter appended to the end.
	 */
	public static int[] append(int x, int[] xs)
	{
		int n = xs.length;
		int[] newArr = new int[n + 1];
		newArr[n] = x;
		System.arraycopy(xs, 0, newArr, 0, n);
		return newArr;
	}

	/**
	 * Append an element to the end of an array.
	 *
	 * @param x  The element to append
	 * @param xs The source array
	 * @return A new array containing the elements from the source with the
	 *         parameter appended to the end.
	 */
	public static double[] append(double x, double[] xs)
	{
		int n = xs.length;
		double[] newArr = new double[n + 1];
		newArr[n] = x;
		System.arraycopy(xs, 0, newArr, 0, n);
		return newArr;
	}

	/**
	 * Append an element to the end of an array.
	 *
	 * @param x  The element to append
	 * @param xs The source array
	 * @return A new array containing the elements from the source with the
	 *         parameter appended to the end.
	 */
	public static long[] append(long x, long[] xs)
	{
		int n = xs.length;
		long[] newArr = new long[n + 1];
		newArr[n] = x;
		System.arraycopy(xs, 0, newArr, 0, n);
		return newArr;
	}

	/**
	 * Insert an element at the beginning of an array.
	 *
	 * @param x  The element to insert
	 * @param xs The source array
	 * @return A new array containing the elements from the source with the
	 *         parameter inserted at the beginning.
	 */
	public static char[] insert(char x, char[] xs)
	{
		int n = xs.length;
		char[] newArr = new char[n + 1];
		newArr[0] = x;
		System.arraycopy(xs, 0, newArr, 1, n);
		return newArr;
	}

	/**
	 * Insert an element at the beginning of an array.
	 *
	 * @param x  The element to insert
	 * @param xs The source array
	 * @return A new array containing the elements from the source with the
	 *         parameter inserted at the beginning.
	 */
	public static int[] insert(int x, int[] xs)
	{
		int n = xs.length;
		int[] newArr = new int[n + 1];
		newArr[0] = x;
		System.arraycopy(xs, 0, newArr, 1, n);
		return newArr;
	}

	/**
	 * Insert an element at the beginning of an array.
	 *
	 * @param x  The element to insert
	 * @param xs The source array
	 * @return A new array containing the elements from the source with the
	 *         parameter inserted at the beginning.
	 */
	public static double[] insert(double x, double[] xs)
	{
		int n = xs.length;
		double[] newArr = new double[n + 1];
		newArr[0] = x;
		System.arraycopy(xs, 0, newArr, 1, n);
		return newArr;
	}

	/**
	 * Insert an element at the beginning of an array.
	 *
	 * @param x  The element to insert
	 * @param xs The source array
	 * @return A new array containing the elements from the source with the
	 *         parameter inserted at the beginning.
	 */
	public static long[] insert(long x, long[] xs)
	{
		int n = xs.length;
		long[] newArr = new long[n + 1];
		newArr[0] = x;
		System.arraycopy(xs, 0, newArr, 1, n);
		return newArr;
	}

	public static double[] copy(double[] src)
	{
		return Arrays.copyOf(src, src.length);
	}

	public static int[] copy(int[] src)
	{
		return Arrays.copyOf(src, src.length);
	}

	public static long[] copy(long[] src)
	{
		return Arrays.copyOf(src, src.length);
	}

	public static byte[] copy(byte[] src)
	{
		return Arrays.copyOf(src, src.length);
	}

	public static float[] copy(float[] src)
	{
		return Arrays.copyOf(src, src.length);
	}

	public static char[] copy(char[] src)
	{
		return Arrays.copyOf(src, src.length);
	}

	public static short[] copy(short[] src)
	{
		return Arrays.copyOf(src, src.length);
	}
}
