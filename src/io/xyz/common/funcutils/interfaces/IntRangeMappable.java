/**
 * Copyright © 2018 Lhasa Limited
 * File created: 5 Jan 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.funcutils.interfaces;

import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntUnaryOperator;

import io.xyz.common.function.F;
import io.xyz.common.function.F1;
import io.xyz.common.function.F2;

/**
 * @author ThomasB
 * @since 5 Jan 2018
 */
public interface IntRangeMappable<IntRange, T, TRange, DoubleRange> extends F<IntUnaryOperator, IntRange>, F1<IntFunction<T>, TRange>, F2<IntToDoubleFunction, DoubleRange>
{

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