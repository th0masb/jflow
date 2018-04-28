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
package org.joda.primitives.list;

import java.util.List;

import org.joda.primitives.collection.PrimitiveCollection;

/**
 * Base interface for all primitive list interfaces.
 * <p>
 * This interface extends {@link List} allowing seamless integration with other APIs.
 * All List methods can be used, using the primitive wrapper class.
 * However, it will be <em>much</em> more efficient to use the direct primitive methods
 * in the subinterface.
 * 
 * @author Stephen Colebourne
 * @since 1.0
 * @param <E>  the primitive type
 */
public interface PrimitiveList<E> extends PrimitiveCollection<E>, List<E> {

    // Mandatory operations
    //-----------------------------------------------------------------------
    /**
     * Gets the first list value.
     *
     * @return value at index zero, or null if the size is zero
     */
    E first();

    /**
     * Gets the last list value.
     *
     * @return value at index <code>size() - 1</code> or null if the size is zero
     */
    E last();

    // Optional operations
    //-----------------------------------------------------------------------
    /**
     * Removes a range of values from the list (optional operation).
     * <p>
     * This method is optional, throwing an UnsupportedOperationException if the
     * list cannot be modified.
     *
     * @param fromIndexInclusive  the start of the range to remove, inclusive
     * @param toIndexExclusive  the end of the range to remove, exclusive
     * @return <code>true</code> if the collection was modified
     * @throws IndexOutOfBoundsException if the index is invalid
     * @throws UnsupportedOperationException if remove is not supported
     */
    boolean removeRange(int fromIndexInclusive, int toIndexExclusive);

}
