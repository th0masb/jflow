/**
 *
 */
package io.xyz.common.generators;

/**
 * @author t
 *
 */
public abstract class AbstractGenerator implements BaseGenerator {

	private final int rangeBound;

	/**
	 *
	 */
	public AbstractGenerator(final int rangeBound) {
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
