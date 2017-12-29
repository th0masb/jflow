/**
 * Copyright © 2017 Lhasa Limited
 * File created: 3 Nov 2017 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.graph.splinecurves;

import org.lhasalimited.common.math.ICurveParameterisation;

/**
 * @author ThomasB
 * @since 3 Nov 2017
 */
public interface IParameterisationConstructor
{
	ICurveParameterisation parameterise(IPolyEdge edge);
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