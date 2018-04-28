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
package org.joda.primitives.iterator.impl;

import java.util.NoSuchElementException;

import org.joda.primitives.DoubleUtils;
import org.joda.primitives.iterator.DoubleIterator;

/**
 * An iterator over an array of <code>double</code> values.
 * <p>
 * This class implements {@link java.util.Iterator Iterator} allowing
 * seamless integration with other APIs.
 * <p>
 * The iterator can be reset to the start if required.
 * It is unmodifiable and <code>remove()</code> is unsupported.
 *
 * @author Stephen Colebourne
 * @author Jason Tiscione
 * @version CODE GENERATED
 * @since 1.0
 */
public class ArrayDoubleIterator implements DoubleIterator {
    // This file is CODE GENERATED. Do not change manually.

    /** The array to iterate over */
    protected final double[] array;
    /** Cursor position */
    protected int cursor;

    /**
     * Creates an iterator over a copy of an array of <code>double</code> values.
     * <p>
     * The specified array is copied, making this class effectively immutable.
     * Note that the class is not {@code final} thus it is not truly immutable.
     * 
     * @param array  the array to iterate over, must not be null
     * @return an iterator based on a copy of the input array, not null
     * @throws IllegalArgumentException if the array is null
     */
    public static ArrayDoubleIterator copyOf(double[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array must not be null");
        }
        return new ArrayDoubleIterator(array.clone());
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
    public ArrayDoubleIterator(double[] array) {
        super();
        if (array == null) {
            throw new IllegalArgumentException("Array must not be null");
        }
        this.array = array;
    }

    //-----------------------------------------------------------------------
    public boolean isModifiable() {
        return false;
    }

    public boolean isResettable() {
        return true;
    }

    //-----------------------------------------------------------------------
    public boolean hasNext() {
        return (cursor < array.length);
    }

    public double nextDouble() {
        if (hasNext() == false) {
            throw new NoSuchElementException("No more elements available");
        }
        return array[cursor++];
    }

    public Double next() {
        return DoubleUtils.toObject(nextDouble());
    }

    public void remove() {
        throw new UnsupportedOperationException("ArrayDoubleIterator does not support remove");
    }

    public void reset() {
        cursor = 0;
    }

}
