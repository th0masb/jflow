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
package org.joda.primitives.collection.impl;

import org.joda.primitives.PrimitiveCollectable;

/**
 * Abstract base class for collections of primitive elements.
 *
 * @author Stephen Colebourne
 * @since 1.0
 * @param <E>  the primitive type
 */
public abstract class AbstractPrimitiveCollectable<E> implements PrimitiveCollectable<E> {

    /**
     * Constructor.
     */
    protected AbstractPrimitiveCollectable() {
        super();
    }

    // PrimitiveCollectable
    //-----------------------------------------------------------------------
    /**
     * Optimizes the implementation after initialization.
     * <p>
     * This implementation does nothing.
     */
    public void optimize() {
        // do nothing
    }

    /**
     * Checks whether the object can currently be modified.
     * <p>
     * This implementation returns false.
     *
     * @return <code>false</code>
     */
    public boolean isModifiable() {
        return false;
    }

    /**
     * Checks if the collection is empty.
     *
     * @return true if empty
     */
    public boolean isEmpty() {
        return (size() == 0);
    }

    /**
     * Clone implementation that calls Object clone().
     * 
     * @return the clone
     */
    public Object clone() {
        try {
            return super.clone();
        } catch (Exception ex) {
            throw new UnsupportedOperationException("Clone not supported");
        }
    }

}
