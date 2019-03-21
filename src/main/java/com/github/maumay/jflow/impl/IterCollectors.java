/**
 * 
 */
package com.github.maumay.jflow.impl;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.github.maumay.jflow.iterators.RichIteratorCollector;
import com.github.maumay.jflow.iterators.factories.Iter;
import com.github.maumay.jflow.utils.Exceptions;

/**
 * @author t
 *
 */
public class IterCollectors
{
	private IterCollectors()
	{
	}

	/**
	 * Applies the same logic as {@link #toMap(Function, Function)} but optimizes
	 * the result for enumerate types by returning an instance of {@link EnumMap}.
	 * Note that if this iterator is empty then a {@link HashMap} will be returned
	 * instead.
	 * 
	 * @param             <K> The type of key in the resulting map.
	 * @param             <V> The type of value in the resulting map.
	 * @param keyMapper   see {@link #toMap(Function, Function)}
	 * @param valueMapper see {@link #toMap(Function, Function)}
	 * @return see {@link #toMap(Function, Function)}
	 */
	static class EnumMapCollector<K extends Enum<K>, R>
			implements RichIteratorCollector<K, Map<K, R>>
	{
		private final Function<? super K, ? extends R> targetMapper;

		public EnumMapCollector(Function<? super K, ? extends R> targetMapper)
		{
			this.targetMapper = targetMapper;
		}

		@Override
		public Map<K, R> collect(AbstractRichIterator<? extends K> source)
		{
			// If there are no concrete elements we can't instantiate an enum map so
			// return
			// a HashMap
			Exceptions.require(source.hasOwnership());
			if (!source.hasNext()) {
				return new HashMap<>();
			}
			K first = source.nextImpl();
			@SuppressWarnings("unchecked")
			Map<K, R> dest = new EnumMap<>((Class<K>) first.getClass());
			dest.put(first, targetMapper.apply(first));
			while (source.hasNext()) {
				K next = source.nextImpl();
				if (dest.containsKey(next)) {
					throw new IllegalStateException();
				} else {
					dest.put(next, targetMapper.apply(next));
				}
			}
			return dest;
		}
	}

	enum A
	{
		A, B, C;
	}

	public static void main(String[] args)
	{
		Iter.over(A.class).collect(new EnumMapCollector<>(x -> x));
	}
}
