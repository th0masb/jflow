/**
 *
 */
package xawd.jflow.utilities;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.Flow;
import xawd.jflow.iterators.factories.Iterate;


/**
 * Static methods for operations on strings, e.g. regex pattern matching.
 * 
 * @author t
 */
public class Strings
{
	private Strings()
	{
	}

	/**
	 * Efficiently concatenates a sequence of strings end to end.
	 * 
	 * @param source
	 *            The source of the strings which we want to concatenate.
	 * 
	 * @return The concatenated string.
	 */
	public static String concat(Flow<String> source)
	{
		return source.fold(new StringBuilder(), (b, s) -> b.append(s)).toString();
	}

	/**
	 * Efficiently concatenates a sequence of strings end to end.
	 * 
	 * @param source
	 *            The source of the strings which we want to concatenate.
	 * 
	 * @return The concatenated string.
	 */
	public static String concat(String... source)
	{
		return concat(Iterate.over(source));
	}

	/**
	 * Efficiently concatenates a sequence of strings end to end.
	 * 
	 * @param source
	 *            The source of the strings which we want to concatenate.
	 * 
	 * @return The concatenated string.
	 */
	public static String concat(Collection<String> source)
	{
		return concat(Iterate.over(source));
	}

	/**
	 * Builds a lazy Flow over all the matches of a provided regular expression in
	 * the given source string. Escaping backslashes are required in the regex.
	 * 
	 * @param source
	 *            The string in which to search for the regular expression.
	 * @param regex
	 *            The regular expression to search for.
	 * 
	 * @return A Flow over all matches.
	 */
	public static Flow<String> allMatches(String source, String regex)
	{
		return allMatches(source, Pattern.compile(regex));
	}

	/**
	 * Builds a lazy Flow over all the matches of a provided regular expression
	 * pattern in the given source string.
	 * 
	 * @param source
	 *            The string in which to search for the regular expression.
	 * @param pattern
	 *            The regular expression to search for.
	 * 
	 * @return A Flow over all matches.
	 */
	public static Flow<String> allMatches(String source, Pattern pattern)
	{
		return new AbstractFlow<String>(OptionalInt.empty()) {
			Matcher matcher = pattern.matcher(source);
			String current;

			@Override
			public boolean hasNext()
			{
				if (current != null) {
					return true;
				}
				else {
					if (matcher.find()) {
						current = matcher.group();
						return true;
					}
					else {
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
				}
				else {
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
	 * @param source
	 *            The string to search in.
	 * @param pattern
	 *            The regular expression to search for.
	 * 
	 * @return The first match if there is one, nothing otherwise.
	 */
	public static Optional<String> firstMatch(String source, Pattern pattern)
	{
		return allMatches(source, pattern).safeNext();
	}

	/**
	 * Find the first match of a regular expression in a given string. Escaping
	 * backslashes are required in the regex.
	 * 
	 * @param source
	 *            The string to search in.
	 * @param regex
	 *            The regular expression to search for.
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
	 * @param source
	 *            The string to search in.
	 * @param regex
	 *            The regular expression to search for.
	 * 
	 * @return The last match if there is one, nothing otherwise.
	 */
	public static Optional<String> lastMatch(String source, String regex)
	{
		return allMatches(source, regex).last();
	}

	/**
	 * Checks whether a regular expression matches anywhere in a string.
	 * 
	 * @param source
	 *            The string to search in.
	 * @param regex
	 *            The regular expression to search for.
	 * 
	 * @return True if there is a match, false otherwise.
	 */
	public static boolean matchesAnywhere(String source, String regex)
	{
		return firstMatch(source, regex).isPresent();
	}
}
