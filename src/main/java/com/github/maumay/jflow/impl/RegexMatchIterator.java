/**
 * 
 */
package com.github.maumay.jflow.impl;

import java.util.NoSuchElementException;
import java.util.regex.Matcher;

/**
 * @author thomasb
 */
public final class RegexMatchIterator extends AbstractRichIterator<String>
{
	private final Matcher matcher;
	private String current;

	public RegexMatchIterator(Matcher matcher)
	{
		super(LowerBound.of(0));
		this.matcher = matcher;
	}

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
	public String nextImpl()
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
	public void forwardImpl()
	{
		nextImpl();
	}
}
