/**
 * Copyright © 2018 Lhasa Limited
 * File created: 10 Jan 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.fxutils;

import static io.xyz.common.funcutils.CollectionUtil.len;
import static io.xyz.common.funcutils.PredicateUtil.all;

import io.xyz.common.matrix.RPoint;
import javafx.scene.canvas.GraphicsContext;

/**
 * @author ThomasB
 * @since 10 Jan 2018
 */
public final class FXContextBinding
{
	private final GraphicsContext gc;

	public GraphicsContext getBoundContext()
	{
		return gc;
	}

	public void beginPath()
	{
		gc.beginPath();
	}

	public void moveTo(final RPoint p)
	{
		assert twoDimensional(p);
		gc.moveTo(p.x(), p.y());
	}

	public void lineTo(final RPoint p)
	{
		assert twoDimensional(p);
		gc.lineTo(p.x(), p.y());
	}

	public void quadraticTo(final RPoint control, final RPoint end)
	{
		assert twoDimensional(control, end);
		gc.quadraticCurveTo(control.x(), control.y(), end.x(), end.y());
	}

	public void cubicTo(final RPoint control1, final RPoint control2, final RPoint end)
	{
		assert twoDimensional(control1, control2, end);
	}

	public void stroke()
	{
		gc.stroke();
	}

	public void closePath()
	{
		gc.closePath();
	}

	private boolean twoDimensional(final RPoint... ps)
	{
		assert len(ps) > 0;
		return all(p -> p.dim() == 2, ps);
	}

	/**
	 *
	 */
	public FXContextBinding(final GraphicsContext gc)
	{
		this.gc = gc;
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