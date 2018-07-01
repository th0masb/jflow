/**
 *
 */
package xawd.jflow.utilities;

import static xawd.jflow.utilities.CollectionUtil.tail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import xawd.jflow.collections.FlowList;
import xawd.jflow.collections.impl.DelegatingFlowList;

/**
 * @author t
 *
 */
public class StringUtils {

	private StringUtils() {}

	public static FlowList<String> getAllMatches(final String source, final String regex)
	{
		return getAllMatches(source, Pattern.compile(regex));
	}

	public static FlowList<String> getAllMatches(final String source, final Pattern pattern)
	{
		final Matcher patternMatcher = pattern.matcher(source);
		final List<String> matches = new ArrayList<>();
		while (patternMatcher.find()) {
			matches.add(patternMatcher.group());
		}
		return new DelegatingFlowList<>(matches);
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

	public static Optional<String> findLastMatch(String source, String regex)
	{
		final FlowList<String> allMatches = getAllMatches(source, regex);
		return allMatches.isEmpty()? Optional.empty() : Optionals.of(tail(allMatches));
	}

	public static boolean matchesAnywhere(final String source, final String regex)
	{
		return findFirstMatch(source, regex).isPresent();
	}
}
