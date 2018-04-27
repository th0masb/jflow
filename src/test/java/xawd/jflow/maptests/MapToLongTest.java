package xawd.jflow.maptests;

import java.util.function.DoubleToLongFunction;
import java.util.function.IntToLongFunction;
import java.util.function.ToLongFunction;

import org.junit.Test;

import xawd.jflow.AbstractDoubleFlow;
import xawd.jflow.AbstractFlow;
import xawd.jflow.AbstractIntFlow;
import xawd.jflow.testutilities.IteratorTest;
import xawd.jflow.testutilities.IteratorExampleProvider;

public class MapToLongTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	public void testAbstractFlowMapToLong() 
	{
		final AbstractFlow<String> populated = getPopulatedObjectTestIterator();
		final AbstractFlow<String> empty = getEmptyObjectTestIterator();
		final ToLongFunction<String> mapper = Long::parseLong;
		
		assertIteratorAsExpected(new long[] {0, 1, 2, 3, 4}, populated.mapToLong(mapper));
		assertIteratorAsExpected(new long[] {}, empty.mapToLong(mapper));
	}
	
	@Test
	public void testAbstractDoubleFlowMapToLong()
	{
		final AbstractDoubleFlow populated = getPopulatedDoubleTestIterator();
		final AbstractDoubleFlow empty = getEmptyDoubleTestIterator();
		final DoubleToLongFunction mapper = x -> 0L;
		
		assertIteratorAsExpected(new long[] {0, 0, 0, 0, 0}, populated.mapToLong(mapper));
		assertIteratorAsExpected(new long[] {}, empty.mapToLong(mapper));
	}
	
	@Test
	public void testAbstractIntFlowMapToLong()
	{
		final AbstractIntFlow populated = getPopulatedIntTestIterator();
		final AbstractIntFlow empty = getEmptyIntTestIterator();
		final IntToLongFunction mapper = x -> 0L;
		
		assertIteratorAsExpected(new long[] {0, 0, 0, 0, 0}, populated.mapToLong(mapper));
		assertIteratorAsExpected(new long[] {}, empty.mapToLong(mapper));
	}
}
