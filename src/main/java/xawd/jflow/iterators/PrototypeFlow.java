/**
 *
 */
package xawd.jflow.iterators;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * An interface extending the requisite interfaces for Flow and adding some
 * necessary but somewhat unrelated functionality.
 *
 * @author t
 */
public interface PrototypeFlow<E> extends Iterator<E>, Skippable, OptionallySized
{
	/**
	 * A safe alternative to directly calling {@link #next()} method.
	 *
	 * @return An Optional wrapping the next element if there is one.
	 */
	default Optional<E> safeNext()
	{
		return hasNext() ? Optional.of(next()) : Optional.empty();
	}

	/**
	 * Perform the supplied action for each element left in this iterator sequence,
	 * in doing so the iterator is consumed.
	 *
	 * @param action
	 *            The action to perform.
	 */
	default void forEach(final Consumer<? super E> action)
	{
		while (hasNext()) {
			action.accept(next());
		}
	}
}
