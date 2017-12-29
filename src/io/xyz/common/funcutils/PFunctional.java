/**
 * 
 */
package io.xyz.common.funcutils;

import static java.lang.Math.abs;
import static java.lang.Math.signum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntUnaryOperator;
import java.util.stream.Stream;

/**
 * @author t
 *
 *	Functional utilities for working with primitive arrays
 */
public final class PFunctional 
{
	private static final double ZERO_TOLERANCE = 0.00001;
	
	public static <T> List<T> rangeMap(IntFunction<T> f, int upperBound)
	{
		List<T> mapped = new ArrayList<>(upperBound);
		for (int i = 0; i < upperBound; i++)
		{
			mapped.add(f.apply(i));
		}
		return mapped;
	}
	
	public static <T> Stream<T> rangeStream(IntFunction<T> f, int upperBound)
	{
		List<T> mapped = new ArrayList<>(upperBound);
		for (int i = 0; i < upperBound; i++)
		{
			mapped.add(f.apply(i));
		}
		return mapped.stream();
	}
	
	public static int[] range(int upperBound)
	{
		return range(0, upperBound);
	}
	
	public static int[] range(int lowerBound, int upperBound)
	{
		return range(lowerBound, upperBound, 1);
	}
	
	/**
	 * Think geometrically
	 * 
	 * @param startBound
	 * @param endBound
	 * @param step
	 * @return
	 */
	public static int[] range(int startBound, int endBound, int step)
	{
		assert step != 0 : "Cannot have 0 step";
		int boundDiff = endBound - startBound, stepSign = (int) signum(step);
		
		if (startBound == endBound)
		{
			return new int[] {};
		}
		else if (signum(boundDiff) != stepSign)
		{
			return new int[] {startBound};
		}
		else
		{
			int n = max(boundDiff/step, 1);
			int[] range = new int[n];
			for (int i = 0; i < n; i++)
			{
				range[i] = startBound + i*step;
			}
			return range;
		}
	}
	
	public static double[] drange(int upper)
	{
		return drange(0, upper, 1);
	}
	
	public static double[] drange(double lower, double upper, double step)
	{
		assert abs(step) >= ZERO_TOLERANCE  : "Cannot have 0 step";
		double boundDiff = upper - lower, stepSign = signum(step);
		
		if (abs(upper - lower) < ZERO_TOLERANCE)
		{
			return new double[] {};
		}
		else if (signum(boundDiff) != stepSign)
		{
			return new double[] {lower};
		}
		else
		{
			int n = (int) max(boundDiff/step, 1);
			double[] range = new double[n];
			for (int i = 0; i < n; i++)
			{
				range[i] = lower + i*step;
			}
			return range;
		}
	}
	
	public static double[] mapToDouble(IntToDoubleFunction f, int[] xs)
	{
		double[] result = new double[xs.length];
		for (int i = 0; i < xs.length; i++)
		{
			result[i] = f.applyAsDouble(xs[i]);
		}
		return result;
	}
	
	public static int[] mapToInt(DoubleToIntFunction f, double[] xs)
	{
		int[] result = new int[xs.length];
		for (int i = 0; i < xs.length; i++)
		{
			result[i] = f.applyAsInt(xs[i]);
		}
		return result;
	}
	
	public static double[] map(DoubleUnaryOperator f, double[] xs)
	{
		double[] result = new double[xs.length];
		for (int i = 0; i < xs.length; i++)
		{
			result[i] = f.applyAsDouble(xs[i]);
		}
		return result;
	}
	
	public static int[] map(IntUnaryOperator f, int[] xs)
	{
		int[] result = new int[xs.length];
		for (int i = 0; i < xs.length; i++)
		{
			result[i] = f.applyAsInt(xs[i]);
		}
		return result;
	}
	
	public static double[] mapIP(DoubleUnaryOperator f, double[] xs)
	{
		for (int i = 0; i < xs.length; i++)
		{
			xs[i] = f.applyAsDouble(xs[i]);
		}
		return xs;
	}
	
	public static int[] mapIP(IntUnaryOperator f, int[] xs)
	{
		for (int i = 0; i < xs.length; i++)
		{
			xs[i] = f.applyAsInt(xs[i]);
		}
		return xs;
	}
	
	public static int foldr(IntBinaryOperator f, int start, int[] xs)
	{
		int cumulativeFold = start;
		for (int i = xs.length - 1; i > -1; i--)
		{
			cumulativeFold = f.applyAsInt(xs[i], cumulativeFold);
		}
		return cumulativeFold;
	}
	
//	public static double foldr(DoubleBinaryOperator f, double start, int[] xs)
//	{
//		double cumulativeFold = start;
//		for (int i = xs.length - 1; i > -1; i--)
//		{
//			cumulativeFold = f.applyAsDouble(xs[i], cumulativeFold);
//		}
//		return cumulativeFold;
//	}
	
	public static double foldr(DoubleBinaryOperator f, double start, double[] xs)
	{
		double cumulativeFold = start;
		for (int i = xs.length - 1; i > -1; i--)
		{
			cumulativeFold = f.applyAsDouble(xs[i], cumulativeFold);
		}
		return cumulativeFold;
	}
	
	public static double sum(double... xs)
	{
		return foldr((a,b) -> a+b, 0, xs);
	}
	
	public static double product(double... xs)
	{
		return foldr((a,b) -> a*b, 1, xs);
	}
	
	public static int sum(int... xs)
	{
		return foldr((a,b) -> a+b, 0, xs);
	}
	
	public static int product(int... xs)
	{
		return foldr((a,b) -> a*b, 1, xs);
	}
	
	public static int min(int a, int b)
	{
		return a < b? a : b;
	}
	
	public static int min(int... xs)
	{
		return foldr((a,b) -> min(a, b), Integer.MAX_VALUE, xs);
	}
	
	public static double min(double a, double b)
	{
		return a < b? a : b;
	}
	
	public static double min(double... xs)
	{
		return foldr((a,b) -> min(a, b), Double.MAX_VALUE, xs);
	}
	
	public static int max(int a, int b)
	{
		return a < b? b : a;
	}
	
	public static int max(int... xs)
	{
		return foldr((a,b) -> max(a, b), Integer.MIN_VALUE, xs);
	}
	
	public static double max(double a, double b)
	{
		return a < b? b : a;
	}
	
	public static double max(double... xs)
	{
		return foldr((a,b) -> max(a, b), Double.MIN_VALUE, xs);
	}
	
	public static int foldl(IntBinaryOperator f, int start, int[] xs)
	{
		int cumulativeFold = start;
		for (int i = 0; i < xs.length; i++)
		{
			cumulativeFold = f.applyAsInt(cumulativeFold, xs[i]);
		}
		return cumulativeFold;
	}
	
	public static double foldl(DoubleBinaryOperator f, double start, int[] xs)
	{
		double cumulativeFold = start;
		for (int i = 0; i < xs.length; i++)
		{
			cumulativeFold = f.applyAsDouble(cumulativeFold, xs[i]);
		}
		return cumulativeFold;
	}
	
	public static double foldl(DoubleBinaryOperator f, double start, double[] xs)
	{
		double cumulativeFold = start;
		for (int i = 0; i < xs.length; i++)
		{
			cumulativeFold = f.applyAsDouble(cumulativeFold, xs[i]);
		}
		return cumulativeFold;
	}
	
	public static double[] combine(DoubleBinaryOperator f, double[] a, double[] b)
	{
		assert a.length == b.length;
		int size = a.length;
		double[] combined = new double[size];
		for (int i = 0; i < size; i++)
		{
			combined[i] = f.applyAsDouble(a[i], b[i]);
		}
		return combined;
	}
	
	public static int[] combine(IntBinaryOperator f, int[] a, int[] b)
	{
		assert a.length == b.length;
		int size = a.length;
		int[] combined = new int[size];
		for (int i = 0; i < size; i++)
		{
			combined[i] = f.applyAsInt(a[i], b[i]);
		}
		return combined;
	}
	
	public static <T> List<T> mapToObj(IntFunction<T> f, int[] xs)
	{
		List<T> mappedList = new ArrayList<>(xs.length);
		for (int x : xs)
		{
			mappedList.add(f.apply(x));
		}
		return mappedList;
	}
	
	public static <T> Stream<T> mapToObjStream(IntFunction<T> f, int[] xs)
	{
		return mapToObj(f, xs).stream();
	}
	
	public static <T> List<T> mapToObj(DoubleFunction<T> f, double[] xs)
	{
		List<T> mappedList = new ArrayList<>(xs.length);
		for (double x : xs)
		{
			mappedList.add(f.apply(x));
		}
		return mappedList;
	}
	
	public static <T> Stream<T> mapToObjStream(DoubleFunction<T> f, double[] xs)
	{
		return mapToObj(f, xs).stream();
	}
	
//	public static double[] dpartition(double lower, double upper, int nSubInterval)
//	{
//		double step = (upper - lower)/nSubInterval;
//		int nBoundaries = nSubInterval + 1;
//		double[] boundaries = new double[nBoundaries];
//		for (int i = 0; i < nBoundaries; i++)
//		{
//			boundaries[i] = lower + i*step;
//		}
//		return boundaries;
//	}
	
	public static void main(String[] args)
	{
		int[] a = map(i -> i%8, map(i -> i*i*i, range(30)));
		System.out.println(Arrays.toString(a));
	}
}
