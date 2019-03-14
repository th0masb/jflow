/**
 * 
 */
package com.github.maumay.jflow.iterators.bindingbehaviour;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.TreeSet;
import java.util.Vector;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author thomasb
 *
 */
class CollectionIteratorBindingTest
{
	@ParameterizedTest
	@MethodSource("emptyCollectionSupplier")
	void testConcurrentModificationExceptionIsThrownForNonEmptyCollection(Collection<String> xs)
	{
		assertTrue(xs.size() == 0);
		String element = "1";
		xs.add(element);
		Iterator<?> iter1 = xs.iterator();
		xs.remove(element);
		assertThrows(ConcurrentModificationException.class, () -> iter1.next());

		xs.add(element);
		Iterator<?> iter2 = xs.iterator();
		xs.add("2");
		assertThrows(ConcurrentModificationException.class, () -> iter2.next());
	}

	static Stream<Arguments> emptyCollectionSupplier()
	{
		return Stream.of(Arguments.of(new ArrayList<String>()),
				Arguments.of(new LinkedList<String>()), Arguments.of(new Vector<String>()),
				Arguments.of(new Stack<String>()), Arguments.of(new HashSet<String>()),
				Arguments.of(new TreeSet<String>()));
	}

	@ParameterizedTest
	@MethodSource("emptyCollectionSupplier")
	void testEmptyCollections(Collection<String> xs)
	{
		assertTrue(xs.size() == 0);
		Iterator<?> iter = xs.iterator();
		xs.add("");
		try {
			iter.next();
			fail();
		} catch (ConcurrentModificationException | NoSuchElementException ex) {
		}
	}
}
