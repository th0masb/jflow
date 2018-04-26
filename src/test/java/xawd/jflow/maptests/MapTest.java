package xawd.jflow.maptests;

import static java.util.Arrays.asList;

import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.LongUnaryOperator;

import org.junit.Test;

import xawd.jflow.AbstractDoubleFlow;
import xawd.jflow.AbstractFlow;
import xawd.jflow.AbstractIntFlow;
import xawd.jflow.AbstractLongFlow;
import xawd.jflow.testutilities.IteratorComparisonTest;
import xawd.jflow.testutilities.IteratorExampleProvider;

public class MapTest extends IteratorExampleProvider implements IteratorComparisonTest
{
	@Test
	public void testAbstractObjectFlowMap() 
	{
		final AbstractFlow<String> populated = getPopulatedObjectTestIterator();
		final AbstractFlow<String> empty = getEmptyObjectTestIterator();
		final Function<String, String> mapper = string -> string + string;
		
		assertIteratorAsExpected(asList("00", "11", "22", "33", "44"), populated.map(mapper));
		assertIteratorAsExpected(asList(), empty.map(mapper));
	}

	@Test
	public void testAbstractLongFlowMap()
	{
		final AbstractLongFlow populated = getPopulatedLongTestIterator();
		final AbstractLongFlow empty = getEmptyLongTestIterator();
		final LongUnaryOperator mapper = x -> 2*x;
		
		assertIteratorAsExpected(new long[] {0, 2, 4, 6, 8 }, populated.map(mapper));
		assertIteratorAsExpected(new long[] {}, empty.map(mapper));
	}
	
	@Test
	public void testAbstractDoubleFlowMap()
	{
		final AbstractDoubleFlow populated = getPopulatedDoubleTestIterator();
		final AbstractDoubleFlow empty = getEmptyDoubleTestIterator();
		final DoubleUnaryOperator mapper = x -> 2*x;
		
		assertIteratorAsExpected(new double[] {0, 2, 4, 6, 8 }, populated.map(mapper));
		assertIteratorAsExpected(new double[] {}, empty.map(mapper));
	}
	
	@Test
	public void testAbstractIntFlowMap()
	{
		final AbstractIntFlow populated = getPopulatedIntTestIterator();
		final AbstractIntFlow empty = getEmptyIntTestIterator();
		final IntUnaryOperator mapper = x -> 2*x;
		
		assertIteratorAsExpected(new int[] {0, 2, 4, 6, 8 }, populated.map(mapper));
		assertIteratorAsExpected(new int[] {}, empty.map(mapper));
	}
}
