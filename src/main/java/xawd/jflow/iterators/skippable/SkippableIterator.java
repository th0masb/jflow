/**
 *
 */
package xawd.jflow.iterators.skippable;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * @author t
 *
 */
public interface SkippableIterator<E> extends Iterator<E>, Skippable
{
	default void forEach(final Consumer<? super E> action)
	{
		while (hasNext()) {
			action.accept(next());
		}
	}
}
