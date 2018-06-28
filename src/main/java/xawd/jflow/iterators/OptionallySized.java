/**
 *
 */
package xawd.jflow.iterators;

/**
 * Abstraction of the concept of a sequence having an 'Optional size', more
 * accurately it describes a sequence which has a (possibly infinite) size but
 * the specific quantity may not be known.
 *
 * @author ThomasB
 */
@FunctionalInterface
public interface OptionallySized
{
	/**
	 * @return The size of the sequence if it known, otherwise a negative number to
	 *         indicate the value is unknown.
	 */
	int size();

	default boolean isSized()
	{
		return size() > -1;
	}
}
