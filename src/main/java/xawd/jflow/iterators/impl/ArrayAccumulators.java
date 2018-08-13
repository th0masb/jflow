/**
 *
 */
package xawd.jflow.iterators.impl;

import static xawd.jflow.utilities.CollectionUtil.last;

import java.util.ArrayList;
import java.util.List;

/**
 * @author t
 *
 */
final class ArrayAccumulators
{
	static final int INITIAL_ARRAY_SIZE            = 20;
	static final int INITIAL_ACCUMULATION_CAPACITY = 4;
	static final int MAX_ARRAY_SIZE                = 1 << 20;
	static final int GROWTH_FACTOR                 = 2;
	
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
		private final List<int[]> arrays = new ArrayList<>(INITIAL_ACCUMULATION_CAPACITY);
		private int runningIndex = 0;
		
		OfInt()
		{
			arrays.add(new int[INITIAL_ARRAY_SIZE]);
		}
		
		void add(int n)
		{
			int[] currentStore = last(arrays);
			currentStore[runningIndex] = n;
			runningIndex = (runningIndex + 1) % currentStore.length;
			
			if (runningIndex == 0) {
				int newSize = Math.min(MAX_ARRAY_SIZE, GROWTH_FACTOR * currentStore.length);
				arrays.add(new int[newSize]);
			}
		}
		
		int[] compress()
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
			System.arraycopy(last(arrays), 0, result, indexTracker, runningIndex);
			return result;
		}
	}

	static class OfDouble
	{
		private final List<double[]> arrays = new ArrayList<>(INITIAL_ACCUMULATION_CAPACITY);
		private int runningIndex = 0;
		
		OfDouble()
		{
			arrays.add(new double[INITIAL_ARRAY_SIZE]);
		}
		
		void add(double n)
		{
			double[] currentStore = last(arrays);
			currentStore[runningIndex] = n;
			runningIndex = (runningIndex + 1) % currentStore.length;
			
			if (runningIndex == 0) {
				int newSize = Math.min(MAX_ARRAY_SIZE, GROWTH_FACTOR * currentStore.length);
				arrays.add(new double[newSize]);
			}
		}
		
		double[] compress()
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
			System.arraycopy(last(arrays), 0, result, indexTracker, runningIndex);
			return result;
		}
	}

	static class OfLong
	{
		private final List<long[]> arrays = new ArrayList<>(INITIAL_ACCUMULATION_CAPACITY);
		private int runningIndex = 0;
		
		OfLong()
		{
			arrays.add(new long[INITIAL_ARRAY_SIZE]);
		}
		
		void add(long n)
		{
			long[] currentStore = last(arrays);
			currentStore[runningIndex] = n;
			runningIndex = (runningIndex + 1) % currentStore.length;
			
			if (runningIndex == 0) {
				int newSize = Math.min(MAX_ARRAY_SIZE, GROWTH_FACTOR * currentStore.length);
				arrays.add(new long[newSize]);
			}
		}
		
		long[] compress()
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
			System.arraycopy(last(arrays), 0, result, indexTracker, runningIndex);
			return result;
		}
	}
}
