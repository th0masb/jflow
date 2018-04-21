/**
 * 
 */
package xawd.jflow;

import java.util.Iterator;

import xawd.jflow.iterators.SkippableIterator;

/**
 * @author t
 *
 */
public final class Flow 
{
	public static <T> ObjectFlow<T> from(final Iterable<T> src)
	{
		return new AbstractObjectFlow<T>() {
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
	
	
	
}
