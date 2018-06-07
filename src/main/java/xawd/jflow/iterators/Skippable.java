/**
 *
 */
package xawd.jflow.iterators;

/**
 * Abstraction of a process which can be 'skipped' in some way. For example during iteration
 * of a sequence we may which to skip an element.
 *
 * @author t
 */
@FunctionalInterface
public interface Skippable
{
	void skip();
}
