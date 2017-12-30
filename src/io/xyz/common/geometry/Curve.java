/**
 * 
 */
package io.xyz.common.geometry;

import static io.xyz.common.funcutils.PFunctional.combine;

/**
 * @author t
 *
 */
public interface Curve 
{
	RPoint map(double t);
	
	static double length(Curve c)
	{
		
	}

	static Curve straightLine(RPoint p1, RPoint p2) 
	{
		/* 
		 * We could write nicer but we can make faster. E.g
		 * 
		 * t -> p1.scale(1-t).add(p2.scale(t));
		 * t -> new RPoint(combine((x,y) -> (1-t)*x + t*y p1.coords(), p2.coords()));
		 */
		assert RPoint.dimensionsAgree(p1, p2);
		int n = p1.dim();
		
		return t -> 
		{
			double[] newCoords = new double[n];
			for (int i = 0; i < n; i++)
			{
				newCoords[i] = (1 - t)*p1.get(i) + t*p2.get(i); 
			}
			return new RPoint(newCoords);
		};
	}
	
	static Curve quadLine(RPoint p1, RPoint c, RPoint p2)
	{
		assert RPoint.dimensionsAgree(p1, c, p2);
		int n = p1.dim();
		
		return t -> 
		{
			double[] newCoords = new double[n];
			double s = 1 - t;
			for (int i = 0; i < n; i++)
			{
				newCoords[i] = s*s*p1.get(i) + 2*t*s*c.get(i) + t*t*p2.get(i); 
			}
			return new RPoint(newCoords);
		};
	}
	
	static Curve cubicLine(RPoint p1, RPoint c1, RPoint c2, RPoint p2)
	{
		assert RPoint.dimensionsAgree(p1, c1, c2, p2);
		int n = p1.dim();
		
		return t -> 
		{
			double[] newCoords = new double[n];
			double s = 1 - t;
			double a = s*s*s, b = 3*s*s*t, c = 3*s*t*t, d = t*t*t;
			for (int i = 0; i < n; i++)
			{
				newCoords[i] = a*p1.get(i) + b*c1.get(i) + c*c2.get(i) + d*p2.get(i); 
			}
			return new RPoint(newCoords);
		};
	}
}

