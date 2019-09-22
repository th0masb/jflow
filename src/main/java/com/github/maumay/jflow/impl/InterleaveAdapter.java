/**
 *
 */
package com.github.maumay.jflow.impl;

/**
 * @author thomasb
 *
 */
public final class InterleaveAdapter<E> extends
        AbstractIteratorBiAdapters.OfObject<AbstractRichIterator<E>, AbstractRichIterator<? extends E>, E>
{
    private boolean oneEngaged;

    public InterleaveAdapter(AbstractRichIterator<E> sourceOne,
            AbstractRichIterator<? extends E> sourceTwo)
    {
        super(new LowerBound(0), sourceOne, sourceTwo);
        oneEngaged = true;
    }

    @Override
    public E nextImpl()
    {
        E next = oneEngaged ? getSourceOne().nextImpl() : getSourceTwo()
                .nextImpl();
        oneEngaged = !oneEngaged;
        return next;
    }

    @Override
    public void forwardImpl()
    {
        if (oneEngaged) {
            getSourceOne().forwardImpl();
        } else {
            getSourceTwo().forwardImpl();
        }
        oneEngaged = !oneEngaged;
    }

    @Override
    public boolean hasNext()
    {
        return oneEngaged ? getSourceOne().hasNext() : getSourceTwo().hasNext();
    }
}
