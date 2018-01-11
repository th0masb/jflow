/**
 * Copyright © 2017 Lhasa Limited
 * File created: 26 Jun 2017 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.fxutils;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;

/**
 * @author ThomasB
 * @since 26 Jun 2017
 */
public class DrawingCanvas extends Group
{
	/** Canvas' we will draw on */
	private final Canvas backing, middle, overlay;

	private final FXContextBinding backingGC, middleGC, overlayGC;

	public DrawingCanvas(final int width, final int height)
	{
		backing = new Canvas(width, height);
		backingGC = new FXContextBinding(backing.getGraphicsContext2D());

		middle = new Canvas(width, height);
		middleGC = new FXContextBinding(middle.getGraphicsContext2D());

		overlay = new Canvas(width, height);
		overlayGC = new FXContextBinding(overlay.getGraphicsContext2D());

		getChildren().addAll(backing, middle, overlay);
	}

	public FXContextBinding getBackingGC()
	{
		return backingGC;
	}

	public FXContextBinding getMiddleGC()
	{
		return middleGC;
	}

	public FXContextBinding getOverlayGC()
	{
		return overlayGC;
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