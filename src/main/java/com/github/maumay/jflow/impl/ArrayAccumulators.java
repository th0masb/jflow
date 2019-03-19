/**
 *
 */
package com.github.maumay.jflow.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.maumay.jflow.utils.Exceptions;

/**
 * Used for building primitive arrays when we have an unknown amount of entries.
 * 
 * @author t
 */
public final class ArrayAccumulators
{
	static final int INITIAL_ARRAY_SIZE = 20;
	static final int INITIAL_ACCUMULATION_CAPACITY = 10;
	static final int MAX_ARRAY_SIZE = 1 << 16;
	static final int GROWTH_FACTOR = 2;

	@SuppressWarnings("unchecked")
	static <E> E[] consume(AbstractRichIterator<? extends E> src)
	{
		Exceptions.require(src.hasOwnership());
		switch (src.getSize().getType()) {
		case EXACT: {
			int size = ((KnownSize) src.getSize()).getValue();
			Object[] dest = new Object[size];
			for (int i = 0; i < size; i++) {
				dest[i] = src.nextImpl();
			}
			return (E[]) dest;
		}
		case BOUNDED: {
			int upper = ((BoundedSize) src.getSize()).upper();
			if (upper < MAX_ARRAY_SIZE) {
				Object[] tmp = new Object[upper];
				int counter = 0;
				while (src.hasNext()) {
					tmp[counter++] = src.nextImpl();
				}
				if (counter == upper) {
					return (E[]) tmp;
				} else {
					Object[] dest = new Object[counter];
					System.arraycopy(tmp, 0, dest, 0, counter);
					return (E[]) dest;
				}
			}
			// Fall through if upper bound is too large.
		}
		case LOWER_BOUND: {
			OfObject<E> accumulator = createObjectAccumulator();
			while (src.hasNext()) {
				accumulator.add(src.nextImpl());
			}
			return accumulator.compress();
		}
		case INFINITE:
			throw new InfiniteConsumptionException();
		default:
			throw new RuntimeException();
		}
	}

	static int[] consume(AbstractIntIterator src)
	{
		Exceptions.require(src.hasOwnership());
		switch (src.getSize().getType()) {
		case EXACT: {
			int size = ((KnownSize) src.getSize()).getValue();
			int[] dest = new int[size];
			for (int i = 0; i < size; i++) {
				dest[i] = src.nextIntImpl();
			}
			return dest;
		}
		case BOUNDED: {
			int upper = ((BoundedSize) src.getSize()).upper();
			if (upper < MAX_ARRAY_SIZE) {
				int[] tmp = new int[upper];
				int counter = 0;
				while (src.hasNext()) {
					tmp[counter++] = src.nextIntImpl();
				}
				if (counter == upper) {
					return tmp;
				} else {
					int[] dest = new int[counter];
					System.arraycopy(tmp, 0, dest, 0, counter);
					return dest;
				}
			}
			// Fall through if upper bound is too large.
		}
		case LOWER_BOUND: {
			OfInt accumulator = createIntAccumulator();
			while (src.hasNext()) {
				accumulator.add(src.nextIntImpl());
			}
			return accumulator.compress();
		}
		case INFINITE:
			throw new InfiniteConsumptionException();
		default:
			throw new RuntimeException();
		}
	}

	static long[] consume(AbstractLongIterator src)
	{
		Exceptions.require(src.hasOwnership());
		switch (src.getSize().getType()) {
		case EXACT: {
			int size = ((KnownSize) src.getSize()).getValue();
			long[] dest = new long[size];
			for (int i = 0; i < size; i++) {
				dest[i] = src.nextLongImpl();
			}
			return dest;
		}
		case BOUNDED: {
			int upper = ((BoundedSize) src.getSize()).upper();
			if (upper < MAX_ARRAY_SIZE) {
				long[] tmp = new long[upper];
				int counter = 0;
				while (src.hasNext()) {
					tmp[counter++] = src.nextLongImpl();
				}
				if (counter == upper) {
					return tmp;
				} else {
					long[] dest = new long[counter];
					System.arraycopy(tmp, 0, dest, 0, counter);
					return dest;
				}
			}
			// Fall through if upper bound is too large.
		}
		case LOWER_BOUND: {
			OfLong accumulator = createLongAccumulator();
			while (src.hasNext()) {
				accumulator.add(src.nextLongImpl());
			}
			return accumulator.compress();
		}
		case INFINITE:
			throw new InfiniteConsumptionException();
		default:
			throw new RuntimeException();
		}
	}

	static double[] consume(AbstractDoubleIterator src)
	{
		Exceptions.require(src.hasOwnership());
		switch (src.getSize().getType()) {
		case EXACT: {
			int size = ((KnownSize) src.getSize()).getValue();
			double[] dest = new double[size];
			for (int i = 0; i < size; i++) {
				dest[i] = src.nextDoubleImpl();
			}
			return dest;
		}
		case BOUNDED: {
			int upper = ((BoundedSize) src.getSize()).upper();
			if (upper < MAX_ARRAY_SIZE) {
				double[] tmp = new double[upper];
				int counter = 0;
				while (src.hasNext()) {
					tmp[counter++] = src.nextDoubleImpl();
				}
				if (counter == upper) {
					return tmp;
				} else {
					double[] dest = new double[counter];
					System.arraycopy(tmp, 0, dest, 0, counter);
					return dest;
				}
			}
			// Fall through if upper bound is too large.
		}
		case LOWER_BOUND: {
			OfDouble accumulator = createDoubleAccumulator();
			while (src.hasNext()) {
				accumulator.add(src.nextDoubleImpl());
			}
			return accumulator.compress();
		}
		case INFINITE:
			throw new InfiniteConsumptionException();
		default:
			throw new RuntimeException();
		}
	}

	public static <E> OfObject<E> createObjectAccumulator()
	{
		return new OfObject<>();
	}

	public static OfInt createIntAccumulator()
	{
		return new OfInt();
	}

	public static OfDouble createDoubleAccumulator()
	{
		return new OfDouble();
	}

	public static OfLong createLongAccumulator()
	{
		return new OfLong();
	}

	public static class OfObject<E>
	{
		private final List<Object[]> arrays;
		private int runningIndex;

		OfObject()
		{
			arrays = new ArrayList<>(INITIAL_ACCUMULATION_CAPACITY);
			arrays.add(new Object[INITIAL_ARRAY_SIZE]);
			runningIndex = 0;
		}

		public void add(E element)
		{
			Object[] currentStore = arrays.get(arrays.size() - 1);
			currentStore[runningIndex] = element;
			runningIndex = (runningIndex + 1) % currentStore.length;

			if (runningIndex == 0) {
				int newSize = Math.min(MAX_ARRAY_SIZE, GROWTH_FACTOR * currentStore.length);
				arrays.add(new Object[newSize]);
			}
		}

		@SuppressWarnings("unchecked")
		public E[] compress()
		{
			int total = 0;
			for (int i = 0; i < arrays.size() - 1; i++) {
				total += arrays.get(i).length;
			}

			Object[] result = new Object[runningIndex + total];
			int indexTracker = 0;

			// Copy the full arrays
			for (int i = 0; i < arrays.size() - 1; i++) {
				Object[] array = arrays.get(i);
				System.arraycopy(array, 0, result, indexTracker, array.length);
				indexTracker += array.length;
			}
			// copy the last array which is only partially full.
			System.arraycopy(arrays.get(arrays.size() - 1), 0, result, indexTracker, runningIndex);
			return (E[]) result;
		}
	}

	public static class OfInt
	{
		private final List<int[]> arrays = new ArrayList<>(INITIAL_ACCUMULATION_CAPACITY);
		private int runningIndex = 0;

		OfInt()
		{
			arrays.add(new int[INITIAL_ARRAY_SIZE]);
		}

		public void add(int n)
		{
			int[] currentStore = arrays.get(arrays.size() - 1);
			currentStore[runningIndex] = n;
			runningIndex = (runningIndex + 1) % currentStore.length;

			if (runningIndex == 0) {
				int newSize = Math.min(MAX_ARRAY_SIZE, GROWTH_FACTOR * currentStore.length);
				arrays.add(new int[newSize]);
			}
		}

		public int[] compress()
		{
			int total = 0;
			for (int i = 0; i < arrays.size() - 1; i++) {
				total += arrays.get(i).length;
			}

			int[] result = new int[runningIndex + total];
			int indexTracker = 0;

			// Copy the full arrays
			for (int i = 0; i < arrays.size() - 1; i++) {
				int[] array = arrays.get(i);
				System.arraycopy(array, 0, result, indexTracker, array.length);
				indexTracker += array.length;
			}
			// copy the last array which is only partially full.
			System.arraycopy(arrays.get(arrays.size() - 1), 0, result, indexTracker, runningIndex);
			return result;
		}
	}

	public static class OfDouble
	{
		private final List<double[]> arrays = new ArrayList<>(INITIAL_ACCUMULATION_CAPACITY);
		private int runningIndex = 0;

		OfDouble()
		{
			arrays.add(new double[INITIAL_ARRAY_SIZE]);
		}

		public void add(double n)
		{
			double[] currentStore = arrays.get(arrays.size() - 1);
			currentStore[runningIndex] = n;
			runningIndex = (runningIndex + 1) % currentStore.length;

			if (runningIndex == 0) {
				int newSize = Math.min(MAX_ARRAY_SIZE, GROWTH_FACTOR * currentStore.length);
				arrays.add(new double[newSize]);
			}
		}

		public double[] compress()
		{
			int total = 0;
			for (int i = 0; i < arrays.size() - 1; i++) {
				total += arrays.get(i).length;
			}

			double[] result = new double[runningIndex + total];
			int indexTracker = 0;

			// Copy the full arrays
			for (int i = 0; i < arrays.size() - 1; i++) {
				double[] array = arrays.get(i);
				System.arraycopy(array, 0, result, indexTracker, array.length);
				indexTracker += array.length;
			}
			// copy the last array which is only partially full.
			System.arraycopy(arrays.get(arrays.size() - 1), 0, result, indexTracker, runningIndex);
			return result;
		}
	}

	public static class OfLong
	{
		private final List<long[]> arrays = new ArrayList<>(INITIAL_ACCUMULATION_CAPACITY);
		private int runningIndex = 0;

		OfLong()
		{
			arrays.add(new long[INITIAL_ARRAY_SIZE]);
		}

		public void add(long n)
		{
			long[] currentStore = arrays.get(arrays.size() - 1);
			currentStore[runningIndex] = n;
			runningIndex = (runningIndex + 1) % currentStore.length;

			if (runningIndex == 0) {
				int newSize = Math.min(MAX_ARRAY_SIZE, GROWTH_FACTOR * currentStore.length);
				arrays.add(new long[newSize]);
			}
		}

		public long[] compress()
		{
			int total = 0;
			for (int i = 0; i < arrays.size() - 1; i++) {
				total += arrays.get(i).length;
			}

			long[] result = new long[runningIndex + total];
			int indexTracker = 0;

			// Copy the full arrays
			for (int i = 0; i < arrays.size() - 1; i++) {
				long[] array = arrays.get(i);
				System.arraycopy(array, 0, result, indexTracker, array.length);
				indexTracker += array.length;
			}
			// copy the last array which is only partially full.
			System.arraycopy(arrays.get(arrays.size() - 1), 0, result, indexTracker, runningIndex);
			return result;
		}
	}
}
