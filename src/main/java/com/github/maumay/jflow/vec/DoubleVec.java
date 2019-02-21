/**
 * 
 */
package com.github.maumay.jflow.vec;

import java.util.stream.DoubleStream;

import com.github.maumay.jflow.iterators.EnhancedDoubleIterator;
import com.github.maumay.jflow.iterators.iterables.DoubleIterable;

/**
 * Will improve documentation and add more methods in a future version...
 * 
 * @author thomasb
 */
public interface DoubleVec extends DoubleIterable
{
	int size();

	double get(int index);

	EnhancedDoubleIterator revIter();

	DoubleStream stream();

	static DoubleVec of(double... xs)
	{
		throw new RuntimeException();
	}
}
