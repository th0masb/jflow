package xawd.jchain.chains.utilities;

import static org.junit.Assert.assertTrue;
import static xawd.jchain.chains.utilities.CollectionUtil.str;
import static xawd.jchain.chains.utilities.FilterUtil.filter;

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