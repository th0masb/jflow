/**
 * Copyright © 2018 Lhasa Limited
 * File created: 14 Feb 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.chains.utilities;

import static io.xyz.chains.utilities.CollectionUtil.str;
import static io.xyz.chains.utilities.FilterUtil.filter;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author ThomasB
 * @since 14 Feb 2018
 */
public class FilterUtilTest extends TestDataProvider
{
	@Test
	public void testFilter()
	{
		final int[] expectedResult = {THREE};

		assertTrue(str(filter(x -> x > TWO, XS_INT_ARR)), areEqual(expectedResult, filter(x -> x > TWO, XS_INT_ARR)));
		assertTrue(str(filter(x -> x > TWO, XS_INT_IMARR)), areEqual(expectedResult, filter(x -> x > TWO, XS_INT_IMARR)));
		assertTrue(str(filter(x -> x > TWO, XS_INT_GEN)), areEqual(expectedResult, filter(x -> x > TWO, XS_INT_GEN)));

		assertTrue(str(filter(x -> x > TWO, XS_DOUBLE_ARR)), areEqual(expectedResult, filter(x -> x > TWO, XS_DOUBLE_ARR)));
		assertTrue(str(filter(x -> x > TWO, XS_DOUBLE_IMARR)), areEqual(expectedResult, filter(x -> x > TWO, XS_DOUBLE_IMARR)));
		assertTrue(str(filter(x -> x > TWO, XS_DOUBLE_GEN)), areEqual(expectedResult, filter(x -> x > TWO, XS_DOUBLE_GEN)));

		assertTrue(str(filter(x -> x > TWO, XS_LONG_ARR)), areEqual(expectedResult, filter(x -> x > TWO, XS_LONG_ARR)));
		assertTrue(str(filter(x -> x > TWO, XS_LONG_IMARR)), areEqual(expectedResult, filter(x -> x > TWO, XS_LONG_IMARR)));
		assertTrue(str(filter(x -> x > TWO, XS_LONG_GEN)), areEqual(expectedResult, filter(x -> x > TWO, XS_LONG_GEN)));

		assertTrue(str(filter(x -> x > TWO, XS_OBJ_ARR)), areEqual(expectedResult, filter(x -> x > TWO, XS_OBJ_ARR)));
		assertTrue(str(filter(x -> x > TWO, XS_OBJ_LIST)), areEqual(expectedResult, filter(x -> x > TWO, XS_OBJ_LIST)));
		assertTrue(str(filter(x -> x > TWO, XS_OBJ_IMLIST)), areEqual(expectedResult, filter(x -> x > TWO, XS_OBJ_IMLIST)));
		assertTrue(str(filter(x -> x > TWO, XS_OBJ_GEN)), areEqual(expectedResult, filter(x -> x > TWO, XS_OBJ_GEN)));
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