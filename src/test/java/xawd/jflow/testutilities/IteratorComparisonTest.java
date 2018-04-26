/**
 * 
 */
package xawd.jflow.testutilities;

import java.util.List;

import xawd.jflow.Flow;

/**
 * @author t
 *
 */
public interface IteratorComparisonTest 
{
	default <T> void assertIteratorAsExpected(final List<T> expected, final Flow<T> actual)
	{
		final int count = 0;
		while (actual.hasNext()) {
			final T next = 
		}
	}
}
