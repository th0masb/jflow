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
package org.joda.primitives.collection;

import java.util.Collection;

import org.joda.primitives.PrimitiveCollectable;

/**
 * Base interface for all primitive collection interfaces.
 * <p>
 * This interface extends {@link java.util.Collection Collection} allowing seamless
 * integration with other APIs.
 * All Collection methods can be used, using the primitive wrapper class.
 * However, it will be <em>much</em> more efficient to use the direct primitive methods
 * in the subinterface.
 * 
 * @author Stephen Colebourne
 * @since 1.0
 * @param <E>  the primitive type
 */
public interface PrimitiveCollection<E> extends PrimitiveCollectable<E>, Collection<E> {

    // Mandatory operations
    //-----------------------------------------------------------------------
    /**
     * Checks if this collection contains any of the values in the specified collection.
     * If the specified collection is empty, <code>false</code> is returned.
     *
     * @param values  the values to search for, null treated as empty collection
     * @return <code>true</code> if at least one of the values is found
     */
    boolean containsAny(Collection<?> values);

}
