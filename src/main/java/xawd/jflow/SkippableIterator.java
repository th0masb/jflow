/**
 * 
 */
package xawd.jflow;

import java.util.Iterator;

/**
 * @author t
 *
 */
public interface SkippableIterator<T> extends Iterator<T> 
{
	void skip();
}
