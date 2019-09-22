/**
 *
 */
package com.github.maumay.jflow.exp;

import com.github.maumay.jflow.iterator.Iter;
import com.github.maumay.jflow.vec.Vec;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.abs;

/**
 * @author thomasb
 *
 */
public class Geometry
{
    static interface Shape
    {
    }

    static class Square implements Shape
    {
    }

    static class Circle implements Shape
    {
    }

    static class Triangle implements Shape
    {
    }

    public static void main(String[] args)
    {
        double x = abs(9);
        Point a = new Point(0, 0), b = new Point(1, 1), c = new Point(1, 0);

        // Want to get the bounds of these points...

        // What if I have a lazy sequence of points? Say a Stream

        UnaryOperator<Point> translateUpwards = p -> new Point(p.x, p.y + 1);

        computeBounds(
                () -> Stream.of(a, b, c).map(translateUpwards).iterator());
        computeBounds(Iter.args(a, b, c).map(translateUpwards).lift());

        List<Triangle> triangles = new ArrayList<>();
        List<Circle> circles = new ArrayList<>();
        List<Square> squares = new ArrayList<>();

        List<Shape> combined = Stream.of(triangles, circles, squares)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        Vec<Triangle> triangles2 = Vec.empty();
        Vec<Circle> circles2 = Vec.empty();
        Vec<Square> squares2 = Vec.empty();

        Vec<Shape> combined2 = triangles2.iter().<Shape>cast()
                .chain(circles2.iter(), squares2.iter()).toVec();
    }

    static Bounds computeBounds(Iterable<Point> points)
    {
        double minx = Double.POSITIVE_INFINITY, maxx = Double.NEGATIVE_INFINITY;
        double miny = Double.POSITIVE_INFINITY, maxy = Double.NEGATIVE_INFINITY;
        for (Point p : points) {
            minx = Math.min(minx, p.x);
            maxx = Math.max(maxx, p.x);
            miny = Math.min(miny, p.y);
            maxy = Math.max(maxy, p.y);
        }
        return new Bounds(minx, miny, maxx - minx, maxy - miny);
    }
}
