/**
 * Copyright � 2018 Lhasa Limited
 * File created: 27 Apr 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package xawd.jflow.iterables;

import xawd.jflow.Flow;

/**
 * @author ThomasB
 * @since 27 Apr 2018
 */
public interface FlowIterable<T> extends Iterable<T>
{
	Flow<T> iter();

	@Deprecated
	@Override
	default Flow<T> iterator()
	{
		return iter();
	}
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