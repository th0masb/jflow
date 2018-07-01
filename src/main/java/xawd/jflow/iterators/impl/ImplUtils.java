/**
 *
 */
package xawd.jflow.iterators.impl;

import java.util.Iterator;
import java.util.Objects;
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

	static OptionalInt getSize(Iterator<?> x)
	{
		x = Objects.requireNonNull(x);
		return x instanceof OptionallySized? ((OptionallySized) x).size() : OptionalInt.empty();
	}

	static OptionalInt getSize(PrimitiveIterator.OfInt x)
	{
		x = Objects.requireNonNull(x);
		return x instanceof OptionallySized? ((OptionallySized) x).size() : OptionalInt.empty();
	}

	static OptionalInt getSize(PrimitiveIterator.OfLong x)
	{
		x = Objects.requireNonNull(x);
		return x instanceof OptionallySized? ((OptionallySized) x).size() : OptionalInt.empty();
	}

	static OptionalInt getSize(PrimitiveIterator.OfDouble x)
	{
		x = Objects.requireNonNull(x);
		return x instanceof OptionallySized? ((OptionallySized) x).size() : OptionalInt.empty();
	}

	static OptionalInt calculateNewSize(Object firstSource, Object secondSource)
	{
		firstSource = Objects.requireNonNull(firstSource);
		secondSource = Objects.requireNonNull(secondSource);
		if (firstSource instanceof OptionallySized && secondSource instanceof OptionallySized) {
			final OptionallySized fsrc = (OptionallySized) firstSource, ssrc = (OptionallySized) secondSource;
			if (fsrc.sizeIsKnown() && ssrc.sizeIsKnown()) {
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
		x = Objects.requireNonNull(x);
		if (x instanceof Skippable) {
			((Skippable) x).skip();
		}
		else {
			x.next();
		}
	}

	static void skip(PrimitiveIterator.OfInt x)
	{
		x = Objects.requireNonNull(x);
		if (x instanceof Skippable) {
			((Skippable) x).skip();
		}
		else {
			x.nextInt();
		}
	}

	static void skip(PrimitiveIterator.OfLong x)
	{
		x = Objects.requireNonNull(x);
		if (x instanceof Skippable) {
			((Skippable) x).skip();
		}
		else {
			x.nextLong();
		}
	}

	static void skip(PrimitiveIterator.OfDouble x)
	{
		x = Objects.requireNonNull(x);
		if (x instanceof Skippable) {
			((Skippable) x).skip();
		}
		else {
			x.nextDouble();
		}
	}
}
