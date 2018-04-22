/**
 * 
 */
package xawd.jflow.utilities;

/**
 * @author t
 *
 */
public enum Sign {
	POS(1), NEG(-1);
	
	public final int unitVal;
	
	private Sign(final int unitVal) {
		this.unitVal = unitVal;
	}
}
