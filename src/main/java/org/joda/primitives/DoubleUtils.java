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

import org.joda.primitives.collection.DoubleCollection;

/**
 * Provides utility methods and constants for <code>double</code>.
 *
 * @author Stephen Colebourne
 * @author Jason Tiscione
 * @version CODE GENERATED
 * @since 1.0
 */
public class DoubleUtils {
	// This file is CODE GENERATED. Do not change manually.

	/**
	 * Immutable empty array.
	 */
	public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];

	/**
	 * Constructor that should not usually be used.
	 */
	public DoubleUtils() {
		super();
	}

	/**
	 * Wraps an <code>double</code> with an Object wrapper.
	 *
	 * @param value  the primitive value
	 * @return the Object wrapper
	 */
	public static Double toObject(double value) {
		return new Double(value);
	}

	/**
	 * Unwraps the <code>Double</code> to retrieve the primitive <code>double</code>.
	 *
	 * @param value  the Object wrapper, must not be null
	 * @return the primitive value
	 * @throws NullPointerException if the value if null
	 * @throws ClassCastException if the object is not <code>Double</code>
	 */
	public static double toPrimitive(Object value) {
		return ((Double) value).doubleValue();
	}

	/**
	 * Unwraps a <code>Collection</code> to retrieve the primitive <code>double</code>.
	 *
	 * @param coll  the Collection of Double, must not be null
	 * @return the primitive value array
	 * @throws NullPointerException if the collection if null
	 * @throws ClassCastException if any object is not <code>Double</code>
	 */
	public static double[] toPrimitiveArray(Collection<?> coll) {
		if (coll instanceof DoubleCollection) {
			return ((DoubleCollection) coll).toDoubleArray();
		}
		final double[] result = new double[coll.size()];
		int i = 0;
		for (final Iterator<?> it = coll.iterator(); it.hasNext(); i++) {
			final Double value = (Double) it.next();
			result[i] = value.doubleValue();
		}
		return result;
	}

}
