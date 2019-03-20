/**
 * 
 */
package com.github.maumay.jflow.impl;

/**
 * @author thomasb
 *
 */
public final class IteratorOwnershipException extends RuntimeException
{
	private static final long serialVersionUID = -9105879031930038528L;

	public IteratorOwnershipException()
	{
	}

	public IteratorOwnershipException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IteratorOwnershipException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public IteratorOwnershipException(String message)
	{
		super(message);
	}

	public IteratorOwnershipException(Throwable cause)
	{
		super(cause);
	}
}
