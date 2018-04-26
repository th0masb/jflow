package xawd.jflow.maptests;

import java.util.function.DoubleToIntFunction;
import java.util.function.LongToIntFunction;
import java.util.function.ToIntFunction;

import org.junit.Test;

import xawd.jflow.AbstractDoubleFlow;
import xawd.jflow.AbstractFlow;
import xawd.jflow.AbstractLongFlow;
import xawd.jflow.testutilities.IteratorComparisonTest;
import xawd.jflow.testutilities.IteratorExampleProvider;

public class MapToIntTest extends IteratorExampleProvider implements IteratorComparisonTest
{
	@Test
	public void testAbstractFlowMapToInt() 
	{
		final AbstractFlow<String> populated = getPopulatedObjectTestIterator();
		final AbstractFlow<String> empty = getEmptyObjectTestIterator();
		final ToIntFunction<String> mapper = Integer::parseInt;
		
		assertIteratorAsExpected(new int[] {0, 1, 2, 3, 4}, populated.mapToInt(mapper));
		assertIteratorAsExpected(new int[] {}, empty.mapToInt(mapper));
	}
	
	@Test
	public void testAbstractDoubleFlowMapToInt()
	{
		final AbstractDoubleFlow populated = getPopulatedDoubleTestIterator();
		final AbstractDoubleFlow empty = getEmptyDoubleTestIterator();
		final DoubleToIntFunction mapper = x -> (int) x - 1;
		
		assertIteratorAsExpected(new int[]{-1, 0, 1, 2, 3}, populated.mapToInt(mapper));
		assertIteratorAsExpected(new int[] {}, empty.mapToInt(mapper));
	}
	
	@Test
	public void testAbstractLongFlowMapToInt()
	{
		final AbstractLongFlow populated = getPopulatedLongTestIterator();
		final AbstractLongFlow empty = getEmptyLongTestIterator();
		final LongToIntFunction mapper = x -> (int) x - 1;
		
		assertIteratorAsExpected(new int[]{-1, 0, 1, 2, 3}, populated.mapToInt(mapper));
		assertIteratorAsExpected(new int[] {}, empty.mapToInt(mapper));
	}
}
