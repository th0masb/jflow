# A functional iterator library for Java

Provides support for a multitude of sequence manipulation 
features for both objects and primitives using sequential 
lazy evaluating iterator objects inspired by python generators 
and functional programming techniques.

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

It will be helpful to see some examples of the API:

###### Mapping

``` 
Iterate.over("a", "b", "c").map(x -> x + x).toList();  ==> ["aa", "bb", "cc"]
```

##### Filtering

```
Iterate.over(1, 2, 3).filter(x -> (x % 2) == 0).toArray(); ==> [2]
```

#### Building the Jar files and documentation

To us this library you need to build the archives and documentation from this source 
repository. To build the latest version on Windows do the following:

1. Clone this repository
2. Make sure the master branch is checked out
3. Make sure the pwd is the directory containing gradle.bat
4. Run the command `gradlew clean build` in the command prompt

The jars will be built in build/libs directory and an uncompressed version of the documentation
will be built in build/docs/javadoc directory. If you are using unix simply substitute the 
command prompt instruction for the following in the Terminal; `./gradlew clean build`.

