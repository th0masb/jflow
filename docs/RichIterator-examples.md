Here is a snippet of boilerplate free Java code demonstrating some of the functionality provided by the `RichIterator` 
interface. **Please note** that I use referential equality in the assertion statements which will cause many to fail if
 you executed this code but it greatly improves readability.

All iterators in this example are sourced from a static factory method in the `Iter` class (which I assume you have read
about). The functionality here should look familiar to you already but I'd like to stress the laziness point again. When 
you create an iterator it does not evaluate anything until you specifically command it to by calling terminal method. It 
is easy to tell whether a method is lazy or strict, if the method returns another iterator we call it an 'intermediate 
operation' which evaluates lazily and if it returns anything else it is called a 'terminal operation' and evaluates in a 
strict manner.

```java
// *****************************************************************************************
// Create collections
assert Iter.args(1, 2, 3).toVec() == Vec.of(1, 2, 3);
assert Iter.args(1, 2, 3).toList() == Arrays.asList(1, 2, 3);
assert Iter.args(1, 2, 3).toSet() == new HashSet<>(Arrays.asList(1, 2, 3));
assert Iter.args(1, 2, 3).to(ArrayDeque::new) == new ArrayDeque<>(Arrays.asList(1, 2, 3));

// *****************************************************************************************
// Create maps
Map<Integer, Integer> expected = new HashMap<>();
expected.put(1, 2);
expected.put(2, 4);
expected.put(3, 6);

assert Iter.args(1, 2, 3).toMap(n -> n, n -> n + n) == expected;
assert Iter.args(1, 2, 3).associate(n -> n + n) == expected;

// *****************************************************************************************
// Mapping
assert Iter.args(1, 2, 3).map(n -> n.toString()).toVec() == vec("1", "2", "3");
assert Iter.args(1, 2, 3).mapToInt(n -> n).toVec() == IntVec.of(1, 2, 3);
assert Iter.args(1, 2, 3).mapToDouble(n -> n.doubleValue()).toVec() == DoubleVec.of(1, 2, 3);
assert Iter.args(1, 2, 3).mapToLong(n -> n.longValue()).toVec() == LongVec.of(1, 2, 3);

// *****************************************************************************************
// Filter
assert Iter.args(1, 2, 3).filter(n -> n.equals(2)).toVec() == vec(2);

// *****************************************************************************************
// FilterMap - map to optional before filtering on present values before unwrapping
Function<Integer, Optional<String>> fn = s -> Optional.of(s.toString()).filter(x -> x.equals("3"));
assert Iter.args(1, 2, 3).filterMap(fn).toVec() == vec("3");

// *****************************************************************************************
// Chaining
assert Iter.args(1, 2, 3).chain(Iter.args(3, 2, 1)).toVec() == vec(1, 2, 3, 3, 2, 1);
assert Iter.args(1, 2, 3).rchain(Iter.args(3, 2, 1)).toVec() == vec(3, 2, 1, 1, 2, 3);

// *****************************************************************************************
// Zipping
assert Iter.args(1, 2, 3).zip(Iter.args(4, 5, 6)).map(p -> p._1 + p._2).toVec() == vec(5, 7, 9);

// *****************************************************************************************
// Easy type manipulation. It is unsafe (any type can be passed) due to Java
// generics deficiencies but can be very useful and convenient.
assert Iter.args(1, 2, 3).<Number>cast().toVec() == Vec.<Number>of(1, 2, 3);

// *****************************************************************************************
// Take, skip
assert Iter.args(1, 2, 3).take(1).toVec() == vec(1);
assert Iter.args(1, 2, 3).skip(1).toVec() == vec(2, 3);

assert Iter.args(1, 2, 3).takeWhile(n -> n < 2).toVec() == vec(1);
assert Iter.args(1, 2, 3).skipWhile(n -> n < 2).toVec() == vec(2, 3);

// *****************************************************************************************
// Predicate matching / finding
assert Iter.args(1, 2, 3).all(n -> n < 4);
assert Iter.args(1, 2, 3).any(n -> n > 2);
assert Iter.args(1, 2, 3).none(n -> n > 5);
assert Iter.args(1, 2, 3).find(n -> n > 1) == 2;
assert Iter.args(1, 2, 3).findOp(n -> n > 3) == Optional.<Integer>empty();

// *****************************************************************************************
// Slicing
assert Iter.args(0, 1, 2, 3, 4).slice(i -> i + 2).toVec() == vec(2, 3, 4);
assert Iter.args(0, 1, 2, 3, 4).slice(i -> 2 * i).toVec() == vec(0, 2, 4);
assert Iter.args(0, 1, 2, 3, 4).slice(i -> 2 * i + 1).toVec() == vec(1, 3);

// *****************************************************************************************
// Fine grained control over consumption.
RichIterator<Integer> iter = Iter.args(1, 2);
assert iter.next() == 1;
assert iter.nextOp() == Optional.of(2);
assert !iter.hasNext();
assert !iter.nextOp().isPresent();
try {
    System.out.println(iter.next());
} catch (NoSuchElementException ex) {
}
```
