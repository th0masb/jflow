Here is a snippet of boilerplate free Java code demonstrating some of the functionality provided by the `RichIterator` interface. **Please note** that I use referential equality in the assertion statements which will cause many to fail if you executed this code but it greatly improves readability.

All iterators in this example are sourced from one instance of `Vec` (which I assume you have read about), other sources will be demonstrated in another file. The functionality here should look familiar to you already but I'd like to stress the laziness point again. When you create an iterator it does not evaluate enything until you specifically command it to by calling certain types of method. It is easy to tell whether a method is lazy or strict, if the method returns another iterator we call it an 'adapter method' (or intermediate operation) which evaluates lazily and if it returns anything else it is called a 'consuming method' and evaluates in a strict manner. Note that a consuming method does not have to consume the *whole* iterator.

```java
// The source of the RichIterator instances used in the following examples.
Supplier<RichIterator<String>> supplier = () -> Iter.args("a", "b");

// *****************************************************************************************
// Create collections
assert supplier.get().toVec() == Vec.of("a", "b");
assert supplier.get().toList() == Arrays.asList("a", "b");
assert supplier.get().toSet() == new HashSet<>(Arrays.asList("a", "b"));
assert supplier.get().to(ArrayDeque::new) == new ArrayDeque<>(Arrays.asList("a", "b"));

// *****************************************************************************************
// Create maps
Map<String, String> expected = new HashMap<>();
expected.put("a", "aa");
expected.put("b", "bb");

assert supplier.get().toMap(x -> x, x -> x + x) == expected;
assert supplier.get().associate(x -> x + x) == expected;

// *****************************************************************************************
// Mapping
assert supplier.get().map(s -> s.length()).toVec() == vec(1, 1);
assert supplier.get().mapToInt(s -> s.length()).toVec() == IntVec.of(1, 1);
assert supplier.get().mapToDouble(s -> s.length()).toVec() == DoubleVec.of(1, 1);
assert supplier.get().mapToLong(s -> s.length()).toVec() == LongVec.of(1, 1);

// *****************************************************************************************
// Filter
assert supplier.get().filter(s -> s.equals("a")).toVec() == vec("a");

// *****************************************************************************************
// Chaining
assert supplier.get().chain(supplier.get()).toVec() == vec("a", "b", "b", "a");

// *****************************************************************************************
// Zipping
assert supplier.get().zip(supplier.get()).map(pair -> pair._1 + pair._2).toVec() == vec("aa", "bb");

// *****************************************************************************************
// Easy type manipulation. It is unsafe (any type can be passed) due to Java generics
// deficiencies but can be very useful and convenient.
assert supplier.get().<CharSequence>cast().toVec() == Vec.<CharSequence>of("a", "b");

// *****************************************************************************************
// Take, skip
assert supplier.get().take(1).toVec() == vec("a");
assert supplier.get().skip(1).toVec() == vec("b");

assert supplier.get().takeWhile(s -> s.equals("a")).toVec() == vec("a");
assert supplier.get().skipWhile(s -> s.equals("a")).toVec() == vec("b");

// *****************************************************************************************
// Predicate matching / finding
assert supplier.get().all(s -> !s.equals("c"));
assert supplier.get().any(s -> s.equals("b"));
assert supplier.get().none(s -> s.equals("0"));
assert supplier.get().find(s -> !s.equals("z")) == "a";
assert supplier.get().findOp(s -> s.equals("c")) == Optional.<String>empty();

// *****************************************************************************************
// Fine grained control over consumption.
RichIterator<String> iter = supplier.get();
assert iter.next() == "a";
assert iter.nextOp() == Option.of("b");
assert !iter.hasNext();
assert !iter.nextOp().isPresent();
try {
	System.out.println(iter.next());
} catch (NoSuchElementException ex) {
}
```
