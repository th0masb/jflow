/**
 *
 */
package com.github.maumay.jflow.iterators.bindingbehaviour;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author thomasb
 *
 */
class CollectionIteratorBindingTest
{
    @ParameterizedTest
    @MethodSource("emptyCollectionSupplier")
    void testConcurrentModificationExceptionIsThrownForNonEmptyCollection(
            Collection<String> xs)
    {
        assertTrue(xs.size() == 0);
        String element = "1";
        xs.add(element);
        Iterator<?> iter1 = xs.iterator();
        xs.remove(element);
        assertThrows(ConcurrentModificationException.class, iter1::next);

        xs.add(element);
        Iterator<?> iter2 = xs.iterator();
        xs.add("2");
        assertThrows(ConcurrentModificationException.class, iter2::next);
    }

    @ParameterizedTest
    @MethodSource("emptyCollectionSupplier")
    void testEmptyCollections(Collection<String> xs)
    {
        assertTrue(xs.size() == 0);
        Iterator<?> iter = xs.iterator();
        xs.add("");
        assertThrows(RuntimeException.class, iter::next);
    }

    static Stream<Arguments> emptyCollectionSupplier()
    {
        return Stream.of(Arguments.of(new ArrayList<String>()),
                Arguments.of(new LinkedList<String>()),
                Arguments.of(new Vector<String>()),
                Arguments.of(new Stack<String>()),
                Arguments.of(new HashSet<String>()),
                Arguments.of(new TreeSet<String>()));
    }

}
