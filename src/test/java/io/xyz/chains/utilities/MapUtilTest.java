package io.xyz.chains.utilities;

import static io.xyz.chains.utilities.CollectionUtil.str;
import static io.xyz.chains.utilities.MapUtil.doubleMap;
import static io.xyz.chains.utilities.MapUtil.intMap;
import static io.xyz.chains.utilities.MapUtil.longMap;
import static io.xyz.chains.utilities.MapUtil.objMap;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MapUtilTest extends TestDataProvider
{
	@Test
	public void testIntMap()
	{
		final int[] expectedResult = {4*ONE, 4*TWO, 4*THREE};

		assertTrue(str(intMap(x -> 4*x, XS_INT_ARR)), areEqual(expectedResult, intMap(x -> 4*x, XS_INT_ARR)));
		assertTrue(str(intMap(x -> 4*x, XS_INT_IMARR)), areEqual(expectedResult, intMap(x -> 4*x, XS_INT_IMARR)));
		assertTrue(str(intMap(x -> 4*x, XS_INT_GEN)), areEqual(expectedResult, intMap(x -> 4*x, XS_INT_GEN)));

		assertTrue(str(intMap(x -> 4*((int)x), XS_DOUBLE_ARR)), areEqual(expectedResult, intMap(x -> 4*((int)x), XS_DOUBLE_ARR)));
		assertTrue(str(intMap(x -> 4*((int)x), XS_DOUBLE_IMARR)), areEqual(expectedResult, intMap(x -> 4*((int)x), XS_DOUBLE_IMARR)));
		assertTrue(str(intMap(x -> 4*((int)x), XS_DOUBLE_GEN)), areEqual(expectedResult, intMap(x -> 4*((int)x), XS_DOUBLE_GEN)));

		assertTrue(str(intMap(x -> 4*((int)x), XS_LONG_ARR)), areEqual(expectedResult, intMap(x -> 4*((int)x), XS_LONG_ARR)));
		assertTrue(str(intMap(x -> 4*((int)x), XS_LONG_IMARR)), areEqual(expectedResult, intMap(x -> 4*((int)x), XS_LONG_IMARR)));
		assertTrue(str(intMap(x -> 4*((int)x), XS_LONG_GEN)), areEqual(expectedResult, intMap(x -> 4*((int)x), XS_LONG_GEN)));

		assertTrue(str(intMap(x -> 4*x, XS_OBJ_ARR)), areEqual(expectedResult, intMap(x -> 4*x, XS_OBJ_ARR)));
		assertTrue(str(intMap(x -> 4*x, XS_OBJ_LIST)), areEqual(expectedResult, intMap(x -> 4*x, XS_OBJ_LIST)));
		assertTrue(str(intMap(x -> 4*x, XS_OBJ_IMLIST)), areEqual(expectedResult, intMap(x -> 4*x, XS_OBJ_IMLIST)));
		assertTrue(str(intMap(x -> 4*x, XS_OBJ_GEN)), areEqual(expectedResult, intMap(x -> 4*x, XS_OBJ_GEN)));
	}

	@Test
	public void testDoubleMap()
	{
		final int[] expectedResult = {4*ONE, 4*TWO, 4*THREE};

		assertTrue(str(doubleMap(x -> 4*x, XS_INT_ARR)), areEqual(expectedResult, doubleMap(x -> 4*x, XS_INT_ARR)));
		assertTrue(str(doubleMap(x -> 4*x, XS_INT_IMARR)), areEqual(expectedResult, doubleMap(x -> 4*x, XS_INT_IMARR)));
		assertTrue(str(doubleMap(x -> 4*x, XS_INT_GEN)), areEqual(expectedResult, doubleMap(x -> 4*x, XS_INT_GEN)));

		assertTrue(str(doubleMap(x -> 4*x, XS_DOUBLE_ARR)), areEqual(expectedResult, doubleMap(x -> 4*x, XS_DOUBLE_ARR)));
		assertTrue(str(doubleMap(x -> 4*x, XS_DOUBLE_IMARR)), areEqual(expectedResult, doubleMap(x -> 4*x, XS_DOUBLE_IMARR)));
		assertTrue(str(doubleMap(x -> 4*x, XS_DOUBLE_GEN)), areEqual(expectedResult, doubleMap(x -> 4*x, XS_DOUBLE_GEN)));

		assertTrue(str(doubleMap(x -> 4*x, XS_LONG_ARR)), areEqual(expectedResult, doubleMap(x -> 4*x, XS_LONG_ARR)));
		assertTrue(str(doubleMap(x -> 4*x, XS_LONG_IMARR)), areEqual(expectedResult, doubleMap(x -> 4*x, XS_LONG_IMARR)));
		assertTrue(str(doubleMap(x -> 4*x, XS_LONG_GEN)), areEqual(expectedResult, doubleMap(x -> 4*x, XS_LONG_GEN)));

		assertTrue(str(doubleMap(x -> 4*x, XS_OBJ_ARR)), areEqual(expectedResult, doubleMap(x -> 4*x, XS_OBJ_ARR)));
		assertTrue(str(doubleMap(x -> 4*x, XS_OBJ_LIST)), areEqual(expectedResult, doubleMap(x -> 4*x, XS_OBJ_LIST)));
		assertTrue(str(doubleMap(x -> 4*x, XS_OBJ_IMLIST)), areEqual(expectedResult, doubleMap(x -> 4*x, XS_OBJ_IMLIST)));
		assertTrue(str(doubleMap(x -> 4*x, XS_OBJ_GEN)), areEqual(expectedResult, doubleMap(x -> 4*x, XS_OBJ_GEN)));
	}

	@Test
	public void testLongMap()
	{
		final int[] expectedResult = {4*ONE, 4*TWO, 4*THREE};

		assertTrue(str(longMap(x -> 4*x, XS_INT_ARR)), areEqual(expectedResult, longMap(x -> 4*x, XS_INT_ARR)));
		assertTrue(str(longMap(x -> 4*x, XS_INT_IMARR)), areEqual(expectedResult, longMap(x -> 4*x, XS_INT_IMARR)));
		assertTrue(str(longMap(x -> 4*x, XS_INT_GEN)), areEqual(expectedResult, longMap(x -> 4*x, XS_INT_GEN)));

		assertTrue(str(longMap(x -> 4*((long)x), XS_DOUBLE_ARR)), areEqual(expectedResult, longMap(x -> 4*((long)x), XS_DOUBLE_ARR)));
		assertTrue(str(longMap(x -> 4*((long)x), XS_DOUBLE_IMARR)), areEqual(expectedResult, longMap(x -> 4*((long)x), XS_DOUBLE_IMARR)));
		assertTrue(str(longMap(x -> 4*((long)x), XS_DOUBLE_GEN)), areEqual(expectedResult, longMap(x -> 4*((long)x), XS_DOUBLE_GEN)));

		assertTrue(str(longMap(x -> 4*x, XS_LONG_ARR)), areEqual(expectedResult, longMap(x -> 4*x, XS_LONG_ARR)));
		assertTrue(str(longMap(x -> 4*x, XS_LONG_IMARR)), areEqual(expectedResult, longMap(x -> 4*x, XS_LONG_IMARR)));
		assertTrue(str(longMap(x -> 4*x, XS_LONG_GEN)), areEqual(expectedResult, longMap(x -> 4*x, XS_LONG_GEN)));

		assertTrue(str(longMap(x -> 4*x, XS_OBJ_ARR)), areEqual(expectedResult, longMap(x -> 4*x, XS_OBJ_ARR)));
		assertTrue(str(longMap(x -> 4*x, XS_OBJ_LIST)), areEqual(expectedResult, longMap(x -> 4*x, XS_OBJ_LIST)));
		assertTrue(str(longMap(x -> 4*x, XS_OBJ_IMLIST)), areEqual(expectedResult, longMap(x -> 4*x, XS_OBJ_IMLIST)));
		assertTrue(str(longMap(x -> 4*x, XS_OBJ_GEN)), areEqual(expectedResult, longMap(x -> 4*x, XS_OBJ_GEN)));
	}

	@Test
	public void testObjMap()
	{
		final int[] expectedResult = {4*ONE, 4*TWO, 4*THREE};

		assertTrue(str(objMap(x -> 4*x, XS_INT_ARR)), areEqual(expectedResult, objMap(x -> 4*x, XS_INT_ARR)));
		assertTrue(str(objMap(x -> 4*x, XS_INT_IMARR)), areEqual(expectedResult, objMap(x -> 4*x, XS_INT_IMARR)));
		assertTrue(str(objMap(x -> 4*x, XS_INT_GEN)), areEqual(expectedResult, objMap(x -> 4*x, XS_INT_GEN)));

		assertTrue(str(objMap(x -> 4*((int)x), XS_DOUBLE_ARR)), areEqual(expectedResult, objMap(x -> 4*((int)x), XS_DOUBLE_ARR)));
		assertTrue(str(objMap(x -> 4*((int)x), XS_DOUBLE_IMARR)), areEqual(expectedResult, objMap(x -> 4*((int)x), XS_DOUBLE_IMARR)));
		assertTrue(str(objMap(x -> 4*((int)x), XS_DOUBLE_GEN)), areEqual(expectedResult, objMap(x -> 4*((int)x), XS_DOUBLE_GEN)));

		assertTrue(str(objMap(x -> 4*((int)x), XS_LONG_ARR)), areEqual(expectedResult, objMap(x -> 4*((int)x), XS_LONG_ARR)));
		assertTrue(str(objMap(x -> 4*((int)x), XS_LONG_IMARR)), areEqual(expectedResult, objMap(x -> 4*((int)x), XS_LONG_IMARR)));
		assertTrue(str(objMap(x -> 4*((int)x), XS_LONG_GEN)), areEqual(expectedResult, objMap(x -> 4*((int)x), XS_LONG_GEN)));

		assertTrue(str(objMap(x -> 4*x, XS_OBJ_ARR)), areEqual(expectedResult, objMap(x -> 4*x, XS_OBJ_ARR)));
		assertTrue(str(objMap(x -> 4*x, XS_OBJ_LIST)), areEqual(expectedResult, objMap(x -> 4*x, XS_OBJ_LIST)));
		assertTrue(str(objMap(x -> 4*x, XS_OBJ_IMLIST)), areEqual(expectedResult, objMap(x -> 4*x, XS_OBJ_IMLIST)));
		assertTrue(str(objMap(x -> 4*x, XS_OBJ_GEN)), areEqual(expectedResult, objMap(x -> 4*x, XS_OBJ_GEN)));
	}
}

/* ---------------------------------------------------------------------*
 * This software is the confidential and proprietary
 * information of Lhasa Limited
 * Granary Wharf House, 2 Canal Wharf, Leeds, LS11 5PS
 * ---
 * No part of this confidential information shall be disclosed
 * and it shall be used only in accordance with the terms of a
 * written license agreement entered into by holder of the information
 * with LHASA Ltd.
 * --------------------------------------------------------------------- */