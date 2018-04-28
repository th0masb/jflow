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
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.joda.primitives.DoubleUtils;
import org.joda.primitives.collection.impl.AbstractDoubleCollection;
import org.joda.primitives.iterator.DoubleIterator;
import org.joda.primitives.list.DoubleList;
import org.joda.primitives.listiterator.DoubleListIterator;

/**
 * Abstract base class for lists of primitive <code>double</code> elements.
 * <p>
 * This class implements {@link java.util.Collection Collection} allowing
 * seamless integration with other APIs.
 * <p>
 * The <code>get(int)</code> and <code>size()</code> methods must be
 * implemented by subclases.
 * To make the subclass modifiable, the <code>add(int, double)</code>,
 * <code>removeIndex(int)</code> and set(int, double) must also be implemented.
 * Subclasses may override other methods to increase efficiency.
 *
 * @author Stephen Colebourne
 * @author Rodney Waldhoff
 * @author Jason Tiscione
 * @author Grzegorz Rozniecki
 * @version CODE GENERATED
 * @since 1.0
 */
public abstract class AbstractDoubleList extends AbstractDoubleCollection implements DoubleList {
	// This file is CODE GENERATED. Do not change manually.

	/**
	 * Constructor.
	 */
	protected AbstractDoubleList() {
		super();
	}

	// DoubleList methods
	//-----------------------------------------------------------------------
	/**
	 * Gets an iterator over this list.
	 *
	 * @return an iterator over this list, not null
	 */
	@Override
	public DoubleListIterator iterator() {
		return listIterator(0);
	}

	/**
	 * Gets a list iterator over this list.
	 * <p>
	 * This implementation uses <code>doubleListIterator(int)</code>.
	 *
	 * @return an iterator over this list, not null
	 */
	@Override
	public DoubleListIterator listIterator() {
		return listIterator(0);
	}

	/**
	 * Gets a list iterator over this list from a start index.
	 *
	 * @param index  the index to start from
	 * @return an iterator over this list, not null
	 * @throws IndexOutOfBoundsException if the index is invalid
	 */
	@Override
	public DoubleListIterator listIterator(int index) {
		checkIndex(index);
		return new PListIterator(this, index);
	}

	/**
	 * Gets the first primitive value.
	 *
	 * @return value at index zero
	 * @throws IndexOutOfBoundsException if the size is zero
	 */
	@Override
	public double firstDouble() {
		return getDouble(0);
	}

	/**
	 * Gets the last primitive value.
	 *
	 * @return value at index <code>size() - 1</code>
	 * @throws IndexOutOfBoundsException if the size is zero
	 */
	@Override
	public double lastDouble() {
		return getDouble(size() - 1);
	}

	/**
	 * Checks whether this collection contains a specified primitive value.
	 * <p>
	 * This implementation uses <code>getDouble(int)</code>.
	 *
	 * @param value  the value to search for
	 * @return <code>true</code> if the value is found
	 */
	@Override
	public boolean contains(double value) {
		for (int i = 0, isize = size(); i < isize; i++) {
			if (getDouble(i) == value) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets the first index of the specified primitive value.
	 * <p>
	 * This implementation uses <code>indexof(double, int)</code>.
	 *
	 * @param value  the value to search for
	 * @return the zero-based index, or <code>-1</code> if not found
	 */
	@Override
	public int indexOf(double value) {
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
	public int indexOf(double value, int fromIndexInclusive) {
		if (fromIndexInclusive < 0) {
			fromIndexInclusive = 0;
		}
		for (int i = fromIndexInclusive, isize = size(); i < isize; i++) {
			if (getDouble(i) == value) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Gets the last index of the specified primitive value.
	 * <p>
	 * This implementation uses <code>lastIndexof(double, int)</code>.
	 *
	 * @param value  the value to search for
	 * @return the zero-based index, or <code>-1</code> if not found
	 */
	@Override
	public int lastIndexOf(double value) {
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
	public int lastIndexOf(double value, int fromIndexInclusive) {
		if (fromIndexInclusive >= size()) {
			fromIndexInclusive = size() - 1;
		}
		for (int i = fromIndexInclusive; i >= 0; i--) {
			if (getDouble(i) == value) {
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
	public double[] toDoubleArray(int fromIndexInclusive, int toIndexExclusive) {
		checkRange(fromIndexInclusive, toIndexExclusive);

		if (fromIndexInclusive == toIndexExclusive) {
			return DoubleUtils.EMPTY_DOUBLE_ARRAY;
		}
		final int size = toIndexExclusive - fromIndexInclusive;
		final double[] result = new double[size];
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
	public DoubleList subList(int fromIndexInclusive, int toIndexExclusive) {
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
	 * This implementation uses <code>add(int, double)</code>.
	 *
	 * @param value  the value to add to this collection
	 * @return <code>true</code> if this collection was modified by this method call
	 * @throws IllegalArgumentException if value is rejected by this collection
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	@Override
	public boolean add(double value) {
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
	public boolean add(int index, double value) {
		throw new UnsupportedOperationException("List does not support add");
	}

	/**
	 * Adds an array of primitive values to this list at an index (optional operation).
	 * <p>
	 * This implementation uses <code>addAll(int, double)</code>.
	 *
	 * @param values  the values to add to this collection, null treated as empty array
	 * @return <code>true</code> if this list was modified by this method call
	 * @throws IndexOutOfBoundsException if the index is invalid
	 * @throws IllegalArgumentException if value is rejected by this collection
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	@Override
	public boolean addAll(double[] values) {
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
	public boolean addAll(int index, double[] values) {
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
	public double removeDoubleAt(int index) {
		throw new UnsupportedOperationException("List does not support remove");
	}

	/**
	 * Removes the first occurrence of a primitive value from the list (optional operation).
	 * <p>
	 * This implementation uses <code>get(int)</code> and <code>removeDoubleAt(int)</code>.
	 *
	 * @param value  the value to remove
	 * @return the primitive value previously at this index
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	public boolean removeDouble(double value) {
		checkRemoveModifiable();
		for (int i = 0, isize = size(); i < isize; i++) {
			if (getDouble(i) == value) {
				removeDoubleAt(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Removes a range of values from the list (optional operation).
	 * <p>
	 * This implementation uses <code>removeDoubleAt(int)</code>.
	 *
	 * @param fromIndexInclusive  the start of the range to remove, inclusive
	 * @param toIndexExclusive  the end of the range to remove, exclusive
	 * @return <code>true</code> if the collection was modified
	 * @throws IndexOutOfBoundsException if the index is invalid
	 * @throws UnsupportedOperationException if remove is not supported
	 */
	@Override
	public boolean removeRange(int fromIndexInclusive, int toIndexExclusive) {
		checkRemoveModifiable();
		checkRange(fromIndexInclusive, toIndexExclusive);
		if (fromIndexInclusive == toIndexExclusive) {
			return false;
		}
		for (int i = size() - 1; i >= 0; i--) {
			removeDoubleAt(i);
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
	public double set(int index, double value) {
		throw new UnsupportedOperationException("List does not support set");
	}

	// List methods
	//-----------------------------------------------------------------------
	/**
	 * Gets the <code>Double</code> value at the specified index.
	 *
	 * @param index  the index to get from
	 * @return value at the index
	 * @throws IndexOutOfBoundsException if the index is invalid
	 */
	@Override
	public Double get(int index) {
		return DoubleUtils.toObject(getDouble(index));
	}

	/**
	 * Gets the first <code>Double</code> value.
	 *
	 * @return value at index zero or null if the size is zero
	 */
	@Override
	public Double first() {
		if (size() == 0) {
			return null;
		}
		return get(0);
	}

	/**
	 * Gets the last <code>Double</code> value.
	 *
	 * @return value at index <code>size() - 1</code> or null if the size is zero
	 */
	@Override
	public Double last() {
		if (size() == 0) {
			return null;
		}
		return get(size() - 1);
	}

	/**
	 * Gets the first index of the specified <code>Double</code> value.
	 *
	 * @param value  the value to search for
	 * @return the zero-based index, or <code>-1</code> if not found
	 * @throws NullPointerException if the value if null
	 * @throws ClassCastException if the object is not <code>Double</code>
	 */
	@Override
	public int indexOf(Object value) {
		return indexOf(DoubleUtils.toPrimitive(value));
	}

	/**
	 * Gets the first index of the specified <code>Double</code> value from an index.
	 * <p>
	 * This method follows the conventions of <code>String</code> in that a
	 * negative index is treated as zero, and an index greater than the list
	 * size will simply return <code>-1</code>.
	 *
	 * @param value  the value to search for
	 * @param fromIndexInclusive  the index to start searching from, inclusive
	 * @return the zero-based index, or <code>-1</code> if not found
	 * @throws NullPointerException if the value if null
	 * @throws ClassCastException if the object is not <code>Double</code>
	 */
	public int indexOf(Object value, int fromIndexInclusive) {
		return indexOf(DoubleUtils.toPrimitive(value), fromIndexInclusive);
	}

	/**
	 * Gets the last index of the specified <code>Double</code> value.
	 *
	 * @param value  the value to search for
	 * @return the zero-based index, or <code>-1</code> if not found
	 * @throws NullPointerException if the value if null
	 * @throws ClassCastException if the object is not <code>Double</code>
	 */
	@Override
	public int lastIndexOf(Object value) {
		return lastIndexOf(DoubleUtils.toPrimitive(value));
	}

	/**
	 * Gets the first index of the specified <code>Double</code> value from an index.
	 * <p>
	 * This method follows the conventions of <code>String</code> in that an
	 * index greater than the list size will start searching at the list size,
	 * and a negative index simply returns <code>-1</code>.
	 *
	 * @param value  the value to search for
	 * @param fromIndexInclusive  the index to start searching from, inclusive
	 * @return the zero-based index, or <code>-1</code> if not found
	 * @throws NullPointerException if the value if null
	 * @throws ClassCastException if the object is not <code>Double</code>
	 */
	public int lastIndexOf(Object value, int fromIndexInclusive) {
		return lastIndexOf(DoubleUtils.toPrimitive(value), fromIndexInclusive);
	}

	/**
	 * Adds the <code>Double</code> value to this collection (optional operation).
	 * <p>
	 * This method is optional, throwing an UnsupportedOperationException if the
	 * collection cannot be added to.
	 *
	 * @param value  the value to add to this collection
	 * @return <code>true</code> if this collection was modified by this method call
	 * @throws IllegalArgumentException if value is rejected by this collection
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	@Override
	public boolean add(Double value) {
		checkAddModifiable();
		return add(size(), DoubleUtils.toPrimitive(value));
	}

	/**
	 * Adds the <code>Double</code> value to this list at an index (optional operation).
	 * <p>
	 * This method is optional, throwing an UnsupportedOperationException if the
	 * collection cannot be added to.
	 *
	 * @param index  the index to add at
	 * @param value  the value to add to this collection
	 * @throws IndexOutOfBoundsException if the index is invalid
	 * @throws ClassCastException if the object is not <code>Double</code>
	 * @throws IllegalArgumentException if value is rejected by this collection
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	@Override
	public void add(int index, Double value) {
		checkAddModifiable();
		checkIndex(index);
		add(index, DoubleUtils.toPrimitive(value));
	}

	/**
	 * Adds an array of <code>Double</code> values to this list at an index (optional operation).
	 * <p>
	 * This method is optional, throwing an UnsupportedOperationException if the
	 * collection cannot be added to.
	 *
	 * @param index  the index to add at
	 * @param coll  the values to add to this collection
	 * @return <code>true</code> if this list was modified by this method call
	 * @throws IndexOutOfBoundsException if the index is invalid
	 * @throws ClassCastException if any object is not <code>Double</code>
	 * @throws IllegalArgumentException if value is rejected by this collection
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	@Override
	public boolean addAll(int index, Collection<? extends Double> coll) {
		checkAddModifiable();
		checkIndex(index);
		return addAll(index, DoubleUtils.toPrimitiveArray(coll));
	}

	/**
	 * Removes a primitive value by index from the list (optional operation).
	 * <p>
	 * This implementation uses <code>removeDoubleAt(int)</code>.
	 *
	 * @deprecated This method should only be used when working with List and
	 *  not when working with DoubleList - use <code>removeDoubleAt(int)</code>
	 * @param index  the index to remove from
	 * @return the primitive value previously at this index
	 * @throws IndexOutOfBoundsException if the index is invalid
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	@Override
	@Deprecated
	public Double remove(int index) {
		checkRemoveModifiable();
		return DoubleUtils.toObject(removeDoubleAt(index));
	}

	/**
	 * Sets the <code>Double</code> value at a specified index.
	 * <p>
	 * This implementation uses <code>set(int, double)</code>.
	 *
	 * @param index  the index to set
	 * @param value  the value to store
	 * @return the previous value at the index
	 * @throws IndexOutOfBoundsException if the index is invalid
	 * @throws IllegalArgumentException if value is rejected by this collection
	 * @throws UnsupportedOperationException if not supported by this collection
	 */
	@Override
	public Double set(int index, Double value) {
		checkSetModifiable();
		checkIndexExists(index);
		return DoubleUtils.toObject(set(index, DoubleUtils.toPrimitive(value)));
	}

	//-----------------------------------------------------------------------
	/**
	 * Compares this list to another as per the contract of <code>List</code>.
	 *
	 * @param obj  the object to compare to
	 * @return <code>true</code> if the lists are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof DoubleList) {
			final DoubleList other = (DoubleList) obj;
			if (size() != other.size()) {
				return false;
			}
			final DoubleIterator it1 = listIterator();
			final DoubleIterator it2 = other.listIterator();
			while (it1.hasNext() && it2.hasNext()) {
				if (it1.nextDouble() != it2.nextDouble()) {
					return false;
				}
			}
			return true;
		} else if (obj instanceof List<?>) {
			final List<?> other = (List<?>) obj;
			if (size() != other.size()) {
				return false;
			}
			final DoubleIterator it1 = listIterator();
			final Iterator<?> it2 = other.listIterator();
			while (it1.hasNext() && it2.hasNext()) {
				final Object next = it2.next();
				if (isToPrimitivePossible(next) == false) {
					return false;
				}
				if (it1.nextDouble() != toPrimitive(next)) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gets the hashCode of this list as per the contract of <code>List</code>.
	 *
	 * @return the hash code for this list
	 */
	@Override
	public int hashCode() {
		int hashCode = 1;
		final Iterator<Double> it = iterator();
		while (it.hasNext()) {
			final Object obj = it.next();
			hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
		}
		return hashCode;
	}

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
	protected void arrayCopy(int fromIndex, double[] dest, int destIndex, int size) {
		for (int i = 0; i < size; i++) {
			dest[i + destIndex] = getDouble(i + fromIndex);
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

	//-----------------------------------------------------------------------
	/**
	 * List iterator.
	 */
	protected static class PListIterator implements DoubleListIterator {

		private final AbstractDoubleList iList;
		private final int iStart;
		private int iCursor;
		private int iLastIndex = -1;

		protected PListIterator(AbstractDoubleList list, int start) {
			super();
			this.iList = list;
			this.iStart = start;
			this.iCursor = start;
		}

		//-----------------------------------------------------------------------
		@Override
		public boolean hasNext() {
			return (iCursor < iList.size());
		}

		@Override
		public double nextDouble() {
			if (hasNext() == false) {
				throw new NoSuchElementException("No more elements available");
			}
			iLastIndex = iCursor;
			return iList.getDouble(iCursor++);
		}

		@Override
		public Double next() {
			return iList.toObject(nextDouble());
		}

		@Override
		public int nextIndex() {
			return iCursor;
		}

		//-----------------------------------------------------------------------
		@Override
		public boolean hasPrevious() {
			return (iCursor > 0);
		}

		@Override
		public double previousDouble() {
			if (hasPrevious() == false) {
				throw new NoSuchElementException("No more elements available");
			}
			iLastIndex = --iCursor;
			return iList.getDouble(iCursor);
		}

		@Override
		public Double previous() {
			return iList.toObject(previousDouble());
		}

		@Override
		public int previousIndex() {
			return iCursor - 1;
		}

		//-----------------------------------------------------------------------
		@Override
		public void remove() {
			iList.checkRemoveModifiable();
			if (iLastIndex == -1) {
				throw new IllegalStateException("Element cannot be removed");
			}
			iList.removeDoubleAt(iLastIndex);
			iCursor = iLastIndex;
			iLastIndex = -1;
		}

		@Override
		public void add(double value) {
			iList.checkAddModifiable();
			try {
				iList.add(iCursor++, value);
				iLastIndex = -1;
			} catch (final IndexOutOfBoundsException ex) {
				throw new ConcurrentModificationException("The underlying list was modified");
			}
		}

		@Override
		public void add(Double obj) {
			iList.checkAddModifiable();
			add(iList.toPrimitive(obj));
		}

		@Override
		public void set(double value) {
			iList.checkSetModifiable();
			if (iLastIndex == -1) {
				throw new IllegalStateException("Element cannot be set");
			}
			try {
				iList.set(iLastIndex, value);
			} catch (final IndexOutOfBoundsException ex) {
				throw new ConcurrentModificationException("The underlying list was modified");
			}
		}

		@Override
		public void set(Double obj) {
			iList.checkSetModifiable();
			set(iList.toPrimitive(obj));
		}

		//-----------------------------------------------------------------------
		@Override
		public boolean isModifiable() {
			return iList.isModifiable();
		}

		@Override
		public boolean isResettable() {
			return true;
		}

		@Override
		public void reset() {
			iCursor = iStart;
		}

	}

}
