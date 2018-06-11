# A functional iterator library for Java

Provides support for a multitude of sequence manipulation 
features for both objects and primitives using sequential 
lazy evaluating iterator objects inspired by python generators 
and functional programming techniques. The library comes with
extensive and rigorous unit tests plus informative documentation.

#### Why use this library?
A clean and intuitive API is provided for sequence manipulation 
operations which are frequently useful in programming. The 
introduction of Java Streams was a significant improvement
to the language but I felt that the API was a bit clumsy, the 
machinery used to implement Streams is more complicated than it needs to be when used
in a sequential context and most importantly the constraints on
implementing custom consumption of stream are far too restrictive.
This library add additional functionality in the style of Streams 
with a nicer (in my opinion) API but at a deeper level it simply trades 
potential parallelism for  additional flexibility in manually iterating 
through for use in algorithms. To this end it should be seen as a lightweight 
complement to Steams, not a replacement.

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
List<String> someStrings = Arrays.asList("0", "1", "2", "3");

Iterate.over(someStrings).take(2).toSet(); ==> {"0", "1"}
Iterate.over(someStrings).drop(2).toImmutableSet(); ==> {"3", "2"}

Predicate<String> lessThanTwo = x -> parseInt(x) < 2;
Iterate.over(someStrings).takeWhile(lessThanTwo).toList(); ==> ["0", "1"]
Iterate.over(someStrings).dropWhile(lessThanTwo).toImmutableList(); ==> ["2", "3"]
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

###### Zipping, enumerating (without boxed ints) and combining

```
List<String> strings = Arrays.asList("a", "b");
List<Integer> integers = Arrays.asList(1, 2, 3);

Iterate.over(strings).zipWith(integers.iterator()).toList(); ==> [("a", 1), ("b", 2)]
Iterate.over(strings).enumerate().toList(); ==> [(0, "a"), (1, "b")]
Iterate.over(integers).combineWith(strings.iterator(), (n, s) -> s + n).toList(); ==> ["a1", "b2"]
```

#### Building the Jar files and documentation

To us this library you need to build the archives and documentation from this source 
repository. To build the latest version on Windows do the following:

1. Clone this repository
2. Make sure the master branch is checked out
3. Make sure the pwd is the directory containing gradle.bat
4. Run the command `gradlew clean build` in the command prompt

The jars (including source and javadoc) will be built in `build/libs` directory and an uncompressed 
version of the documentation ready to be viewed in a browser will be built in the `build/docs/javadoc` 
directory. If you are using unix simply substitute the command prompt instruction for the following 
command in the Terminal `./gradlew clean build`.

