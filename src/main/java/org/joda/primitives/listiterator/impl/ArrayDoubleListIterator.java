/*
 *  Copyright 2001-present Stephen Colebourne
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.joda.primitives.listiterator.impl;

import java.util.NoSuchElementException;

import org.joda.primitives.DoubleUtils;
import org.joda.primitives.listiterator.DoubleListIterator;

/**
 * An iterator over an array of <code>double</code> values.
 * <p>
 * This class implements {@link java.util.ListIterator ListIterator} allowing
 * seamless integration with other APIs.
 * <p>
 * The iterator can be reset to the start if required.
 * <code>add()</code> and <code>remove()</code> are unsupported, but
 * <code>set()</code> is supported.
 *
 * @author Stephen Colebourne
 * @author Jason Tiscione
 * @version CODE GENERATED
 * @since 1.0
 */
public class ArrayDoubleListIterator implements DoubleListIterator {
    // This file is CODE GENERATED. Do not change manually.

    /** The array to iterate over */
    protected final double[] array;
    /** Cursor position */
    protected int cursor;
    /** Last returned position */
    protected int last = -1;

    /**
     * Creates an iterator over a copy of an array of <code>double</code> values.
     * <p>
     * The specified array is copied, ensuring the original data is unaltered.
     * Note that the class is not immutable due to the {@code set} methods.
     * 
     * @param array  the array to iterate over, must not be null
     * @return an iterator based on a copy of the input array, not null
     * @throws IllegalArgumentException if the array is null
     */
    public static ArrayDoubleListIterator copyOf(double[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array must not be null");
        }
        return new ArrayDoubleListIterator(array.clone());
    }

    /**
     * Constructs an iterator over an array of <code>double</code> values.
     * <p>
     * The array is assigned internally, thus the caller holds a reference to
     * the internal state of the returned iterator. It is not recommended to
     * modify the state of the array after construction.
     * 
     * @param array  the array to iterate over, must not be null
     * @throws IllegalArgumentException if the array is null
     */
    public ArrayDoubleListIterator(double[] array) {
        super();
        if (array == null) {
            throw new IllegalArgumentException("Array must not be null");
        }
        this.array = array;
    }

    //-----------------------------------------------------------------------
    public boolean isModifiable() {
        return true;
    }

    public boolean isResettable() {
        return true;
    }

    //-----------------------------------------------------------------------
    public boolean hasNext() {
        return (cursor < array.length);
    }

    public int nextIndex() {
        return cursor;
    }

    public double nextDouble() {
        if (hasNext() == false) {
            throw new NoSuchElementException("No more elements available");
        }
        last = cursor;
        return array[cursor++];
    }

    public Double next() {
        return DoubleUtils.toObject(nextDouble());
    }

    public boolean hasPrevious() {
        return (cursor > 0);
    }

    public int previousIndex() {
        return cursor - 1;
    }

    public double previousDouble() {
        if (hasPrevious() == false) {
            throw new NoSuchElementException("No more elements available");
        }
        last = --cursor;
        return array[cursor];
    }

    public Double previous() {
        return DoubleUtils.toObject(previousDouble());
    }

    public void add(double value) {
        throw new UnsupportedOperationException("ArrayDoubleListIterator does not support add");
    }

    public void add(Double value) {
        throw new UnsupportedOperationException("ArrayDoubleListIterator does not support add");
    }

    public void remove() {
        throw new UnsupportedOperationException("ArrayDoubleListIterator does not support remove");
    }

    public void set(double value) {
        if (last < 0) {
            throw new IllegalStateException("ArrayDoubleListIterator cannot be set until next is called");
        }
        array[last] = value;
    }

    public void set(Double value) {
        set(DoubleUtils.toPrimitive(value));
    }

    public void reset() {
        cursor = 0;
        last = -1;
    }

}
