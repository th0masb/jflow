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
package org.joda.primitives;

/**
 * Interface that is shared between Collection and Map implementations.
 * 
 * @author Stephen Colebourne
 * @version $Id: PrimitiveCollectable.java,v 1.4 2006/03/27 22:42:11 scolebourne Exp $
 * @since 1.0
 * @param <E>  the primitive type
 */
public interface PrimitiveCollectable<E> {

    // Mandatory operations
    //-----------------------------------------------------------------------
    /**
     * Gets the number of elements in this collection/map.
     * <p>
     * If this collection contains more than <code>Integer.MAX_VALUE</code>
     * elements, <code>Integer.MAX_VALUE</code> is returned.
     * 
     * @return the size of the collection/map
     */
    int size();

    /**
     * Checks whether the collection/map currently has no elements.
     *
     * @return <code>true</code> if has a size of zero
     */
    boolean isEmpty();

    /**
     * Optimizes the implementation after initialization.
     * <p>
     * The exact nature of the optimization is undefined and is implementation specific.
     * A standard optimization is to trim the internal storage array to the size.
     * An implementation may choose to do nothing, but it should NOT throw an
     * UnsupportedOperationException.
     */
    void optimize();

    /**
     * Checks whether the collection/map can currently be modified.
     *
     * @return <code>true</code> if the collection/map allows some kind of modification
     */
    boolean isModifiable();

    /**
     * Clones the object, returning an independent copy.
     * <p>
     * If the implementation is immutable, the object may be returned unaltered.
     * 
     * @return a newly cloned object, not null
     */
    Object clone();

    // Optional operations
    //-----------------------------------------------------------------------
    /**
     * Clears the collection/map of all elements (optional operation).
     * <p>
     * The collection/map will have a zero size after this method completes.
     * This method is optional, throwing an UnsupportedOperationException if the
     * collection/map cannot be cleared.
     *
     * @throws UnsupportedOperationException if method not supported by this collection
     */
    void clear();

}
