/**
 *
 */
package com.github.maumay.jflow.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author thomasb
 */
public final class StringsTest
{
    @Test
    void testIsTrimmed()
    {
        assertTrue(Strings.isTrimmed(""));
        assertTrue(Strings.isTrimmed("hdddklj"));
        assertTrue(Strings.isTrimmed("hdd   dklj"));
        assertFalse(Strings.isTrimmed(" sjsn"));
        assertFalse(Strings.isTrimmed("sjsn  "));
        assertFalse(Strings.isTrimmed(" s jsn  "));
        assertFalse(Strings.isTrimmed("\tsjsn"));
        assertFalse(Strings.isTrimmed("sjsn\n"));
        assertFalse(Strings.isTrimmed("sjsn\r"));
    }

    @Test
    void testConvert()
    {
        Object obj = new Object();
        assertEquals(obj.toString(), Strings.convert(obj));
        assertEquals(Arrays.toString(new boolean[2]),
                Strings.convert(new boolean[2]));
        assertEquals(Arrays.toString(new double[2]),
                Strings.convert(new double[2]));
        assertEquals(Arrays.toString(new int[2]), Strings.convert(new int[2]));
        assertEquals(Arrays.toString(new long[2]),
                Strings.convert(new long[2]));
        assertEquals(Arrays.toString(new float[2]),
                Strings.convert(new float[2]));
        assertEquals(Arrays.toString(new char[2]),
                Strings.convert(new char[2]));
        assertEquals(Arrays.toString(new short[2]),
                Strings.convert(new short[2]));
        assertEquals(Arrays.toString(new byte[2]),
                Strings.convert(new byte[2]));
        assertEquals(Integer.toString(12), Strings.convert(12));
        assertEquals(Long.toString(128374287498243L),
                Strings.convert(128374287498243L));
        assertEquals(Double.toString(0.7323), Strings.convert(0.7323));
        assertEquals(new String(new char[]{'a'}), Strings.convert('a'));
    }

    @ParameterizedTest
    @MethodSource
    void testConcat(String expected, List<String> xs)
    {
        assertEquals(expected, Strings.concat(xs));
    }

    static Stream<Arguments> testConcat()
    {
        return Stream.of(Arguments.of("123", Arrays.asList("1", "2", "3")),
                Arguments.of("123", Arrays.asList("", "1", "", "23", "")));
    }

    @Test
    void firstMatchTest()
    {
        String pattern = "abo";
        assertEquals(Optional.of("abo"),
                Strings.firstMatch("gdjdjjabo, sloabos", pattern));
        assertEquals(Optional.of("abo"),
                Strings.firstMatch("gdjdjjabo, sloabos",
                        Pattern.compile(pattern)));

        assertEquals(Optional.empty(),
                Strings.firstMatch("gdjdjjadosls", pattern));
        assertEquals(Optional.empty(),
                Strings.firstMatch("gdjdjjadosls", Pattern.compile(pattern)));
    }

    @Test
    void lastMatchTest()
    {
        String pattern = "a[bc]o";
        assertEquals(Optional.of("aco"),
                Strings.lastMatch("gdjdjjabo, sloacos", pattern));
        assertEquals(Optional.of("aco"),
                Strings.lastMatch("gdjdjjabo, sloacos",
                        Pattern.compile(pattern)));

        assertEquals(Optional.empty(),
                Strings.lastMatch("gdjdjjadosls", pattern));
        assertEquals(Optional.empty(),
                Strings.lastMatch("gdjdjjadosls", Pattern.compile(pattern)));
    }
}
