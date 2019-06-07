/**
 * 
 */
package com.github.maumay.jflow.exp;

import com.github.maumay.jflow.vec.Vec;

/**
 * @author thomasb
 *
 */
public class Presentation
{

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		// List<String> xs = Arrays.asList("a", "b", "c");
		// List<String> ys = xs.stream().map(x -> x + x).collect(Collectors.toList());

		// Vec<String> xs = Vec.of("a", "b", "c");
		// Vec<String> ys = xs.map(x -> x + x).filter(x -> x != null).map(x -> x + x);

		Vec<String> xs = Vec.of("a", "b", "c");
		Vec<String> ys = xs.iter().map(x -> x + x).filter(x -> x != null).map(x -> x + x).toVec();
	}
}
