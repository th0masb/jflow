/**
 *
 */
package xawd.jflow.iterators.examples;

import java.util.function.DoubleBinaryOperator;

/**
 * @author t
 *
 */
public interface PointMapping
{
	DoubleBinaryOperator getX();

	DoubleBinaryOperator getY();

	default Point mapPoint(final Point input)
	{
		return new Point(
				getX().applyAsDouble(input.x, input.y),
				getY().applyAsDouble(input.x, input.y)
				);
	}

	default Spline mapSpline(final Spline input)
	{
		return input.map(this);
	}

	default Splinegon mapSplinegon(final Splinegon input)
	{
		return input.iterateOverSplines().map(this::mapSpline).build(Splinegon::new);
	}
}
