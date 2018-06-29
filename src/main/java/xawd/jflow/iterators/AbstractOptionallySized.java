/**
 *
 */
package xawd.jflow.iterators;

import java.util.OptionalInt;

/**
 * A skeletal implementation of an object which has an 'optional' (i.e possibly
 * unknown) size.
 *
 * @author ThomasB
 */
public abstract class AbstractOptionallySized implements OptionallySized
{
	protected final OptionalInt size;

	public AbstractOptionallySized(OptionalInt size)
	{
		this.size = size;
	}

	@Override
	public OptionalInt size()
	{
		return size;
	}
}
