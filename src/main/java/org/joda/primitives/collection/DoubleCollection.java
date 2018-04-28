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

import org.joda.primitives.iterable.DoubleIterable;
import org.joda.primitives.iterator.DoubleIterator;

import xawd.jflow.iterables.DoubleFlowIterable;

/**
 * Defines a collection of primitive <code>double</code> values.
 * <p>
 * This interface extends {@link java.util.Collection Collection} allowing
 * seamless integration with other APIs.
 * All Collection methods can be used, using the primitive wrapper class {@link Double}.
 * However, it will be <em>much</em> more efficient to use the methods defined here.
 *
 * @author Stephen Colebourne
 * @author Jason Tiscione
 * @version CODE GENERATED
 * @since 1.0
 */
public interface DoubleCollection extends PrimitiveCollection<Double>, DoubleIterable, DoubleFlowIterable {
	// This file is CODE GENERATED. Do not change manually.

	// Mandatory operations
	//-----------------------------------------------------------------------


	/**
	 * Gets an iterator over this collection capable of accessing the primitive values.
	 *
	 * @return an iterator over this collection, not null
	 */
	@Deprecated
	@Override
	DoubleIterator iterator();
	// This method is specified here, despite being in {@code DoubleIterable},
	// due to compiler bug 6487370.

	/**
	 * Checks whether this collection contains a specified primitive value.
	 *
	 * @param value  the value to search for
	 * @return <code>true</code> if the value is found
	 */
	boolean contains(double value);

	/**
	 * Checks if this collection contains all of the values in the specified array.
	 * If the specified array is empty, <code>true</code> is returned.
	 *
	 * @param values  the values to search for, null treated as empty array
	 * @return <code>true</code> if all of the values are found
	 */
	boolean containsAll(double[] values);

	/**
	 * Checks if this collection contains all of the values in the specified collection.
	 * If the specified collection is empty, <code>true</code> is returned.
	 *
	 * @param values  the values to search for, null treated as empty collection
	 * @return <code>true</code> if all of the values are found
	 */
	boolean containsAll(DoubleCollection values);

	/**
	 * Checks if this collection contains any of the values in the specified array.
	 * If the specified array is empty, <code>false</code> is returned.
	 *
	 * @param values  the values to search for, null treated as empty array
	 * @return <code>true</code> if at least one of the values is found
	 */
	boolean containsAny(double[] values);

	/**
	 * Checks if this collection contains any of the values in the specified collection.
	 * If the specified collection is empty, <code>false</code> is returned.
	 *
	 * @param coll  the values to search for, null treated as empty collection
	 * @return <code>true</code> if at least one of the values is found
	 */
	boolean containsAny(DoubleCollection coll);

	/**
	 * Gets the elements of this collection as an array.
	 *
	 * @return a new array containing a copy of the elements of this collection
	 */
	double[] toDoubleArray();

	/**
	 * Copies the elements of this collection into an array at a specified position.
	 * Previous values in the array are overwritten.
	 * <p>
	 * If the array specified is null a new array is created.
	 * If the array specified is large enough, it will be modified.
	 * If the array is not large enough, a new array will be created containing the
	 * values from the specified array before the startIndex plus those from this collection.
	 *
	 * @param array  the array to add the elements to, null creates new array
	 * @param startIndex  the position in the array to start setting elements
	 * @return the array with the populated collection, not null
	 * @throws IndexOutOfBoundsException if the index is negative
	 */
	double[] toDoubleArray(double[] array, int startIndex);

	// Optional operations
	//-----------------------------------------------------------------------
	/**
	 * Adds a primitive value to this collection (optional operation).
	 * <p>
	 * This method is optional, throwing an UnsupportedOperationException if the
	 * collection cannot be added to.
	 *
	 * @param value  the value to add to this collection
	 * @return <code>true</code> if this collection was modified by this method call
	 * @throws IllegalArgumentException if value is rejected by this collection
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	boolean add(double value);

	/**
	 * Adds an array of primitive values to this collection (optional operation).
	 * <p>
	 * This method is optional, throwing an UnsupportedOperationException if the
	 * collection cannot be added to.
	 *
	 * @param values  the values to add to this collection, null treated as empty array
	 * @return <code>true</code> if this collection was modified by this method call
	 * @throws IllegalArgumentException if a value is rejected by this collection
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	boolean addAll(double[] values);

	/**
	 * Adds a collection of primitive values to this collection (optional operation).
	 * <p>
	 * This method is optional, throwing an UnsupportedOperationException if the
	 * collection cannot be added to.
	 *
	 * @param values  the values to add to this collection, null treated as empty collection
	 * @return <code>true</code> if this collection was modified by this method call
	 * @throws IllegalArgumentException if a value is rejected by this collection
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	boolean addAll(DoubleCollection values);

	/**
	 * Removes the first occurrence of the specified primitive value from this collection
	 * (optional operation).
	 * <p>
	 * This method is optional, throwing an UnsupportedOperationException if the
	 * collection cannot be removed from.
	 *
	 * @param value  the value to remove
	 * @return <code>true</code> if this collection was modified by this method call
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	boolean removeFirst(double value);

	/**
	 * Removes all occurrences of the specified primitive value from this collection
	 * (optional operation).
	 * <p>
	 * This method is optional, throwing an UnsupportedOperationException if the
	 * collection cannot be removed from.
	 *
	 * @param value  the value to remove
	 * @return <code>true</code> if this collection was modified by this method call
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	boolean removeAll(double value);

	/**
	 * Removes all occurrences from this collection of each primitive in the specified array
	 * (optional operation).
	 * <p>
	 * This method is optional, throwing an UnsupportedOperationException if the
	 * collection cannot be removed from.
	 *
	 * @param values  the values to remove from this collection, null treated as empty array
	 * @return <code>true</code> if this collection was modified by this method call
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	boolean removeAll(double[] values);

	/**
	 * Removes all occurrences from this collection of each primitive in the specified collection
	 * (optional operation).
	 * <p>
	 * This method is optional, throwing an UnsupportedOperationException if the
	 * collection cannot be removed from.
	 *
	 * @param values  the values to remove from this collection, null treated as empty collection
	 * @return <code>true</code> if this collection was modified by this method call
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	boolean removeAll(DoubleCollection values);

	/**
	 * Retains each element of this collection that is present in the specified array
	 * removing all other values (optional operation).
	 * <p>
	 * This method is optional, throwing an UnsupportedOperationException if the
	 * collection cannot be removed from.
	 *
	 * @param values  the values to retain in this collection, null treated as empty array
	 * @return <code>true</code> if this collection was modified by this method call
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	boolean retainAll(double[] values);

	/**
	 * Retains each element of this collection that is present in the specified collection
	 * removing all other values (optional operation).
	 * <p>
	 * This method is optional, throwing an UnsupportedOperationException if the
	 * collection cannot be removed from.
	 *
	 * @param values  the values to retain in this collection, null treated as empty collection
	 * @return <code>true</code> if this collection was modified by this method call
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	boolean retainAll(DoubleCollection values);

}
