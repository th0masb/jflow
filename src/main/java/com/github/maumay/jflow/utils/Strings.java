/**
 *
 */
package com.github.maumay.jflow.utils;

import static java.lang.Character.isWhitespace;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.regex.Pattern;

import com.github.maumay.jflow.impl.RegexMatchIterator;
import com.github.maumay.jflow.iterator.Iter;
import com.github.maumay.jflow.iterator.RichIterator;

/**
 * Static utility methods for working with strings.
 * 
 * @author t
 */
public final class Strings
{
	private Strings()
	{
	}

	/**
	 * Checks whether a string is in a trimmed state (according to
	 * {@link String#trim()}).
	 * 
	 * @param text The string to test.
	 * @return True if the string has no leading or trailing whitespace characters,
	 *         false otherwise.
	 */
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
	public static String convert(Object obj)
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
	public static String convert(int x)
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
	public static String convert(double x)
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
	public static String convert(long x)
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
	public static String convert(char x)
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
	public static String convert(int[] xs)
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
	public static String convert(double[] xs)
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
	public static String convert(long[] xs)
	{
		return Arrays.toString(xs);
	}

	/**
	 * Unified and null-safe toString function. Works for all objects, arrays and
	 * primitives.
	 *
	 * @param xs A boolean array reference.
	 * @return The String representation of the parameter array.
	 */
	public static String convert(boolean[] xs)
	{
		return Arrays.toString(xs);
	}

	/**
	 * Unified and null-safe toString function. Works for all objects, arrays and
	 * primitives.
	 *
	 * @param xs A byte array reference.
	 * @return The String representation of the parameter array.
	 */
	public static String convert(byte[] xs)
	{
		return Arrays.toString(xs);
	}

	/**
	 * Unified and null-safe toString function. Works for all objects, arrays and
	 * primitives.
	 *
	 * @param xs A short array reference.
	 * @return The String representation of the parameter array.
	 */
	public static String convert(short[] xs)
	{
		return Arrays.toString(xs);
	}

	/**
	 * Unified and null-safe toString function. Works for all objects, arrays and
	 * primitives.
	 *
	 * @param xs A float array reference.
	 * @return The String representation of the parameter array.
	 */
	public static String convert(float[] xs)
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
	public static String convert(char[] xs)
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
	public static String concat(RichIterator<String> source)
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
		return concat(Iter.args(source));
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
	 * Builds an iterator traversing over all the matches of a provided regular
	 * expression in the given source string. Escaping backslashes are required in
	 * the regex.
	 * 
	 * @param source The string in which to search for the regular expression.
	 * @param regex  The regular expression to search for.
	 * 
	 * @return An iterator traversing all regex matches.
	 */
	public static RichIterator<String> allMatches(String source, String regex)
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
	public static RichIterator<String> allMatches(String source, Pattern pattern)
	{
		return new RegexMatchIterator(pattern.matcher(source));
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
		return allMatches(source, pattern).nextOp();
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
	public static Optional<String> lastMatch(String source, Pattern regex)
	{
		Iterator<String> allMatches = allMatches(source, regex);
		if (allMatches.hasNext()) {
			String curr = allMatches.next();
			while (allMatches.hasNext()) {
				curr = allMatches.next();
			}
			return Option.of(curr);
		} else {
			return Optional.empty();
		}
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
		return lastMatch(source, Pattern.compile(regex));
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
