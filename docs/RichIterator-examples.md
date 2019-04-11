Here is a snippet of (boilerplate free and liberally incorrect) Java code demonstrating some of the functionality provided by the `RichIterator` interface. Please note that I use `==` instead of `.equals(...)` here incorrectly so all the assertions involving comparisons would fail but it is *far* nicer to read. All iterators in this example are sourced from one instance of `Vec` (which I assume you have read about), other sources will be demonstrated in another file. The functionality here should look familiar to you already but I'd like to stress the laziness point again. When you create an iterator it does not evaluate enything until you specifically command it to by calling certain types of method. It is easy to tell whether a method is lazy or strict, if the method returns another iterator we call it an 'adapter method' (or intermediate operation) which evaluates lazily and if it returns anything else it is called a 'consuming method' and evaluates in a strict manner. Note that a consuming method does not have to consume the *whole* iterator.

```java
// Factory method designed for importing statically
Vec<Integer> ints = vec(1, 2, 3);
// or can use ints = Vec.of(1, 2, 3);

// *****************************************************************************************
// Easy to make enhanced iterators and streams
ints.iter();
ints.stream();

// *****************************************************************************************
// Streams still work
assert ints.stream().map(n -> 2 * n).collect(Vec.collector()) == vec(2, 4, 6);
assert ints.parstream().map(n -> 3 * n).collect(Vec.collector()) == vec(3, 6, 9);

// *****************************************************************************************
// Can map directly
assert ints.map(n -> 2 * n) == vec(2, 4, 6);
assert ints.map(n -> 3 * n) == vec(3, 6, 9);

// *****************************************************************************************
// Filter
assert ints.filter(n -> n > 2) == vec(3);

// *****************************************************************************************
// Take, skip
assert ints.take(1) == vec(1);
assert ints.drop(2) == vec(3);

assert ints.takeWhile(n -> n % 2 == 1) == vec(1);
assert ints.dropWhile(n -> n % 2 == 1) == vec(2, 3);

// *****************************************************************************************
// Predicate matching
assert ints.anyMatch(n -> n > 2);
assert ints.allMatch(n -> n > 0);
assert ints.noneMatch(n -> n > 3);
assert !ints.find(n -> n > 3).isPresent();

// *****************************************************************************************
// Min/max

// Safe versions (returns nothing if vector is empty)
assert ints.minOp(Comparator.naturalOrder()) == Optional.of(1);
assert ints.maxOp(Comparator.naturalOrder()) == Optional.of(3);

// Unsafe versions (throws exception if the vector is empty)
assert ints.min(Comparator.naturalOrder()) == 1;
assert ints.max(Comparator.naturalOrder()) == 3;

// *****************************************************************************************
// Safe indexing
assert ints.headOp() == Optional.of(1);
assert ints.lastOp() == Optional.of(3);
assert !ints.getOp(6).isPresent();

// *****************************************************************************************
// Unsafe indexing
assert ints.head() == 1;
assert ints.last() == 3;
assert ints.get(1) == 2;

try {
	Integer seventh = ints.get(7);
	throw new AssertionError();
} catch (IndexOutOfBoundsException ex) {
}

// *****************************************************************************************
// Easy type manipulation
Vec<Number> nums = ints.cast(Number.class);

// *****************************************************************************************
// Easy to convert to/from other collection types
List<Integer> listInts = Arrays.asList(1, 2, 3);

assert ints.toList() == listInts;
assert ints.toSet() == new HashSet<>(listInts);
assert ints.toCollection(ArrayList::new) == listInts;

assert ints == Vec.copy(listInts);

// *****************************************************************************************
// Easy to convert to maps
Map<Integer, Integer> expected = new HashMap<>();
expected.put(1, 2);
expected.put(2, 4);
expected.put(3, 6);

Map<Integer, Integer> map1 = ints.toMap(n -> n, n -> 2 * n);
Map<Integer, Integer> map2 = ints.associate(n -> 2 * n);

assert map1 == expected && map2 == expected;

// *****************************************************************************************
// Vec splitting
Tup<Vec<Integer>, Vec<Integer>> partitioned = ints.partition(n -> n % 2 == 0);

// Scala inspired tuple.
assert partitioned._1 == vec(2);
assert partitioned._2 == vec(1, 3);

Tup<Integer, Vec<Integer>> headTail = Tup.of(ints.head(), ints.drop(1));

assert headTail._1 == 1;
assert headTail._2 == vec(2, 3);

// *****************************************************************************************
// Fold vectors
assert ints.fold((a, b) -> a | b) == 0b11;
```