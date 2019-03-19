/**
 * 
 */
package com.github.maumay.jflow.iterators.impl.map;

import java.util.ArrayList;
import java.util.List;

import com.github.maumay.jflow.testutilities.AbstractAdapterTest;

/**
 * @author thomasb
 *
 */
public final class MapTest2 extends AbstractAdapterTest<String>
{
	@Override
	protected List<Case<String>> getTestCases()
	{
		List<Case<String>> cases = new ArrayList<>();
		Adapter<String> adapter = iter -> iter.map(s -> s + s);
		cases.add(new Case<>(list(), adapter, list()));
		cases.add(new Case<>(list("1", "2"), adapter, list("11", "22")));
		cases.add(new Case<>(list("1", "2", "3", "4", "6"), adapter, list("11", "22")));
		return cases;
	}
}
