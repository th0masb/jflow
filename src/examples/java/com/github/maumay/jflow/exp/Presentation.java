/**
 * 
 */
package com.github.maumay.jflow.exp;

import static com.github.maumay.jflow.vec.Vec.vec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.github.maumay.jflow.iterators.Iter;
import com.github.maumay.jflow.vec.Vec;

/**
 * @author thomasb
 *
 */
public class Presentation
{

	static class Point
	{
		static final Random PRNG = new Random();

		double x, y;

		Point()
		{
			x = PRNG.nextDouble();
			y = PRNG.nextDouble();
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		// List<String> xs = Arrays.asList("a", "b", "c");
		// List<String> ys = xs.stream().map(x -> x + x).collect(Collectors.toList());

		// Vec<String> xs = vec("a", "b", "c");
		// Vec<String> ys = xs.map(x -> x + x).filter(x -> x.equals("cc"));

		// Vec<String> xs = Vec.of("a", "b", "c");
		// Vec<String> ys = xs.iter().map(x -> x + x).filter(x ->
		// x.equals("cc")).toVec();

		// Vec<Iterator<String>> xs = getSomeIterators();

		// Iterator<Iterator<String>> knownSize = xs.iter();
		// Iterator<String> unknownSize = xs.iter().flatMap(x -> x);

		// Vec<String> xs = vec("a", "b", "c").iter().map(x -> x + x).toVec();

		Vec<List<String>> lists = Iter.call(() -> new ArrayList<String>())
				.<List<String>>cast().take(10).toVec();

		List<String> left = Arrays.asList("a", "b", "c");
		Vec<String> right = vec("d", "e", "f");

		Vec<String> result = Iter.over(left).zip(right.iter())
				.map(pair -> doSomething(pair._1, pair._2)).toVec();
	}

	public static String doSomething(String left, String right)
	{
		return "";
	}

	private static Vec<Iterator<String>> getSomeIterators()
	{
		throw new RuntimeException();
	}
}
