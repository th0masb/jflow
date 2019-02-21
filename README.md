# An enhanced iterator library for Java

Provides support for a multitude of sequence manipulation 
features for both objects and primitives using sequential 
lazy evaluating iterators inspired by Java streams and Scala collections. 
The central building block is the `EnhancedIterator` interface (along with it's primitive analogs)
which extends the `Iterator` interface adding a lot of functionality.
An immutable analog of a `List` named `Vec` is also provided which, in my opinion,
is much nicer (and not to mention safer) to program with.

## Why use this library?

Let me make it clear that the introduction of streams and lambdas in Java 8 was a *significant* improvement to the Java programming language. There was understandably a large focus on parallelism and how it could be exploited to improve performance. It is also clear, however, that attempting to parallelise every operation on every collection of data no matter the size or operation is silly. In my experience programming, operating sequentially on a collection of data is often appropriate. In my opinion most of the Stream API is great, but some of it... some of it silly, notably transforming streams into collections.

This is a library built for **sequential** operations on collections of data which builds on the existing Java Iterator interface with an API roughly aligned with that of the stream library. Therefore this is not a library designed to replace streams, but one to complement them and together encourage better (and more enjoyable) programming practices.

### Excessively verbose Stream collecting

I've spent a large amount of time working with streams and found myself writing variants of the following code an awful lot:

```
List<MyObject> dataCollection = ...;
List<String> dataNames = dataCollection.stream().map(MyObject::toString).collect(Collectors.toList());

```

Is this really the best we can do for a simple mapping operation? Sure we could favourite static imports so we can reduce the size a bit but I found this to interrupt my flow and generally be a bit frustrating. Wouldn't it be nicer (and equally as readable) to have something like this:

```
List<MyObject> dataCollection = ...;
List<String> dataNames = dataCollection.stream().map(MyObject::toString).toList();
```
well I definitely think so. Of course this has also been addressed in other libraries people have 
written to enhance the stream api so this is not the selling point but it is ultimately the
reason I started writing this library, so I could have more control over an api I use so much. 
Iterators and Streams are fundamentally different beasts though and so having both in your toolbox
is a good idea.

### More control

The constraints on consuming streams in a custom way can be prohibitively restrictive for even very simple use cases, an example is drawing a polygon represented by a stream of points onto a JavaFx canvas **without caching the points first**. This is a trivial task with the polygon represented by an iterator of points since we can easily apply custom logic in the consumption of the iterator. No such luck with a stream. 

In general I have encountered enough algorithms which can be implemented efficiently with iterators
to warrant this work enhancing them.

### Immutable, null-safe List alternative

Sure you could use Guava's ImmutableList but it is fundamentally flawed, it makes no sense to 
implement an interface but decide to ban some of the methods from use. How can you program to
the interface if you aren't sure whether the particular implementation you're working with 
supports certain methods or not? I provide an immutable, array-backed vector which does not 
implement the `Collection` interface and does not accept null values. How does it work 
with existing methods I hear you ask? Well instead of

```
List<T> xs = ...;
someFunc(xs);
```

you have

```
Vec<T> xs = ...;
someFunc(xs.toList());
```




To conclude, this library adds functionality in the style of Streams with some tweaks to the API. At a deeper level it trades potential parallelism for flexibility in consumption (e.g. for use in algorithms). To this end it should be seen as a lightweight complement to Steams, not a replacement. It is very lightweight, has no other dependencies, is well tested, has good documentation and, importantly, makes writing Java so much nicer. I use it extensively in the Java I write. 

## API examples

###### Mapping

``` 
Iter.over("a", "b", "c").map(x -> x + x).toList();  ==> List["aa", "bb", "cc"]
```

###### Filtering

```
Iter.over(1, 2, 3).filter(x -> (x % 2) == 0).toArray(); ==> [2]
```

###### Take, takeWhile, drop, dropWhile

```
Seq<String> someStrings = Seq.of("0", "1", "2", "3");

someStrings.take(2); ==> Seq["0", "1"]
someStrings.flow().drop(2).toSet(); ==> {"3", "2"}

Predicate<String> lessThanTwo = x -> parseInt(x) < 2;
someStrings.takeWhile(lessThanTwo); ==> Seq["0", "1"]
someStrings.dropWhile(lessThanTwo).toList(); ==> List["2", "3"]
```

###### Building integer ranges

```
IterRange.to(5).toArray(); ==> [0, 1, 2, 3, 4]
IterRange.between(2, 6).toArray(); ==> [2, 3, 4, 5]
```

###### Creating Maps and arbitrary mutable collections

```
Iter.over("a", "b").toMap(x -> x, x -> x + x); ==> {"a": "aa", "b": "bb"}
Iter.over("0", "1", "2", "3").groupBy(x -> parseInt(x) % 2); ==> {0: ["0", "2"], 1: ["1", "3"]}
Iter.over("0", "1").toCollection(ArrayList::new); ==> ArrayList<String>["0", "1"]
```

###### Zipping, enumerating and combining

```
Seq<String> strings = Seq.of("a", "b");
Seq<Integer> integers = Seq.of(1, 2, 3);

strings.flow().zipWith(integers).toSeq(); ==> Seq[("a", 1), ("b", 2)]
strings.flow().enumerate().toList(); ==> List[(0, "a"), (1, "b")]
```

###### Folding (Reducing)
```
Seq.of("0", "1", "2").fold(new StringBuilder(), (b, s) -> b.append(s)).toString(); ==> "012"
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

