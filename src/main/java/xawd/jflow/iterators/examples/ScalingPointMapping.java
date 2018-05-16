/**
 *
 */
package xawd.jflow.iterators.examples;

/**
 * Preserves ratio of distances over the whole space. If |p - q| = d
 * then there exists a positive constant c such that |T(p) - T(q)| = c*d
 * for all points p and q. Examples are rotations, scaling, translations etc.
 *
 * @author ThomasB
 */
public interface ScalingPointMapping extends PointMapping
{
	double getDistanceScalingFactor();
}
