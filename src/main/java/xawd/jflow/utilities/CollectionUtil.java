package xawd.jflow.utilities;


import java.util.ArrayList;
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
	 * @param e - the object to convert
	 * @return the string representation of <b>t</b>
	 */
	public static <E> String str(final E e)
	{
		return e.toString();
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

	/**
	 * Reverses the passed sequence
	 *
	 * @param xs - the passed sequence
	 * @return a new sequence of the same type as passed containing the same values but with a reversed order.
	 */
	public static <E> List<E> reverse(final List<? extends E> source)
	{
		final int n = len(source);
		final List<E> reversed = new ArrayList<>(n);
		for (int i = n - 1; i > -1; i--) {
			reversed.add(source.get(i));
		}
		return reversed;
	}

	//
	//	/* -----------------------------------------------------------------------------------------------
	//	 * A unified take function a la Haskell |
	//	 * -----------------------------------------------------------------------------------------------
	//	 *
	//	 */
	//
	/**
	 * Take the first n elements from the beginning of an array retaining their order.
	 *
	 * @param n - The number of elements to take
	 * @param xs - The sequence to take from
	 * @return an int array containing the taken elements.
	 */
	public static int[] take(final int n, final int[] xs)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final int newSize = Math.min(len(xs), n);
		return Arrays.copyOf(xs, newSize);
	}


	/**
	 * Take the first n elements from the beginning of an array retaining their order.
	 *
	 * @param n - The number of elements to take
	 * @param xs - The array to take from
	 * @return a double array containing the taken elements.
	 */
	public static double[] take(final int n, final double[] xs)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final int newSize = Math.min(len(xs), n);
		return Arrays.copyOf(xs, newSize);
	}

	/**
	 * Take the first n elements from the beginning of an array retaining their order.
	 *
	 * @param n - The number of elements to take
	 * @param xs - The array to take from
	 * @return a long array containing the taken elements.
	 */
	public static long[] take(final int n, final long[] xs)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final int newSize = Math.min(len(xs), n);
		return Arrays.copyOf(xs, newSize);
	}

	/**
	 * Take the first n elements from the beginning of a List retaining their order.
	 *
	 * @param n - The number of elements to take
	 * @param xs - The List to take from
	 * @return an {@link ImmutableList} containing the taken elements.
	 */
	public static <E> List<E> take(final int n, final List<? extends E> xs)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final int newSize = Math.min(len(xs), n);
		final List<E> taken = new ArrayList<>(newSize);
		for (int i = 0; i < newSize; i++) {
			taken.add(xs.get(i));
		}
		return taken;
	}

	/* -----------------------------------------------------------------------------------------------
	 * A unified drop function a la Haskell |
	 * -----------------------------------------------------------------------------------------------
	 *
	 */

	/**
	 * Drop the first n elements from the beginning of an int array retaining the remaining order.
	 *
	 * @param n - The number of elements to drop
	 * @param xs - The int array to drop from
	 * @return an int array containing the remaining elements.
	 */
	public static int[] drop(final int n, final int[] xs)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final int dropPos = Math.min(len(xs), n);
		final int newSize = len(xs) - dropPos;
		final int[] newArr = new int[newSize];
		System.arraycopy(xs, dropPos, newArr, 0, newSize);
		return newArr;
	}

	/**
	 * Drop the first n elements from the beginning of an array retaining the remaining order.
	 *
	 * @param n - The number of elements to drop
	 * @param xs - The array to drop from
	 * @return a double array containing the remaining elements.
	 */
	public static double[] drop(final int n, final double[] xs)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final int dropPos = Math.min(len(xs), n);
		final int newSize = len(xs) - dropPos;
		final double[] newArr = new double[newSize];
		System.arraycopy(xs, dropPos, newArr, 0, newSize);
		return newArr;
	}

	/**
	 * Drop the first n elements from the beginning of an array retaining the remaining order.
	 *
	 * @param n - The number of elements to drop
	 * @param xs - The array to drop from
	 * @return a long array containing the remaining elements.
	 */
	public static long[] drop(final int n, final long[] xs)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final int dropPos = Math.min(len(xs), n);
		final int newSize = len(xs) - dropPos;
		final long[] newArr = new long[newSize];
		System.arraycopy(xs, dropPos, newArr, 0, newSize);
		return newArr;
	}

	/**
	 * Drop the first n elements from the beginning of a List retaining the remaining order.
	 *
	 * @param n - the number of elements to drop
	 * @param xs - The List to drop from
	 * @return an {@linkplain List} containing the remaining elements.
	 */
	public static <E> List<E> drop(final int n, final List<? extends E> xs)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final int dropPos = Math.min(len(xs), n);
		final int newSize = len(xs) - dropPos;
		final List<E> newList = new ArrayList<>(newSize);
		for (int i = 0; i < newSize; i++) {
			newList.add(xs.get(i + dropPos));
		}
		return newList;
	}

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
	public static <E> E head(final List<? extends E> xs)
	{
		assert len(xs) > 0;
		return xs.get(0);
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
	public static <E> E tail(final List<? extends E> xs)
	{
		assert len(xs) > 0;
		return xs.get(len(xs) - 1);
	}

	//	/* -----------------------------------------------------------------------------------------------
	//	 * A unified append function |
	//	 * -----------------------------------------------------------------------------------------------
	//	 *
	//	 */

	/**
	 * Append an element to the end of a sequence.
	 *
	 * @param x - To append
	 * @param xs - To append to
	 * @return a new sequence representing the result of the operation.
	 */
	public static <E, E2 extends E> List<E> append(final E2 x, final List<? extends E> xs)
	{
		final int n = len(xs);
		final List<E> newList = new ArrayList<>(n + 1);
		newList.addAll(xs);
		newList.add(x);
		return newList;
	}


	/**
	 * Append an element to the end of a sequence.
	 *
	 * @param x - To append
	 * @param xs - To append to
	 * @return a new sequence representing the result of the operation.
	 */
	public static double[] append(final double x, final double[] xs)
	{
		final int n = len(xs);
		final double[] newArr = new double[n + 1];
		newArr[n] = x;
		System.arraycopy(xs, 0, newArr, 0, n);
		return newArr;
	}


	/**
	 * Append an element to the end of a sequence.
	 *
	 * @param x - To append
	 * @param xs - To append to
	 * @return a new sequence representing the result of the operation.
	 */
	public static int[] append(final int x, final int[] xs)
	{
		final int n = len(xs);
		final int[] newArr = new int[n + 1];
		newArr[n] = x;
		System.arraycopy(xs, 0, newArr, 0, n);
		return newArr;
	}

	/**
	 * Append an element to the end of a sequence.
	 *
	 * @param x - To append
	 * @param xs - To append to
	 * @return a new sequence representing the result of the operation.
	 */
	public static long[] append(final long x, final long[] xs)
	{
		final int n = len(xs);
		final long[] newArr = new long[n + 1];
		newArr[n] = x;
		System.arraycopy(xs, 0, newArr, 0, n);
		return newArr;
	}

	//	/* -----------------------------------------------------------------------------------------------
	//	 * A unified insert function |
	//	 * -----------------------------------------------------------------------------------------------
	//	 *
	//	 * TODO - performance of method to append elements to the google primitives will depened on length */

	/**
	 * Insert an element at the start of a sequence.
	 *
	 * @param x - To insert
	 * @param xs - To insert into
	 * @return a new sequence representing the result of the operation.
	 */
	public static <E, E2 extends E> List<E> insert(final E2 x, final List<? extends E> xs)
	{
		final int n = len(xs);
		final List<E> newList = new ArrayList<>(n + 1);
		newList.add(x);
		newList.addAll(xs);
		return newList;
	}


	/**
	 * Insert an element at the start of a sequence.
	 *
	 * @param x - To insert
	 * @param xs - To insert into
	 * @return a new sequence representing the result of the operation.
	 */
	public static double[] insert(final double x, final double[] xs)
	{
		final int n = len(xs);
		final double[] newArr = new double[n + 1];
		newArr[0] = x;
		System.arraycopy(xs, 0, newArr, 1, n);
		return newArr;
	}

	/**
	 * Insert an element at the start of a sequence.
	 *
	 * @param x - To insert
	 * @param xs - To insert into
	 * @return a new sequence representing the result of the operation.
	 */
	public static int[] insert(final int x, final int[] xs)
	{
		final int n = len(xs);
		final int[] newArr = new int[n + 1];
		newArr[0] = x;
		System.arraycopy(xs, 0, newArr, 1, n);
		return newArr;
	}

	/**
	 * Insert an element at the start of a sequence.
	 *
	 * @param x - To insert
	 * @param xs - To insert into
	 * @return a new sequence representing the result of the operation.
	 */
	public static long[] insert(final long x, final long[] xs)
	{
		final int n = len(xs);
		final long[] newArr = new long[n + 1];
		newArr[0] = x;
		System.arraycopy(xs, 0, newArr, 1, n);
		return newArr;
	}
}
