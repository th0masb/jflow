/**
 *
 */
package xawd.lists.listflow;

import java.util.ArrayList;

import xawd.jflow.Flow;
import xawd.jflow.impl.FlowFromValues;
import xawd.jflow.impl.ReverseFlowFromValues;

/**
 * @author t
 */
public class ArrayListFlow<E> extends ArrayList<E> implements ListFlow<E> {
	/**
	 *
	 */
	private static final long serialVersionUID = -7069256425982985037L;

	@Override
	public Flow<E> iter() {
		return new FlowFromValues.OfObject<>(this);
	}

	@Override
	public Flow<E> rIter() {
		return new ReverseFlowFromValues.OfObject<>(this);
	}
}
