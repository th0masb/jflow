package io.xyz.chains.utilities;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
		CollectionUtilTest.class,
		CombineUtilTest.class,
		FilterUtilTest.class,
		FoldUtilTest.class,
		MapUtilTest.class,
		PredicateUtilTest.class,
		RangeUtilTest.class,
		SliceUtilTest.class })

public class AllChainUtilityTests
{
}
