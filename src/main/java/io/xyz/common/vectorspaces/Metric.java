/**
 *
 */
package io.xyz.common.vectorspaces;

import java.util.function.ToDoubleBiFunction;

/**
 * @author t
 *
 */
public interface Metric<X> extends ToDoubleBiFunction<X, X> {

}
