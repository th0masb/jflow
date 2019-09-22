/**
 *
 */
package com.github.maumay.jflow.exp;

import com.github.maumay.jflow.vec.Vec;

import java.util.ArrayList;

/**
 * @author thomasb
 *
 */
public class DynamicJava
{

    /**
     *
     */
    public DynamicJava()
    {
        // TODO Auto-generated constructor stub
    }

    public static <T, R> R cast(T input)
    {
        return (R) input;
    }

    public static void main(String[] args)
    {
        // You can change the type of variables
        Vec<Integer> xs = Vec.of(1, 2, 3);
        Vec ys = xs;
        Vec<String> zs = ys;
        String one = zs.head();

        // cast an integer to a string?
        String string = cast(1);
        // Cast a vector of lists of objects to a string?
        String string2 = cast(
                Vec.of(new ArrayList<Object>(), new ArrayList<Object>()));
    }
}
