/**
 * 
 */
package com.github.maumay.jflow.utils;

import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.impl.RegexMatchIterator;
import com.github.maumay.jflow.test.AbstractSourceTest;

/**
 * @author thomasb
 *
 */
public class StringsMatchIteratorTest extends AbstractSourceTest<AbstractRichIterator<String>>
{
	@Override
	protected List<Case<AbstractRichIterator<String>>> getTestCases()
	{
		Pattern re = Pattern.compile("a[bc]d");
		Function<String, AbstractRichIterator<String>> cons = s -> new RegexMatchIterator(
				re.matcher(s));
		return list(new Case<>(() -> cons.apply("abe"), list()),
				new Case<>(() -> cons.apply("abdedyabdacbdacd"), list("abd", "abd", "acd")));
	}

}
