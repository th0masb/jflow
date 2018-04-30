package xawd.lists.listflow;

import xawd.jflow.construction.Iter;
import xawd.jflow.geometry.Point;
import xawd.jflow.geometry.impl.PointImpl;

public class ConsolePlay {

	public static void main(String[] args)
	{
		//		final ListFlow<String> strings = Iter.of("1", "2", "3").toListFlow();
		//		final IntListFlow integers = Iter.of(new int[] {1, 2, 3}).toListFlow();
		//
		//		System.out.println(strings);
		//		System.out.println(integers);
		//
		//		strings.add("4");
		//		integers.add(4);
		//
		//		System.out.println(strings);
		//		System.out.println(integers);
		//
		//		strings.iter().anyMatch(string -> string.equals("4"));
		//		integers.iter().filter(x -> x%2 == 0).toArray();

		final Point p1 = new PointImpl(), p2 = new PointImpl(1, 1) , p3 = new PointImpl(2, 0);
		Iter.of(p1, p2, p3).forEach(System.out::println);
		System.out.println();
		//
		//		final ListFlow<String> mappedString = strings.iter().map(x -> x + x).toListFlow();
		//		System.out.println(mappedString);
		//
		//		final ListFlow<String> slicedStrings = strings.rIter()
		//				.slice(i -> 2*i)
		//				.toListFlow();
		//
		//		System.out.println(slicedStrings);
	}
}
