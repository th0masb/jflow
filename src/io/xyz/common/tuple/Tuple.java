/**
 * 
 */
package io.xyz.common.tuple;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author t
 *
 */
public class Tuple<T> 
{
	private final List<T> backing;
	
	@SafeVarargs
	public Tuple(T...ts)
	{
		backing = asList(ts);
	}
	
	public T get(int index)
	{
		return backing.get(index);
	}
	
	public T arg(int index)
	{
		return backing.get(index - 1);
	}
	
	public Stream<T> stream()
	{
		return backing.stream();
	}
}
