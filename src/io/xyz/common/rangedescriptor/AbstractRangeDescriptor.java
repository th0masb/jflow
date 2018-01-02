/**
 *
 */
package io.xyz.common.rangedescriptor;

import java.util.function.IntSupplier;

/**
 * @author t
 *
 */
public abstract class AbstractRangeDescriptor implements IntSupplier {

	private final int rangeBound;

	/**
	 *
	 */
	public AbstractRangeDescriptor(final int rangeBound) {
		this.rangeBound = rangeBound;
	}

	public int rangeBound() {
		return rangeBound;
	}

	@Override
	public int getAsInt() {
		return rangeBound;
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		// TODO Auto-generated method stub

	}
}
