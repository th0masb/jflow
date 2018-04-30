/**
 *
 */
package org.lhasalimited.common.math.geometry.point;

import java.util.function.UnaryOperator;

/**
 * @author t
 *
 */
public interface PointTransformND extends UnaryOperator<IPointND>
{
	int domainDim();

	int targetDim();

	static PointTransformND identity(final int dimension)
	{
		return new PointTransformND() {
			@Override
			public int targetDim()
			{
				return dimension;
			}

			@Override
			public int domainDim()
			{
				return dimension;
			}

			@Override
			public IPointND apply(final IPointND p)
			{
				return p;
			}
		};
	}
}
