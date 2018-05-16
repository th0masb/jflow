/**
 *
 */
package xawd.jflow.iterators.testutilities;

/**
 * Alternate between calling next and skip to test both
 *
 * need to test next(), hasNext() -> next(), hasNext() -> skip(), skip()
 *
 */
public interface IteratorTest extends ObjectIteratorTest, LongIteratorTest, IntIteratorTest, DoubleIteratorTest
{
}
