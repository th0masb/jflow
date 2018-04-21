/**
 * 
 */
package xawd.jflow;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author t
 *
 */
public final class Flows 
{
	public static <T> Flow<T> from(final Iterable<T> src)
	{
		return new AbstractFlow<T>() {
			@Override
			public SkippableIterator<T> iterator() {
				return skipifyIterator(src.iterator());
			}
		};
	}
	
	private static <T> SkippableIterator<T> skipifyIterator(final Iterator<T> iterator)
	{
		return new SkippableIterator<T>() {
			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}
			@Override
			public T next() {
				return iterator.next();
			}
			@Override
			public void skip() {
					next();
			}
		};
	}
	
	private static <T> Flow<IntWith<T>> enumerate(final Iterable<T> src)
	{
		return from(src).enumerate();
	}
	
	public static void main(final String[] args)
	{
		final List<String> strings = Arrays.asList("hello", "world", "hopefully", "this", "will", "be", "good");
		final List<String> strings2 = Arrays.asList("today", "is", "a", "lovely", "day");
		
		System.out.println(from(strings).combineWith(strings2, (a, b) -> a + b).toList());
		
//		for (final IntWith<String> enumeratedString : enumerate(strings)) 
//		{
//			System.out.println(enumeratedString);
//		}
		
		System.out.println(from(strings).drop(2).append(strings2).toList());
		System.out.println(from(strings).insert(strings2).filter(s -> s.charAt(0) == 'h').toList());
	}
}
