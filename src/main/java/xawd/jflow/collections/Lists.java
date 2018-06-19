/**
 *
 */
package xawd.jflow.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import xawd.jflow.collections.impl.FlowArrayList;
import xawd.jflow.collections.impl.ImmutableFlowList;

/**
 * @author ThomasB
 */
public final class Lists
{
	private Lists()
	{
	}

	@SafeVarargs
	public static <E> FlowList<E> of(E... elements)
	{
		return new ImmutableFlowList<>(elements);
	}

	public static <E> FlowList<E> copyOf(Collection<? extends E> src)
	{
		final List<E> mutable = new ArrayList<>(src);
		return new ImmutableFlowList<>(mutable::get, mutable.size());
	}

	@SafeVarargs
	public static <E> FlowList<E> mutableOf(E... elements)
	{
		final FlowList<E> mutable = new FlowArrayList<>(elements.length);
		for (final E element : elements) {
			mutable.add(element);
		}
		return mutable;
	}

	public static <E> FlowList<E> mutableCopyOf(Collection<? extends E> src)
	{
		return new FlowArrayList<>(src);
	}

//	public static void main(String[] args)
//	{
//		final FlowList<String> xs = Lists.of("a", "b", "c");
//		final FlowList<String> ys = xs.map(s -> s + s).toList();
//	}
}
