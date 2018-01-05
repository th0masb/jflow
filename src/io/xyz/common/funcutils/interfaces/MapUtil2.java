package io.xyz.common.funcutils.interfaces;

import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntUnaryOperator;

public final class MapUtil2
{
	static <IntRange> IntRange map(final IntUnaryOperator f, final IntRangeMappable<IntRange, ?, ?, ?> toMap) {
		return toMap.apply(f);
	}

	static <DoubleRange> DoubleRange map(final DoubleUnaryOperator f, final DoubleRangeMappable<DoubleRange, ?, ?> toMap) {
		return toMap.apply(f);
	}

	static <T, TRange, R, RRange> ObjRange map(final DoubleUnaryOperator f, final DoubleRangeMappable<DoubleRange, ?, ?> toMap) {
		return toMap.apply(f);
	}

	static <IntRange, DoubleRange> IntRange mapToInt(final DoubleToIntFunction f, final DoubleRangeMappable<DoubleRange, IntRange, ?> toMap) {
		return toMap.apply1(f);
	}

	//	static <IntRange> IntRange map(final IntUnaryOperator f, final IntRangeMappable<IntRange, ?, ?> toMap) {
	//		return toMap.apply(f);
	//	}
}

/* ---------------------------------------------------------------------*
 * This software is the confidential and proprietary
 * information of Lhasa Limited
 * Granary Wharf House, 2 Canal Wharf, Leeds, LS11 5PS
 * ---
 * No part of this confidential information shall be disclosed
 * and it shall be used only in accordance with the terms of a
 * written license agreement entered into by holder of the information
 * with LHASA Ltd.
 * --------------------------------------------------------------------- */