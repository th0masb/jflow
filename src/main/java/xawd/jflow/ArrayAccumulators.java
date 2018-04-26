/**
 *
 */
package xawd.jflow;

import static xawd.jchain.chains.utilities.CollectionUtil.tail;

import java.util.ArrayList;
import java.util.List;

/**
 * @author t
 *
 */
final class ArrayAccumulators
{
	static final int DEFAULT_ARRAY_SIZE = 20;

	static OfInt intAccumulator()
	{
		return new OfInt();
	}

	static OfDouble doubleAccumulator()
	{
		return new OfDouble();
	}

	static OfLong longAccumulator()
	{
		return new OfLong();
	}

	static class OfInt
	{
		private final List<int[]> arrays = new ArrayList<>();
		private int count = 0;

		public OfInt()
		{
			arrays.add(new int[DEFAULT_ARRAY_SIZE]);
		}

		void add(final int i)
		{
			final int[] currentStore = tail(arrays);
			currentStore[(count++ % DEFAULT_ARRAY_SIZE)] = i;
			if (count % DEFAULT_ARRAY_SIZE == 0) {
				arrays.add(new int[DEFAULT_ARRAY_SIZE]);
			}
		}

		int[] compress()
		{
			final int[] compressed = new int[count];
			final int cacheSizeMinusOne = arrays.size() - 1;
			for (int i = 0; i < cacheSizeMinusOne; i++) {
				System.arraycopy(arrays.get(i), 0, compressed, i*DEFAULT_ARRAY_SIZE, DEFAULT_ARRAY_SIZE);
			}
			final int lastCacheSize = compressed.length - cacheSizeMinusOne*DEFAULT_ARRAY_SIZE;
			System.arraycopy(tail(arrays), 0, compressed, cacheSizeMinusOne*DEFAULT_ARRAY_SIZE, lastCacheSize);
			return compressed;
		}
	}

	static class OfDouble
	{
		private final List<double[]> arrays = new ArrayList<>();
		private int count = 0;

		public OfDouble()
		{
			arrays.add(new double[DEFAULT_ARRAY_SIZE]);
		}

		void add(final double i)
		{
			final double[] currentStore = tail(arrays);
			currentStore[(count++ % DEFAULT_ARRAY_SIZE)] = i;
			if (count % DEFAULT_ARRAY_SIZE == 0) {
				arrays.add(new double[DEFAULT_ARRAY_SIZE]);
			}
		}

		double[] compress()
		{
			final double[] compressed = new double[count];
			final int cacheSizeMinusOne = arrays.size() - 1;
			for (int i = 0; i < cacheSizeMinusOne; i++) {
				System.arraycopy(arrays.get(i), 0, compressed, i*DEFAULT_ARRAY_SIZE, DEFAULT_ARRAY_SIZE);
			}
			final int lastCacheSize = compressed.length - cacheSizeMinusOne*DEFAULT_ARRAY_SIZE;
			System.arraycopy(tail(arrays), 0, compressed, cacheSizeMinusOne*DEFAULT_ARRAY_SIZE, lastCacheSize);
			return compressed;
		}
	}

	static class OfLong
	{
		private final List<long[]> arrays = new ArrayList<>();
		private int count = 0;

		public OfLong()
		{
			arrays.add(new long[DEFAULT_ARRAY_SIZE]);
		}

		void add(final long i)
		{
			final long[] currentStore = tail(arrays);
			currentStore[(count++ % DEFAULT_ARRAY_SIZE)] = i;
			if (count % DEFAULT_ARRAY_SIZE == 0) {
				arrays.add(new long[DEFAULT_ARRAY_SIZE]);
			}
		}

		long[] compress()
		{
			final long[] compressed = new long[count];
			final int cacheSizeMinusOne = arrays.size() - 1;
			for (int i = 0; i < cacheSizeMinusOne; i++) {
				System.arraycopy(arrays.get(i), 0, compressed, i*DEFAULT_ARRAY_SIZE, DEFAULT_ARRAY_SIZE);
			}
			final int lastCacheSize = compressed.length - cacheSizeMinusOne*DEFAULT_ARRAY_SIZE;
			System.arraycopy(tail(arrays), 0, compressed, cacheSizeMinusOne*DEFAULT_ARRAY_SIZE, lastCacheSize);
			return compressed;
		}
	}
}
