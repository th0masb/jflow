package io.xyz.chains.utilities;


import static io.xyz.chains.utilities.CollectionUtil.asList;
import static io.xyz.chains.utilities.CollectionUtil.len;

import java.util.List;

import io.xyz.chains.Chain;


/**
 * Untested and unsupported
 *
 * @author ThomasB
 * @since 30 Jan 2018
 */
public final class StringUtil
{
	private StringUtil() {}

	//	//	/**
	//	* @return The String xs[0] + delimiter + xs[1] + ... + delimiter + xs[n-1]
	//			*/
	public static String concat(final String delimiter, final List<String> xs)
	{
		final StringBuilder sb = new StringBuilder();
		final int length = len(xs);
		for (int i = 0; i < length; i++) {
			sb.append(xs.get(i));
			if (i < length - 1) {
				sb.append(delimiter);
			}
		}
		return sb.toString();
	}

	/**
	 * @return The String: xs[0] + delimiter + xs[1] + ... + delimiter + xs[n-1]
	 */
	public static String concat(final String delimiter, final Chain<String> xs)
	{
		final StringBuilder sb = new StringBuilder();
		final int length = len(xs);
		for (int i = 0; i < length; i++) {
			sb.append(xs.elementAt(i));
			if (i < length - 1) {
				sb.append(delimiter);
			}
		}
		return sb.toString();
	}

	public static String concat(final List<String> xs)
	{
		return concat("", xs);
	}

	public static String concat(final String delimiter, final String[] xs)
	{
		return concat(delimiter, asList(xs));
	}

	public static String concat(final String... xs)
	{
		return concat("", asList(xs));
	}
}