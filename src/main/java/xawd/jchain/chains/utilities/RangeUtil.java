package xawd.jchain.chains.utilities;


import static xawd.jchain.chains.misc.Constants.EPSILON;
import static xawd.jchain.chains.utilities.PrimitiveUtil.abs;
import static xawd.jchain.chains.utilities.PrimitiveUtil.isZero;
import static xawd.jchain.chains.utilities.PrimitiveUtil.max;
import static xawd.jchain.chains.utilities.PrimitiveUtil.signum;

import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;

import xawd.jchain.chains.Chain;
import xawd.jchain.chains.DoubleChain;
import xawd.jchain.chains.IntChain;
import xawd.jchain.chains.LongChain;
import xawd.jchain.chains.rangedfunction.RangedDoubleFunction;
import xawd.jchain.chains.rangedfunction.RangedFunction;
import xawd.jchain.chains.rangedfunction.RangedIntFunction;
import xawd.jchain.chains.rangedfunction.RangedLongFunction;

/**
 * @author ThomasB
 * @since 26 Jan 2018
 */
public final class RangeUtil
{
	private RangeUtil()
	{
	}

	/**
	 * Static constructor for an {@link IntChain}.
	 *
	 * @param f - The descriptor of the required chain.
	 * @param size - The (non-zero) length of the chain
	 * @return the required {@linkplain IntChain}
	 */
	public static RangedIntFunction range(final IntUnaryOperator f, final int size)
	{
		assert size > -1;
		return RangedIntFunction.of(f, size);
	}

	/**
	 * <h1>Function explanation:</h1><br>
	 * Let {@code x = startBound, y = endBound} and {@code I} be an interval on the real line defined by: <br>
	 * <ul>
	 * <li>{@code I = x <= y? [x, y) : (y, x]}</li>
	 * </ul>
	 * Then this function returns an {@link IntChain} representing the numbers contained in {@code I} obtained by
	 * starting at {@code x} and stepping by the specified {@code step} size.
	 * <br>
	 * <b>The step cannot be zero</b>, an assertion statement checks for this and if assertions are not enabled a div by zero
	 * exception should be thrown.
	 *
	 * <h2>Examples:</h2>
	 * <ul>
	 * <li>{@code range(0, 5, 1) ==> [0, 1, 2, 3, 4]}
	 * <li>{@code range(1, 1, k) ==> []}
	 * <li>{@code range(1, -3, 1) ==> [1]}
	 * <li>{@code range(1, -3, -1) ==> [1, 0, -1, -2]}
	 * </ul>
	 *
	 * @param startBound - The startBound as described above.
	 * @param endBound - The endBound as describe above.
	 * @param step - The step size as described above.
	 * @return an {@linkplain IntChain} describing the resulting range.
	 */
	public static RangedIntFunction range(final int startBound, final int endBound, final int step)
	{
		assert step != 0 : "Cannot have 0 step";
		final int boundDiff = endBound - startBound, stepSign = signum(step);

		if (startBound == endBound) {
			return RangedIntFunction.empty();
		}
		else if (signum(boundDiff) != stepSign) {
			return RangedIntFunction.of(i -> startBound, 1);
		}
		else {
			final int n = (int) max(Math.ceil(((double) boundDiff) / step), 1);
			return RangedIntFunction.of(i -> startBound + i * step, n);
		}
	}

	/**
	 * <h1>Function explanation:</h1><br>
	 * Let {@code x = startBound, y = endBound} and {@code I} be an interval on the real line defined by: <br>
	 * <ul>
	 * <li>{@code I = x <= y? [x, y) : (y, x]}</li>
	 * </ul>
	 * Then this function returns an {@link IntChain} representing the numbers contained in {@code I} obtained by
	 * starting at {@code x} and stepping by 1.<br>
	 *
	 * <h2>Examples:</h2>
	 * <ul>
	 * <li>{@code range(1, 1) ==> []}
	 * <li>{@code range(1, -3) ==> [1]}
	 * <li>{@code range(-1, 4) ==> [-1, 0, 1, 2, 3]}
	 * </ul>
	 *
	 * @param startBound - The startBound as described above.
	 * @param endBound - The endBound as describe above.
	 * @return an {@linkplain IntChain} describing the resulting range.
	 */
	public static RangedIntFunction range(final int startBound, final int endBound)
	{
		return range(startBound, endBound, 1);
	}

	/**
	 * <h1>Function explanation:</h1><br>
	 * Let {@code y = endBound} and {@code I} be an interval on the real line defined by: <br>
	 * <ul>
	 * <li>{@code I = 0 <= y? [0, y) : (y, 0]}</li>
	 * </ul>
	 * Then this function returns an {@link IntChain} representing the numbers contained in {@code I} obtained by
	 * starting at {@code 0} and stepping by 1.<br>
	 *
	 * <h2>Examples:</h2>
	 * <ul>
	 * <li>{@code range(1) ==> [0]}
	 * <li>{@code range(-1) ==> [0]}
	 * <li>{@code range(4) ==> [0, 1, 2, 3]}
	 * <li>{@code range(0) ==> []}
	 * </ul>
	 *
	 * @param endBound - The endBound as describe above.
	 * @return an {@linkplain IntChain} describing the resulting range.
	 */
	public static RangedIntFunction range(final int endBound)
	{
		return range(0, endBound);
	}

	/**
	 * <h1>Function explanation:</h1><br>
	 * Let {@code x = startBound, y = endBound} and {@code I} be an interval on the real line defined by: <br>
	 * <ul>
	 * <li>{@code I = x <= y? [x, y] : [y, x]}</li>
	 * </ul>
	 * Then this function returns an {@link IntChain} representing the numbers contained in {@code I} obtained by
	 * starting at {@code x} and stepping by the specified {@code step} size.
	 * <br>
	 * <b>The step cannot be zero</b>, an assertion statement checks for this and if assertions are not enabled a div by zero
	 * exception should be thrown.
	 *
	 * <h2>Examples:</h2>
	 * <ul>
	 * <li>{@code rangei(0, 5, 1) ==> [0, 1, 2, 3, 4, 5]}
	 * <li>{@code rangei(1, 1, k) ==> [1]}
	 * <li>{@code rangei(1, -3, 1) ==> [1]}
	 * <li>{@code rangei(1, -3, -1) ==> [1, 0, -1, -2, -3]}
	 * </ul>
	 *
	 * @param startBound - The startBound as described above.
	 * @param endBound - The endBound as describe above.
	 * @param step - The step size as described above.
	 * @return an {@linkplain IntChain} describing the resulting range.
	 */
	public static RangedIntFunction rangei(final int startBound, final int endBound, final int step)
	{
		final int boundDiff = endBound - startBound;
		return range(startBound, endBound + (boundDiff >= 0 ? 1 : -1), step);
	}

	/**
	 * <h1>Function explanation:</h1><br>
	 * Let {@code x = startBound, y = endBound} and {@code I} be an interval on the real line defined by: <br>
	 * <ul>
	 * <li>{@code I = x <= y? [x, y] : [y, x]}</li>
	 * </ul>
	 * Then this function returns an {@link IntChain} representing the numbers contained in {@code I} obtained by
	 * starting at {@code x} and stepping by 1.<br>
	 *
	 * <h2>Examples:</h2>
	 * <ul>
	 * <li>{@code rangei(1, 1) ==> [1]}
	 * <li>{@code rangei(1, -3) ==> [1]}
	 * <li>{@code rangei(-1, 4) ==> [-1, 0, 1, 2, 3, 4]}
	 * </ul>
	 *
	 * @param startBound - The startBound as described above.
	 * @param endBound - The endBound as describe above.
	 * @return an {@linkplain IntChain} describing the resulting range.
	 */
	public static RangedIntFunction rangei(final int startBound, final int endBound)
	{
		return rangei(startBound, endBound, 1);
	}

	/**
	 * <h1>Function explanation:</h1><br>
	 * Let {@code y = endBound} and {@code I} be an interval on the real line defined by: <br>
	 * <ul>
	 * <li>{@code I = 0 <= y? [0, y] : [y, 0]}</li>
	 * </ul>
	 * Then this function returns an {@link IntChain} representing the numbers contained in {@code I} obtained by
	 * starting at {@code 0} and stepping by 1.<br>
	 *
	 * <h2>Examples:</h2>
	 * <ul>
	 * <li>{@code range(1) ==> [0, 1]}
	 * <li>{@code range(-1) ==> [0]}
	 * <li>{@code range(4) ==> [0, 1, 2, 3]}
	 * <li>{@code range(0) ==> [0]}
	 * </ul>
	 *
	 * @param endBound - The endBound as describe above.
	 * @return an {@linkplain IntChain} describing the resulting range.
	 */
	public static RangedIntFunction rangei(final int endBound)
	{
		return rangei(0, endBound);
	}

	/**
	 * Static constructor for an {@link DoubleChain}.
	 *
	 * @param f - The descriptor of the required chain.
	 * @param size - The (non-zero) length of the chain
	 * @return the required {@linkplain DoubleChain}
	 */
	public static RangedDoubleFunction doubleRange(final IntToDoubleFunction f, final int size)
	{
		assert size > -1;
		return RangedDoubleFunction.of(f, size);
	}

	/**
	 * <h1>Function explanation:</h1><br>
	 * Let {@code x = startBound, y = endBound} and {@code I} be an interval on the real line defined by: <br>
	 * <ul>
	 * <li>{@code I = x <= y? [x, y) : (y, x]}</li>
	 * </ul>
	 * Then this function returns an {@link DoubleChain} representing the numbers contained in {@code I} obtained by
	 * starting at {@code x} and stepping by the specified {@code step} size.
	 * <br>
	 * <b>The step cannot be zero.</b><br>
	 * <b>Be wary of imprecision of double arithmetic when using this function. </b>
	 *
	 * <h2>Examples:</h2>
	 * <ul>
	 * <li>{@code doubleRange(0, 0.5, 0.1) ==> [0, 0.1, 0.2, 0.3, 0.4]}
	 * <li>{@code doubleRange(1, 1, k) ==> []}
	 * <li>{@code doubleRange(1, -3, 1) ==> [1]}
	 * <li>{@code doubleRange(1, -3, -1) ==> [1, 0, -1, -2]}
	 * </ul>
	 *
	 * @throws IllegalArgumentException - If the step is too close to zero.
	 * @param startBound - The startBound as described above.
	 * @param endBound - The endBound as describe above.
	 * @param step - The step size as described above.
	 * @return an {@linkplain DoubleChain} describing the resulting range.
	 */
	public static RangedDoubleFunction doubleRange(final double startBound, final double endBound, final double step)
	{
		if (isZero(step)) {
			throw new IllegalArgumentException("Cannot have 0 step");
		}
		final double boundDiff = endBound - startBound, stepSign = signum(step);
		if (abs(endBound - startBound) < EPSILON) {
			return RangedDoubleFunction.empty();
		}
		else if (signum(boundDiff) != stepSign) {
			return RangedDoubleFunction.of(i -> startBound, 1);
		}
		else {
			final int n = max((int) Math.ceil(boundDiff / step), 1);
			return RangedDoubleFunction.of(i -> startBound + i * step, n);
		}
	}

	/**
	 * <h1>Function explanation:</h1><br>
	 * Let {@code x = startBound, y = endBound} and {@code I} be an interval on the real line defined by: <br>
	 * <ul>
	 * <li>{@code I = x <= y? [x, y) : (y, x]}</li>
	 * </ul>
	 * Then this function returns an {@link DoubleChain} representing the numbers contained in {@code I} obtained by
	 * starting at {@code x} and stepping by 1.<br>
	 * <b>Be wary of imprecision of double arithmetic when using this function. </b>
	 *
	 * <h2>Examples:</h2>
	 * <ul>
	 * <li>{@code doubleRange(1, 1) ==> []}
	 * <li>{@code doubleRange(1, -3) ==> [1]}
	 * <li>{@code doubleRange(-1, 4) ==> [-1, 0, 1, 2, 3]}
	 * </ul>
	 *
	 * @param startBound - The startBound as described above.
	 * @param endBound - The endBound as describe above.
	 * @return an {@linkplain DoubleChain} describing the resulting range.
	 */
	public static RangedDoubleFunction doubleRange(final double startBound, final double endBound)
	{
		return doubleRange(startBound, endBound, 1);
	}

	/**
	 * <h1>Function explanation:</h1><br>
	 * Let {@code y = endBound} and {@code I} be an interval on the real line defined by: <br>
	 * <ul>
	 * <li>{@code I = 0 <= y? [0, y) : (y, 0]}</li>
	 * </ul>
	 * Then this function returns an {@link DoubleChain} representing the numbers contained in {@code I} obtained by
	 * starting at {@code 0} and stepping by 1.<br>
	 * <b>Be wary of imprecision of double arithmetic when using this function. </b>
	 *
	 * <h2>Examples:</h2>
	 * <ul>
	 * <li>{@code doubleRange(1) ==> [0]}
	 * <li>{@code doubleRange(-1) ==> [0]}
	 * <li>{@code doubleRange(4) ==> [0, 1, 2, 3]}
	 * <li>{@code doubleRange(0) ==> []}
	 * </ul>
	 *
	 * @param endBound - The endBound as describe above.
	 * @return an {@linkplain DoubleChain} describing the resulting range.
	 */
	public static RangedDoubleFunction doubleRange(final double endBound)
	{
		return doubleRange(0, endBound);
	}

	/**
	 * Static constructor for an {@link LongChain}.
	 *
	 * @param f - The descriptor of the required chain.
	 * @param size - The (non-zero) length of the chain
	 * @return the required {@linkplain LongChain}
	 */
	public static RangedLongFunction longRange(final IntToLongFunction f, final int size)
	{
		assert size > -1;
		return RangedLongFunction.of(f, size);
	}

	/**
	 * Static constructor for an {@link Chain}.
	 *
	 * @param f - The descriptor of the required chain.
	 * @param size - The (non-zero) length of the chain
	 * @return the required {@linkplain Chain}
	 */
	public static <T> RangedFunction<T> objRange(final IntFunction<T> f, final int size)
	{
		assert size > -1;
		return RangedFunction.of(f, size);
	}
}