/**
 * 
 */
package xawd.jflow.maptests;

import java.util.function.IntToDoubleFunction;
import java.util.function.LongToDoubleFunction;
import java.util.function.ToDoubleFunction;

import org.junit.Test;

import xawd.jflow.AbstractFlow;
import xawd.jflow.AbstractIntFlow;
import xawd.jflow.AbstractLongFlow;
import xawd.jflow.testutilities.IteratorComparisonTest;
import xawd.jflow.testutilities.IteratorExampleProvider;

/**
 * @author t
 *
 */
public class MapToDoubleTest extends IteratorExampleProvider implements IteratorComparisonTest
{
	@Test
	public void testAbstractFlowMapToDouble() 
	{
		final AbstractFlow<String> populated = getPopulatedObjectTestIterator();
		final AbstractFlow<String> empty = getEmptyObjectTestIterator();
		final ToDoubleFunction<String> mapper = Double::parseDouble;
		
		assertIteratorAsExpected(new double[] {0, 1, 2, 3, 4}, populated.mapToDouble(mapper));
		assertIteratorAsExpected(new double[] {}, empty.mapToDouble(mapper));
	}

	@Test
	public void testAbstractLongFlowMapToDouble()
	{
		final AbstractLongFlow populated = getPopulatedLongTestIterator();
		final AbstractLongFlow empty = getEmptyLongTestIterator();
		final LongToDoubleFunction mapper = x -> x + 3.2;
		
		assertIteratorAsExpected(new double[] {3.2, 4.2, 5.2, 6.2, 7.2}, populated.mapToDouble(mapper));
		assertIteratorAsExpected(new double[] {}, empty.mapToDouble(mapper));
	}
	
	@Test
	public void testAbstractIntFlowMapToDouble()
	{
		final AbstractIntFlow populated = getPopulatedIntTestIterator();
		final AbstractIntFlow empty = getEmptyIntTestIterator();
		final IntToDoubleFunction mapper = x -> x + 1.6;
		
		assertIteratorAsExpected(new double[] {1.6, 2.6, 3.6, 4.6, 5.6}, populated.mapToDouble(mapper));
		assertIteratorAsExpected(new double[] {}, empty.mapToDouble(mapper));
	}
}
