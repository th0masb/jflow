/**
 *
 */
package xawd.lists.listflow;

import java.util.List;

import xawd.jflow.Flow;
import xawd.jflow.iterables.FlowIterable;

/**
 * @author t
 *
 */
public interface ListFlow<E> extends List<E>, FlowIterable<E>
{
	@Override
	Flow<E> iter();

	Flow<E> rIter();

	@Deprecated
	@Override
	default Flow<E> iterator()
	{
		return iter();
	}
}
