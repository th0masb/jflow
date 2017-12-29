/**
 * 
 */
package io.xyz.common.geometry;

import static io.xyz.common.funcutils.PFunctional.combine;
import static io.xyz.common.funcutils.PFunctional.foldr;
import static io.xyz.common.funcutils.PFunctional.map;
import static java.lang.Math.sqrt;

import java.util.Arrays;
import java.util.stream.DoubleStream;

/**
 * @author t
 *
 *	R^n real point
 */
public final class RPoint 
{
	public static final RPoint ORIGIN_1D = RPoint.of(0);
	public static final RPoint ORIGIN_2D = RPoint.of(0, 0);
	public static final RPoint ORIGIN_3D = RPoint.of(0, 0, 0);
	
	private final double[] coords;
	
	private RPoint(double...ds)
	{
		coords = ds;
	}
	
	public static RPoint of(double...ds)
	{
		return new RPoint(Arrays.copyOf(ds, ds.length));
	}
	
	public double x()
	{
		return coords[0];
	}
	
	public double y()
	{
		return coords[1];
	}
	
	public double z()
	{
		return coords[2];
	}
	
	public double w()
	{
		return coords[3];
	}
	
	public int dim()
	{
		return coords.length;
	}
	
//	public IntStream dimStream()
//	{
//		return range(dim());
//	}
	
	public DoubleStream valStream()
	{
		return DoubleStream.of(coords);
	}
	
	public RPoint add(RPoint p)
	{
		return new RPoint(combine((a,b) -> a+b, coords, p.coords));
	}
	
	public RPoint subtract(RPoint p)
	{
		return new RPoint(combine((a,b) -> a-b, coords, p.coords));
	}
	
	public double magnitude()
	{
		return sqrt(foldr( (a,b) -> a+b, 0, map(x -> x*x, coords)));//sqrt(valStream().map(x -> x*x).sum());
	}
	
	public RPoint scale(final double scale)
	{
		return new RPoint(map(x -> scale*x, coords));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(coords);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RPoint other = (RPoint) obj;
		if (!Arrays.equals(coords, other.coords))
			return false;
		return true;
	}
}
