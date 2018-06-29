/**
 *
 */
package xawd.jflow.iterators.misc;

import java.util.Optional;
import java.util.function.Supplier;


/**
 * Monoidal representation of a truth value;
 *
 * @author ThomasB
 */
public enum PredicateResult
{
	PASS(true), FAIL(false);

	private final boolean asBoolean;

	public PredicateResult or(PredicateResult other)
	{
		return asBoolean || other.asBoolean? PASS : FAIL;
	}

	public PredicateResult xor(PredicateResult other)
	{
		return asBoolean ^ other.asBoolean? PASS : FAIL;
	}

	public PredicateResult and(PredicateResult other)
	{
		return asBoolean && other.asBoolean? PASS : FAIL;
	}

	private PredicateResult(boolean asBoolean)
	{
		this.asBoolean = asBoolean;
	}

	public boolean get()
	{
		return asBoolean;
	}

	public <X extends Throwable> void throwIfFailed(Supplier<X> exceptionSupplier) throws X
	{
		if (!asBoolean) {
			throw exceptionSupplier.get();
		}
	}

	public void runIfFailed(Runnable procedure)
	{
		if (!asBoolean) {
			procedure.run();
		}
	}

	public <T> Optional<T> computeIfFailed(Supplier<T> supplier)
	{
		if (!asBoolean) {
			return Optional.of(supplier.get());
		}
		else {
			return Optional.empty();
		}
	}

	public <X extends Throwable> void throwIfPassed(Supplier<X> exceptionSupplier) throws X
	{
		if (asBoolean) {
			throw exceptionSupplier.get();
		}
	}

	public void runIfPassed(Runnable procedure)
	{
		if (asBoolean) {
			procedure.run();
		}
	}

	public <T> Optional<T> computeIfPassed(Supplier<T> supplier)
	{
		if (asBoolean) {
			return Optional.of(supplier.get());
		}
		else {
			return Optional.empty();
		}
	}
}
