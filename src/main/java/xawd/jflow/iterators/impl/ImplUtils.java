/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.Iterator;
import java.util.PrimitiveIterator;

import xawd.jflow.iterators.OptionallySized;
import xawd.jflow.iterators.Skippable;

/**
 * @author ThomasB
 *
 */
final class ImplUtils
{
	private ImplUtils()
	{
	}

	static boolean isSized(Iterator<?> x)
	{
		return x instanceof OptionallySized && ((OptionallySized) x).isSized();
	}

	static boolean isSized(PrimitiveIterator.OfInt x)
	{
		return x instanceof OptionallySized && ((OptionallySized) x).isSized();
	}

	static boolean isSized(PrimitiveIterator.OfLong x)
	{
		return x instanceof OptionallySized && ((OptionallySized) x).isSized();
	}

	static boolean isSized(PrimitiveIterator.OfDouble x)
	{
		return x instanceof OptionallySized && ((OptionallySized) x).isSized();
	}

	static int getSize(Iterator<?> x)
	{
		return ((OptionallySized) x).size();
	}

	static int getSize(PrimitiveIterator.OfInt x)
	{
		return ((OptionallySized) x).size();
	}

	static int getSize(PrimitiveIterator.OfLong x)
	{
		return ((OptionallySized) x).size();
	}

	static int getSize(PrimitiveIterator.OfDouble x)
	{
		return ((OptionallySized) x).size();
	}

	static int calculateNewSize(Object firstSource, final Object secondSource)
	{
		if (firstSource instanceof OptionallySized && secondSource instanceof OptionallySized) {
			final OptionallySized fsrc = (OptionallySized) firstSource, ssrc = (OptionallySized) secondSource;
			if (fsrc.isSized() && ssrc.isSized()) {
				return Math.min(fsrc.size(), ssrc.size());
			}
			else {
				return -1;
			}
		}
		else {
			return -1;
		}
	}

	static void skip(Iterator<?> x)
	{
		if (x instanceof Skippable) {
			((Skippable) x).skip();
		}
		else {
			x.next();
		}
	}

	static void skip(PrimitiveIterator.OfInt x)
	{
		if (x instanceof Skippable) {
			((Skippable) x).skip();
		}
		else {
			x.nextInt();
		}
	}

	static void skip(PrimitiveIterator.OfLong x)
	{
		if (x instanceof Skippable) {
			((Skippable) x).skip();
		}
		else {
			x.nextLong();
		}
	}

	static void skip(PrimitiveIterator.OfDouble x)
	{
		if (x instanceof Skippable) {
			((Skippable) x).skip();
		}
		else {
			x.nextDouble();
		}
	}
}
