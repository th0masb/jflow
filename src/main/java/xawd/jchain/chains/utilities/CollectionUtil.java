package xawd.jchain.chains.utilities;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 * @author ThomasB
 * @since 25 Jan 2018
 */
public final class CollectionUtil
{

	// Uninstantiable
	private CollectionUtil()
	{
	}

	/* -----------------------------------------------------------------------------------------------
	 * A unified length/size function |
	 * ----------------------------------------------------------------------------------------------- */
	/**
	 * A unified length/size function
	 *
	 * @param s - the String to calculate the length of
	 * @return the length of <b>s</b>
	 */
	public static int len(final String s)
	{
		return s.length();
	}

	/**
	 * A unified length/size function
	 *
	 * @param xs - the Collection to calculate the size of
	 * @return the size of <b>xs</b>
	 */
	public static <T> int len(final Collection<T> xs)
	{
		return xs.size();
	}

	/**
	 * A unified length/size function
	 *
	 * @param xs - the Array to calculate the length of
	 * @return the length of <b>xs</b>
	 */
	public static <T> int len(final T[] xs)
	{
		return xs.length;
	}

	/**
	 * A unified length/size function
	 *
	 * @param xs - the Array to calculate the length of
	 * @return the length of <b>xs</b>
	 */
	public static int len(final int[] xs)
	{
		return xs.length;
	}


	/**
	 * A unified length/size function
	 *
	 * @param xs - the Array to calculate the length of
	 * @return the length of <b>xs</b>
	 */
	public static int len(final double[] xs)
	{
		return xs.length;
	}

	/**
	 * A unified length/size function
	 *
	 * @param xs - the Array to calculate the length of
	 * @return the length of <b>xs</b>
	 */
	public static int len(final long[] xs)
	{
		return xs.length;
	}

	/**
	 * A unified length/size function
	 *
	 * @param xs - the Array to calculate the length of
	 * @return the length of <b>xs</b>
	 */
	public static int len(final int[][] xs)
	{
		return xs.length;
	}

	/**
	 * A unified length/size function
	 *
	 * @param xs - the Array to calculate the length of
	 * @return the length of <b>xs</b>
	 */
	public static int len(final double[][] xs)
	{
		return xs.length;
	}

	/**
	 * A unified length/size function
	 *
	 * @param xs - the Array to calculate the length of
	 * @return the length of <b>xs</b>
	 */
	public static int len(final long[][] xs)
	{
		return xs.length;
	}

	/* -----------------------------------------------------------------------------------------------
	 * A unified String conversion function |
	 * ----------------------------------------------------------------------------------------------- */
	/**
	 * A unified String conversion function
	 *
	 * @param t - the object to convert
	 * @return the string representation of <b>t</b>
	 */
	public static <T> String str(final T t)
	{
		return t.toString();
	}

	/**
	 * A unified String conversion function
	 *
	 * @param x - the primitive int to convert
	 * @return the String representation of <b>x</b>
	 */
	public static String str(final int x)
	{
		return Integer.toString(x);
	}

	/**
	 * A unified String conversion function
	 *
	 * @param x - the primitive double to convert
	 * @return the String representation of <b>x</b>
	 */
	public static String str(final double x)
	{
		return Double.toString(x);
	}

	/**
	 * A unified String conversion function
	 *
	 * @param x - the primitive long to convert
	 * @return the String representation of <b>x</b>
	 */
	public static String str(final long x)
	{
		return Long.toString(x);
	}

	/**
	 * A unified String conversion function
	 *
	 * @param xs - the int Array to convert
	 * @return the String representation of <b>xs</b>
	 */
	public static String str(final int[] xs)
	{
		return Arrays.toString(xs);
	}

	/**
	 * A unified String conversion function
	 *
	 * @param xs - the double Array to convert
	 * @return the String representation of <b>xs</b>
	 */
	public static String str(final double[] xs)
	{
		return Arrays.toString(xs);
	}

	/**
	 * A unified String conversion function
	 *
	 * @param xs - the long Array to convert
	 * @return the String representation of <b>xs</b>
	 */
	public static String str(final long[] xs)
	{
		return Arrays.toString(xs);
	}


	/* -----------------------------------------------------------------------------------------------
	 * A unified reverse function |
	 * ----------------------------------------------------------------------------------------------- */
	/**
	 * Reverses the passed sequence
	 *
	 * @param xs - the passed sequence
	 * @return a new sequence of the same type as passed containing the same values but with a reversed order.
	 */
	public static int[] reverse(final int[] xs)
	{
		final int n = len(xs);
		final int[] reversed = new int[n];
		for (int i = 0; i < n; i++) {
			reversed[i] = xs[n - (i + 1)];
		}
		return reversed;
	}


	/**
	 * Reverses the passed sequence
	 *
	 * @param xs - the passed sequence
	 * @return a new sequence of the same type as passed containing the same values but with a reversed order.
	 */
	public static double[] reverse(final double[] xs)
	{
		final int n = len(xs);
		final double[] reversed = new double[n];
		for (int i = 0; i < n; i++) {
			reversed[i] = xs[n - (i + 1)];
		}
		return reversed;
	}


	/**
	 * Reverses the passed sequence
	 *
	 * @param xs - the passed sequence
	 * @return a new sequence of the same type as passed containing the same values but with a reversed order.
	 */
	public static long[] reverse(final long[] xs)
	{
		final int n = len(xs);
		final long[] reversed = new long[n];
		for (int i = 0; i < n; i++) {
			reversed[i] = xs[n - (i + 1)];
		}
		return reversed;
	}
	//
	//	/* -----------------------------------------------------------------------------------------------
	//	 * A unified take function a la Haskell |
	//	 * -----------------------------------------------------------------------------------------------
	//	 *
	//	 * TODO - we could reduce amount of objects created here by implementing each individually */
	//
	//	/**
	//	 * Take the first n elements from the beginning of an array retaining their order.
	//	 *
	//	 * @param n - The number of elements to take
	//	 * @param xs - The sequence to take from
	//	 * @return an int array containing the taken elements.
	//	 */
	//	public static int[] take(final int n, final int[] xs)
	//	{
	//		return take(n, asFunction(xs)).toArray();
	//	}
	//
	//	/**
	//	 * Take the first n elements from the beginning of an {@link RangedDoubleFunction} retaining their order.
	//	 *
	//	 * @param n - The number of elements to take
	//	 * @param xs - The sequence to take from
	//	 * @return a {@linkplain RangedDoubleFunction} representing the taken elements.
	//	 */
	//	public static RangedDoubleFunction take(final int n, final DoubleChain xs)
	//	{
	//		assert 0 <= n && n <= len(xs);
	//		return RangedDoubleFunction.of(xs.getDescriptor(), n);
	//	}
	//
	//	/**
	//	 * Take the first n elements from the beginning of an array retaining their order.
	//	 *
	//	 * @param n - The number of elements to take
	//	 * @param xs - The array to take from
	//	 * @return an {@link ImmutableDoubleArray} containing the taken elements.
	//	 */
	//	public static ImmutableDoubleArray take(final int n, final ImmutableDoubleArray xs)
	//	{
	//		return take(n, asFunction(xs)).toImmutableArray();
	//	}
	//
	//	/**
	//	 * Take the first n elements from the beginning of an array retaining their order.
	//	 *
	//	 * @param n - The number of elements to take
	//	 * @param xs - The array to take from
	//	 * @return a double array containing the taken elements.
	//	 */
	//	public static double[] take(final int n, final double[] xs)
	//	{
	//		return take(n, asFunction(xs)).toArray();
	//	}
	//
	//	/**
	//	 * Take the first n elements from the beginning of an {@link RangedLongFunction} retaining their order.
	//	 *
	//	 * @param n - The number of elements to take
	//	 * @param xs - The sequence to take from
	//	 * @return a {@linkplain RangedLongFunction} representing the taken elements.
	//	 */
	//	public static RangedLongFunction take(final int n, final LongChain xs)
	//	{
	//		assert 0 <= n && n <= len(xs);
	//		return RangedLongFunction.of(xs.getDescriptor(), n);
	//	}
	//
	//	/**
	//	 * Take the first n elements from the beginning of an array retaining their order.
	//	 *
	//	 * @param n - The number of elements to take
	//	 * @param xs - The array to take from
	//	 * @return an {@link ImmutableLongArray} containing the taken elements.
	//	 */
	//	public static ImmutableLongArray take(final int n, final ImmutableLongArray xs)
	//	{
	//		return take(n, asFunction(xs)).toImmutableArray();
	//	}
	//
	//	/**
	//	 * Take the first n elements from the beginning of an array retaining their order.
	//	 *
	//	 * @param n - The number of elements to take
	//	 * @param xs - The array to take from
	//	 * @return a long array containing the taken elements.
	//	 */
	//	public static long[] take(final int n, final long[] xs)
	//	{
	//		return take(n, asFunction(xs)).toArray();
	//	}
	//
	//	/**
	//	 * Take the first n elements from the beginning of a {@link RangedFunction} retaining their order.
	//	 *
	//	 * @param n - The number of elements to take
	//	 * @param xs - The generator to take from
	//	 * @return a {@linkplain RangedFunction} representing the taken elements.
	//	 */
	//	public static <T> RangedFunction<T> take(final int n, final Chain<T> xs)
	//	{
	//		assert 0 <= n && n <= len(xs);
	//		return RangedFunction.of(xs.getDescriptor(), n);
	//	}
	//
	//	/**
	//	 * Take the first n elements from the beginning of a List retaining their order.
	//	 *
	//	 * @param n - The number of elements to take
	//	 * @param xs - The List to take from
	//	 * @return an {@link ImmutableList} containing the taken elements.
	//	 */
	//	public static <T> ImmutableList<T> take(final int n, final List<T> xs)
	//	{
	//		return take(n, asFunction(xs)).toList();
	//	}
	//
	//	/**
	//	 * Take the first n elements from the beginning of a Array retaining their order.
	//	 *
	//	 * @param n - The number of elements to take
	//	 * @param xs - The array to take from
	//	 * @return an {@link ImmutableList} containing the taken elements.
	//	 */
	//	public static <T> ImmutableList<T> take(final int n, final T[] xs)
	//	{
	//		return take(n, asFunction(xs)).toList();
	//	}

	/* -----------------------------------------------------------------------------------------------
	 * A unified drop function a la Haskell |
	 * -----------------------------------------------------------------------------------------------
	 *
	 * TODO - we could reduce amount of objects created here by implementing each individually */

	//	/**
	//	 * Drop the first n elements from the beginning of an {@link RangedIntFunction} retaining the remaining order.
	//	 *
	//	 * @param n - The number of elements to drop
	//	 * @param xs - The sequence to drop from
	//	 * @return an {@linkplain RangedIntFunction} representing the remaining elements.
	//	 */
	//	public static RangedIntFunction drop(final int n, final IntChain xs)
	//	{
	//		assert 0 <= n && n <= len(xs);
	//		return RangedIntFunction.of(xs.getDescriptor().compose(i -> i + n), len(xs) - n);
	//	}
	//
	//	/**
	//	 * Drop the first n elements from the beginning of an {@link ImmutableIntArray} retaining the remaining order.
	//	 *
	//	 * @param n - The number of elements to drop
	//	 * @param xs - The generator to drop from
	//	 * @return an {@linkplain ImmutableIntArray} containing the remaining elements.
	//	 */
	//	public static ImmutableIntArray drop(final int n, final ImmutableIntArray xs)
	//	{
	//		return drop(n, asFunction(xs)).toImmutableArray();
	//	}
	//
	//	/**
	//	 * Drop the first n elements from the beginning of an int array retaining the remaining order.
	//	 *
	//	 * @param n - The number of elements to drop
	//	 * @param xs - The int array to drop from
	//	 * @return an int array containing the remaining elements.
	//	 */
	//	public static int[] drop(final int n, final int[] xs)
	//	{
	//		return drop(n, asFunction(xs)).toArray();
	//	}
	//
	//	/**
	//	 * Drop the first n elements from the beginning of an {@link RangedDoubleFunction} retaining the remaining order.
	//	 *
	//	 * @param n - The number of elements to drop
	//	 * @param xs - The sequence to drop from
	//	 * @return an {@linkplain RangedDoubleFunction} representing the remaining elements.
	//	 */
	//	public static RangedDoubleFunction drop(final int n, final DoubleChain xs)
	//	{
	//		assert 0 <= n && n <= len(xs);
	//		return RangedDoubleFunction.of(compose(xs.getDescriptor(), i -> i + n), len(xs) - n);
	//	}
	//
	//	/**
	//	 * Drop the first n elements from the beginning of an {@link ImmutableDoubleArray} retaining the remaining order.
	//	 *
	//	 * @param n - The number of elements to drop
	//	 * @param xs - The array to drop from
	//	 * @return an {@linkplain ImmutableDoubleArray} containing the remaining elements.
	//	 */
	//	public static ImmutableDoubleArray drop(final int n, final ImmutableDoubleArray xs)
	//	{
	//		return drop(n, asFunction(xs)).toImmutableArray();
	//	}
	//
	//	/**
	//	 * Drop the first n elements from the beginning of an array retaining the remaining order.
	//	 *
	//	 * @param n - The number of elements to drop
	//	 * @param xs - The array to drop from
	//	 * @return a double array containing the remaining elements.
	//	 */
	//	public static double[] drop(final int n, final double[] xs)
	//	{
	//		return drop(n, asFunction(xs)).toArray();
	//	}
	//
	//	/**
	//	 * Drop the first n elements from the beginning of an {@link RangedLongFunction} retaining the remaining order.
	//	 *
	//	 * @param n - The number of elements to drop
	//	 * @param xs - The array to drop from
	//	 * @return an {@linkplain RangedLongFunction} representing the remaining elements.
	//	 */
	//	public static RangedLongFunction drop(final int n, final LongChain xs)
	//	{
	//		assert 0 <= n && n <= len(xs);
	//		return RangedLongFunction.of(compose(xs.getDescriptor(), i -> i + n), len(xs) - n);
	//	}
	//
	//	/**
	//	 * Drop the first n elements from the beginning of an {@link ImmutableLongArray} retaining the remaining order.
	//	 *
	//	 * @param n - The number of elements to drop
	//	 * @param xs - The generator to drop from
	//	 * @return an {@linkplain ImmutableLongArray} containing the remaining elements.
	//	 */
	//	public static ImmutableLongArray drop(final int n, final ImmutableLongArray xs)
	//	{
	//		return drop(n, asFunction(xs)).toImmutableArray();
	//	}
	//
	//	/**
	//	 * Drop the first n elements from the beginning of an array retaining the remaining order.
	//	 *
	//	 * @param n - The number of elements to drop
	//	 * @param xs - The array to drop from
	//	 * @return a long array containing the remaining elements.
	//	 */
	//	public static long[] drop(final int n, final long[] xs)
	//	{
	//		return drop(n, asFunction(xs)).toArray();
	//	}
	//
	//	/**
	//	 * Drop the first n elements from the beginning of an {@link RangedFunction} retaining the remaining order.
	//	 *
	//	 * @param n - The number of elements to drop
	//	 * @param xs - The sequence to drop from
	//	 * @return an {@linkplain RangedFunction} representing the remaining elements.
	//	 */
	//	public static <T> RangedFunction<T> drop(final int n, final Chain<T> xs)
	//	{
	//		assert 0 <= n && n <= len(xs);
	//		return RangedFunction.of(compose(xs.getDescriptor(), i -> i + n), len(xs) - n);
	//	}
	//
	//	/**
	//	 * Drop the first n elements from the beginning of a List retaining the remaining order.
	//	 *
	//	 * @param n - the number of elements to drop
	//	 * @param xs - The List to drop from
	//	 * @return an {@linkplain ImmutableList} containing the remaining elements.
	//	 */
	//	public static <T> ImmutableList<T> drop(final int n, final List<T> xs)
	//	{
	//		return drop(n, asFunction(xs)).toList();
	//	}
	//
	//	/**
	//	 * Drop the first n elements from the beginning of an array retaining the remaining order.
	//	 *
	//	 * @param n - The number of elements to drop
	//	 * @param xs - The array to drop from
	//	 * @return an {@linkplain ImmutableList} containing the remaining elements.
	//	 */
	//	public static <T> ImmutableList<T> drop(final int n, final T[] xs)
	//	{
	//		return drop(n, asFunction(xs)).toList();
	////	}
	////
	////	/* -----------------------------------------------------------------------------------------------
	//	 * A unified head function a la Haskell |
	//	 * ----------------------------------------------------------------------------------------------- */

	/**
	 * Return the first element (head) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the first element in the sequence
	 */
	public static int head(final int[] xs)
	{
		assert len(xs) > 0;
		return xs[0];
	}


	/**
	 * Return the first element (head) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the first element in the sequence
	 */
	public static double head(final double[] xs)
	{
		assert len(xs) > 0;
		return xs[0];
	}


	/**
	 * Return the first element (head) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the first element in the sequence
	 */
	public static long head(final long[] xs)
	{
		assert len(xs) > 0;
		return xs[0];
	}

	/**
	 * Return the first element (head) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the first element in the sequence
	 */
	public static <T> T head(final List<T> xs)
	{
		assert len(xs) > 0;
		return xs.get(0);
	}

	/**
	 * Return the first element (head) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the first element in the sequence
	 */
	public static <T> T head(final T[] xs)
	{
		assert len(xs) > 0;
		return xs[0];
	}

	/* -----------------------------------------------------------------------------------------------
	 * A unified tail function a la Haskell |
	 * ----------------------------------------------------------------------------------------------- */


	/**
	 * Return the last element (tail) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the last element in the sequence.
	 */
	public static int tail(final int[] xs)
	{
		assert len(xs) > 0;
		return xs[len(xs) - 1];
	}



	/**
	 * Return the last element (tail) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the last element in the sequence.
	 */
	public static double tail(final double[] xs)
	{
		assert len(xs) > 0;
		return xs[len(xs) - 1];
	}



	/**
	 * Return the last element (tail) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the last element in the sequence.
	 */
	public static long tail(final long[] xs)
	{
		assert len(xs) > 0;
		return xs[len(xs) - 1];
	}


	/**
	 * Return the last element (tail) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the last element in the sequence.
	 */
	public static <T> T tail(final List<T> xs)
	{
		assert len(xs) > 0;
		return xs.get(len(xs) - 1);
	}

	/**
	 * Return the last element (tail) of this sequence
	 *
	 * @param xs - the element sequence
	 * @return the last element in the sequence.
	 */
	public static <T> T tail(final T[] xs)
	{
		assert len(xs) > 0;
		return xs[len(xs) - 1];
	}

	//	/* -----------------------------------------------------------------------------------------------
	//	 * A unified append function |
	//	 * -----------------------------------------------------------------------------------------------
	//	 *
	//	 * TODO - performance of method to append elements to the google primitives will depened on length */
	//	/**
	//	 * Append an element to the end of a sequence.
	//	 *
	//	 * @param x - To append
	//	 * @param xs - To append to
	//	 * @return a new sequence representing the result of the operation.
	//	 */
	//	public static <T, E extends T> RangedFunction<T> append(final E x, final Chain<T> xs)
	//	{
	//		final int n = len(xs);
	//		return RangedFunction.of(i -> i < n ? xs.elementAt(i) : x, n + 1);
	//	}
	//
	//	/**
	//	 * Append an element to the end of a sequence.
	//	 *
	//	 * @param x - To append
	//	 * @param xs - To append to
	//	 * @return a new sequence representing the result of the operation.
	//	 */
	//	public static <T, E extends T> ImmutableList<T> append(final E x, final List<T> xs)
	//	{
	//		final int n = len(xs);
	//		final Chain<T> combined = RangedFunction.of(i -> i < n ? xs.get(i) : x, n + 1);
	//		return ImmutableList.copyOf(combined);
	//	}
	//
	//	/**
	//	 * Append an element to the end of a sequence.
	//	 *
	//	 * @param x - To append
	//	 * @param xs - To append to
	//	 * @return a new sequence representing the result of the operation.
	//	 */
	//	public static <T, E extends T> ImmutableList<T> append(final E x, final T[] xs)
	//	{
	//		final int n = len(xs);
	//		final Chain<T> combined = RangedFunction.of(i -> i < n ? xs[i] : x, n + 1);
	//		return ImmutableList.copyOf(combined);
	//	}
	//
	//	/**
	//	 * Append an element to the end of a sequence.
	//	 *
	//	 * @param x - To append
	//	 * @param xs - To append to
	//	 * @return a new sequence representing the result of the operation.
	//	 */
	//	public static RangedDoubleFunction append(final double x, final DoubleChain xs)
	//	{
	//		final int n = len(xs);
	//		return RangedDoubleFunction.of(i -> i < n ? xs.elementAt(i) : x, n + 1);
	//	}
	//
	//	/**
	//	 * Append an element to the end of a sequence.
	//	 *
	//	 * @param x - To append
	//	 * @param xs - To append to
	//	 * @return a new sequence representing the result of the operation.
	//	 */
	//	public static ImmutableDoubleArray append(final double x, final ImmutableDoubleArray xs)
	//	{
	//		return append(x, asFunction(xs)).toImmutableArray();
	//	}
	//
	//	/**
	//	 * Append an element to the end of a sequence.
	//	 *
	//	 * @param x - To append
	//	 * @param xs - To append to
	//	 * @return a new sequence representing the result of the operation.
	//	 */
	//	public static double[] append(final double x, final double[] xs)
	//	{
	//		final int n = len(xs);
	//		final double[] newArr = new double[n + 1];
	//		newArr[n] = x;
	//		System.arraycopy(xs, 0, newArr, 0, n);
	//		return newArr;
	//	}
	//
	//	/**
	//	 * Append an element to the end of a sequence.
	//	 *
	//	 * @param x - To append
	//	 * @param xs - To append to
	//	 * @return a new sequence representing the result of the operation.
	//	 */
	//	public static RangedIntFunction append(final int x, final IntChain xs)
	//	{
	//		final int n = len(xs);
	//		return RangedIntFunction.of(i -> i < n ? xs.elementAt(i) : x, n + 1);
	//	}
	//
	//	/**
	//	 * Append an element to the end of a sequence.
	//	 *
	//	 * @param x - To append
	//	 * @param xs - To append to
	//	 * @return a new sequence representing the result of the operation.
	//	 */
	//	public static ImmutableIntArray append(final int x, final ImmutableIntArray xs)
	//	{
	//		return append(x, asFunction(xs)).toImmutableArray();
	//	}
	//
	//	/**
	//	 * Append an element to the end of a sequence.
	//	 *
	//	 * @param x - To append
	//	 * @param xs - To append to
	//	 * @return a new sequence representing the result of the operation.
	//	 */
	//	public static int[] append(final int x, final int[] xs)
	//	{
	//		final int n = len(xs);
	//		final int[] newArr = new int[n + 1];
	//		newArr[n] = x;
	//		System.arraycopy(xs, 0, newArr, 0, n);
	//		return newArr;
	//	}
	//
	//	/**
	//	 * Append an element to the end of a sequence.
	//	 *
	//	 * @param x - To append
	//	 * @param xs - To append to
	//	 * @return a new sequence representing the result of the operation.
	//	 */
	//	public static RangedLongFunction append(final long x, final LongChain xs)
	//	{
	//		final int n = len(xs);
	//		return RangedLongFunction.of(i -> i < n ? xs.elementAt(i) : x, n + 1);
	//	}
	//
	//	/**
	//	 * Append an element to the end of a sequence.
	//	 *
	//	 * @param x - To append
	//	 * @param xs - To append to
	//	 * @return a new sequence representing the result of the operation.
	//	 */
	//	public static ImmutableLongArray append(final long x, final ImmutableLongArray xs)
	//	{
	//		return append(x, asFunction(xs)).toImmutableArray();
	//	}
	//
	//	/**
	//	 * Append an element to the end of a sequence.
	//	 *
	//	 * @param x - To append
	//	 * @param xs - To append to
	//	 * @return a new sequence representing the result of the operation.
	//	 */
	//	public static long[] append(final long x, final long[] xs)
	//	{
	//		final int n = len(xs);
	//		final long[] newArr = new long[n + 1];
	//		newArr[n] = x;
	//		System.arraycopy(xs, 0, newArr, 0, n);
	//		return newArr;
	//	}

	//	/* -----------------------------------------------------------------------------------------------
	//	 * A unified insert function |
	//	 * -----------------------------------------------------------------------------------------------
	//	 *
	//	 * TODO - performance of method to append elements to the google primitives will depened on length */
	//	/**
	//	 * Insert an element at the start of a sequence.
	//	 *
	//	 * @param x - To insert
	//	 * @param xs - To insert into
	//	 * @return a new sequence representing the result of the operation.
	//	 */
	//	public static <T, E extends T> RangedFunction<T> insert(final E x, final Chain<T> xs)
	//	{
	//		final int n = len(xs);
	//		return RangedFunction.of(i -> i > 0 ? xs.elementAt(i - 1) : x, n + 1);
	//	}
	//
	//	/**
	//	 * Insert an element at the start of a sequence.
	//	 *
	//	 * @param x - To insert
	//	 * @param xs - To insert into
	//	 * @return a new sequence representing the result of the operation.
	//	 */
	//	public static <T, E extends T> ImmutableList<T> insert(final E x, final T[] xs)
	//	{
	//		final int n = len(xs);
	//		final Chain<T> combined = RangedFunction.of(i -> i > 0 ? xs[i - 1] : x, n + 1);
	//		return ImmutableList.copyOf(combined);
	//	}
	//
	//	/**
	//	 * Insert an element at the start of a sequence.
	//	 *
	//	 * @param x - To insert
	//	 * @param xs - To insert into
	//	 * @return a new sequence representing the result of the operation.
	//	 */
	//	public static <T, E extends T> ImmutableList<T> insert(final E x, final List<T> xs)
	//	{
	//		final int n = len(xs);
	//		final Chain<T> combined = RangedFunction.of(i -> i > 0 ? xs.get(i - 1) : x, n + 1);
	//		return ImmutableList.copyOf(combined);
	//	}
	//
	//	/**
	//	 * Insert an element at the start of a sequence.
	//	 *
	//	 * @param x - To insert
	//	 * @param xs - To insert into
	//	 * @return a new sequence representing the result of the operation.
	//	 */
	//	public static RangedDoubleFunction insert(final double x, final DoubleChain xs)
	//	{
	//		final int n = len(xs);
	//		return RangedDoubleFunction.of(i -> i > 0 ? xs.elementAt(i - 1) : x, n + 1);
	//	}
	//
	//	/**
	//	 * Insert an element at the start of a sequence.
	//	 *
	//	 * @param x - To insert
	//	 * @param xs - To insert into
	//	 * @return a new sequence representing the result of the operation.
	//	 */
	//	public static ImmutableDoubleArray insert(final double x, final ImmutableDoubleArray xs)
	//	{
	//		return insert(x, asFunction(xs)).toImmutableArray();
	//	}
	//
	//	/**
	//	 * Insert an element at the start of a sequence.
	//	 *
	//	 * @param x - To insert
	//	 * @param xs - To insert into
	//	 * @return a new sequence representing the result of the operation.
	//	 */
	//	public static double[] insert(final double x, final double[] xs)
	//	{
	//		final int n = len(xs);
	//		final double[] newArr = new double[n + 1];
	//		newArr[0] = x;
	//		System.arraycopy(xs, 0, newArr, 1, n);
	//		return newArr;
	//	}
	//
	//	/**
	//	 * Insert an element at the start of a sequence.
	//	 *
	//	 * @param x - To insert
	//	 * @param xs - To insert into
	//	 * @return a new sequence representing the result of the operation.
	//	 */
	//	public static RangedIntFunction insert(final int x, final IntChain xs)
	//	{
	//		final int n = len(xs);
	//		return RangedIntFunction.of(i -> i > 0 ? xs.elementAt(i - 1) : x, n + 1);
	//	}
	//
	//	/**
	//	 * Insert an element at the start of a sequence.
	//	 *
	//	 * @param x - To insert
	//	 * @param xs - To insert into
	//	 * @return a new sequence representing the result of the operation.
	//	 */
	//	public static ImmutableIntArray insert(final int x, final ImmutableIntArray xs)
	//	{
	//		return insert(x, asFunction(xs)).toImmutableArray();
	//	}
	//
	//	/**
	//	 * Insert an element at the start of a sequence.
	//	 *
	//	 * @param x - To insert
	//	 * @param xs - To insert into
	//	 * @return a new sequence representing the result of the operation.
	//	 */
	//	public static int[] insert(final int x, final int[] xs)
	//	{
	//		final int n = len(xs);
	//		final int[] newArr = new int[n + 1];
	//		newArr[0] = x;
	//		System.arraycopy(xs, 0, newArr, 1, n);
	//		return newArr;
	//	}
	//
	//	/**
	//	 * Insert an element at the start of a sequence.
	//	 *
	//	 * @param x - To insert
	//	 * @param xs - To insert into
	//	 * @return a new sequence representing the result of the operation.
	//	 */
	//	public static RangedLongFunction insert(final long x, final LongChain xs)
	//	{
	//		final int n = len(xs);
	//		return RangedLongFunction.of(i -> i > 0 ? xs.elementAt(i - 1) : x, n + 1);
	//	}
	//
	//	/**
	//	 * Insert an element at the start of a sequence.
	//	 *
	//	 * @param x - To insert
	//	 * @param xs - To insert into
	//	 * @return a new sequence representing the result of the operation.
	//	 */
	//	public static ImmutableLongArray insert(final long x, final ImmutableLongArray xs)
	//	{
	//		return insert(x, asFunction(xs)).toImmutableArray();
	//	}
	//
	//	/**
	//	 * Insert an element at the start of a sequence.
	//	 *
	//	 * @param x - To insert
	//	 * @param xs - To insert into
	//	 * @return a new sequence representing the result of the operation.
	//	 */
	//	public static long[] insert(final long x, final long[] xs)
	//	{
	//		final int n = len(xs);
	//		final long[] newArr = new long[n + 1];
	//		newArr[0] = x;
	//		System.arraycopy(xs, 0, newArr, 1, n);
	//		return newArr;
	//	}

}
