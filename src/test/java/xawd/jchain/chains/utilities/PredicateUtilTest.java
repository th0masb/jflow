package xawd.jchain.chains.utilities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static xawd.jchain.chains.utilities.CollectionUtil.asFunction;
import static xawd.jchain.chains.utilities.PredicateUtil.all;
import static xawd.jchain.chains.utilities.PredicateUtil.allEqual;
import static xawd.jchain.chains.utilities.PredicateUtil.any;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.primitives.ImmutableDoubleArray;
import com.google.common.primitives.ImmutableIntArray;
import com.google.common.primitives.ImmutableLongArray;

/**
 * @author ThomasB
 * @since 14 Feb 2018
 */
public class PredicateUtilTest extends TestDataProvider
{
	@Test
	public void testAll()
	{
		assertTrue(all(x -> x < THREE + 1, XS_INT_ARR));
		assertTrue(all(x -> x < THREE + 1, XS_INT_IMARR));
		assertTrue(all(x -> x < THREE + 1, XS_INT_GEN));

		assertTrue(all(x -> x < THREE + 1, XS_DOUBLE_ARR));
		assertTrue(all(x -> x < THREE + 1, XS_DOUBLE_IMARR));
		assertTrue(all(x -> x < THREE + 1, XS_DOUBLE_GEN));

		assertTrue(all(x -> x < THREE + 1, XS_LONG_ARR));
		assertTrue(all(x -> x < THREE + 1, XS_LONG_IMARR));
		assertTrue(all(x -> x < THREE + 1, XS_LONG_GEN));

		assertTrue(all(x -> x < THREE + 1, XS_OBJ_ARR));
		assertTrue(all(x -> x < THREE + 1, XS_OBJ_LIST));
		assertTrue(all(x -> x < THREE + 1, XS_OBJ_IMLIST));
		assertTrue(all(x -> x < THREE + 1, XS_OBJ_GEN));

		assertFalse(all(x -> x < THREE, XS_INT_ARR));
		assertFalse(all(x -> x < THREE, XS_INT_IMARR));
		assertFalse(all(x -> x < THREE, XS_INT_GEN));

		assertFalse(all(x -> x < THREE, XS_DOUBLE_ARR));
		assertFalse(all(x -> x < THREE, XS_DOUBLE_IMARR));
		assertFalse(all(x -> x < THREE, XS_DOUBLE_GEN));

		assertFalse(all(x -> x < THREE, XS_LONG_ARR));
		assertFalse(all(x -> x < THREE, XS_LONG_IMARR));
		assertFalse(all(x -> x < THREE, XS_LONG_GEN));

		assertFalse(all(x -> x < THREE, XS_OBJ_ARR));
		assertFalse(all(x -> x < THREE, XS_OBJ_LIST));
		assertFalse(all(x -> x < THREE, XS_OBJ_IMLIST));
		assertFalse(all(x -> x < THREE, XS_OBJ_GEN));
	}

	@Test
	public void testAny()
	{
		assertTrue(any(x -> x < THREE, XS_INT_ARR));
		assertTrue(any(x -> x < THREE, XS_INT_IMARR));
		assertTrue(any(x -> x < THREE, XS_INT_GEN));

		assertTrue(any(x -> x < THREE, XS_DOUBLE_ARR));
		assertTrue(any(x -> x < THREE, XS_DOUBLE_IMARR));
		assertTrue(any(x -> x < THREE, XS_DOUBLE_GEN));

		assertTrue(any(x -> x < THREE, XS_LONG_ARR));
		assertTrue(any(x -> x < THREE, XS_LONG_IMARR));
		assertTrue(any(x -> x < THREE, XS_LONG_GEN));

		assertTrue(any(x -> x < THREE, XS_OBJ_ARR));
		assertTrue(any(x -> x < THREE, XS_OBJ_LIST));
		assertTrue(any(x -> x < THREE, XS_OBJ_IMLIST));
		assertTrue(any(x -> x < THREE, XS_OBJ_GEN));

		assertFalse(any(x -> x > THREE, XS_INT_ARR));
		assertFalse(any(x -> x > THREE, XS_INT_IMARR));
		assertFalse(any(x -> x > THREE, XS_INT_GEN));

		assertFalse(any(x -> x > THREE, XS_DOUBLE_ARR));
		assertFalse(any(x -> x > THREE, XS_DOUBLE_IMARR));
		assertFalse(any(x -> x > THREE, XS_DOUBLE_GEN));

		assertFalse(any(x -> x > THREE, XS_LONG_ARR));
		assertFalse(any(x -> x > THREE, XS_LONG_IMARR));
		assertFalse(any(x -> x > THREE, XS_LONG_GEN));

		assertFalse(any(x -> x > THREE, XS_OBJ_ARR));
		assertFalse(any(x -> x > THREE, XS_OBJ_LIST));
		assertFalse(any(x -> x > THREE, XS_OBJ_IMLIST));
		assertFalse(any(x -> x > THREE, XS_OBJ_GEN));
	}

	@Test
	public void testAllEqual()
	{
		assertTrue(allEqual( new int[] {1, 1, 1}));
		assertTrue(allEqual(ImmutableIntArray.copyOf(new int[] {1, 1, 1})));
		assertTrue(allEqual(asFunction(new int[] {1, 1, 1})));

		assertTrue(allEqual( new double[] {1, 1, 1}));
		assertTrue(allEqual(ImmutableDoubleArray.copyOf(new double[] {1, 1, 1})));
		assertTrue(allEqual(asFunction(new double[] {1, 1, 1})));

		assertTrue(allEqual( new long[] {1, 1, 1}));
		assertTrue(allEqual(ImmutableLongArray.copyOf(new long[] {1, 1, 1})));
		assertTrue(allEqual(asFunction(new long[] {1, 1, 1})));

		assertTrue(allEqual( new Integer[] {1, 1, 1}));
		assertTrue(allEqual(ImmutableList.copyOf(new Integer[] {1, 1, 1})));
		assertTrue(allEqual(asFunction(new Integer[] {1, 1, 1})));

		assertFalse(allEqual(XS_INT_ARR));
		assertFalse(allEqual(XS_INT_IMARR));
		assertFalse(allEqual(XS_INT_GEN));

		assertFalse(allEqual(XS_DOUBLE_ARR));
		assertFalse(allEqual(XS_DOUBLE_IMARR));
		assertFalse(allEqual(XS_DOUBLE_GEN));

		assertFalse(allEqual(XS_LONG_ARR));
		assertFalse(allEqual(XS_LONG_IMARR));
		assertFalse(allEqual(XS_LONG_GEN));

		assertFalse(allEqual(XS_OBJ_ARR));
		assertFalse(allEqual(XS_OBJ_LIST));
		assertFalse(allEqual(XS_OBJ_IMLIST));
		assertFalse(allEqual(XS_OBJ_GEN));
	}
}