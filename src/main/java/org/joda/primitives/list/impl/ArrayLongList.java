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
package org.joda.primitives.list.impl;

import java.util.Collection;

import org.joda.primitives.LongUtils;

/**
 * Array based implementation of <code>LongList</code> for
 * primitive <code>int</code> elements.
 * <p>
 * This class implements {@link java.util.List List} allowing
 * seamless integration with other APIs.
 * <p>
 * Add, Remove, Set and Clear are supported.
 *
 * @author Stephen Colebourne
 * @author Rodney Waldhoff
 * @author Jason Tiscione
 * @version CODE GENERATED
 * @since 1.0
 */
public class ArrayLongList extends AbstractLongList implements Cloneable {
    // This file is CODE GENERATED. Do not change manually.

    /** The minimum size allowed when growth occurs */
    private static final int MIN_GROWTH_SIZE = 4;
    /** The amount the collection grows by when resized (3/2) */
    private static final int GROWTH_FACTOR_MULTIPLIER = 3;
    /** The amount the collection grows by when resized (3/2) */
    private static final int GROWTH_FACTOR_DIVISOR = 2;

    /** The array of elements */
    private long[] data;
    /** The current size */
    private int size;

    /**
     * Constructor.
     */
    public ArrayLongList() {
        super();
        data = LongUtils.EMPTY_LONG_ARRAY;
    }

    /**
     * Constructor that defines an initial size for the internal storage array.
     * 
     * @param initialSize  the initial size of the internal array, negative treated as zero
     */
    public ArrayLongList(int initialSize) {
        super();
        if (initialSize <= 0) {
            data = LongUtils.EMPTY_LONG_ARRAY;
        } else {
            data = new long[initialSize];
        }
    }

    /**
     * Constructor that copies the specified values.
     * 
     * @param values  an array of values to copy, null treated as zero size array
     */
    public ArrayLongList(long[] values) {
        super();
        if (values == null) {
            data = LongUtils.EMPTY_LONG_ARRAY;
        } else {
            data = (long[]) values.clone();
            size = values.length;
        }
    }

    /**
     * Constructor that copies the specified values.
     * 
     * @param coll  a collection of values to copy, null treated as zero size collection
     */
    public ArrayLongList(Collection<Long> coll) {
        super();
        if (coll == null) {
            data = LongUtils.EMPTY_LONG_ARRAY;
        } else if (coll instanceof ArrayLongList) {
            ArrayLongList c = (ArrayLongList) coll;
            this.data = new long[c.size];
            System.arraycopy(c.data, 0, this.data, 0, c.size);
            size = c.size;
        } else {
            data = toPrimitiveArray(coll);
            size = coll.size();
        }
    }

    // Implementation
    //-----------------------------------------------------------------------
    /**
     * Gets the current size of the collection.
     * 
     * @return the current size
     */
    public int size() {
        return size;
    }

    /**
     * Gets the primitive value at the specified index.
     *
     * @param index  the index to get from
     * @return value at the index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public long getLong(int index) {
        checkIndexExists(index);
        return data[index];
    }

    /**
     * Adds a primitive value to this collection.
     *
     * @param index  the index to insert at
     * @param value  the value to add to this collection
     * @return <code>true</code> if this collection was modified by this method call
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public boolean add(int index, long value) {
        checkAddModifiable();
        checkIndex(index);
        ensureCapacity(size + 1);
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
        return true;
    }

    /**
     * Removes a primitive value by index from the list.
     *
     * @param index  the index to remove from
     * @return the primitive value previously at this index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public long removeLongAt(int index) {
        checkRemoveModifiable();
        checkIndexExists(index);
        long result = data[index];
        System.arraycopy(data, index + 1, data, index, size - 1 - index);
        size--;
        return result;
    }

    /**
     * Removes a range of values from the list.
     *
     * @param fromIndexInclusive  the start of the range to remove, inclusive
     * @param toIndexExclusive  the end of the range to remove, exclusive
     * @return <code>true</code> if the collection was modified
     */
    public boolean removeRange(int fromIndexInclusive, int toIndexExclusive) {
        checkRemoveModifiable();
        checkRange(fromIndexInclusive, toIndexExclusive);
        if (fromIndexInclusive == toIndexExclusive) {
            return false;
        }
        System.arraycopy(data, toIndexExclusive, data, fromIndexInclusive, size - toIndexExclusive);
        size -= (toIndexExclusive - fromIndexInclusive);
        return true;
    }

    /**
     * Sets the primitive value at a specified index.
     *
     * @param index  the index to set
     * @param value  the value to store
     * @return the previous value at the index
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public long set(int index, long value) {
        checkSetModifiable();
        checkIndexExists(index);
        long result = data[index];
        data[index] = value;
        return result;
    }

    // Overrides
    //-----------------------------------------------------------------------
    /**
     * Optimizes the implementation.
     * <p>
     * This implementation changes the internal array to be the same size as
     * the size of the collection.
     */
    public void optimize() {
        if (size < data.length) {
            long[] array = new long[size];
            System.arraycopy(data, 0, array, 0, size);
            data = array;
        }
    }

    /**
     * Clears the collection/map of all elements.
     * <p>
     * This implementation resets the size, but does not reduce the internal storage array.
     * <p>
     * The collection/map will have a zero size after this method completes.
     */
    public void clear() {
        size = 0;
    }

    /**
     * Checks whether this collection contains a specified primitive value.
     * <p>
     * This implementation accesses the internal storage array directly.
     *
     * @param value  the value to search for
     * @return <code>true</code> if the value is found
     */
    public boolean contains(long value) {
        for (int i = 0; i < size; i++) {
            if (data[i] == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an array of primitive values to this collection at a specified index.
     *
     * @param index  the index to add at
     * @param values  the values to add to this collection
     * @return <code>true</code> if this collection was modified by this method call
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public boolean addAll(int index, long[] values) {
        checkAddModifiable();
        checkIndex(index);
        if (values == null || values.length == 0) {
            return false;
        }
        int len = values.length;
        ensureCapacity(size + len);
        System.arraycopy(data, index, data, index + len, size - index);
        System.arraycopy(values, 0, data, index, len);
        size += len;
        return true;
    }

    //-----------------------------------------------------------------------
    /**
     * Are the add methods supported.
     *
     * @return <code>true</code>
     */
    protected boolean isAddModifiable() {
        return true;
    }

    /**
     * Are the remove methods supported.
     *
     * @return <code>true</code>
     */
    protected boolean isRemoveModifiable() {
        return true;
    }

    /**
     * Are the remove methods supported.
     *
     * @return <code>true</code>
     */
    protected boolean isSetModifiable() {
        return true;
    }

    /**
     * Checks whether the object can currently be modified.
     *
     * @return <code>true</code>
     */
    public boolean isModifiable() {
        return true;
    }

    /**
     * Clone implementation that calls Object clone().
     * 
     * @return the clone
     */
    public Object clone() {
        ArrayLongList cloned = (ArrayLongList) super.clone();
        cloned.data = (long[]) data.clone();
        return cloned;
    }

    /**
     * Copies data from this collection into the specified array.
     * This method is pre-validated.
     * 
     * @param fromIndex  the index to start from
     * @param dest  the destination array
     * @param destIndex  the destination start index
     * @param size  the number of items to copy
     */
    protected void arrayCopy(int fromIndex, long[] dest, int destIndex, int size) {
        System.arraycopy(data, fromIndex, dest, destIndex, size);
    }

    // Internal implementation
    //-----------------------------------------------------------------------
    /**
     * Ensures that the internal storage array has at least the specified size.
     * 
     * @param capacity  the amount to expand to
     */
    protected void ensureCapacity(int capacity) {
        int len = data.length;
        if (capacity <= len) {
            return;
        }
        int newLen = len * GROWTH_FACTOR_MULTIPLIER / GROWTH_FACTOR_DIVISOR;
        if (newLen < capacity) {
            newLen = capacity;
        }
        if (newLen < MIN_GROWTH_SIZE) {
            newLen = MIN_GROWTH_SIZE;
        }
        long[] newArray = new long[newLen];
        System.arraycopy(data, 0, newArray, 0, len);
        data = newArray;
    }

}
