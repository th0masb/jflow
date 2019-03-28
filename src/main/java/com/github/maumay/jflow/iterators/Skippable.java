/**
 *
 */
package com.github.maumay.jflow.iterators;

/**
 * An incremental process which can be 'skipped' to a point further along the
 * process ignoring any potential computation in the skipped section. For
 * example during iteration of a sequence we may which to skip forward to some
 * element further along.
 *
 * @author t
 */
@FunctionalInterface
public interface Skippable
{
	/**
	 * Instructs this process to move forward one step.
	 */
	void skip();
}
