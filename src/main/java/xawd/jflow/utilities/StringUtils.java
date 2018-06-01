/**
 *
 */
package xawd.jflow.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author t
 *
 */
public class StringUtils {

	private StringUtils() {}

	public static List<String> getAllMatches(final String source, final String regex)
	{
		return getAllMatches(source, Pattern.compile(regex));
	}

	public static List<String> getAllMatches(final String source, final Pattern pattern)
	{
		final Matcher patternMatcher = pattern.matcher(source);
		final List<String> matches = new ArrayList<>();
		while (patternMatcher.find()) {
			matches.add(patternMatcher.group());
		}
		return matches;
	}

	public static Optional<String> findFirstMatch(final String source, final Pattern pattern)
	{
		final Matcher patternMatcher = pattern.matcher(source);
		return patternMatcher.find()? Optional.of(patternMatcher.group()) : Optional.empty();
	}

	public static Optional<String> findFirstMatch(final String source, final String regex)
	{
		return findFirstMatch(source, Pattern.compile(regex));
	}

	public static boolean matchesAnywhere(final String source, final String regex)
	{
		return findFirstMatch(source, regex).isPresent();
	}
}
