# A functional iterator library for Java

Provides support for a multitude of sequence manipulation 
features for both objects and primitives using sequential 
lazy evaluating iterators inspired by Java streams, Python generators 
and Scala collections. Enhanced `List` and `Set` interfaces (`FlowList` and `FlowSet` respectively)
are included which provide a selection of convenient default methods (for mapping, filtering etc) by delegating
to these iterators.

#### Why use this library?
The introduction of streams and lambdas in Java 8 was a **significant** improvement
to the language but having spent a lot of time working with the API 
I really felt it could have been better, for example `someCollection.stream().map(someFunction).collect(Collectors.toList())` 
is presumably considered by the implementors as a clean way to write the extremely common operation of sequentially
applying a mapping function element-wise to some collection and collecting the result to a List? I think not.  
Apart from the API the machinery used to implement Streams is more complicated than it needs to be when used
in a sequential context and there is no attempt to encourage use of immutable collections (weird since 
parallelism is such a big focus) in fact the `.collect(...)` method explicitly requires a mutable container. 

Most importantly the constraints on consuming streams in a custom way can be prohibitively restrictive for even very simple use cases, an example is drawing a polygon represented by a stream of points onto a JavaFX canvas **without caching the points first**. This is a trivial task with the polygon represented by an **iterator** of points since we can easily apply custom logic in the consumption of the iterator. No such luck with a stream.


This library add additional functionality in the style of Streams 
with some tweaks to the API but at a deeper level it simply trades 
potential parallelism for  additional flexibility in manual consumption 
for use in algorithms. To this end it should be seen as a lightweight 
complement to Steams, not a replacement. Immutability is the default and a highly efficient, 
immutable List implementation is provided which incidentally is a much better candidate for 
constructing parallel streams than all the mutable List implementations in the Java collections
library.

#### API examples

###### Mapping

``` 
Iterate.over("a", "b", "c").map(x -> x + x).toList();  ==> ["aa", "bb", "cc"]
```

###### Filtering

```
Iterate.over(1, 2, 3).filter(x -> (x % 2) == 0).toArray(); ==> [2]
```

###### Take, takeWhile, drop, dropWhile

```
FlowList<String> someStrings = Lists.build("0", "1", "2", "3");

someStrings.take(2).toSet(); ==> {"0", "1"}
someStrings.drop(2).toMutableSet(); ==> {"3", "2"}

Predicate<String> lessThanTwo = x -> parseInt(x) < 2;
someStrings.takeWhile(lessThanTwo).toList(); ==> ["0", "1"]
someStrings.dropWhile(lessThanTwo).toMutableList(); ==> ["2", "3"]
```

###### Building integer ranges

```
IterRange.to(5).toArray(); ==> [0, 1, 2, 3, 4]
IterRange.between(2, 6).toArray(); ==> [2, 3, 4, 5]
```

###### Creating Maps and arbitrary mutable collections

```
Iterate.over("a", "b").toMap(x -> x, x -> x + x); ==> {"a": "aa", "b": "bb"}
Iterate.over("0", "1", "2", "3").groupBy(x -> parseInt(x) % 2); ==> {0: ["0", "2"], 1: ["1", "3"]}
Iterate.over("0", "1").toCollection(ArrayList::new); ==> ArrayList<String>["0", "1"]
```

###### Zipping, enumerating and combining

```
FlowList<String> strings = Lists.build("a", "b");
FlowList<Integer> integers = Lists.build(1, 2, 3);

strings.flow().zipWith(integers.flow()).toList(); ==> [("a", 1), ("b", 2)]
strings.enumerate().toList(); ==> [(0, "a"), (1, "b")]
integers.flow().combineWith(strings.flow(), (n, s) -> s + n).toList(); ==> ["a1", "b2"]
```

###### Folding
```
Lists.build("1", "2").fold("0", (a, b) -> a + b); ==> "012"
```

#### Building the Jar files and documentation

To us this library you need to build the archives and documentation from this source 
repository. To build the latest version on Windows do the following:

1. Clone this repository
2. Make sure the pwd is the directory containing gradle.bat
3. Run the command `gradlew clean build` in the command prompt

The jars (including source and javadoc) will be built in `build/libs` directory and an uncompressed 
version of the documentation ready to be viewed in a browser will be built in the `build/docs/javadoc` 
directory. If you are using Unix simply substitute the command prompt instruction for the following 
command in the Terminal `./gradlew clean build`.

