/**
 * 
 */
package com.github.maumay.jflow.test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.function.Function;

import com.github.maumay.jflow.impl.AbstractIterator;
import com.github.maumay.jflow.impl.IteratorOwnershipException;

/**
 * @author thomasb
 *
 */
public abstract class AbstractAdapterTest extends AbstractListBuilder implements FiniteIteratorTest
{
	protected final <I extends AbstractIterator> void assertAdaptionRemovesOwnership(I iterator,
			Function<I, ? extends AbstractIterator> adapter)
	{
		// Can't check empty iterators
		if (iterator.hasNext()) {
			adapter.apply(iterator);
			assertThrows(IteratorOwnershipException.class, iterator::skip);
			assertThrows(IteratorOwnershipException.class, () -> next(iterator));
		}
	}
}
