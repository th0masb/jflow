/**
 * Copyright © 2018 Lhasa Limited
 * File created: 5 Jan 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.funcutils.interfaces;

import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;

import io.xyz.common.function.F;
import io.xyz.common.function.F1;
import io.xyz.common.function.F2;

/**
 * @author ThomasB
 * @since 5 Jan 2018
 */
public interface ObjRangeMapper<T, TRange, S, SRange, DoubleRange, IntRange> extends F<Function<T, S>, SRange>, F1<ToDoubleFunction<T>, DoubleRange>, F2<ToIntFunction<T>, IntRange>
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