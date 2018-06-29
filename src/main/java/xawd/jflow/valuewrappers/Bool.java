/**
 *
 */
package xawd.jflow.valuewrappers;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Wrapped truth values, useful for chaining boolean operations.
 *
 * @author ThomasB
 */
public enum Bool
{
	TRUE(true), FALSE(false);

	private final boolean primitiveVal;

	/**
	 * Applies a boolean OR operation with another wrapped truth value.
	 *
	 * @param other
	 *            A wrapped truth value.
	 *
	 * @return true if either wrapped boolean is true, false otherwise.
	 */
	public Bool or(Bool other)
	{
		return primitiveVal || other.primitiveVal ? TRUE : FALSE;
	}

	public Bool xor(Bool other)
	{
		return primitiveVal ^ other.primitiveVal ? TRUE : FALSE;
	}

	public Bool and(Bool other)
	{
		return primitiveVal && other.primitiveVal ? TRUE : FALSE;
	}

	private Bool(boolean asBoolean)
	{
		this.primitiveVal = asBoolean;
	}

	public boolean get()
	{
		return primitiveVal;
	}

	public <X extends Throwable> void throwIfFailed(Supplier<X> exceptionSupplier) throws X
	{
		if (!primitiveVal) {
			throw exceptionSupplier.get();
		}
	}

	public void runIfFailed(Runnable procedure)
	{
		if (!primitiveVal) {
			procedure.run();
		}
	}

	public <T> Optional<T> computeIfFailed(Supplier<T> supplier)
	{
		if (!primitiveVal) {
			return Optional.of(supplier.get());
		} else {
			return Optional.empty();
		}
	}

	public <X extends Throwable> void throwIfPassed(Supplier<X> exceptionSupplier) throws X
	{
		if (primitiveVal) {
			throw exceptionSupplier.get();
		}
	}

	public void runIfPassed(Runnable procedure)
	{
		if (primitiveVal) {
			procedure.run();
		}
	}

	public <T> Optional<T> computeIfPassed(Supplier<T> supplier)
	{
		if (primitiveVal) {
			return Optional.of(supplier.get());
		} else {
			return Optional.empty();
		}
	}
}
