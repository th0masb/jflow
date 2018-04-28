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
import org.joda.primitives.collection.impl.AbstractLongCollection;
import org.joda.primitives.list.LongList;

/**
 * Abstract base class for lists of primitive <code>long</code> elements.
 * <p>
 * This class implements {@link java.util.Collection Collection} allowing
 * seamless integration with other APIs.
 * <p>
 * The <code>get(int)</code> and <code>size()</code> methods must be
 * implemented by subclases.
 * To make the subclass modifiable, the <code>add(int, long)</code>,
 * <code>removeIndex(int)</code> and set(int, long) must also be implemented.
 * Subclasses may override other methods to increase efficiency.
 *
 * @author Stephen Colebourne
 * @author Rodney Waldhoff
 * @author Jason Tiscione
 * @author Grzegorz Rozniecki
 * @version CODE GENERATED
 * @since 1.0
 */
public abstract class AbstractLongList extends AbstractLongCollection implements LongList {
	// This file is CODE GENERATED. Do not change manually.

	/**
	 * Constructor.
	 */
	protected AbstractLongList() {
		super();
	}

	// LongList methods
	//-----------------------------------------------------------------------

	/**
	 * Gets the first primitive value.
	 *
	 * @return value at index zero
	 * @throws IndexOutOfBoundsException if the size is zero
	 */
	@Override
	public long firstLong() {
		return getLong(0);
	}

	/**
	 * Gets the last primitive value.
	 *
	 * @return value at index <code>size() - 1</code>
	 * @throws IndexOutOfBoundsException if the size is zero
	 */
	@Override
	public long lastLong() {
		return getLong(size() - 1);
	}

	/**
	 * Checks whether this collection contains a specified primitive value.
	 * <p>
	 * This implementation uses <code>getLong(int)</code>.
	 *
	 * @param value  the value to search for
	 * @return <code>true</code> if the value is found
	 */
	@Override
	public boolean contains(long value) {
		for (int i = 0, isize = size(); i < isize; i++) {
			if (getLong(i) == value) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the first index of the specified primitive value.
	 * <p>
	 * This implementation uses <code>indexof(long, int)</code>.
	 *
	 * @param value  the value to search for
	 * @return the zero-based index, or <code>-1</code> if not found
	 */
	@Override
	public int indexOf(long value) {
		return indexOf(value, 0);
	}

	/**
	 * Gets the first index of the specified primitive value from an index.
	 * <p>
	 * This method follows the conventions of <code>String</code> in that a
	 * negative index is treated as zero, and an index greater than the list
	 * size will simply return <code>-1</code>.
	 * <p>
	 * This implementation uses <code>get(int)</code>.
	 *
	 * @param value  the value to search for
	 * @param fromIndexInclusive  the index to start searching from, inclusive
	 * @return the zero-based index, or <code>-1</code> if not found
	 */
	@Override
	public int indexOf(long value, int fromIndexInclusive) {
		if (fromIndexInclusive < 0) {
			fromIndexInclusive = 0;
		}
		for (int i = fromIndexInclusive, isize = size(); i < isize; i++) {
			if (getLong(i) == value) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Gets the last index of the specified primitive value.
	 * <p>
	 * This implementation uses <code>lastIndexof(long, int)</code>.
	 *
	 * @param value  the value to search for
	 * @return the zero-based index, or <code>-1</code> if not found
	 */
	@Override
	public int lastIndexOf(long value) {
		return lastIndexOf(value, size());
	}

	/**
	 * Gets the first index of the specified primitive value from an index.
	 * <p>
	 * This method follows the conventions of <code>String</code> in that an
	 * index greater than the list size will start searching at the list size,
	 * and a negative index simply returns <code>-1</code>.
	 * <p>
	 * This implementation uses <code>get(int)</code>.
	 *
	 * @param value  the value to search for
	 * @param fromIndexInclusive  the index to start searching from, inclusive
	 * @return the zero-based index, or <code>-1</code> if not found
	 */
	@Override
	public int lastIndexOf(long value, int fromIndexInclusive) {
		if (fromIndexInclusive >= size()) {
			fromIndexInclusive = size() - 1;
		}
		for (int i = fromIndexInclusive; i >= 0; i--) {
			if (getLong(i) == value) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Gets a range of elements as an array.
	 *
	 * @param fromIndexInclusive  the index to start from, inclusive
	 * @param toIndexExclusive  the index to end at, exclusive
	 * @return a new array containing a copy of the range of elements, not null
	 * @throws IndexOutOfBoundsException if either index is invalid
	 */
	@Override
	public long[] toLongArray(int fromIndexInclusive, int toIndexExclusive) {
		checkRange(fromIndexInclusive, toIndexExclusive);

		if (fromIndexInclusive == toIndexExclusive) {
			return LongUtils.EMPTY_LONG_ARRAY;
		}
		final int size = toIndexExclusive - fromIndexInclusive;
		final long[] result = new long[size];
		arrayCopy(fromIndexInclusive, result, 0, size);
		return result;
	}

	/**
	 * Gets a range view of part of this list.
	 * <p>
	 * This method allows operations to work on a range within the greater list.
	 * Changes made to the either object will affect the other.
	 *
	 * @param fromIndexInclusive  the index to start from, inclusive
	 * @param toIndexExclusive  the index to end at, exclusive
	 * @return always throws
	 * @throws UnsupportedOperationException always
	 * @deprecated Not implemented yet.
	 */
	@Override
	@Deprecated
	public LongList subList(int fromIndexInclusive, int toIndexExclusive) {
		throw new UnsupportedOperationException("Not implemented yet"); // TODO
	}

	/**
	 * Clears the listof all elements (optional operation).
	 * <p>
	 * This implementation uses <code>removeRange(int, int)</code>.
	 *
	 * @throws UnsupportedOperationException if method not supported by this collection
	 */
	@Override
	public void clear() {
		removeRange(0, size());
	}

	/**
	 * Adds a primitive value to this collection (optional operation).
	 * <p>
	 * This implementation uses <code>add(int, long)</code>.
	 *
	 * @param value  the value to add to this collection
	 * @return <code>true</code> if this collection was modified by this method call
	 * @throws IllegalArgumentException if value is rejected by this collection
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	@Override
	public boolean add(long value) {
		checkAddModifiable();
		return add(size(), value);
	}

	/**
	 * Adds a primitive value to this list at an index (optional operation).
	 * <p>
	 * This implementation throws UnsupportedOperationException.
	 *
	 * @param index  the index to add at
	 * @param value  the value to add to this collection
	 * @return <code>true</code> if this list was modified by this method call
	 * @throws IndexOutOfBoundsException if the index is invalid
	 * @throws IllegalArgumentException if value is rejected by this collection
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	@Override
	public boolean add(int index, long value) {
		throw new UnsupportedOperationException("List does not support add");
	}

	/**
	 * Adds an array of primitive values to this list at an index (optional operation).
	 * <p>
	 * This implementation uses <code>addAll(int, long)</code>.
	 *
	 * @param values  the values to add to this collection, null treated as empty array
	 * @return <code>true</code> if this list was modified by this method call
	 * @throws IndexOutOfBoundsException if the index is invalid
	 * @throws IllegalArgumentException if value is rejected by this collection
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	@Override
	public boolean addAll(long[] values) {
		checkAddModifiable();
		return addAll(size(), values);
	}

	/**
	 * Adds an array of primitive values to this list at an index (optional operation).
	 * <p>
	 * This method is optional, throwing an UnsupportedOperationException if the
	 * collection cannot be added to.
	 *
	 * @param index  the index to add at
	 * @param values  the values to add to this collection, null treated as empty array
	 * @return <code>true</code> if this list was modified by this method call
	 * @throws IndexOutOfBoundsException if the index is invalid
	 * @throws IllegalArgumentException if value is rejected by this collection
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	@Override
	public boolean addAll(int index, long[] values) {
		checkAddModifiable();
		checkIndex(index);
		boolean changed = false;
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				changed |= add(index + i, values[i]);
			}
		}
		return changed;
	}

	/**
	 * Removes a primitive value by index from the list (optional operation).
	 * <p>
	 * This implementation throws UnsupportedOperationException.
	 *
	 * @param index  the index to remove from
	 * @return the primitive value previously at this index
	 * @throws IndexOutOfBoundsException if the index is invalid
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	@Override
	public long removeLongAt(int index) {
		throw new UnsupportedOperationException("List does not support remove");
	}

	/**
	 * Removes the first occurrence of a primitive value from the list (optional operation).
	 * <p>
	 * This implementation uses <code>get(int)</code> and <code>removeLongAt(int)</code>.
	 *
	 * @param value  the value to remove
	 * @return the primitive value previously at this index
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	public boolean removeLong(long value) {
		checkRemoveModifiable();
		for (int i = 0, isize = size(); i < isize; i++) {
			if (getLong(i) == value) {
				removeLongAt(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Removes a range of values from the list (optional operation).
	 * <p>
	 * This implementation uses <code>removeLongAt(int)</code>.
	 *
	 * @param fromIndexInclusive  the start of the range to remove, inclusive
	 * @param toIndexExclusive  the end of the range to remove, exclusive
	 * @return <code>true</code> if the collection was modified
	 * @throws IndexOutOfBoundsException if the index is invalid
	 * @throws UnsupportedOperationException if remove is not supported
	 */
	public boolean removeRange(int fromIndexInclusive, int toIndexExclusive) {
		checkRemoveModifiable();
		checkRange(fromIndexInclusive, toIndexExclusive);
		if (fromIndexInclusive == toIndexExclusive) {
			return false;
		}
		for (int i = size() - 1; i >= 0; i--) {
			removeLongAt(i);
		}
		return true;
	}

	/**
	 * Sets the primitive value at a specified index.
	 * <p>
	 * This implementation throws UnsupportedOperationException.
	 *
	 * @param index  the index to set
	 * @param value  the value to store
	 * @return the previous value at the index
	 * @throws IndexOutOfBoundsException if the index is invalid
	 * @throws IllegalArgumentException if value is rejected by this collection
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	@Override
	public long set(int index, long value) {
		throw new UnsupportedOperationException("List does not support set");
	}

	// List methods
	//-----------------------------------------------------------------------
	/**
	 * Gets the <code>Long</code> value at the specified index.
	 *
	 * @param index  the index to get from
	 * @return value at the index
	 * @throws IndexOutOfBoundsException if the index is invalid
	 */
	public Long get(int index) {
		return LongUtils.toObject(getLong(index));
	}

	/**
	 * Gets the first <code>Long</code> value.
	 *
	 * @return value at index zero or null if the size is zero
	 */
	public Long first() {
		if (size() == 0) {
			return null;
		}
		return get(0);
	}

	/**
	 * Gets the last <code>Long</code> value.
	 *
	 * @return value at index <code>size() - 1</code> or null if the size is zero
	 */
	public Long last() {
		if (size() == 0) {
			return null;
		}
		return get(size() - 1);
	}

	/**
	 * Gets the first index of the specified <code>Long</code> value.
	 *
	 * @param value  the value to search for
	 * @return the zero-based index, or <code>-1</code> if not found
	 * @throws NullPointerException if the value if null
	 * @throws ClassCastException if the object is not <code>Long</code>
	 */
	public int indexOf(Object value) {
		return indexOf(LongUtils.toPrimitive(value));
	}

	/**
	 * Gets the first index of the specified <code>Long</code> value from an index.
	 * <p>
	 * This method follows the conventions of <code>String</code> in that a
	 * negative index is treated as zero, and an index greater than the list
	 * size will simply return <code>-1</code>.
	 *
	 * @param value  the value to search for
	 * @param fromIndexInclusive  the index to start searching from, inclusive
	 * @return the zero-based index, or <code>-1</code> if not found
	 * @throws NullPointerException if the value if null
	 * @throws ClassCastException if the object is not <code>Long</code>
	 */
	public int indexOf(Object value, int fromIndexInclusive) {
		return indexOf(LongUtils.toPrimitive(value), fromIndexInclusive);
	}

	/**
	 * Gets the last index of the specified <code>Long</code> value.
	 *
	 * @param value  the value to search for
	 * @return the zero-based index, or <code>-1</code> if not found
	 * @throws NullPointerException if the value if null
	 * @throws ClassCastException if the object is not <code>Long</code>
	 */
	public int lastIndexOf(Object value) {
		return lastIndexOf(LongUtils.toPrimitive(value));
	}

	/**
	 * Gets the first index of the specified <code>Long</code> value from an index.
	 * <p>
	 * This method follows the conventions of <code>String</code> in that an
	 * index greater than the list size will start searching at the list size,
	 * and a negative index simply returns <code>-1</code>.
	 *
	 * @param value  the value to search for
	 * @param fromIndexInclusive  the index to start searching from, inclusive
	 * @return the zero-based index, or <code>-1</code> if not found
	 * @throws NullPointerException if the value if null
	 * @throws ClassCastException if the object is not <code>Long</code>
	 */
	public int lastIndexOf(Object value, int fromIndexInclusive) {
		return lastIndexOf(LongUtils.toPrimitive(value), fromIndexInclusive);
	}

	/**
	 * Adds the <code>Long</code> value to this collection (optional operation).
	 * <p>
	 * This method is optional, throwing an UnsupportedOperationException if the
	 * collection cannot be added to.
	 *
	 * @param value  the value to add to this collection
	 * @return <code>true</code> if this collection was modified by this method call
	 * @throws IllegalArgumentException if value is rejected by this collection
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	public boolean add(Long value) {
		checkAddModifiable();
		return add(size(), LongUtils.toPrimitive(value));
	}

	/**
	 * Adds the <code>Long</code> value to this list at an index (optional operation).
	 * <p>
	 * This method is optional, throwing an UnsupportedOperationException if the
	 * collection cannot be added to.
	 *
	 * @param index  the index to add at
	 * @param value  the value to add to this collection
	 * @throws IndexOutOfBoundsException if the index is invalid
	 * @throws ClassCastException if the object is not <code>Long</code>
	 * @throws IllegalArgumentException if value is rejected by this collection
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	public void add(int index, Long value) {
		checkAddModifiable();
		checkIndex(index);
		add(index, LongUtils.toPrimitive(value));
	}

	/**
	 * Adds an array of <code>Long</code> values to this list at an index (optional operation).
	 * <p>
	 * This method is optional, throwing an UnsupportedOperationException if the
	 * collection cannot be added to.
	 *
	 * @param index  the index to add at
	 * @param coll  the values to add to this collection
	 * @return <code>true</code> if this list was modified by this method call
	 * @throws IndexOutOfBoundsException if the index is invalid
	 * @throws ClassCastException if any object is not <code>Long</code>
	 * @throws IllegalArgumentException if value is rejected by this collection
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	public boolean addAll(int index, Collection<? extends Long> coll) {
		checkAddModifiable();
		checkIndex(index);
		return addAll(index, LongUtils.toPrimitiveArray(coll));
	}


	/**
	 * Sets the <code>Long</code> value at a specified index.
	 * <p>
	 * This implementation uses <code>set(int, long)</code>.
	 *
	 * @param index  the index to set
	 * @param value  the value to store
	 * @return the previous value at the index
	 * @throws IndexOutOfBoundsException if the index is invalid
	 * @throws IllegalArgumentException if value is rejected by this collection
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	public Long set(int index, Long value) {
		checkSetModifiable();
		checkIndexExists(index);
		return LongUtils.toObject(set(index, LongUtils.toPrimitive(value)));
	}

	//-----------------------------------------------------------------------

	//-----------------------------------------------------------------------
	/**
	 * Copies data from this collection into the specified array.
	 * This method is pre-validated.
	 *
	 * @param fromIndex  the index to start from
	 * @param dest  the destination array
	 * @param destIndex  the destination start index
	 * @param size  the number of items to copy
	 */
	@Override
	protected void arrayCopy(int fromIndex, long[] dest, int destIndex, int size) {
		for (int i = 0; i < size; i++) {
			dest[i + destIndex] = getLong(i + fromIndex);
		}
	}

	/**
	 * Are the set methods supported.
	 * <p>
	 * This implementation returns false.
	 *
	 * @return true if supported
	 */
	protected boolean isSetModifiable() {
		return false;
	}

	/**
	 * Is the collection modifiable in any way.
	 *
	 * @return true if supported
	 */
	@Override
	public boolean isModifiable() {
		return isAddModifiable() || isRemoveModifiable() || isSetModifiable();
	}

	/**
	 * Check whether add is suported and throw an exception.
	 */
	protected void checkSetModifiable() {
		if (isSetModifiable() == false) {
			throw new UnsupportedOperationException("Collection does not support set");
		}
	}

	/**
	 * Checks whether an index is valid or not.
	 *
	 * @param index  the index to check
	 * @throws IndexOutOfBoundsException if either index is invalid
	 */
	protected void checkIndexExists(int index) {
		if (index < 0) {
			throw new ArrayIndexOutOfBoundsException(
					"Index less than zero: " + index + " < 0");
		}
		if (index >= size()) {
			throw new ArrayIndexOutOfBoundsException(
					"Index greater than/equal to size(): " + index + " >= " + size());
		}
	}

	/**
	 * Checks whether an index is valid or not.
	 *
	 * @param index  the index to check
	 * @throws IndexOutOfBoundsException if either index is invalid
	 */
	protected void checkIndex(int index) {
		if (index < 0) {
			throw new ArrayIndexOutOfBoundsException(
					"Index less than zero: " + index + " < 0");
		}
		if (index > size()) {
			throw new ArrayIndexOutOfBoundsException(
					"Index greater than size(): " + index + " > " + size());
		}
	}

	/**
	 * Checks whether a range is valid or not.
	 *
	 * @param fromIndexInclusive  the index to start from, inclusive
	 * @param toIndexExclusive  the index to end at, exclusive
	 * @throws IndexOutOfBoundsException if either index is invalid
	 */
	protected void checkRange(int fromIndexInclusive, int toIndexExclusive) {
		if (fromIndexInclusive < 0) {
			throw new ArrayIndexOutOfBoundsException(
					"From index less than zero: " + fromIndexInclusive + " < 0");
		}
		if (toIndexExclusive > size()) {
			throw new ArrayIndexOutOfBoundsException(
					"To index greater than size(): " + toIndexExclusive + " > " + size());
		}
		if (fromIndexInclusive > toIndexExclusive) {
			throw new ArrayIndexOutOfBoundsException(
					"To index greater than from index: " + fromIndexInclusive + " > " + toIndexExclusive);
		}
	}

	@Override
	public boolean isEmpty()
	{
		return size() == 0;
	}
}
