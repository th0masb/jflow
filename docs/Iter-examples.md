This file demonstrates some of the static factory methods contained in the class `Iter` which enable you to construct rich iterators from various sources. **Please note** that I use referential equality in the assertion statements which will cause many to fail if you executed this code but it greatly improves readability.


```java
// Construct from varargs
assert Iter.args(1, 2, 3).toVec() == vec(1, 2, 3);

// Construct from existing collections
assert Iter.over(Arrays.asList(1, 2, 3)).toVec() == vec(1, 2, 3);

// *****************************************************************************************
// Construct from enumerated types
assert Iter.enums(Hand.class).toVec() == vec(Hand.LEFT, Hand.RIGHT);

// *****************************************************************************************
// Construct from optionals
assert Iter.over(Optional.empty()).toVec() == vec();
assert Iter.over(Optional.of("a")).toVec() == vec("a");

// Example usage is flattening a sequence of optional values
assert Iter.args(Optional.of("a"), Optional.empty()).flatMap(Iter::over).toVec() == vec("a");

// *****************************************************************************************
// Flatten stacked collections
Iterable<Iterable<String>> stacked = asList(asList("a"), new HashSet<>(), vec("b"));
assert Iter.flatten(stacked).toVec() == vec("a", "b");

// *****************************************************************************************
// Construct from maps
Map<Integer, Integer> map = new HashMap<>();
map.put(1, 10);
map.put(2, 20);

BinaryOperator<Integer> sum = (a, b) -> a + b;
assert Iter.keys(map).fold(sum) == 3;
assert Iter.values(map).fold(sum) == 30;
assert Iter.over(map).map(pair -> pair._1 * pair._2).fold(sum) == 50;

// *****************************************************************************************
// Construct primitive iterators
assert Iter.ints(1, 2, 3).any(n -> n == 2);
assert Iter.doubles(1.0, 2.0, 3.0).all(n -> n > 0);
assert Iter.longs(1, 2, 3).none(n -> n > 3);

// *****************************************************************************************
// Construct primitive number ranges
assert Iter.until(5).toVec() == IntVec.of(0, 1, 2, 3, 4);
assert Iter.between(1, 4).toVec() == IntVec.of(1, 2, 3);
assert Iter.between(1, 10, 2).toVec() == IntVec.of(1, 3, 5, 7, 9);
assert Iter.between(9, 0, -2).toVec() == IntVec.of(9, 7, 5, 3, 1);
```
