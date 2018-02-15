/**
 *
 */
package io.xyz.chains.misc;


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
		this(nbits);
		for (int i = 0; i < nbits; i++) {
			set(i, p.test(i));
		}
	}

	private BitArray(final int nbits) {
		super(nbits + 1);
		set(nbits, true);
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

	public static BitArray nEmptyBits(final int nbits) {
		return new BitArray(nbits);
	}

	public static void main(final String[] args) {
		final BitArray arr = BitArray.nEmptyBits(10);
//		System.out.println(len(arr));
	}
}
