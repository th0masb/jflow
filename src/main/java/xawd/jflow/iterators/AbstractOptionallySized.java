/**
 *
 */
package xawd.jflow.iterators;

/**
 * @author ThomasB
 *
 */
public abstract class AbstractOptionallySized implements OptionallySized
{
	protected final int size;

	public AbstractOptionallySized(int size)
	{
		this.size = size;
	}

	@Override
	public int size()
	{
		return size;
	}

}
