/**
 *
 */
package xawd.jflow.collections;

import java.util.Set;

import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.iterables.FlowIterable;

/**
 * An instance of this interface has all the functionality of a standard Java
 * Set along with enhanced iterators and functional programming style
 * methods implemented by delegating to these enhanced iterators. See
 * {@link Flow}.
 *
 * @author ThomasB
 */
public interface FlowSet<E> extends FlowIterable<E>, Set<E>
{
}
