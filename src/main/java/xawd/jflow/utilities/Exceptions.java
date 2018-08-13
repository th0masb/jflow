/**
 * 
 */
package xawd.jflow.utilities;

/**
 * Static methods for throwing exceptions.
 * 
 * @author ThomasB
 */
public final class Exceptions
{
	private Exceptions()
	{
	}

	/**
	 * Checks a given condition is true, if it is then nothing happens otherwise a
	 * {@linkplain RuntimeException} with no message is thrown.
	 * 
	 * @param condition
	 *            The condition which is required to be true.
	 */
	public static void require(boolean condition)
	{
		require(condition, "");
	}

	/**
	 * Checks a given condition is true, if it is then nothing happens otherwise a
	 * {@linkplain RuntimeException} with the provided message is thrown.
	 * 
	 * @param condition
	 *            The condition which is required to be true.
	 * @param message
	 *            The message which should be attached to the exception if it is
	 *            thrown.
	 */
	public static void require(boolean condition, String message)
	{
		if (!condition) {
			throw new RuntimeException(message);
		}
	}

	/**
	 * Checks a given condition is true, if it is then nothing happens otherwise a
	 * {@linkplain IllegalArgumentException} with no message is thrown.
	 * 
	 * @param condition
	 *            The condition which is required to be true.
	 */
	public static void requireArgument(boolean condition)
	{
		requireArgument(condition, "");
	}

	/**
	 * Checks a given condition is true, if it is then nothing happens otherwise a
	 * {@linkplain IllegalArgumentException} with the provided message is thrown.
	 * 
	 * @param condition
	 *            The condition which is required to be true.
	 * @param message
	 *            The message which should be attached to the exception if it is
	 *            thrown.
	 */
	public static void requireArgument(boolean condition, String message)
	{
		if (!condition) {
			throw new IllegalArgumentException(message);
		}
	}
}
