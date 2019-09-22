/**
 *
 */
package com.github.maumay.jflow.impl;

import com.github.maumay.jflow.impl.ArrayAccumulators.OfObject;
import com.github.maumay.jflow.vec.Vec;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author thomasb
 *
 */
public class VecCollector<E> implements
        Collector<E, ArrayAccumulators.OfObject<E>, Vec<E>>
{
    private static final Set<Characteristics> CHARACTERISTICS = EnumSet
            .noneOf(Characteristics.class);

    @Override
    public BiConsumer<OfObject<E>, E> accumulator()
    {
        return (l, r) -> l.add(r);
    }

    @Override
    public Set<Characteristics> characteristics()
    {
        return CHARACTERISTICS;
    }

    @Override
    public BinaryOperator<OfObject<E>> combiner()
    {
        return (l, r) -> {
            l.add(r);
            return l;
        };
    }

    @Override
    public Function<OfObject<E>, Vec<E>> finisher()
    {
        return x -> Vec.of(x.compress());
    }

    @Override
    public Supplier<OfObject<E>> supplier()
    {
        return OfObject::new;
    }
}
