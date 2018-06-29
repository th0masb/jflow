/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.Iterator;
import java.util.OptionalInt;
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

//	static boolean isSized(Iterator<?> x)
//	{
//		return x instanceof OptionallySized && ((OptionallySized) x).isSized();
//	}
//
//	static boolean isSized(PrimitiveIterator.OfInt x)
//	{
//		return x instanceof OptionallySized && ((OptionallySized) x).isSized();
//	}
//
//	static boolean isSized(PrimitiveIterator.OfLong x)
//	{
//		return x instanceof OptionallySized && ((OptionallySized) x).isSized();
//	}
//
//	static boolean isSized(PrimitiveIterator.OfDouble x)
//	{
//		return x instanceof OptionallySized && ((OptionallySized) x).isSized();
//	}

	static OptionalInt getSize(Iterator<?> x)
	{
		return x instanceof OptionallySized? ((OptionallySized) x).size() : OptionalInt.empty();
	}

	static OptionalInt getSize(PrimitiveIterator.OfInt x)
	{
		return x instanceof OptionallySized? ((OptionallySized) x).size() : OptionalInt.empty();
	}

	static OptionalInt getSize(PrimitiveIterator.OfLong x)
	{
		return x instanceof OptionallySized? ((OptionallySized) x).size() : OptionalInt.empty();
	}

	static OptionalInt getSize(PrimitiveIterator.OfDouble x)
	{
		return x instanceof OptionallySized? ((OptionallySized) x).size() : OptionalInt.empty();
	}

	static OptionalInt calculateNewSize(Object firstSource, final Object secondSource)
	{
		if (firstSource instanceof OptionallySized && secondSource instanceof OptionallySized) {
			final OptionallySized fsrc = (OptionallySized) firstSource, ssrc = (OptionallySized) secondSource;
			if (fsrc.isSizeKnown() && ssrc.isSizeKnown()) {
				final OptionalInt fsize = fsrc.size(), ssize = ssrc.size();
				return fsize.getAsInt() < ssize.getAsInt()? fsize : ssize;
			}
			else {
				return OptionalInt.empty();
			}
		}
		else {
			return OptionalInt.empty();
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
