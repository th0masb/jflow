/**
 *
 */
package xawd.jflow.iterators.examples;

import java.util.Iterator;
import java.util.List;

import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.iterables.FlowIterable;

/**
 * @author t
 *
 */
public interface ListFlow<E> extends List<E>, FlowIterable<E>
{
	Flow<E> rIter();

	@Deprecated
	@Override
	default Iterator<E> iterator()
	{
		return iter();
	}
}
