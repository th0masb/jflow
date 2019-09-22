package com.github.maumay.jflow.impl;

import com.github.maumay.jflow.iterator.collector.IteratorCollector;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;

/**
 * Collector which converts an iterator of enumerations into a map by
 * associating each with a value obtained by applying a provided function..
 *
 * @param <K>
 * @param <R>
 * @author thomasb
 */
public class EnumAssociator<K extends Enum<K>, R>
        implements IteratorCollector<K, Map<K, R>>
{
    private final Function<? super K, ? extends R> targetMapper;

    public EnumAssociator(Function<? super K, ? extends R> targetMapper)
    {
        this.targetMapper = targetMapper;
    }

    @Override
    public Map<K, R> collect(Iterator<? extends K> source)
    {
        // If there are no concrete elements we can't instantiate an enum map so
        // return
        // a HashMap
        if (!source.hasNext()) {
            return new HashMap<>();
        }
        K first = source.next();
        @SuppressWarnings("unchecked")
        Map<K, R> dest = new EnumMap<>((Class<K>) first.getClass());
        dest.put(first, targetMapper.apply(first));
        while (source.hasNext()) {
            K next = source.next();
            if (dest.containsKey(next)) {
                throw new IllegalStateException();
            } else {
                dest.put(next, targetMapper.apply(next));
            }
        }
        return dest;
    }
}
