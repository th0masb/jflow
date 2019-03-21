/**
 * 
 */
package com.github.maumay.jflow.test;

import java.util.Arrays;
import java.util.List;

/**
 * @author thomasb
 *
 */
public abstract class AbstractListBuilder
{
	@SafeVarargs
	protected final <T> List<T> list(T... ts)
	{
		return Arrays.asList(ts);
	}
}
