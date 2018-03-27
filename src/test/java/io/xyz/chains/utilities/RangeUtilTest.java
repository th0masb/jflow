package io.xyz.chains.utilities;

import static io.xyz.chains.utilities.RangeUtil.doubleRange;
import static io.xyz.chains.utilities.RangeUtil.range;
import static io.xyz.chains.utilities.RangeUtil.rangei;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RangeUtilTest extends TestDataProvider
{
	@Test
	public void testRange()
	{
		assertTrue(areEqual(new int[] {1, 5, 9}, range(i -> 4*i + 1, 3)));
		assertTrue(areEqual(new int[] {1, 5, 9}, range(1, 10, 4)));
		assertTrue(areEqual(new int[] {1, -3, -7}, range(1, -10, -4)));
		assertTrue(areEqual(new int[] {1, 2, 3, 4}, range(1, 5)));
		assertTrue(areEqual(new int[] {0, 1, 2, 3, 4}, range(5)));
	}

	@Test
	public void testRangei()
	{
		assertTrue(areEqual(new int[] {1, 5, 9}, rangei(1, 10, 4)));
		assertTrue(areEqual(new int[] {1, -3, -7}, rangei(1, -10, -4)));
		assertTrue(areEqual(new int[] {1, 2, 3, 4, 5}, rangei(1, 5)));
		assertTrue(areEqual(new int[] {0, 1, 2, 3, 4, 5}, rangei(5)));
	}

	@Test
	public void testDoubleRange()
	{
		assertTrue(areEqual(new int[] {1, 5, 9}, doubleRange(i -> 4*i + 1, 3)));
		assertTrue(areEqual(new int[] {1, 5, 9}, doubleRange(1, 10, 4)));
		assertTrue(areEqual(new int[] {1, -3, -7}, doubleRange(1, -10, -4)));
		assertTrue(areEqual(new int[] {1, 2, 3, 4}, doubleRange(1, 5)));
		assertTrue(areEqual(new int[] {0, 1, 2, 3, 4}, doubleRange(5)));
	}
}