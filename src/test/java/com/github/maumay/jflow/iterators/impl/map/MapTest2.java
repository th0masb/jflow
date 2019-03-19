/**
 * 
 */
package com.github.maumay.jflow.iterators.impl.map;

import java.util.ArrayList;
import java.util.List;

import com.github.maumay.jflow.testutilities.AbstractUniversalAdapterTest;

/**
 * @author thomasb
 *
 */
public final class MapTest2 extends AbstractUniversalAdapterTest<String>
{
	@Override
	public List<Case<String>> getTestCases()
	{
		List<Case<String>> cases = new ArrayList<>();
		Adapter<String> adapter = iter -> iter.map(s -> s + s);
		cases.add(new Case<>(list(), adapter, list()));
		cases.add(new Case<>(list("1", "2"), adapter, list("11", "22")));
		cases.add(new Case<>(list("1", "2", "3", "4"), adapter, list("11", "22", "33", "44")));
		return cases;
	}

	@Override
	public List<IntCase> getIntTestCases()
	{
		List<IntCase> cases = new ArrayList<>();
		IntAdapter adapter = iter -> iter.map(n -> 2 * n);
		cases.add(new IntCase(list(), adapter, list()));
		cases.add(new IntCase(list(1, 2), adapter, list(2, 4)));
		return cases;
	}

	@Override
	public List<LongCase> getLongTestCases()
	{
		List<LongCase> cases = new ArrayList<>();
		LongAdapter adapter = iter -> iter.map(n -> 2 * n);
		cases.add(new LongCase(list(), adapter, list()));
		cases.add(new LongCase(list(1L, 2L), adapter, list(2L, 4L)));
		return cases;
	}

	@Override
	public List<DoubleCase> getDoubleTestCases()
	{
		List<DoubleCase> cases = new ArrayList<>();
		DoubleAdapter adapter = iter -> iter.map(n -> 2 * n);
		cases.add(new DoubleCase(list(), adapter, list()));
		cases.add(new DoubleCase(list(1.0, 2.0), adapter, list(2.0, 4.0)));
		return cases;
	}
}
