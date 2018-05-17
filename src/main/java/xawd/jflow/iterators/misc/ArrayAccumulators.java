/**
 *
 */
package xawd.jflow.iterators.misc;

import static xawd.jflow.utilities.CollectionUtil.tail;

import java.util.ArrayList;
import java.util.List;

/**
 * @author t
 *
 */
public final class ArrayAccumulators
{
	static final int DEFAULT_ARRAY_SIZE = 40;

	public static OfInt intAccumulator()
	{
		return new OfInt();
	}

	public static OfDouble doubleAccumulator()
	{
		return new OfDouble();
	}

	public static OfLong longAccumulator()
	{
		return new OfLong();
	}

	public static class OfInt
	{
		private final List<int[]> arrays = new ArrayList<>();
		private int count = 0;

		public OfInt()
		{
			arrays.add(new int[DEFAULT_ARRAY_SIZE]);
		}

		public void add(final int i)
		{
			final int[] currentStore = tail(arrays);
			currentStore[(count++ % DEFAULT_ARRAY_SIZE)] = i;
			if (count % DEFAULT_ARRAY_SIZE == 0) {
				arrays.add(new int[DEFAULT_ARRAY_SIZE]);
			}
		}

		public int[] compress()
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

	public static class OfDouble
	{
		private final List<double[]> arrays = new ArrayList<>();
		private int count = 0;

		public OfDouble()
		{
			arrays.add(new double[DEFAULT_ARRAY_SIZE]);
		}

		public void add(final double i)
		{
			final double[] currentStore = tail(arrays);
			currentStore[(count++ % DEFAULT_ARRAY_SIZE)] = i;
			if (count % DEFAULT_ARRAY_SIZE == 0) {
				arrays.add(new double[DEFAULT_ARRAY_SIZE]);
			}
		}

		public double[] compress()
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

	public static class OfLong
	{
		private final List<long[]> arrays = new ArrayList<>();
		private int count = 0;

		public OfLong()
		{
			arrays.add(new long[DEFAULT_ARRAY_SIZE]);
		}

		public void add(final long i)
		{
			final long[] currentStore = tail(arrays);
			currentStore[(count++ % DEFAULT_ARRAY_SIZE)] = i;
			if (count % DEFAULT_ARRAY_SIZE == 0) {
				arrays.add(new long[DEFAULT_ARRAY_SIZE]);
			}
		}

		public long[] compress()
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
