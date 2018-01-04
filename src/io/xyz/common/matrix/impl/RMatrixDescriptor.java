/**
 * Copyright © 2018 Lhasa Limited
 * File created: 4 Jan 2018 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.matrix.impl;

import io.xyz.common.matrix.MatrixConstructor;

/**
 * @author ThomasB
 * @since 4 Jan 2018
 */
public final class RMatrixDescriptor
{
	private final MatrixConstructor contents;
	private final short colDim, rowDim;

	public RMatrixDescriptor(final MatrixConstructor contents, final short colDim, final short rowDim)
	{
		this.contents = contents;
		this.colDim = colDim;
		this.rowDim = rowDim;
	}

	public double elementAt(final int i, final int j) {
		return contents.map(i, j);
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