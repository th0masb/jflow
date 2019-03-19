/**
 * 
 */
package com.github.maumay.jflow.testutilities;

import java.util.Arrays;
import java.util.List;

/**
 * @author thomasb
 *
 */
public abstract class AbstractListBuilder
{
	@SafeVarargs
	public final <T> List<T> list(T... ts)
	{
		return Arrays.asList(ts);
	}
}
