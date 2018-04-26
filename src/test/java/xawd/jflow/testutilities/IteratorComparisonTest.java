/**
 *
 */
package xawd.jflow.testutilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
		int count = 0;
		while (actual.hasNext()) {
			final T next = actual.next();
			if (count >= expected.size()) {
				fail("iterator longer than expected element list");
			}
			else {
				assertEquals(expected.get(count), next);
			}
			count++;
		}
		if (count != expected.size()) {
			fail("iterator shorter than expected element list");
		}
	}
}
