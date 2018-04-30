/**
 * Copyright © 2018 Lhasa Limited
 * File created: 22 Feb 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package org.lhasalimited.common.math.geometry.splines;

import static org.lhasalimited.common.chainutilities.CollectionUtil.len;
import static org.lhasalimited.common.chainutilities.MapUtil.objMap;
import static org.lhasalimited.common.chainutilities.RangeUtil.objRange;

import java.io.IOException;
import java.util.List;

import org.lhasalimited.common.math.geometry.point.IPoint2D;
import org.lhasalimited.common.math.geometry.point.PointDTO;
import org.lhasalimited.common.serialization.IDataStreamReader;
import org.lhasalimited.common.serialization.IDataStreamSerializable;
import org.lhasalimited.common.serialization.IDataStreamWriter;

/**
 * Serializable data transfer object. We want to keep splines immutable
 *
 * @author ThomasB
 * @since 22 Feb 2018
 */
public class Spline2DTO implements IDataStreamSerializable
{
	private static final String TYPE_KEY = "types", KNOT_KEY = "knots";

	private List<SegmentType> types;
	private List<? extends List<IPoint2D>> knots;

	public Spline2DTO() {}

	public Spline2DTO(final ISpline2D spline)
	{
		types = objMap(seg -> seg.getType(), spline).toList();
		knots = objMap(seg -> seg.toList(), spline).toList();
	}

	public ISpline2D construct()
	{
		return new Spline2D(objRange(i -> types.get(i).construct(knots.get(i)), len(types)));
	}

	@Override
	public void fromDataStream(final IDataStreamReader reader) throws IOException
	{
		types = objMap(s -> SegmentType.valueOf(s), reader.readStringCollection());
		knots = objMap(dto -> dto.getPoints(), reader.readObjectCollection(KnotDTO.class));
	}

	@Override
	public void toDataStream(final IDataStreamWriter writer) throws IOException
	{
		writer.writeStrings(TYPE_KEY, objMap(x -> x.name(), types));
		writer.writeObjects(KNOT_KEY, objMap(KnotDTO::new, knots));
	}

	public static class KnotDTO implements IDataStreamSerializable
	{
		private static final String POINT_KEY = "points";
		private List<IPoint2D> points;

		public KnotDTO() {}

		public KnotDTO(final List<IPoint2D> ps)
		{
			points = ps;
		}

		public List<IPoint2D> getPoints()
		{
			return points;
		}

		@Override
		public void fromDataStream(final IDataStreamReader reader) throws IOException
		{
			points = objMap(x -> x.toPoint2D(), reader.readObjectCollection(PointDTO.class));
		}

		@Override
		public void toDataStream(final IDataStreamWriter writer) throws IOException
		{
			writer.writeObjects(POINT_KEY, objMap(p -> new PointDTO(p), points));
		}
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