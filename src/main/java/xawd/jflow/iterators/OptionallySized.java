/**
 *
 */
package xawd.jflow.iterators;

import java.util.OptionalInt;

/**
 * Abstraction of a sequence whose (possibly infinite) size may not be known.
 *
 * @author ThomasB
 */
@FunctionalInterface
public interface OptionallySized
{
	/**
	 * @return The size of the sequence if it known, otherwise nothing to indicate
	 *         the value is unknown.
	 */
	OptionalInt size();

	/**
	 * @return true if the size of this sequence is known, false otherwise.
	 */
	default boolean sizeIsKnown()
	{
		return size().isPresent();
	}
}
