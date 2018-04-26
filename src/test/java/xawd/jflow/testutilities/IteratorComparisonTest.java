/**
 *
 */
package xawd.jflow.testutilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import xawd.jflow.DoubleFlow;
import xawd.jflow.Flow;
import xawd.jflow.IntFlow;
import xawd.jflow.LongFlow;

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
	
	default void assertIteratorAsExpected(final long[] expected, final LongFlow actual)
	{
		int count = 0;
		while (actual.hasNext()) {
			final long next = actual.nextLong();
			if (count >= expected.length) {
				fail("iterator longer than expected element list");
			}
			else {
				assertEquals(expected[count], next);
			}
			count++;
		}
		if (count != expected.length) {
			fail("iterator shorter than expected element list");
		}
	}
	
	default void assertIteratorAsExpected(final double[] expected, final DoubleFlow actual)
	{
		int count = 0;
		while (actual.hasNext()) {
			final double next = actual.nextDouble();
			if (count >= expected.length) {
				fail("iterator doubleer than expected element list");
			}
			else {
				assertEquals(expected[count], next, 0.001);
			}
			count++;
		}
		if (count != expected.length) {
			fail("iterator shorter than expected element list");
		}
	}
	
	default void assertIteratorAsExpected(final int[] expected, final IntFlow actual)
	{
		int count = 0;
		while (actual.hasNext()) {
			final int next = actual.nextInt();
			if (count >= expected.length) {
				fail("iterator inter than expected element list");
			}
			else {
				assertEquals(expected[count], next);
			}
			count++;
		}
		if (count != expected.length) {
			fail("iterator shorter than expected element list");
		}
	}
}
