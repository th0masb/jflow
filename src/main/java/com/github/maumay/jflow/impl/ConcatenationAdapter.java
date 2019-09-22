/**
 *
 */
package com.github.maumay.jflow.impl;

/**
 * @author thomasb
 *
 */
public final class ConcatenationAdapter
{
    private ConcatenationAdapter()
    {
    }

    public static final class OfObject<E> extends
            AbstractIteratorBiAdapters.OfObject<AbstractRichIterator<? extends E>, AbstractRichIterator<? extends E>, E>
    {
        public OfObject(AbstractRichIterator<? extends E> sourceOne,
                AbstractRichIterator<? extends E> sourceTwo)
        {
            super(IteratorSizes.sum(sourceOne.getSize(), sourceTwo.getSize()),
                    sourceOne,
                    sourceTwo);
        }

        @Override
        public boolean hasNext()
        {
            return getSourceOne().hasNext() || getSourceTwo().hasNext();
        }

        @Override
        public E nextImpl()
        {
            return getSourceOne().hasNext() ? getSourceOne()
                    .nextImpl() : getSourceTwo().nextImpl();
        }

        @Override
        public void forwardImpl()
        {
            if (getSourceOne().hasNext()) {
                getSourceOne().forwardImpl();
            } else {
                getSourceTwo().forwardImpl();
            }
        }
    }

    public static final class OfInt
            extends
            AbstractIteratorBiAdapters.OfInt<AbstractIntIterator, AbstractIntIterator>
    {
        public OfInt(AbstractIntIterator sourceOne,
                AbstractIntIterator sourceTwo)
        {
            super(IteratorSizes.sum(sourceOne.getSize(), sourceTwo.getSize()),
                    sourceOne,
                    sourceTwo);
        }

        @Override
        public boolean hasNext()
        {
            return getSourceOne().hasNext() || getSourceTwo().hasNext();
        }

        @Override
        public int nextIntImpl()
        {
            return getSourceOne().hasNext() ? getSourceOne().nextIntImpl()
                                            : getSourceTwo().nextIntImpl();
        }

        @Override
        public void forwardImpl()
        {
            if (getSourceOne().hasNext()) {
                getSourceOne().forwardImpl();
            } else {
                getSourceTwo().forwardImpl();
            }
        }
    }

    public static final class OfLong
            extends
            AbstractIteratorBiAdapters.OfLong<AbstractLongIterator, AbstractLongIterator>
    {
        public OfLong(AbstractLongIterator sourceOne,
                AbstractLongIterator sourceTwo)
        {
            super(IteratorSizes.sum(sourceOne.getSize(), sourceTwo.getSize()),
                    sourceOne,
                    sourceTwo);
        }

        @Override
        public boolean hasNext()
        {
            return getSourceOne().hasNext() || getSourceTwo().hasNext();
        }

        @Override
        public long nextLongImpl()
        {
            return getSourceOne().hasNext() ? getSourceOne().nextLongImpl()
                                            : getSourceTwo().nextLongImpl();
        }

        @Override
        public void forwardImpl()
        {
            if (getSourceOne().hasNext()) {
                getSourceOne().forwardImpl();
            } else {
                getSourceTwo().forwardImpl();
            }
        }
    }

    public static final class OfDouble extends
            AbstractIteratorBiAdapters.OfDouble<AbstractDoubleIterator, AbstractDoubleIterator>
    {
        public OfDouble(AbstractDoubleIterator sourceOne,
                AbstractDoubleIterator sourceTwo)
        {
            super(IteratorSizes.sum(sourceOne.getSize(), sourceTwo.getSize()),
                    sourceOne,
                    sourceTwo);
        }

        @Override
        public boolean hasNext()
        {
            return getSourceOne().hasNext() || getSourceTwo().hasNext();
        }

        @Override
        public double nextDoubleImpl()
        {
            return getSourceOne().hasNext() ? getSourceOne().nextDoubleImpl()
                                            : getSourceTwo().nextDoubleImpl();
        }

        @Override
        public void forwardImpl()
        {
            if (getSourceOne().hasNext()) {
                getSourceOne().forwardImpl();
            } else {
                getSourceTwo().forwardImpl();
            }
        }
    }
}
