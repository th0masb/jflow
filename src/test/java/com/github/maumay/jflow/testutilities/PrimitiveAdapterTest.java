/**
 * 
 */
package com.github.maumay.jflow.testutilities;

import java.util.List;
import java.util.function.UnaryOperator;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.impl.AbstractIntIterator;
import com.github.maumay.jflow.impl.AbstractLongIterator;

/**
 * @author thomasb
 *
 */
public interface PrimitiveAdapterTest extends DoubleIteratorTest, IntIteratorTest, LongIteratorTest
{
	List<IntCase> getIntTestCases();

	List<LongCase> getLongTestCases();

	List<DoubleCase> getDoubleTestCases();

	@FunctionalInterface
	static interface IntAdapter extends UnaryOperator<AbstractIntIterator>
	{
	}

	static class IntCase
	{
		final List<Integer> source, result;
		final IntAdapter adapter;

		public IntCase(List<Integer> source, IntAdapter adapter, List<Integer> result)
		{
			this.source = source;
			this.result = result;
			this.adapter = adapter;
		}
	}

	@FunctionalInterface
	static interface LongAdapter extends UnaryOperator<AbstractLongIterator>
	{
	}

	static class LongCase
	{
		final List<Long> source, result;
		final LongAdapter adapter;

		public LongCase(List<Long> source, LongAdapter adapter, List<Long> result)
		{
			this.source = source;
			this.result = result;
			this.adapter = adapter;
		}
	}

	@FunctionalInterface
	static interface DoubleAdapter extends UnaryOperator<AbstractDoubleIterator>
	{
	}

	static class DoubleCase
	{
		final List<Double> source, result;
		final DoubleAdapter adapter;

		public DoubleCase(List<Double> source, DoubleAdapter adapter, List<Double> result)
		{
			this.source = source;
			this.result = result;
			this.adapter = adapter;
		}
	}

}
