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

import java.util.Collection;
import java.util.Iterator;

import org.joda.primitives.collection.IntCollection;

/**
 * Provides utility methods and constants for <code>int</code>.
 *
 * @author Stephen Colebourne
 * @author Jason Tiscione
 * @version CODE GENERATED
 * @since 1.0
 */
public class IntUtils {
	// This file is CODE GENERATED. Do not change manually.

	/**
	 * Immutable empty array.
	 */
	public static final int[] EMPTY_INT_ARRAY = new int[0];

	/**
	 * Constructor that should not usually be used.
	 */
	public IntUtils() {
		super();
	}

	/**
	 * Wraps an <code>int</code> with an Object wrapper.
	 *
	 * @param value  the primitive value
	 * @return the Object wrapper
	 */
	public static Integer toObject(int value) {
		return new Integer(value);
	}

	/**
	 * Unwraps the <code>Integer</code> to retrieve the primitive <code>int</code>.
	 *
	 * @param value  the Object wrapper, must not be null
	 * @return the primitive value
	 * @throws NullPointerException if the value if null
	 * @throws ClassCastException if the object is not <code>Integer</code>
	 */
	public static int toPrimitive(Object value) {
		return ((Integer) value).intValue();
	}

	/**
	 * Unwraps a <code>Collection</code> to retrieve the primitive <code>int</code>.
	 *
	 * @param coll  the Collection of Integer, must not be null
	 * @return the primitive value array
	 * @throws NullPointerException if the collection if null
	 * @throws ClassCastException if any object is not <code>Integer</code>
	 */
	public static int[] toPrimitiveArray(Collection<?> coll) {
		if (coll instanceof IntCollection) {
			return ((IntCollection) coll).toIntArray();
		}
		final int[] result = new int[coll.size()];
		int i = 0;
		for (final Iterator<?> it = coll.iterator(); it.hasNext(); i++) {
			final Integer value = (Integer) it.next();
			result[i] = value.intValue();
		}
		return result;
	}

}
