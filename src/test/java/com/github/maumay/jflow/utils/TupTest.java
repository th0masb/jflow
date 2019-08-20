/**
 * 
 */
package com.github.maumay.jflow.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

/**
 * @author thomasb
 *
 */
class TupTest
{
	@Test
	void testEquality()
	{
		assertEquals(Tup.of(1, 2), Tup.of(1, 2));
		assertEquals(Tup.of(1, 2).hashCode(), Tup.of(1, 2).hashCode());
		assertNotEquals(Tup.of(1, 2), Tup.of(1, 3));
		assertNotEquals(Tup.of(1, 2), Tup.of(3, 2));
		assertNotEquals(Tup.of(1, 2), new Object());

		assertEquals(LongTup.of(1, 2), new LongTup(1, 2));
		assertEquals(LongTup.of(1, 2).hashCode(), LongTup.of(1, 2).hashCode());
		assertNotEquals(LongTup.of(1, 2), LongTup.of(1, 3));
		assertNotEquals(LongTup.of(1, 2), LongTup.of(3, 2));
		assertNotEquals(LongTup.of(1, 2), new Object());

		assertEquals(DoubleTup.of(1, 2), new DoubleTup(1, 2));
		assertEquals(DoubleTup.of(1, 2).hashCode(), DoubleTup.of(1, 2).hashCode());
		assertNotEquals(DoubleTup.of(1, 2), DoubleTup.of(1, 3));
		assertNotEquals(DoubleTup.of(1, 2), DoubleTup.of(3, 2));
		assertNotEquals(DoubleTup.of(1, 2), new Object());

		assertEquals(IntTup.of(1, 2), new IntTup(1, 2));
		assertEquals(IntTup.of(1, 2).hashCode(), IntTup.of(1, 2).hashCode());
		assertNotEquals(IntTup.of(1, 2), IntTup.of(1, 3));
		assertNotEquals(IntTup.of(1, 2), IntTup.of(3, 2));
		assertNotEquals(IntTup.of(1, 2), new Object());
	}

	@Test
	void testFlip()
	{
		assertEquals(Tup.of(1, 2), Tup.of(2, 1).flip());
	}

	@Test
	void testToString()
	{
		assertEquals("(1, 2)", Tup.of(1, 2).toString());
		assertEquals("(1, 2)", LongTup.of(1, 2).toString());
		assertEquals("(1, 2)", IntTup.of(1, 2).toString());
		assertEquals("(1.0, 2.0)", DoubleTup.of(1, 2).toString());
	}
}
