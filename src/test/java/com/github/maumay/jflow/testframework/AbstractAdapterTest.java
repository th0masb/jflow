/**
 * 
 */
package com.github.maumay.jflow.testframework;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.github.maumay.jflow.impl.AbstractIterator;
import com.github.maumay.jflow.impl.IteratorOwnershipException;

/**
 * @author thomasb
 *
 */
public abstract class AbstractAdapterTest implements FiniteIteratorTest
{
	@SafeVarargs
	protected final <T> List<T> list(T... ts)
	{
		return Arrays.asList(ts);
	}

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
