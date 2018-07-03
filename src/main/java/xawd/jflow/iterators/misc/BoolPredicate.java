/**
 *
 */
package xawd.jflow.iterators.misc;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author ThomasB
 *
 */
@FunctionalInterface
public interface BoolPredicate<T> extends Function<T, Bool>
{
	default Predicate<T> toPrimitivePredicate()
	{
		return x -> apply(x).get();
	}
}
