package xawd.lists.listflow;

import static java.util.Arrays.asList;

public class ConsolePlay {

	public static void main(String[] args)
	{
		final ListFlow<String> strings = new ArrayListFlow<>(asList("1", "2", "3"));
		System.out.println(strings);

		final ListFlow<String> mappedString = strings.iter().map(x -> x + x).toListFlow();
		System.out.println(mappedString);

		final ListFlow<String> slicedStrings = strings.rIter()
				.slice(i -> 2*i)
				.toListFlow();

		System.out.println(slicedStrings);
	}
}
