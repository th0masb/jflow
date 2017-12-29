/**
 * 
 */
package io.xyz.common.funcutils;

import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author t
 *
 */
public final class Functional 
{
	static final double ZERO_TOL = 0.0001;
	
	private Functional() {}
	
	/**
	 * Convenience method for collecting a stream of 
	 * objects into a {@code List}
	 * @param stream - a stream of objects
	 * @return the collected stream
	 */
	public static <T> List<T> collect(Stream<T> stream)
	{
		return stream.collect(Collectors.toList());
	}
	
	public static <T> Set<T> collectSet(Stream<T> stream)
	{
		return stream.collect(Collectors.toSet());
	}
	
	public static IntStream range(int n)
	{
		return IntStream.range(0, n);
	}
	
	public static IntStream irange(int n)
	{
		return IntStream.rangeClosed(0, n);
	}
	
	public static DoubleStream dloop(double lower, double upper, double step)
	{
		int arrSize = (int) ((upper - lower)/step);
		double[] arr = new double[arrSize];
		for (int i = 0; i < arrSize; i++)
		{
			arr[i] = lower + i*step;
		}
		return DoubleStream.of(arr);
	}
	
	public static DoubleStream dpartition(double lower, double upper, int nSubInterval)
	{
		double step = (upper - lower)/nSubInterval;
		int nBoundaries = nSubInterval + 1;
		double[] boundaries = new double[nBoundaries];
		for (int i = 0; i < nBoundaries; i++)
		{
			boundaries[i] = lower + i*step;
		}
		return DoubleStream.of(boundaries);
	}
	
	public static <T> List<T> zipMerge(List<? extends T> one, List<? extends T> two)
	{
		List<T> merged = new ArrayList<>(one.size() + two.size());
		
		int minLen = min(one.size(), two.size());
		for (int i = 0; i < 2*minLen; i++)
		{
			boolean even = i % 2 == 0;
			merged.add((even? one : two).get(i/2));
		}
		
		List<? extends T> nonConsumed = minLen == one.size() ? one:two;
		for (T t : nonConsumed)
		{
			merged.add(t);
		}
		
		return Collections.unmodifiableList(merged);
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
	
	public static double[] mapToDouble(DoubleUnaryOperator f, double[] xs)
	{
		double[] result = new double[xs.length];
		for (int i = 0; i < xs.length; i++)
		{
			result[i] = f.applyAsDouble(xs[i]);
		}
		return result;
	}
	
	public static void main(String[] args)
	{
		System.out.println(1.0/0.3333);
	}
}
