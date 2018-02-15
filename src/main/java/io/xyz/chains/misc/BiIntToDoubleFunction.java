/**
 *
 */
package io.xyz.chains.misc;

import java.util.function.DoubleUnaryOperator;

/**
 * @author t
 *
 */
public interface BiIntToDoubleFunction {

	double map(int i, int j);

	default BiIntToDoubleFunction compose(final DoubleUnaryOperator f) {
		return (i, j) -> f.applyAsDouble(map(i, j));
	}
}
