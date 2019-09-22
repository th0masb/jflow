/**
 *
 */
package com.github.maumay.jflow.exp;

import com.github.maumay.jflow.iterable.DoubleIterable;
import com.github.maumay.jflow.iterator.DoubleIterator;
import com.github.maumay.jflow.iterator.Iter;

import static java.lang.Math.abs;

/**
 * @author thomasb
 *
 */
public class QuartileRange implements DoubleIterable
{
    double min, lq, median, uq, max;

    @Override
    public DoubleIterator iter()
    {
        return Iter.doubles(min, lq, median, uq, max);
    }

    public boolean equalWithinTolerance(QuartileRange other, double tolerance)
    {
        return iter().zip(other.iter())
                .all(pair -> abs(pair._1 - pair._2) < tolerance);
    }
}
