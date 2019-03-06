/**
 * 
 */
package com.github.maumay.jflow.iterators.termination;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author t
 *
 */
@Target({ ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE,
		ElementType.TYPE_PARAMETER, ElementType.PARAMETER })
public @interface UsedOneIterator
{

}
