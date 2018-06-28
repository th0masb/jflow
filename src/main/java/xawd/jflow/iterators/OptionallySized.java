/**
 *
 */
package xawd.jflow.iterators;

/**
 * @author ThomasB
 */
@FunctionalInterface
public interface OptionallySized
{
	int size();

	default boolean isSized()
	{
		return size() > -1;
	}
}
