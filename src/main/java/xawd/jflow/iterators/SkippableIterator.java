/**
 *
 */
package xawd.jflow.iterators;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * @author t
 *
 */
public interface SkippableIterator<T> extends Iterator<T>, Skippable
{
	default void forEach(final Consumer<? super T> action)
	{
		while (hasNext()) {
			action.accept(next());
		}
	}
}
