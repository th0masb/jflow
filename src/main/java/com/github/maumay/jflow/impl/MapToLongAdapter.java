/**
 *
 */
package com.github.maumay.jflow.impl;

import java.util.function.DoubleToLongFunction;
import java.util.function.IntToLongFunction;
import java.util.function.ToLongFunction;

/**
 * @author thomasb
 *
 */
public final class MapToLongAdapter
{
    private MapToLongAdapter()
    {
    }

    public static final class FromObject<E>
            extends AbstractIteratorAdapter.OfLong<AbstractRichIterator<E>>
    {
        private final ToLongFunction<? super E> map;

        public FromObject(AbstractRichIterator<E> source,
                ToLongFunction<? super E> map)
        {
            super(source.getSize().copy(), source);
            this.map = map;
        }

        @Override
        public boolean hasNext()
        {
            return getSource().hasNext();
        }

        @Override
        public long nextLongImpl()
        {
            return map.applyAsLong(getSource().nextImpl());
        }

        @Override
        public void forwardImpl()
        {
            getSource().forwardImpl();
        }
    }

    public static final class FromInt extends
            AbstractIteratorAdapter.OfLong<AbstractIntIterator>
    {
        private final IntToLongFunction map;

        public FromInt(AbstractIntIterator source, IntToLongFunction map)
        {
            super(source.getSize().copy(), source);
            this.map = map;
        }

        @Override
        public boolean hasNext()
        {
            return getSource().hasNext();
        }

        @Override
        public long nextLongImpl()
        {
            return map.applyAsLong(getSource().nextIntImpl());
        }

        @Override
        public void forwardImpl()
        {
            getSource().forwardImpl();
        }
    }

    public static final class FromDouble
            extends AbstractIteratorAdapter.OfLong<AbstractDoubleIterator>
    {
        private final DoubleToLongFunction map;

        public FromDouble(AbstractDoubleIterator source,
                DoubleToLongFunction map)
        {
            super(source.getSize().copy(), source);
            this.map = map;
        }

        @Override
        public boolean hasNext()
        {
            return getSource().hasNext();
        }

        @Override
        public long nextLongImpl()
        {
            return map.applyAsLong(getSource().nextDoubleImpl());
        }

        @Override
        public void forwardImpl()
        {
            getSource().forwardImpl();
        }
    }
}
