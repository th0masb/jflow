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

import org.joda.primitives.collection.LongCollection;

/**
 * Provides utility methods and constants for <code>long</code>.
 *
 * @author Stephen Colebourne
 * @author Jason Tiscione
 * @version CODE GENERATED
 * @since 1.0
 */
public class LongUtils {
	// This file is CODE GENERATED. Do not change manually.

	/**
	 * Immutable empty array.
	 */
	public static final long[] EMPTY_LONG_ARRAY = new long[0];

	/**
	 * Constructor that should not usually be used.
	 */
	public LongUtils() {
		super();
	}

	/**
	 * Wraps an <code>long</code> with an Object wrapper.
	 *
	 * @param value  the primitive value
	 * @return the Object wrapper
	 */
	public static Long toObject(long value) {
		return new Long(value);
	}

	/**
	 * Unwraps the <code>Long</code> to retrieve the primitive <code>long</code>.
	 *
	 * @param value  the Object wrapper, must not be null
	 * @return the primitive value
	 * @throws NullPointerException if the value if null
	 * @throws ClassCastException if the object is not <code>Long</code>
	 */
	public static long toPrimitive(Object value) {
		return ((Long) value).longValue();
	}

	/**
	 * Unwraps a <code>Collection</code> to retrieve the primitive <code>long</code>.
	 *
	 * @param coll  the Collection of Long, must not be null
	 * @return the primitive value array
	 * @throws NullPointerException if the collection if null
	 * @throws ClassCastException if any object is not <code>Long</code>
	 */
	public static long[] toPrimitiveArray(Collection<?> coll) {
		if (coll instanceof LongCollection) {
			return ((LongCollection) coll).toLongArray();
		}
		final long[] result = new long[coll.size()];
		int i = 0;
		for (final Iterator<?> it = coll.iterator(); it.hasNext(); i++) {
			final Long value = (Long) it.next();
			result[i] = value.longValue();
		}
		return result;
	}

}
