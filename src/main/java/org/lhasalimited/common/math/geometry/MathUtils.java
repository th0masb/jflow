package org.lhasalimited.common.math.geometry;

import java.util.Arrays;

/**
 * This class contains utility methods that relate to mathematics.
 * @author Andy
 */
public class MathUtils
{
	/**
	 * Prevents this class from being instantiated.
	 */
	private MathUtils()
	{
	}

	/**
	 * Returns the greatest common divisor of the two specified non-negative integers.  The GCD is computed
	 * with the Euclidean algorithm.
	 * @param  value1  the first value.
	 * @param  value2  the second value.
	 * @return the greatest common divisor of {@code value1} and {@code value2}.
	 * @throws IllegalArgumentException
	 *			 if either {@code value1} or {@code value2} is negative.
	 */
	public static int gcd(int value1, int value2)
	{
		if ((value1 < 0) || (value2 < 0))
			throw new IllegalArgumentException("Value out of bounds");

		while (value2 != 0)
		{
			final int temp = value1 % value2;
			value1 = value2;
			value2 = temp;
		}
		return value1;
	}

	/**
	 * Implementation of the tridiagonal matrix algorithm (Thomas algorithm) which solves systems (of a
	 * particular form) of simultaneous equations. See, for example, wikipedia.
	 *
	 * Note that the input arrays will change values
	 */
	public static double[] solveTridiagonalMatrixSystem(final double[] a, final double[] b, final double[] c, final double[] d)
	{
		// Make check on parameters.
		final int[] lens = { a.length, b.length, c.length, d.length };
		final boolean allSameLen = Arrays.stream(lens).distinct().count() == 1;
		if (!allSameLen || a[0] != 0 || c[c.length - 1] != 0)
		{
			throw new IllegalArgumentException("Invalid initial conditions:\nAll same length: " + allSameLen + "\na[0] = " + a[0] + "\nc[-1] = " + c[c.length - 1]);
		}

		final int n = a.length;
		// Perform forward coefficient modification sweep
		c[0] = c[0]/b[0];
		d[0] = d[0]/b[0];

		for (int i = 1; i < n-1; i++)
		{
			c[i] = c[i]/(b[i] - a[i]*c[i-1]);
		}
		for (int i = 1; i < n; i++)
		{
			d[i] = (d[i] - a[i]*d[i-1])/(b[i] - a[i]*c[i-1]);
		}

		// Perform back substitution to recover solution.
		final double[] solution = new double[n];
		solution[n-1] = d[n-1];
		for (int i = n-2; i >= 0; i--)
		{
			solution[i] = d[i] - c[i]*solution[i + 1];
		}
		return solution;
	}

	/**
	 * Calculates inner angle formed between sides a and b in triangle formed by sides
	 * a, b, c.
	 *
	 * @param a - length of side a
	 * @param b - length of side b
	 * @param c - length of side c
	 * @return
	 */
	public static double cosineRule(final double a, final double b, final double c)
	{
		final double cosGamma = (a*a + b*b - c*c)/(2*a*b);

		return Math.acos(cosGamma);
	}
}
