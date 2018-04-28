package xawd.lists.listflow;

import org.joda.primitives.list.IntListFlow;

import xawd.jflow.construction.Iter;

public class ConsolePlay {

	public static void main(String[] args)
	{
		final ListFlow<String> strings = Iter.of("1", "2", "3").toListFlow();
		final IntListFlow integers = Iter.of(new int[] {1, 2, 3}).toListFlow();

		System.out.println(strings);
		System.out.println(integers);

		strings.add("4");
		integers.add(4);

		System.out.println(strings);
		System.out.println(integers);

		strings.iter().anyMatch(string -> string.equals("4"));
		integers.iter().filter(x -> x%2 == 0).toArray();

		//		System.out.println(strings);
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
