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
public interface ListBuilder
{
	@SuppressWarnings("unchecked")
	default <T> List<T> list(T... ts)
	{
		return Arrays.asList(ts);
	}
}
