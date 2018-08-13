# A functional iterator library for Java

Provides support for a multitude of sequence manipulation 
features for both objects and primitives using sequential 
lazy evaluating iterators inspired by Java streams, Python generators 
and Scala collections. Enhanced `List` and `Set` interfaces (`FlowList` and `FlowSet` respectively)
are included which provide a selection of convenient default methods (for mapping, filtering etc) by delegating
to these iterators.

#### Why use this library?

Let me make it clear that the introduction of streams and lambdas in Java 8 was a **significant** improvement to the Java programming language. There was understandably a large focus on parallelism and how it could be exploited to improve performance. It is also clear, however, that attempting to parallelise every operation on every collection of data no matter the size or operation is silly. In my experience programming, operating sequentially on a collection of data is often appropriate. I feel that the syntax of very common operations has been stunted by this focus on parallelism and I wanted to have a go at writing a companion library to rectify these syntactical issues. This is a library built for **sequential** operations on collections of data which builds on the existing Java `Iterator` interface with an API roughly aligned with that of the stream library. Therefore this is not a library designed to replace streams, but one to complement them and toegther encourage better (and more enjoyable) programming practices.

I've spent a large amount of time working with streams and found myself writing variants of the following code an awful lot:

```
List<MyObject> dataCollection = ...;
List<String> dataNames = dataCollection.stream().map(MyObject::toString).collect(Collectors.toList());

```

Is this really the best we can do for a simple mapping operation? Sure we could favourite static imports so we can reduce the size a bit but I found this to interrupt my flow and generally be a bit frustrating. Wouldn't it be nicer (and equally as readable) to have something like this:

```
List<MyObject> dataCollection = ...;
List<String> dataNames = dataCollection.map(MyObject::toString).toList();
```
well I definitely think so.

Is using all the implementation machinery for parallelising operations in a sequential context efficient? Does use of streams naturally lead to encouraging the use of immutable collections? Well I need to do some benchmarking for the first question but I can quite safely say that the answer to the second question is no. It seems that encouraging use of higher order functions is good but encouraging one of the fundamental tenets of FP - immutability - isn't worth it. Immutability and null-safety are the default approaches here.  


The constraints on consuming streams in a custom way can be prohibitively restrictive for even very simple use cases, an example is drawing a polygon represented by a stream of points onto a JavaFX canvas **without caching the points first**. This is a trivial task with the polygon represented by an **iterator** of points since we can easily apply custom logic in the consumption of the iterator. No such luck with a stream.


To conclude, this library adds functionality in the style of Streams with some tweaks to the API in a way optimised for sequential operations. At a deeper level it trades potential parallelism for flexibility in custom consumption (e.g. for use in algorithms). To this end it should be seen as a lightweight complement to Steams, not a replacement.

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
FList<String> someStrings = Lists.build("0", "1", "2", "3");

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

strings.flow().zipWith(integers).toList(); ==> [("a", 1), ("b", 2)]
strings.enumerate().toList(); ==> [(0, "a"), (1, "b")]
integers.flow().combineWith(strings, (n, s) -> s + n).toList(); ==> ["a1", "b2"]
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

