package com.github.maumay.jflow.exp;

import com.github.maumay.jflow.iterator.Iter;
import com.github.maumay.jflow.iterator.collector.IteratorCollector2;

import java.util.Iterator;

class Bounds
{
	double x, y, w, h;

	public Bounds(double x, double y, double w, double h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public static Bounds points1(Iterator<Point> ps) {
		throw new RuntimeException();
	}

	public static Bounds points2(Iterator<? extends AbstractPoint> ps) {
	    throw new RuntimeException();
	}

	public static Bounds strings(Iterator<? extends String> xs) {
		throw new RuntimeException();
	}

	//public static String convert(Integer )

	public static void main(String[] args) {
		//Iter.args("a", "b").<Point>cast().collect(Bounds::points1);
		//IteratorCollector1<AbstractPoint, Bounds> coll1 = Bounds::points2;
		IteratorCollector2<AbstractPoint, Bounds> coll2 = Bounds::points2;
		Iter.args("a", "b").<Point>cast().collect(Bounds::points2);
		Iter.args("a", "b").collect(Bounds::strings);


	}
}
