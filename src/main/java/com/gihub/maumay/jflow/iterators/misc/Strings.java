/**
 *
 */
package com.gihub.maumay.jflow.iterators.misc;

import static java.lang.Character.isWhitespace;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.maumay.jflow.iterators.AbstractEnhancedIterator;
import com.github.maumay.jflow.iterators.EnhancedIterator;
import com.github.maumay.jflow.iterators.factories.Iter;

/**
 * Static methods for operations on strings, e.g. regex pattern matching.
 * 
 * @author t
 */
public final class Strings
{
	private Strings()
	{
	}

	public static boolean isTrimmed(String text)
	{
		return text.isEmpty() || (!isWhitespace(text.charAt(0))
				&& !isWhitespace(text.charAt(text.length() - 1)));
	}

	/**
	 * Unified and null-safe toString function. Works for all objects, arrays and
	 * primitives.
	 *
	 * @param obj An object reference.
	 * @return the string representation of the parameter object.
	 */
	public static String $(Object obj)
	{
		return obj.toString();
	}

	/**
	 * Unified and null-safe toString function. Works for all objects, arrays and
	 * primitives.
	 *
	 * @param x A primitive int.
	 * @return The String representation of the parameter int.
	 */
	public static String $(int x)
	{
		return Integer.toString(x);
	}

	/**
	 * Unified and null-safe toString function. Works for all objects, arrays and
	 * primitives.
	 *
	 * @param x A primitive double.
	 * @return The String representation of the parameter double.
	 */
	public static String $(double x)
	{
		return Double.toString(x);
	}

	/**
	 * Unified and null-safe toString function. Works for all objects, arrays and
	 * primitives.
	 *
	 * @param x A primitive long.
	 * @return The String representation of the parameter long.
	 */
	public static String $(long x)
	{
		return Long.toString(x);
	}

	/**
	 * Unified and null-safe toString function. Works for all objects, arrays and
	 * primitives.
	 *
	 * @param x A primitive char.
	 * @return The String representation of the parameter char.
	 */
	public static String $(char x)
	{
		return Character.toString(x);
	}

	/**
	 * Unified and null-safe toString function. Works for all objects, arrays and
	 * primitives.
	 *
	 * @param xs An int array reference.
	 * @return The String representation of the parameter array.
	 */
	public static String $(int[] xs)
	{
		return Arrays.toString(xs);
	}

	/**
	 * Unified and null-safe toString function. Works for all objects, arrays and
	 * primitives.
	 *
	 * @param xs A double array reference.
	 * @return The String representation of the parameter array.
	 */
	public static String $(double[] xs)
	{
		return Arrays.toString(xs);
	}

	/**
	 * Unified and null-safe toString function. Works for all objects, arrays and
	 * primitives.
	 *
	 * @param xs A long array reference.
	 * @return The String representation of the parameter array.
	 */
	public static String $(long[] xs)
	{
		return Arrays.toString(xs);
	}

	/**
	 * Unified and null-safe toString function. Works for all objects, arrays and
	 * primitives.
	 *
	 * @param xs A char array reference.
	 * @return The String representation of the parameter array.
	 */
	public static String $(char[] xs)
	{
		return Arrays.toString(xs);
	}

	/**
	 * Efficiently concatenates a sequence of strings end to end.
	 * 
	 * @param source The source of the strings which we want to concatenate.
	 * 
	 * @return The concatenated string.
	 */
	public static String concat(EnhancedIterator<String> source)
	{
		return source.fold(new StringBuilder(), (b, s) -> b.append(s)).toString();
	}

	/**
	 * Efficiently concatenates a sequence of strings end to end.
	 * 
	 * @param source The source of the strings which we want to concatenate.
	 * 
	 * @return The concatenated string.
	 */
	public static String concat(String... source)
	{
		return concat(Iter.over(source));
	}

	/**
	 * Efficiently concatenates a sequence of strings end to end.
	 * 
	 * @param source The source of the strings which we want to concatenate.
	 * 
	 * @return The concatenated string.
	 */
	public static String concat(Collection<String> source)
	{
		return concat(Iter.over(source));
	}

	/**
	 * Builds a lazy Flow over all the matches of a provided regular expression in
	 * the given source string. Escaping backslashes are required in the regex.
	 * 
	 * @param source The string in which to search for the regular expression.
	 * @param regex  The regular expression to search for.
	 * 
	 * @return A Flow over all matches.
	 */
	public static EnhancedIterator<String> allMatches(String source, String regex)
	{
		return allMatches(source, Pattern.compile(regex));
	}

	/**
	 * Builds a lazy Flow over all the matches of a provided regular expression
	 * pattern in the given source string.
	 * 
	 * @param source  The string in which to search for the regular expression.
	 * @param pattern The regular expression to search for.
	 * 
	 * @return A Flow over all matches.
	 */
	public static EnhancedIterator<String> allMatches(String source, Pattern pattern)
	{
		return new AbstractEnhancedIterator<String>(OptionalInt.empty()) {
			Matcher matcher = pattern.matcher(source);
			String current;

			@Override
			public boolean hasNext()
			{
				if (current != null) {
					return true;
				} else {
					if (matcher.find()) {
						current = matcher.group();
						return true;
					} else {
						return false;
					}
				}
			}

			@Override
			public String next()
			{
				if (hasNext()) {
					String next = current;
					current = null;
					return next;
				} else {
					throw new NoSuchElementException();
				}
			}

			@Override
			public void skip()
			{
				next();
			}
		};
	}

	/**
	 * Find the first match of a regular expression in a given string.
	 * 
	 * @param source  The string to search in.
	 * @param pattern The regular expression to search for.
	 * 
	 * @return The first match if there is one, nothing otherwise.
	 */
	public static Optional<String> firstMatch(String source, Pattern pattern)
	{
		return allMatches(source, pattern).nextOption();
	}

	/**
	 * Find the first match of a regular expression in a given string. Escaping
	 * backslashes are required in the regex.
	 * 
	 * @param source The string to search in.
	 * @param regex  The regular expression to search for.
	 * 
	 * @return The first match if there is one, nothing otherwise.
	 */
	public static Optional<String> firstMatch(String source, String regex)
	{
		return firstMatch(source, Pattern.compile(regex));
	}

	/**
	 * Find the last match of a regular expression in a given string. Escaping
	 * backslashes are required in the regex.
	 * 
	 * @param source The string to search in.
	 * @param regex  The regular expression to search for.
	 * 
	 * @return The last match if there is one, nothing otherwise.
	 */
	public static Optional<String> lastMatch(String source, String regex)
	{
		Iterator<String> allMatches = allMatches(source, regex);
		if (allMatches.hasNext()) {
			String curr = allMatches.next();
			while (allMatches.hasNext()) {
				curr = allMatches.next();
			}
			return Optional.of(curr);
		} else {
			return Optional.empty();
		}
	}

	/**
	 * Checks whether a regular expression matches anywhere in a string.
	 * 
	 * @param source The string to search in.
	 * @param regex  The regular expression to search for.
	 * 
	 * @return True if there is a match, false otherwise.
	 */
	public static boolean matchesAnywhere(String source, String regex)
	{
		return firstMatch(source, regex).isPresent();
	}
}
