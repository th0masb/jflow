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

	/**
	 *
	 */
	public StringUtils() {
		// TODO Auto-generated constructor stub
	}

	public static List<String> getAllMatches(String source, String regex)
	{
		return getAllMatches(source, Pattern.compile(regex));
	}

	public static List<String> getAllMatches(String source, Pattern pattern)
	{
		final Matcher patternMatcher = pattern.matcher(source);
		final List<String> matches = new ArrayList<>();
		while (patternMatcher.find()) {
			matches.add(patternMatcher.group());
		}
		return matches;
	}

	public static Optional<String> findFirstMatch(String source, Pattern pattern)
	{
		final Matcher patternMatcher = pattern.matcher(source);
		return patternMatcher.find()? Optional.of(patternMatcher.group()) : Optional.empty();
	}

	public static Optional<String> findFirstMatch(String source, String regex)
	{
		return findFirstMatch(source, Pattern.compile(regex));
	}
}
