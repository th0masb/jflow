/**
 *
 */
package xawd.jflow.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ThomasB
 *
 */
public final class StringUtils {

	private StringUtils() {
	}

	public static List<String> getAllMatches(final String source, final Pattern pattern)
	{
		final Matcher patternMatcher = pattern.matcher(source);
		final List<String> allMatches = new ArrayList<>();
		while (patternMatcher.find()) {
			allMatches.add(patternMatcher.group());
		}
		return allMatches;
	}
}
