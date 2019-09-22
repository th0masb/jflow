/**
 *
 */
package com.github.maumay.jflow.iterator;

import java.util.function.IntUnaryOperator;

/**
 * A strictly monotonically increasing function whose domain and range is the
 * set of natural numbers.
 *
 * @author thomasb
 */
@FunctionalInterface
public interface IteratorSlicer extends IntUnaryOperator
{
}
