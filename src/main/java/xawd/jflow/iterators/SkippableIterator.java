/**
 *
 */
package xawd.jflow.iterators;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author t
 *
 */
public interface SkippableIterator<E> extends Iterator<E>, Skippable
{
	default Optional<E> safeNext()
	{
		return hasNext()? Optional.of(next()) : Optional.empty();
	}

	default void forEach(final Consumer<? super E> action)
	{
		while (hasNext()) {
			action.accept(next());
		}
	}
}
