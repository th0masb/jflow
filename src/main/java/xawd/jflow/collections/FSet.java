/**
 *
 */
package xawd.jflow.collections;

import java.util.Set;

import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.iterables.FlowIterable;

/**
 * An extension of the Set interface adding enhanced iterators to support a
 * more functional style of programming. See {@link Flow}.
 *
 * @param <E>
 *            The type of the elements in this set.
 *
 * @author ThomasB
 */
public interface FSet<E> extends Set<E>, FlowIterable<E>
{
}
