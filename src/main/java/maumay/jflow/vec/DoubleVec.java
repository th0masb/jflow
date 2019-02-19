/**
 * 
 */
package maumay.jflow.vec;

import java.util.stream.DoubleStream;

import maumay.jflow.iterators.EnhancedDoubleIterator;
import maumay.jflow.iterators.iterables.DoubleIterable;

/**
 * @author thomasb
 *
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
