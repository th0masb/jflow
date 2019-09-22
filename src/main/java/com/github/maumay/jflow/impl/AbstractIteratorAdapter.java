/**
 *
 */
package com.github.maumay.jflow.impl;

/**
 * Contains a set of static classes useful for implementing iterator adapters.
 * These are iterators which are constructed from a single source iterator.
 *
 * @author thomasb
 */
public class AbstractIteratorAdapter
{
    private AbstractIteratorAdapter()
    {
    }

    /**
     * Superclass of an object iterator ({@link AbstractRichIterator} which adapts
     * some provided source iterator and removes it's ownership.
     *
     * @author t
     *
     * @param <S> The type of the source iterator.
     * @param <R> The element type of this iterator.
     */
    public static abstract class OfObject<S extends AbstractIterator, R>
            extends AbstractRichIterator<R>
    {
        private final S source;

        public OfObject(AbstractIteratorSize size, S source)
        {
            super(size);
            // assert size.isInfinite() || size != source.getSize() : "Must not share size
            // instance";
            this.source = source;

            // The source is now considered locked and can only be consumed indirectly via
            // this iterator.
            source.relinquishOwnership();
        }

        protected final S getSource()
        {
            return source;
        }
    }

    /**
     * Superclass of an int iterator ({@link AbstractIntIterator} which adapts some
     * provided source iterator and removes it's ownership.
     *
     * @author t
     *
     * @param <S> The type of the source iterator.
     */
    public static abstract class OfInt<S extends AbstractIterator> extends
            AbstractIntIterator
    {
        private final S source;

        public OfInt(AbstractIteratorSize size, S source)
        {
            super(size);
            // assert size.isInfinite() || size != source.getSize() : "Must not share size
            // instance";
            this.source = source;

            // The source is now considered locked and can only be consumed indirectly via
            // this iterator.
            source.relinquishOwnership();
        }

        protected final S getSource()
        {
            return source;
        }
    }

    /**
     * Superclass of a long iterator ({@link AbstractLongIterator} which adapts some
     * provided source iterator and removes it's ownership.
     *
     * @author t
     *
     * @param <S> The type of the source iterator.
     */
    public static abstract class OfLong<S extends AbstractIterator> extends
            AbstractLongIterator
    {
        private final S source;

        public OfLong(AbstractIteratorSize size, S source)
        {
            super(size);
            // assert size.isInfinite() || size != source.getSize() : "Must not share size
            // instance";
            this.source = source;

            // The source is now considered locked and can only be consumed indirectly via
            // this iterator.
            source.relinquishOwnership();
        }

        protected final S getSource()
        {
            return source;
        }
    }

    /**
     * Superclass of an double iterator ({@link AbstractDoubleIterator} which adapts
     * some provided source iterator and removes it's ownership.
     *
     * @author t
     *
     * @param <S> The type of the source iterator.
     */
    public static abstract class OfDouble<S extends AbstractIterator> extends
            AbstractDoubleIterator
    {
        private final S source;

        public OfDouble(AbstractIteratorSize size, S source)
        {
            super(size);
            // assert size.isInfinite()
            // || size != source.getSize() : "Must not share size instance";
            this.source = source;

            // The source is now considered locked and can only be consumed indirectly via
            // this iterator.
            source.relinquishOwnership();
        }

        protected final S getSource()
        {
            return source;
        }
    }
}
