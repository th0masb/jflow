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
package org.joda.primitives.iterator;

import java.util.NoSuchElementException;

/**
 * Defines an iterator over primitive <code>double</code> values.
 * <p>
 * This interface extends {@link java.util.Iterator Iterator} allowing
 * seamless integration with other APIs.
 * 
 * @author Stephen Colebourne
 * @author Jason Tiscione
 * @version CODE GENERATED
 * @since 1.0
 */
public interface DoubleIterator extends PrimitiveIterator<Double> {
    // This file is CODE GENERATED. Do not change manually.

    // Mandatory operations
    //-----------------------------------------------------------------------
    /**
     * Gets the next value from the iterator
     *
     * @return the next available value
     * @throws NoSuchElementException if there are no more values available
     */
    double nextDouble() throws NoSuchElementException;

}
