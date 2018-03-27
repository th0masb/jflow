package io.xyz.chains.utilities;

import static io.xyz.chains.utilities.CollectionUtil.append;
import static io.xyz.chains.utilities.CollectionUtil.drop;
import static io.xyz.chains.utilities.CollectionUtil.head;
import static io.xyz.chains.utilities.CollectionUtil.insert;
import static io.xyz.chains.utilities.CollectionUtil.reverse;
import static io.xyz.chains.utilities.CollectionUtil.str;
import static io.xyz.chains.utilities.CollectionUtil.tail;
import static io.xyz.chains.utilities.CollectionUtil.take;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author ThomasB
 * @since 14 Feb 2018
 */
public class CollectionUtilTest extends TestDataProvider
{
	@Test
	public void testReverse()
	{
		final int[] expectedResult = {THREE, TWO, ONE};

		assertTrue(str(reverse(XS_INT_ARR)), areEqual(expectedResult, reverse(XS_INT_ARR)));
		assertTrue(str(reverse(XS_INT_IMARR)), areEqual(expectedResult, reverse(XS_INT_IMARR)));
		assertTrue(str(reverse(XS_INT_GEN)), areEqual(expectedResult, reverse(XS_INT_GEN)));

		assertTrue(str(reverse(XS_DOUBLE_ARR)), areEqual(expectedResult, reverse(XS_DOUBLE_ARR)));
		assertTrue(str(reverse(XS_DOUBLE_IMARR)), areEqual(expectedResult, reverse(XS_DOUBLE_IMARR)));
		assertTrue(str(reverse(XS_DOUBLE_GEN)), areEqual(expectedResult, reverse(XS_DOUBLE_GEN)));

		assertTrue(str(reverse(XS_LONG_ARR)), areEqual(expectedResult, reverse(XS_LONG_ARR)));
		assertTrue(str(reverse(XS_LONG_IMARR)), areEqual(expectedResult, reverse(XS_LONG_IMARR)));
		assertTrue(str(reverse(XS_LONG_GEN)), areEqual(expectedResult, reverse(XS_LONG_GEN)));

		assertTrue(str(reverse(XS_OBJ_ARR)), areEqual(expectedResult, reverse(XS_OBJ_ARR)));
		assertTrue(str(reverse(XS_OBJ_LIST)), areEqual(expectedResult, reverse(XS_OBJ_LIST)));
		assertTrue(str(reverse(XS_OBJ_IMLIST)), areEqual(expectedResult, reverse(XS_OBJ_IMLIST)));
		assertTrue(str(reverse(XS_OBJ_GEN)), areEqual(expectedResult, reverse(XS_OBJ_GEN)));
	}

	@Test
	public void testHead()
	{
		final int[] expectedResult = {ONE};

		assertTrue(areEqual(expectedResult, new int[]{head(XS_INT_ARR)}));
		assertTrue(areEqual(expectedResult, new int[]{head(XS_INT_IMARR)}));
		assertTrue(areEqual(expectedResult, new int[]{head(XS_INT_GEN)}));

		assertTrue(areEqual(expectedResult, new int[]{(int) head(XS_DOUBLE_ARR)}));
		assertTrue(areEqual(expectedResult, new int[]{(int) head(XS_DOUBLE_IMARR)}));
		assertTrue(areEqual(expectedResult, new int[]{(int) head(XS_DOUBLE_GEN)}));

		assertTrue(areEqual(expectedResult, new int[]{(int) head(XS_LONG_ARR)}));
		assertTrue(areEqual(expectedResult, new int[]{(int) head(XS_LONG_IMARR)}));
		assertTrue(areEqual(expectedResult, new int[]{(int) head(XS_LONG_GEN)}));

		assertTrue(areEqual(expectedResult, new int[]{head(XS_OBJ_ARR)}));
		assertTrue(areEqual(expectedResult, new int[]{head(XS_OBJ_LIST)}));
		assertTrue(areEqual(expectedResult, new int[]{head(XS_OBJ_IMLIST)}));
		assertTrue(areEqual(expectedResult, new int[]{head(XS_OBJ_GEN)}));
	}

	@Test
	public void testTail()
	{
		final int[] expectedResult = {THREE};

		assertTrue(areEqual(expectedResult, new int[]{tail(XS_INT_ARR)}));
		assertTrue(areEqual(expectedResult, new int[]{tail(XS_INT_IMARR)}));
		assertTrue(areEqual(expectedResult, new int[]{tail(XS_INT_GEN)}));

		assertTrue(areEqual(expectedResult, new int[]{(int) tail(XS_DOUBLE_ARR)}));
		assertTrue(areEqual(expectedResult, new int[]{(int) tail(XS_DOUBLE_IMARR)}));
		assertTrue(areEqual(expectedResult, new int[]{(int) tail(XS_DOUBLE_GEN)}));

		assertTrue(areEqual(expectedResult, new int[]{(int) tail(XS_LONG_ARR)}));
		assertTrue(areEqual(expectedResult, new int[]{(int) tail(XS_LONG_IMARR)}));
		assertTrue(areEqual(expectedResult, new int[]{(int) tail(XS_LONG_GEN)}));

		assertTrue(areEqual(expectedResult, new int[]{tail(XS_OBJ_ARR)}));
		assertTrue(areEqual(expectedResult, new int[]{tail(XS_OBJ_LIST)}));
		assertTrue(areEqual(expectedResult, new int[]{tail(XS_OBJ_IMLIST)}));
		assertTrue(areEqual(expectedResult, new int[]{tail(XS_OBJ_GEN)}));
	}

	@Test
	public void testTake()
	{
		final int[] expectedResult = {ONE, TWO};

		assertTrue(str(take(2, XS_INT_ARR)), areEqual(expectedResult, take(2, XS_INT_ARR)));
		assertTrue(str(take(2, XS_INT_IMARR)), areEqual(expectedResult, take(2, XS_INT_IMARR)));
		assertTrue(str(take(2, XS_INT_GEN)), areEqual(expectedResult, take(2, XS_INT_GEN)));

		assertTrue(str(take(2, XS_DOUBLE_ARR)), areEqual(expectedResult, take(2, XS_DOUBLE_ARR)));
		assertTrue(str(take(2, XS_DOUBLE_IMARR)), areEqual(expectedResult, take(2, XS_DOUBLE_IMARR)));
		assertTrue(str(take(2, XS_DOUBLE_GEN)), areEqual(expectedResult, take(2, XS_DOUBLE_GEN)));

		assertTrue(str(take(2, XS_LONG_ARR)), areEqual(expectedResult, take(2, XS_LONG_ARR)));
		assertTrue(str(take(2, XS_LONG_IMARR)), areEqual(expectedResult, take(2, XS_LONG_IMARR)));
		assertTrue(str(take(2, XS_LONG_GEN)), areEqual(expectedResult, take(2, XS_LONG_GEN)));

		assertTrue(str(take(2, XS_OBJ_ARR)), areEqual(expectedResult, take(2, XS_OBJ_ARR)));
		assertTrue(str(take(2, XS_OBJ_LIST)), areEqual(expectedResult, take(2, XS_OBJ_LIST)));
		assertTrue(str(take(2, XS_OBJ_IMLIST)), areEqual(expectedResult, take(2, XS_OBJ_IMLIST)));
		assertTrue(str(take(2, XS_OBJ_GEN)), areEqual(expectedResult, take(2, XS_OBJ_GEN)));
	}

	@Test
	public void testDrop()
	{
		final int[] expectedResult = {TWO, THREE};

		assertTrue(str(drop(1, XS_INT_ARR)), areEqual(expectedResult, drop(1, XS_INT_ARR)));
		assertTrue(str(drop(1, XS_INT_IMARR)), areEqual(expectedResult, drop(1, XS_INT_IMARR)));
		assertTrue(str(drop(1, XS_INT_GEN)), areEqual(expectedResult, drop(1, XS_INT_GEN)));

		assertTrue(str(drop(1, XS_DOUBLE_ARR)), areEqual(expectedResult, drop(1, XS_DOUBLE_ARR)));
		assertTrue(str(drop(1, XS_DOUBLE_IMARR)), areEqual(expectedResult, drop(1, XS_DOUBLE_IMARR)));
		assertTrue(str(drop(1, XS_DOUBLE_GEN)), areEqual(expectedResult, drop(1, XS_DOUBLE_GEN)));

		assertTrue(str(drop(1, XS_LONG_ARR)), areEqual(expectedResult, drop(1, XS_LONG_ARR)));
		assertTrue(str(drop(1, XS_LONG_IMARR)), areEqual(expectedResult, drop(1, XS_LONG_IMARR)));
		assertTrue(str(drop(1, XS_LONG_GEN)), areEqual(expectedResult, drop(1, XS_LONG_GEN)));

		assertTrue(str(drop(1, XS_OBJ_ARR)), areEqual(expectedResult, drop(1, XS_OBJ_ARR)));
		assertTrue(str(drop(1, XS_OBJ_LIST)), areEqual(expectedResult, drop(1, XS_OBJ_LIST)));
		assertTrue(str(drop(1, XS_OBJ_IMLIST)), areEqual(expectedResult, drop(1, XS_OBJ_IMLIST)));
		assertTrue(str(drop(1, XS_OBJ_GEN)), areEqual(expectedResult, drop(1, XS_OBJ_GEN)));
	}

	@Test
	public void testInsert()
	{
		final int[] expectedResult = {EXTRA_VAL, ONE, TWO, THREE};

		assertTrue(str(insert(EXTRA_VAL, XS_INT_ARR)), areEqual(expectedResult, insert(EXTRA_VAL, XS_INT_ARR)));
		assertTrue(str(insert(EXTRA_VAL, XS_INT_IMARR)), areEqual(expectedResult, insert(EXTRA_VAL, XS_INT_IMARR)));
		assertTrue(str(insert(EXTRA_VAL, XS_INT_GEN)), areEqual(expectedResult, insert(EXTRA_VAL, XS_INT_GEN)));

		assertTrue(str(insert(EXTRA_VAL, XS_DOUBLE_ARR)), areEqual(expectedResult, insert(EXTRA_VAL, XS_DOUBLE_ARR)));
		assertTrue(str(insert(EXTRA_VAL, XS_DOUBLE_IMARR)), areEqual(expectedResult, insert(EXTRA_VAL, XS_DOUBLE_IMARR)));
		assertTrue(str(insert(EXTRA_VAL, XS_DOUBLE_GEN)), areEqual(expectedResult, insert(EXTRA_VAL, XS_DOUBLE_GEN)));

		assertTrue(str(insert(EXTRA_VAL, XS_LONG_ARR)), areEqual(expectedResult, insert(EXTRA_VAL, XS_LONG_ARR)));
		assertTrue(str(insert(EXTRA_VAL, XS_LONG_IMARR)), areEqual(expectedResult, insert(EXTRA_VAL, XS_LONG_IMARR)));
		assertTrue(str(insert(EXTRA_VAL, XS_LONG_GEN)), areEqual(expectedResult, insert(EXTRA_VAL, XS_LONG_GEN)));

		assertTrue(str(insert(EXTRA_VAL, XS_OBJ_ARR)), areEqual(expectedResult, insert(EXTRA_VAL, XS_OBJ_ARR)));
		assertTrue(str(insert(EXTRA_VAL, XS_OBJ_LIST)), areEqual(expectedResult, insert(EXTRA_VAL, XS_OBJ_LIST)));
		assertTrue(str(insert(EXTRA_VAL, XS_OBJ_IMLIST)), areEqual(expectedResult, insert(EXTRA_VAL, XS_OBJ_IMLIST)));
		assertTrue(str(insert(EXTRA_VAL, XS_OBJ_GEN)), areEqual(expectedResult, insert(EXTRA_VAL, XS_OBJ_GEN)));
	}

	@Test
	public void testAppend()
	{
		final int[] expectedResult = {ONE, TWO, THREE, EXTRA_VAL};

		assertTrue(str(append(EXTRA_VAL, XS_INT_ARR)), areEqual(expectedResult, append(EXTRA_VAL, XS_INT_ARR)));
		assertTrue(str(append(EXTRA_VAL, XS_INT_IMARR)), areEqual(expectedResult, append(EXTRA_VAL, XS_INT_IMARR)));
		assertTrue(str(append(EXTRA_VAL, XS_INT_GEN)), areEqual(expectedResult, append(EXTRA_VAL, XS_INT_GEN)));

		assertTrue(str(append(EXTRA_VAL, XS_DOUBLE_ARR)), areEqual(expectedResult, append(EXTRA_VAL, XS_DOUBLE_ARR)));
		assertTrue(str(append(EXTRA_VAL, XS_DOUBLE_IMARR)), areEqual(expectedResult, append(EXTRA_VAL, XS_DOUBLE_IMARR)));
		assertTrue(str(append(EXTRA_VAL, XS_DOUBLE_GEN)), areEqual(expectedResult, append(EXTRA_VAL, XS_DOUBLE_GEN)));

		assertTrue(str(append(EXTRA_VAL, XS_LONG_ARR)), areEqual(expectedResult, append(EXTRA_VAL, XS_LONG_ARR)));
		assertTrue(str(append(EXTRA_VAL, XS_LONG_IMARR)), areEqual(expectedResult, append(EXTRA_VAL, XS_LONG_IMARR)));
		assertTrue(str(append(EXTRA_VAL, XS_LONG_GEN)), areEqual(expectedResult, append(EXTRA_VAL, XS_LONG_GEN)));

		assertTrue(str(append(EXTRA_VAL, XS_OBJ_ARR)), areEqual(expectedResult, append(EXTRA_VAL, XS_OBJ_ARR)));
		assertTrue(str(append(EXTRA_VAL, XS_OBJ_LIST)), areEqual(expectedResult, append(EXTRA_VAL, XS_OBJ_LIST)));
		assertTrue(str(append(EXTRA_VAL, XS_OBJ_IMLIST)), areEqual(expectedResult, append(EXTRA_VAL, XS_OBJ_IMLIST)));
		assertTrue(str(append(EXTRA_VAL, XS_OBJ_GEN)), areEqual(expectedResult, append(EXTRA_VAL, XS_OBJ_GEN)));
	}
}