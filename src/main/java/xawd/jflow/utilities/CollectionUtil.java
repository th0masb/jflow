package xawd.jflow.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Static methods for manipulating sequences.
 *
 * @author ThomasB
 * @since 25 Jan 2018
 */
public final class CollectionUtil
{
	private CollectionUtil()
	{
	}

	/**
	 * A unified size function.
	 *
	 * @param s
	 *            A non-null {@linkplain String} reference.
	 * @return The character length of the parameter String.
	 */
	public static int sizeOf(final String s)
	{
		return s.length();
	}

	/**
	 * A unified size function.
	 *
	 * @param <E>
	 *            The upper bound on the sequence element types.
	 *
	 * @param xs
	 *            A non-null {@linkplain Collection} reference.
	 * @return The size of the parameter Collection.
	 */
	public static <E> int sizeOf(final Collection<? extends E> xs)
	{
		return xs.size();
	}

	/**
	 * A unified size function.
	 *
	 * @param <E>
	 *            The upper bound on the sequence element types.
	 *
	 * @param xs
	 *            A non-null array reference.
	 * @return The length of the parameter array.
	 */
	public static <E> int sizeOf(final E[] xs)
	{
		return xs.length;
	}

	/**
	 * A unified size function.
	 *
	 * @param xs
	 *            A non-null array reference.
	 * @return The length of the parameter array.
	 */
	public static int sizeOf(final int[] xs)
	{
		return xs.length;
	}

	/**
	 * A unified size function.
	 *
	 * @param xs
	 *            A non-null array reference.
	 * @return The length of the parameter array.
	 */
	public static int sizeOf(final double[] xs)
	{
		return xs.length;
	}

	/**
	 * A unified size function.
	 *
	 * @param xs
	 *            A non-null array reference.
	 * @return The length of the parameter array.
	 */
	public static int sizeOf(final long[] xs)
	{
		return xs.length;
	}

	/**
	 * A unified size function.
	 *
	 * @param xs
	 *            A non-null array reference.
	 * @return The length of the parameter array.
	 */
	public static int sizeOf(final char[] xs)
	{
		return xs.length;
	}

	/**
	 * A unified size function.
	 *
	 * @param xs
	 *            A non-null array reference.
	 * @return The length of the parameter array.
	 */
	public static int sizeOf(final int[][] xs)
	{
		return xs.length;
	}

	/**
	 * A unified size function.
	 *
	 * @param xs
	 *            A non-null array reference.
	 * @return The length of the parameter array.
	 */
	public static int sizeOf(final double[][] xs)
	{
		return xs.length;
	}

	/**
	 * A unified size function.
	 *
	 * @param xs
	 *            A non-null array reference.
	 * @return The length of the parameter array.
	 */
	public static int sizeOf(final long[][] xs)
	{
		return xs.length;
	}

	/**
	 * Converts an object reference to a {@linkplain String}.
	 *
	 * @param obj
	 *            An object reference.
	 * @return the string representation of the parameter object.
	 */
	public static String string(final Object obj)
	{
		return obj.toString();
	}

	/**
	 * Converts an int to a {@linkplain String}.
	 *
	 * @param x
	 *            A primitive int.
	 * @return The String representation of the parameter int.
	 */
	public static String string(final int x)
	{
		return Integer.toString(x);
	}

	/**
	 * Converts a double to a {@linkplain String}.
	 *
	 * @param x
	 *            A primitive double.
	 * @return The String representation of the parameter double.
	 */
	public static String string(final double x)
	{
		return Double.toString(x);
	}

	/**
	 * Converts a long to a {@linkplain String}.
	 *
	 * @param x
	 *            A primitive long.
	 * @return The String representation of the parameter long.
	 */
	public static String string(final long x)
	{
		return Long.toString(x);
	}

	/**
	 * Converts a char to a {@linkplain String}.
	 *
	 * @param x
	 *            A primitive char.
	 * @return The String representation of the parameter char.
	 */
	public static String string(final char x)
	{
		return Character.toString(x);
	}

	/**
	 * Converts an int array to a {@linkplain String}.
	 *
	 * @param xs
	 *            An int array reference.
	 * @return The String representation of the parameter array.
	 */
	public static String string(final int[] xs)
	{
		return Arrays.toString(xs);
	}

	/**
	 * Converts a double array to a {@linkplain String}.
	 *
	 * @param xs
	 *            A double array reference.
	 * @return The String representation of the parameter array.
	 */
	public static String string(final double[] xs)
	{
		return Arrays.toString(xs);
	}

	/**
	 * Converts a long array to a {@linkplain String}.
	 *
	 * @param xs
	 *            A long array reference.
	 * @return The String representation of the parameter array.
	 */
	public static String string(final long[] xs)
	{
		return Arrays.toString(xs);
	}

	/**
	 * Converts a char array to a {@linkplain String}.
	 *
	 * @param xs
	 *            A char array reference.
	 * @return The String representation of the parameter array.
	 */
	public static String string(final char[] xs)
	{
		return Arrays.toString(xs);
	}

	/**
	 * Reverses an int array.
	 *
	 * @param xs
	 *            An int array reference.
	 * @return An int array containing the same values as the parameter but with a
	 *         reversed order.
	 */
	public static int[] reverse(final int[] xs)
	{
		final int n = sizeOf(xs);
		final int[] reversed = new int[n];
		for (int i = 0; i < n; i++) {
			reversed[i] = xs[n - (i + 1)];
		}
		return reversed;
	}

	/**
	 * Reverses a double array.
	 *
	 * @param xs
	 *            A double array reference.
	 * @return A double array containing the same values as the parameter but with a
	 *         reversed order.
	 */
	public static double[] reverse(final double[] xs)
	{
		final int n = sizeOf(xs);
		final double[] reversed = new double[n];
		for (int i = 0; i < n; i++) {
			reversed[i] = xs[n - (i + 1)];
		}
		return reversed;
	}

	/**
	 * Reverses a long array.
	 *
	 * @param xs
	 *            A long array reference.
	 * @return A long array containing the same values as the parameter but with a
	 *         reversed order.
	 */
	public static long[] reverse(final long[] xs)
	{
		final int n = sizeOf(xs);
		final long[] reversed = new long[n];
		for (int i = 0; i < n; i++) {
			reversed[i] = xs[n - (i + 1)];
		}
		return reversed;
	}

	/**
	 * Reverses a char array.
	 *
	 * @param xs
	 *            A char array reference.
	 * @return A char array containing the same values as the parameter but with a
	 *         reversed order.
	 */
	public static char[] reverse(final char[] xs)
	{
		final int n = sizeOf(xs);
		final char[] reversed = new char[n];
		for (int i = 0; i < n; i++) {
			reversed[i] = xs[n - (i + 1)];
		}
		return reversed;
	}

	/**
	 * Reverses a {@linkplain List}.
	 *
	 * @param <E>
	 *            The upper bound on the sequence element types.
	 *
	 * @param xs
	 *            A List reference.
	 * @return A List containing the same values as the parameter but with a
	 *         reversed order.
	 */
	public static <E> List<E> reverse(final List<? extends E> xs)
	{
		final int n = sizeOf(xs);
		final List<E> reversed = new ArrayList<>(n);
		for (int i = n - 1; i > -1; i--) {
			reversed.add(xs.get(i));
		}
		return reversed;
	}

	/**
	 * Take the first n elements from the beginning of an array retaining their
	 * order.
	 *
	 * @param n
	 *            The number of elements to take.
	 * @param xs
	 *            The array to take elements from.
	 * @throws IllegalArgumentException
	 *             If the take count is negative.
	 * @return An array containing the taken elements.
	 */
	public static int[] take(final int n, final int[] xs)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final int newSize = Math.min(sizeOf(xs), n);
		return Arrays.copyOf(xs, newSize);
	}

	/**
	 * Take the first n elements from the beginning of an array retaining their
	 * order.
	 *
	 * @param n
	 *            The number of elements to take.
	 * @param xs
	 *            The array to take elements from.
	 * @throws IllegalArgumentException
	 *             If the take count is negative.
	 * @return An array containing the taken elements.
	 */
	public static double[] take(final int n, final double[] xs)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final int newSize = Math.min(sizeOf(xs), n);
		return Arrays.copyOf(xs, newSize);
	}

	/**
	 * Take the first n elements from the beginning of an array retaining their
	 * order.
	 *
	 * @param n
	 *            The number of elements to take.
	 * @param xs
	 *            The array to take elements from.
	 * @throws IllegalArgumentException
	 *             If the take count is negative.
	 * @return An array containing the taken elements.
	 */
	public static long[] take(final int n, final long[] xs)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final int newSize = Math.min(sizeOf(xs), n);
		return Arrays.copyOf(xs, newSize);
	}

	/**
	 * Take the first n elements from the beginning of an array retaining their
	 * order.
	 *
	 * @param n
	 *            The number of elements to take.
	 * @param xs
	 *            The array to take elements from.
	 * @throws IllegalArgumentException
	 *             If the take count is negative.
	 * @return An array containing the taken elements.
	 */
	public static char[] take(final int n, final char[] xs)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final int newSize = Math.min(sizeOf(xs), n);
		return Arrays.copyOf(xs, newSize);
	}

	/**
	 * Take the first n elements from the beginning of a {@linkplain List} retaining
	 * their order.
	 *
	 * @param <E>
	 *            The type upper bound on the List elements.
	 *
	 * @param n
	 *            The number of elements to take.
	 * @param xs
	 *            The List to take elements from.
	 * @throws IllegalArgumentException
	 *             If the take count is negative.
	 * @return A List containing the taken elements.
	 */
	public static <E> List<E> take(final int n, final List<? extends E> xs)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final int newSize = Math.min(sizeOf(xs), n);
		final List<E> taken = new ArrayList<>(newSize);
		for (int i = 0; i < newSize; i++) {
			taken.add(xs.get(i));
		}
		return taken;
	}

	/**
	 * Drop the first n elements from the beginning of an array retaining the order
	 * of the remaining elements.
	 *
	 * @param n
	 *            The number of elements to drop.
	 * @param xs
	 *            The array to drop elements from.
	 * @throws IllegalArgumentException
	 *             If the drop count is negative.
	 * @return An array containing the elements which were not dropped from the
	 *         source.
	 */
	public static int[] drop(final int n, final int[] xs)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final int dropPos = Math.min(sizeOf(xs), n);
		final int newSize = sizeOf(xs) - dropPos;
		final int[] newArr = new int[newSize];
		System.arraycopy(xs, dropPos, newArr, 0, newSize);
		return newArr;
	}

	/**
	 * Drop the first n elements from the beginning of an array retaining the order
	 * of the remaining elements.
	 *
	 * @param n
	 *            The number of elements to drop.
	 * @param xs
	 *            The array to drop elements from.
	 * @throws IllegalArgumentException
	 *             If the drop count is negative.
	 * @return An array containing the elements which were not dropped from the
	 *         source.
	 */
	public static double[] drop(final int n, final double[] xs)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final int dropPos = Math.min(sizeOf(xs), n);
		final int newSize = sizeOf(xs) - dropPos;
		final double[] newArr = new double[newSize];
		System.arraycopy(xs, dropPos, newArr, 0, newSize);
		return newArr;
	}

	/**
	 * Drop the first n elements from the beginning of an array retaining the order
	 * of the remaining elements.
	 *
	 * @param n
	 *            The number of elements to drop.
	 * @param xs
	 *            The array to drop elements from.
	 * @throws IllegalArgumentException
	 *             If the drop count is negative.
	 * @return An array containing the elements which were not dropped from the
	 *         source.
	 */
	public static long[] drop(final int n, final long[] xs)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final int dropPos = Math.min(sizeOf(xs), n);
		final int newSize = sizeOf(xs) - dropPos;
		final long[] newArr = new long[newSize];
		System.arraycopy(xs, dropPos, newArr, 0, newSize);
		return newArr;
	}

	/**
	 * Drop the first n elements from the beginning of an array retaining the order
	 * of the remaining elements.
	 *
	 * @param n
	 *            The number of elements to drop.
	 * @param xs
	 *            The array to drop elements from.
	 * @throws IllegalArgumentException
	 *             If the drop count is negative.
	 * @return An array containing the elements which were not dropped from the
	 *         source.
	 */
	public static char[] drop(final int n, final char[] xs)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final int dropPos = Math.min(sizeOf(xs), n);
		final int newSize = sizeOf(xs) - dropPos;
		final char[] newArr = new char[newSize];
		System.arraycopy(xs, dropPos, newArr, 0, newSize);
		return newArr;
	}

	/**
	 * Drop the first n elements from the beginning of a {@linkplain List} retaining
	 * the order of the remaining elements.
	 *
	 * @param <E>
	 *            The type upper bound on the List elements.
	 *
	 * @param n
	 *            The number of elements to drop.
	 * @param xs
	 *            The List to drop elements from.
	 * @throws IllegalArgumentException
	 *             If the drop count is negative.
	 * @return A List containing the elements which were not dropped from the
	 *         source.
	 */
	public static <E> List<E> drop(final int n, final List<? extends E> xs)
	{
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		final int dropPos = Math.min(sizeOf(xs), n);
		final int newSize = sizeOf(xs) - dropPos;
		final List<E> newList = new ArrayList<>(newSize);
		for (int i = 0; i < newSize; i++) {
			newList.add(xs.get(i + dropPos));
		}
		return newList;
	}

	/**
	 * Return the first element (head) of this array.
	 *
	 * @param xs
	 *            The source array
	 * @return The first element in the source
	 */
	public static int head(final int[] xs)
	{
		return xs[0];
	}

	/**
	 * Return the first element (head) of this array.
	 *
	 * @param xs
	 *            The source array
	 * @return The first element in the source
	 */
	public static double head(final double[] xs)
	{
		return xs[0];
	}

	/**
	 * Return the first element (head) of this array.
	 *
	 * @param xs
	 *            The source array
	 * @return The first element in the source
	 */
	public static long head(final long[] xs)
	{
		return xs[0];
	}

	/**
	 * Return the first element (head) of this array.
	 *
	 * @param xs
	 *            The source array
	 * @return The first element in the source
	 */
	public static char head(final char[] xs)
	{
		return xs[0];
	}

	/**
	 * Return the first element (head) of this {@linkplain List}.
	 *
	 * @param <E>
	 *            The upper bound on the sequence element types.
	 *
	 * @param xs
	 *            The source List
	 * @return The first element in the source
	 */
	public static <E> E head(final List<? extends E> xs)
	{
		return xs.get(0);
	}

	/**
	 * Return the last element (tail) of this array.
	 *
	 * @param xs
	 *            The source array
	 * @return The last element in the source
	 */
	public static int last(final int[] xs)
	{
		return xs[sizeOf(xs) - 1];
	}

	/**
	 * Return the last element (tail) of this array.
	 *
	 * @param xs
	 *            The source array
	 * @return The last element in the source
	 */
	public static double last(final double[] xs)
	{
		return xs[sizeOf(xs) - 1];
	}

	/**
	 * Return the last element (tail) of this array.
	 *
	 * @param xs
	 *            The source array
	 * @return The last element in the source
	 */
	public static long last(final long[] xs)
	{
		return xs[sizeOf(xs) - 1];
	}

	/**
	 * Return the last element (tail) of this array.
	 *
	 * @param xs
	 *            The source array
	 * @return The last element in the source
	 */
	public static char last(final char[] xs)
	{
		return xs[sizeOf(xs) - 1];
	}

	/**
	 * Return the last element (tail) of this {@linkplain List}.
	 *
	 * @param <E>
	 *            The type upper bound on the List elements.
	 *
	 * @param xs
	 *            The source List
	 * @return The last element in the source
	 */
	public static <E> E last(final List<? extends E> xs)
	{
		return xs.get(sizeOf(xs) - 1);
	}

	/**
	 * Append an element to the end of an array.
	 *
	 * @param x
	 *            The element to append
	 * @param xs
	 *            The source array
	 * @return A new array containing the elements from the source with the
	 *         parameter appended to the end.
	 */
	public static double[] append(final double x, final double[] xs)
	{
		final int n = sizeOf(xs);
		final double[] newArr = new double[n + 1];
		newArr[n] = x;
		System.arraycopy(xs, 0, newArr, 0, n);
		return newArr;
	}

	/**
	 * Append an element to the end of an array.
	 *
	 * @param x
	 *            The element to append
	 * @param xs
	 *            The source array
	 * @return A new array containing the elements from the source with the
	 *         parameter appended to the end.
	 */
	public static int[] append(final int x, final int[] xs)
	{
		final int n = sizeOf(xs);
		final int[] newArr = new int[n + 1];
		newArr[n] = x;
		System.arraycopy(xs, 0, newArr, 0, n);
		return newArr;
	}

	/**
	 * Append an element to the end of an array.
	 *
	 * @param x
	 *            The element to append
	 * @param xs
	 *            The source array
	 * @return A new array containing the elements from the source with the
	 *         parameter appended to the end.
	 */
	public static long[] append(final long x, final long[] xs)
	{
		final int n = sizeOf(xs);
		final long[] newArr = new long[n + 1];
		newArr[n] = x;
		System.arraycopy(xs, 0, newArr, 0, n);
		return newArr;
	}

	/**
	 * Append an element to the end of an array.
	 *
	 * @param x
	 *            The element to append
	 * @param xs
	 *            The source array
	 * @return A new array containing the elements from the source with the
	 *         parameter appended to the end.
	 */
	public static char[] append(final char x, final char[] xs)
	{
		final int n = sizeOf(xs);
		final char[] newArr = new char[n + 1];
		newArr[n] = x;
		System.arraycopy(xs, 0, newArr, 0, n);
		return newArr;
	}

	/**
	 * Append an element to the end of a {@linkplain List}.
	 *
	 * @param <E>
	 *            The type upper bound on the List elements.
	 *
	 * @param <E2>
	 *            The type of the element to append.
	 *
	 * @param x
	 *            The element to append
	 * @param xs
	 *            The source List
	 * @return A new List containing the elements from the source with the parameter
	 *         appended to the end.
	 */
	public static <E, E2 extends E> List<E> append(final E2 x, final List<? extends E> xs)
	{
		final int n = sizeOf(xs);
		final List<E> newList = new ArrayList<>(n + 1);
		newList.addAll(xs);
		newList.add(x);
		return newList;
	}

	/**
	 * Insert an element at the beginning of an array.
	 *
	 * @param x
	 *            The element to insert
	 * @param xs
	 *            The source array
	 * @return A new array containing the elements from the source with the
	 *         parameter inserted at the beginning.
	 */
	public static double[] insert(final double x, final double[] xs)
	{
		final int n = sizeOf(xs);
		final double[] newArr = new double[n + 1];
		newArr[0] = x;
		System.arraycopy(xs, 0, newArr, 1, n);
		return newArr;
	}

	/**
	 * Insert an element at the beginning of an array.
	 *
	 * @param x
	 *            The element to insert
	 * @param xs
	 *            The source array
	 * @return A new array containing the elements from the source with the
	 *         parameter inserted at the beginning.
	 */
	public static int[] insert(final int x, final int[] xs)
	{
		final int n = sizeOf(xs);
		final int[] newArr = new int[n + 1];
		newArr[0] = x;
		System.arraycopy(xs, 0, newArr, 1, n);
		return newArr;
	}

	/**
	 * Insert an element at the beginning of an array.
	 *
	 * @param x
	 *            The element to insert
	 * @param xs
	 *            The source array
	 * @return A new array containing the elements from the source with the
	 *         parameter inserted at the beginning.
	 */
	public static long[] insert(final long x, final long[] xs)
	{
		final int n = sizeOf(xs);
		final long[] newArr = new long[n + 1];
		newArr[0] = x;
		System.arraycopy(xs, 0, newArr, 1, n);
		return newArr;
	}

	/**
	 * Insert an element at the beginning of an array.
	 *
	 * @param x
	 *            The element to insert
	 * @param xs
	 *            The source array
	 * @return A new array containing the elements from the source with the
	 *         parameter inserted at the beginning.
	 */
	public static char[] insert(final char x, final char[] xs)
	{
		final int n = sizeOf(xs);
		final char[] newArr = new char[n + 1];
		newArr[0] = x;
		System.arraycopy(xs, 0, newArr, 1, n);
		return newArr;
	}

	/**
	 * Insert an element at the beginning of a {@linkplain List}.
	 *
	 * @param <E>
	 *            The type upper bound on the List elements.
	 *
	 * @param <E2>
	 *            The type of the element to append.
	 *
	 * @param x
	 *            The element to insert
	 * @param xs
	 *            The source List
	 * @return A new List containing the elements from the source with the parameter
	 *         inserted at the beginning.
	 */
	public static <E, E2 extends E> List<E> insert(final E2 x, final List<? extends E> xs)
	{
		final int n = sizeOf(xs);
		final List<E> newList = new ArrayList<>(n + 1);
		newList.add(x);
		newList.addAll(xs);
		return newList;
	}
}
