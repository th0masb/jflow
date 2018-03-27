package io.xyz.chains.utilities;

import static io.xyz.chains.utilities.CollectionUtil.str;
import static io.xyz.chains.utilities.FoldUtil.accumulate;
import static io.xyz.chains.utilities.FoldUtil.foldl;
import static io.xyz.chains.utilities.FoldUtil.foldr;
import static io.xyz.chains.utilities.PrimitiveUtil.isZero;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FoldUtilTest extends TestDataProvider
{
	@Test
	public void testFoldr()
	{
		final Integer expectedResult = ONE * TWO * THREE;

		assertTrue(str(foldr((a, b) -> a * b, 1, XS_INT_ARR)), isZero(foldr((a, b) -> a * b, 1, XS_INT_ARR).getAsInt() - expectedResult));
		assertTrue(str(foldr((a, b) -> a * b, 1, XS_INT_IMARR)), isZero(foldr((a, b) -> a * b, 1, XS_INT_IMARR).getAsInt() - expectedResult));
		assertTrue(str(foldr((a, b) -> a * b, 1, XS_INT_GEN)), isZero(foldr((a, b) -> a * b, 1, XS_INT_GEN).getAsInt() - expectedResult));

		assertTrue(str(foldr((a, b) -> a * b, 1, XS_DOUBLE_ARR)), isZero(foldr((a, b) -> a * b, 1, XS_DOUBLE_ARR).getAsDouble() - expectedResult));
		assertTrue(str(foldr((a, b) -> a * b, 1, XS_DOUBLE_IMARR)), isZero(foldr((a, b) -> a * b, 1, XS_DOUBLE_IMARR).getAsDouble() - expectedResult));
		assertTrue(str(foldr((a, b) -> a * b, 1, XS_DOUBLE_GEN)), isZero(foldr((a, b) -> a * b, 1, XS_DOUBLE_GEN).getAsDouble() - expectedResult));

		assertTrue(str(foldr((a, b) -> a * b, 1, XS_LONG_ARR)), isZero(foldr((a, b) -> a * b, 1, XS_LONG_ARR).getAsLong() - expectedResult));
		assertTrue(str(foldr((a, b) -> a * b, 1, XS_LONG_IMARR)), isZero(foldr((a, b) -> a * b, 1, XS_LONG_IMARR).getAsLong() - expectedResult));
		assertTrue(str(foldr((a, b) -> a * b, 1, XS_LONG_GEN)), isZero(foldr((a, b) -> a * b, 1, XS_LONG_GEN).getAsLong() - expectedResult));

		assertTrue(str(foldr((a, b) -> a * b, 1, XS_OBJ_ARR)), isZero(foldr((a, b) -> a * b, 1, XS_OBJ_ARR).get() - expectedResult));
		assertTrue(str(foldr((a, b) -> a * b, 1, XS_OBJ_LIST)), isZero(foldr((a, b) -> a * b, 1, XS_OBJ_LIST).get() - expectedResult));
		assertTrue(str(foldr((a, b) -> a * b, 1, XS_OBJ_GEN)), isZero(foldr((a, b) -> a * b, 1, XS_OBJ_GEN).get() - expectedResult));
	}

	@Test
	public void testFoldl()
	{
		final Integer expectedResult = ONE * TWO * THREE;

		assertTrue(str(foldl((a, b) -> a * b, 1, XS_INT_ARR)), isZero(foldl((a, b) -> a * b, 1, XS_INT_ARR).getAsInt() - expectedResult));
		assertTrue(str(foldl((a, b) -> a * b, 1, XS_INT_IMARR)), isZero(foldl((a, b) -> a * b, 1, XS_INT_IMARR).getAsInt() - expectedResult));
		assertTrue(str(foldl((a, b) -> a * b, 1, XS_INT_GEN)), isZero(foldl((a, b) -> a * b, 1, XS_INT_GEN).getAsInt() - expectedResult));

		assertTrue(str(foldl((a, b) -> a * b, 1, XS_DOUBLE_ARR)), isZero(foldl((a, b) -> a * b, 1, XS_DOUBLE_ARR).getAsDouble() - expectedResult));
		assertTrue(str(foldl((a, b) -> a * b, 1, XS_DOUBLE_IMARR)), isZero(foldl((a, b) -> a * b, 1, XS_DOUBLE_IMARR).getAsDouble() - expectedResult));
		assertTrue(str(foldl((a, b) -> a * b, 1, XS_DOUBLE_GEN)), isZero(foldl((a, b) -> a * b, 1, XS_DOUBLE_GEN).getAsDouble() - expectedResult));

		assertTrue(str(foldl((a, b) -> a * b, 1, XS_LONG_ARR)), isZero(foldl((a, b) -> a * b, 1, XS_LONG_ARR).getAsLong() - expectedResult));
		assertTrue(str(foldl((a, b) -> a * b, 1, XS_LONG_IMARR)), isZero(foldl((a, b) -> a * b, 1, XS_LONG_IMARR).getAsLong() - expectedResult));
		assertTrue(str(foldl((a, b) -> a * b, 1, XS_LONG_GEN)), isZero(foldl((a, b) -> a * b, 1, XS_LONG_GEN).getAsLong() - expectedResult));

		assertTrue(str(foldl((a, b) -> a * b, 1, XS_OBJ_ARR)), isZero(foldl((a, b) -> a * b, 1, XS_OBJ_ARR).get() - expectedResult));
		assertTrue(str(foldl((a, b) -> a * b, 1, XS_OBJ_LIST)), isZero(foldl((a, b) -> a * b, 1, XS_OBJ_LIST).get() - expectedResult));
		assertTrue(str(foldl((a, b) -> a * b, 1, XS_OBJ_GEN)), isZero(foldl((a, b) -> a * b, 1, XS_OBJ_GEN).get() - expectedResult));
	}

	@Test
	public void testAccumulate()
	{
		final int[] expectedResult = { ONE, ONE * TWO, ONE * TWO * THREE };

		assertTrue(str(accumulate((a, b) -> a * b, XS_INT_ARR)), areEqual(expectedResult, accumulate((a, b) -> a * b, XS_INT_ARR)));
		assertTrue(str(accumulate((a, b) -> a * b, XS_INT_IMARR)), areEqual(expectedResult, accumulate((a, b) -> a * b, XS_INT_IMARR)));
		assertTrue(str(accumulate((a, b) -> a * b, XS_INT_GEN)), areEqual(expectedResult, accumulate((a, b) -> a * b, XS_INT_GEN)));

		assertTrue(str(accumulate((a, b) -> a * b, XS_DOUBLE_ARR)), areEqual(expectedResult, accumulate((a, b) -> a * b, XS_DOUBLE_ARR)));
		assertTrue(str(accumulate((a, b) -> a * b, XS_DOUBLE_IMARR)), areEqual(expectedResult, accumulate((a, b) -> a * b, XS_DOUBLE_IMARR)));
		assertTrue(str(accumulate((a, b) -> a * b, XS_DOUBLE_GEN)), areEqual(expectedResult, accumulate((a, b) -> a * b, XS_DOUBLE_GEN)));

		assertTrue(str(accumulate((a, b) -> a * b, XS_LONG_ARR)), areEqual(expectedResult, accumulate((a, b) -> a * b, XS_LONG_ARR)));
		assertTrue(str(accumulate((a, b) -> a * b, XS_LONG_IMARR)), areEqual(expectedResult, accumulate((a, b) -> a * b, XS_LONG_IMARR)));
		assertTrue(str(accumulate((a, b) -> a * b, XS_LONG_GEN)), areEqual(expectedResult, accumulate((a, b) -> a * b, XS_LONG_GEN)));

		assertTrue(str(accumulate((a, b) -> a * b, XS_OBJ_ARR)), areEqual(expectedResult, accumulate((a, b) -> a * b, XS_OBJ_ARR)));
		assertTrue(str(accumulate((a, b) -> a * b, XS_OBJ_LIST)), areEqual(expectedResult, accumulate((a, b) -> a * b, XS_OBJ_LIST)));
		assertTrue(str(accumulate((a, b) -> a * b, XS_OBJ_GEN)), areEqual(expectedResult, accumulate((a, b) -> a * b, XS_OBJ_GEN)));
	}
}