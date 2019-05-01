Here is a snippet of boilerplate free Java code demonstrating some of the functionality provided by the `RichIterator` interface. **Please note** that I use referential equality in the assertion statements which will cause many to fail if you executed this code but it greatly improves readability.

All iterators in this example are sourced from one instance of `Vec` (which I assume you have read about), other sources will be demonstrated in another file. The functionality here should look familiar to you already but I'd like to stress the laziness point again. When you create an iterator it does not evaluate enything until you specifically command it to by calling certain types of method. It is easy to tell whether a method is lazy or strict, if the method returns another iterator we call it an 'adapter method' (or intermediate operation) which evaluates lazily and if it returns anything else it is called a 'consuming method' and evaluates in a strict manner. Note that a consuming method does not have to consume the *whole* iterator.

```java
// This vector is the source of the RichIterator instances used in the
// following examples.
Vec<String> strings = vec("a", "b");

// *****************************************************************************************
// Create other collections
List<String> stringsList = asList("a", "b");

assert strings.iter().toVec() == strings;
assert strings.iter().toList() == stringsList;
assert strings.iter().toSet() == new HashSet<>(stringsList);
assert strings.iter().toCollection(ArrayList::new) == stringsList;

// *****************************************************************************************
// Create maps
Map<String, String> expected = new HashMap<>();
expected.put("a", "aa");
expected.put("b", "bb");

assert strings.iter().toMap(x -> x, x -> x + x) == expected;
assert strings.iter().associate(x -> x + x) == expected;

// *****************************************************************************************
// Map
assert strings.iter().map(s -> s.length()).toVec() == vec(1, 1);
assert strings.iter().mapToInt(s -> s.length()).toVec() == IntVec.of(1, 1);
assert strings.iter().mapToDouble(s -> s.length()).toVec() == DoubleVec.of(1, 1);
assert strings.iter().mapToLong(s -> s.length()).toVec() == LongVec.of(1, 1);

// *****************************************************************************************
// Filter
assert strings.iter().filter(s -> s.equals("a")).toVec() == vec("a");

// *****************************************************************************************
// Take, skip
assert strings.iter().take(1).toVec() == vec("a");
assert strings.iter().drop(1).toVec() == vec("b");

assert strings.iter().takeWhile(s -> s.equals("a")).toVec() == vec("a");
assert strings.iter().dropWhile(s -> s.equals("a")).toVec() == vec("b");

// *****************************************************************************************
// Predicate matching (these are terminal methods triggering the consumption of
// the iterator).
assert strings.iter().allMatch(s -> !s.equals("c"));
assert strings.iter().anyMatch(s -> s.equals("b"));
assert strings.iter().noneMatch(s -> s.equals("0"));

// *****************************************************************************************
// Fine grained control over consuming an iterator
RichIterator<String> iter = strings.iter();
assert iter.next() == "a";
assert iter.nextOp() == Option.of("b");
assert !iter.hasNext();
assert !iter.nextOp().isPresent();
try {
	System.out.println(iter.next());
} catch (NoSuchElementException ex) {
}

// *****************************************************************************************
// Append / insert
assert strings.iter().append("c").toVec() == vec("a", "b", "c");
assert strings.iter().insert("c").toVec() == vec("c", "a", "b");

assert strings.iter().append(strings.iterRev()).toVec() == vec("a", "b", "b", "a");
assert strings.iter().insert(strings.iterRev()).toVec() == vec("b", "a", "a", "b");

// *****************************************************************************************
// Zipping
assert strings.iter().zip(strings.iterRev()).map(pair -> pair._1 + pair._2).toVec() == vec("ab", "ba");

```
