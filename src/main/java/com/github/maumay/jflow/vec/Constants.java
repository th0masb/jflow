/**
 *
 */
package com.github.maumay.jflow.vec;

import com.github.maumay.jflow.impl.EmptyIterator;

/**
 * @author thomasb
 *
 */
final class Constants
{

    private Constants()
    {
    }

    static final DoubleVec EMPTY_DOUBLE_VEC = EmptyIterator.ofDouble().toVec();
    static final IntVec EMPTY_INT_VEC = EmptyIterator.ofInt().toVec();
    static final LongVec EMPTY_LONG_VEC = EmptyIterator.ofLong().toVec();
}
