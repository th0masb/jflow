/**
 * 
 */
package com.github.maumay.jflow.exp;

import static com.github.maumay.jflow.vec.Vec.vec;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.github.maumay.jflow.iterator.Iter;
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

		Vec<List<String>> lists = Iter.call(() -> new ArrayList<String>()).<List<String>>cast()
				.take(10).toVec();

		List<String> left = Arrays.asList("a", "b", "c");
		Vec<String> right = vec("d", "e", "f");

		Vec<String> result = Iter.over(left).zip(right.iter())
				.map(pair -> doSomething(pair._1, pair._2)).toVec();

		Vec<Square> squares = getSomeSquares();
		Vec<Circle> circles = getSomeCircles();
		Vec<Triangle> triangles = getSomeTriangles();

		Vec<Shape> shapes = squares.iter().<Shape>cast().chain(circles.iter())
				.chain(triangles.iter()).toVec();

		Vec<String> strings = vec("a", "b", "c");

		List<String> list = strings.toList();
		Set<String> set = strings.toSet();
		Deque<String> deque = strings.to(ArrayDeque::new);
	}

	private static Vec<Triangle> getSomeTriangles()
	{
		throw new RuntimeException("Not yet implemented");
	}

	private static Vec<Circle> getSomeCircles()
	{
		throw new RuntimeException("Not yet implemented");
	}

	private static Vec<Square> getSomeSquares()
	{
		throw new RuntimeException("Not yet implemented");
	}

	public static String doSomething(String left, String right)
	{
		return "";
	}

	private static Vec<Iterator<String>> getSomeIterators()
	{
		throw new RuntimeException();
	}

	interface Shape
	{
	}

	class Square implements Shape
	{
	}

	class Circle implements Shape
	{
	}

	class Triangle implements Shape
	{
	}
}
