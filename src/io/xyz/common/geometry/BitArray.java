/**
 *
 */
package io.xyz.common.geometry;

import java.util.BitSet;
import java.util.function.IntPredicate;

/**
 * @author t
 *
 */
public class BitArray extends BitSet {

	private static final long serialVersionUID = -4612432642710116734L;

	/**
	 * @param nbits
	 */
	public BitArray(final IntPredicate p, final int nbits) {
		super(nbits + 1);
		set(nbits, true);
		for (int i = 0; i < nbits; i++) {
			set(i, p.test(i));
		}
	}

	@Override
	public int length() {
		return super.length() - 1;
	}

	@Override
	public int cardinality() {
		return super.cardinality() - 1;
	}

	public static BitArray of(final IntPredicate p, final int nbits) {
		return new BitArray(p, nbits);
	}

	public static BitArray nSetBits(final int nbits) {
		return new BitArray(i -> true, nbits);
	}
}
