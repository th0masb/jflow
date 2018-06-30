/**
 *
 */
package xawd.jflow.iterators;

import java.util.OptionalInt;

/**
 * Abstraction of the concept of a sequence having an 'optional' size, more
 * accurately it describes a sequence which has a (possibly infinite) size but
 * the specific quantity may not be known.
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
