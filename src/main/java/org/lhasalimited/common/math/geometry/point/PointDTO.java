/**
 * Copyright © 2018 Lhasa Limited
 * File created: 22 Feb 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry.point;

import java.io.IOException;

import org.lhasalimited.common.serialization.IDataStreamReader;
import org.lhasalimited.common.serialization.IDataStreamSerializable;
import org.lhasalimited.common.serialization.IDataStreamWriter;

/**
 * @author ThomasB
 * @since 22 Feb 2018
 */
public class PointDTO implements IDataStreamSerializable
{
	private double[] coords;

	public PointDTO() {}

	public PointDTO(final IPointND p)
	{
		coords = p.toArray();
	}

	public IPoint2D toPoint2D()
	{
		return IPoint2D.of(coords[0], coords[1]);
	}

	@Override
	public void fromDataStream(final IDataStreamReader reader) throws IOException
	{
		coords = reader.readDoubleArray();
	}

	@Override
	public void toDataStream(final IDataStreamWriter writer) throws IOException
	{
		writer.writeDoubles("coords", coords);
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