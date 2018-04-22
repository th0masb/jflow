package xawd.jflow.zippedpairs;

/**
 * @author ThomasB
 * @since 20 Apr 2018
 */
public final class Pair<T, U>
{
	private final T first;
	private final U second;

	public Pair(final T first, final U second)
	{
		this.first = first;
		this.second = second;
	}

	public static <T, U> Pair<T, U> of(final T t, final U u)
	{
		return new Pair<>(t, u);
	}

	public T first()
	{
		return first;
	}

	public U second()
	{
		return second;
	}

	@Override
	public String toString()
	{
		return new StringBuilder("(")
				.append(first.toString())
				.append(", ")
				.append(second.toString())
				.append(")")
				.toString();
	}
}
