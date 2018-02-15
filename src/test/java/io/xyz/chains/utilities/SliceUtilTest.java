package io.xyz.chains.utilities;

import static io.xyz.chains.utilities.CollectionUtil.str;
import static io.xyz.chains.utilities.RangeUtil.range;
import static io.xyz.chains.utilities.SliceUtil.slice;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.xyz.chains.IntChain;
import io.xyz.chains.rangedfunction.RangedIntFunction;

public class SliceUtilTest extends TestDataProvider
{
	@Test
	public void testSlice()
	{
		final int[] expectedResult1 = {ONE, THREE};
		final IntChain slicer1 = range(0, 3, 2);

		assertTrue(str(slice(slicer1, XS_INT_ARR)), areEqual(expectedResult1, slice(slicer1, XS_INT_ARR)));
		assertTrue(str(slice(slicer1, XS_INT_IMARR)), areEqual(expectedResult1, slice(slicer1, XS_INT_IMARR)));
		assertTrue(str(slice(slicer1, XS_INT_GEN)), areEqual(expectedResult1, slice(slicer1, XS_INT_GEN)));

		assertTrue(str(slice(slicer1, XS_DOUBLE_ARR)), areEqual(expectedResult1, slice(slicer1, XS_DOUBLE_ARR)));
		assertTrue(str(slice(slicer1, XS_DOUBLE_IMARR)), areEqual(expectedResult1, slice(slicer1, XS_DOUBLE_IMARR)));
		assertTrue(str(slice(slicer1, XS_DOUBLE_GEN)), areEqual(expectedResult1, slice(slicer1, XS_DOUBLE_GEN)));

		assertTrue(str(slice(slicer1, XS_LONG_ARR)), areEqual(expectedResult1, slice(slicer1, XS_LONG_ARR)));
		assertTrue(str(slice(slicer1, XS_LONG_IMARR)), areEqual(expectedResult1, slice(slicer1, XS_LONG_IMARR)));
		assertTrue(str(slice(slicer1, XS_LONG_GEN)), areEqual(expectedResult1, slice(slicer1, XS_LONG_GEN)));

		assertTrue(str(slice(slicer1, XS_OBJ_ARR)), areEqual(expectedResult1, slice(slicer1, XS_OBJ_ARR)));
		assertTrue(str(slice(slicer1, XS_OBJ_LIST)), areEqual(expectedResult1, slice(slicer1, XS_OBJ_LIST)));
		assertTrue(str(slice(slicer1, XS_OBJ_IMLIST)), areEqual(expectedResult1, slice(slicer1, XS_OBJ_IMLIST)));
		assertTrue(str(slice(slicer1, XS_OBJ_GEN)), areEqual(expectedResult1, slice(slicer1, XS_OBJ_GEN)));

		final int[] expectedResult2 = {TWO, THREE, ONE, TWO};
		final IntChain slicer2 = RangedIntFunction.from(1, 2, 0, 1);

		assertTrue(str(slice(slicer2, XS_INT_ARR)), areEqual(expectedResult2, slice(slicer2, XS_INT_ARR)));
		assertTrue(str(slice(slicer2, XS_INT_IMARR)), areEqual(expectedResult2, slice(slicer2, XS_INT_IMARR)));
		assertTrue(str(slice(slicer2, XS_INT_GEN)), areEqual(expectedResult2, slice(slicer2, XS_INT_GEN)));

		assertTrue(str(slice(slicer2, XS_DOUBLE_ARR)), areEqual(expectedResult2, slice(slicer2, XS_DOUBLE_ARR)));
		assertTrue(str(slice(slicer2, XS_DOUBLE_IMARR)), areEqual(expectedResult2, slice(slicer2, XS_DOUBLE_IMARR)));
		assertTrue(str(slice(slicer2, XS_DOUBLE_GEN)), areEqual(expectedResult2, slice(slicer2, XS_DOUBLE_GEN)));

		assertTrue(str(slice(slicer2, XS_LONG_ARR)), areEqual(expectedResult2, slice(slicer2, XS_LONG_ARR)));
		assertTrue(str(slice(slicer2, XS_LONG_IMARR)), areEqual(expectedResult2, slice(slicer2, XS_LONG_IMARR)));
		assertTrue(str(slice(slicer2, XS_LONG_GEN)), areEqual(expectedResult2, slice(slicer2, XS_LONG_GEN)));

		assertTrue(str(slice(slicer2, XS_OBJ_ARR)), areEqual(expectedResult2, slice(slicer2, XS_OBJ_ARR)));
		assertTrue(str(slice(slicer2, XS_OBJ_LIST)), areEqual(expectedResult2, slice(slicer2, XS_OBJ_LIST)));
		assertTrue(str(slice(slicer2, XS_OBJ_IMLIST)), areEqual(expectedResult2, slice(slicer2, XS_OBJ_IMLIST)));
		assertTrue(str(slice(slicer2, XS_OBJ_GEN)), areEqual(expectedResult2, slice(slicer2, XS_OBJ_GEN)));
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