package com.github.maumay.jflow.exp;

import java.util.Iterator;
import java.util.Optional;

public class Main
{
    public static void main(String[] args)
    {
        Option<String> x = Some.of("x");
        Optional<String> w = Optional.of("x");
        var y = None.get();
        var z = Optional.empty();

        Option<String> op = Some.of("");
        Option<String> none = None.get();

    }

    static interface Option<E> extends Iterable<E>
    {

    }

    static class None<E> implements Option<E>
    {
        private None(){}

        static <E> None<E> get()
        {
            return new None<>();
        }

        @Override
        public Iterator<E> iterator()
        {
            return null;
        }
    }

    static class Some<E> implements Option<E>
    {
        E x;

        Some(E element) {
            x = element;
        }

        static <E> Some<E> of(E element) {
            return new Some<>(element);
        }

        @Override
        public Iterator<E> iterator()
        {
            return null;
        }
    }
}
