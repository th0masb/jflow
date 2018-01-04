/**
 *
 */
package io.xyz.common.rangedescriptor;

/**
 * @author t
 *
 */
public abstract class AbstractRangeDescriptor implements BaseRangeDescriptor {

	private final int rangeBound;

	/**
	 *
	 */
	public AbstractRangeDescriptor(final int rangeBound) {
		assert rangeBound > -1;
		this.rangeBound = rangeBound;
	}

	@Override
	public int rangeBound() {
		return rangeBound;
	}

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
	}
}
