# jflow
### Enhanced iterators with immutable and ergonomic fixed length arrays

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.maumay/jflow/badge.svg?color=purple)](https://maven-badges.herokuapp.com/maven-central/com.github.maumay/jflow) [![Javadocs](https://javadoc.io/badge/com.github.maumay/jflow.svg?color=purple)](https://javadoc.io/doc/com.github.maumay/jflow) [![License](https://img.shields.io/badge/License-Apache%202.0-purple.svg)](https://opensource.org/licenses/Apache-2.0) [![Build Status](https://travis-ci.org/maumay/JFlow.svg?branch=master)](https://travis-ci.org/maumay/JFlow) [![codecov](https://codecov.io/gh/maumay/jflow/branch/master/graph/badge.svg)](https://codecov.io/gh/maumay/jflow) 


---
#### Overview + Motivation
A lightweight Java library (no other dependencies, ~200KB size) inspired by other languages (notably Rust + Scala) whose functionality can be summarised as follows:  

 - An extension to the `Iterator` interface called `RichIterator` (along with primitive analogs) with a large amount of additional functionality in the style of `Stream` but with some tweaks. 
 - A variety of useful static factory methods for constructing these iterators (both finite and infinite) from various sources.
 - An immutable, array-backed alternative to `List` (along with primitive specializations) called `Vec` which is optimally space efficient, well integrated with the aforementioned iterators to facilitate efficient manipulation (mapping, filtering etc) and can be converted to/from standard `Collection` implementations with ease.

Frustration about the (imo) unnecessary verbosity of the provided stream API coupled with the desire for an immutable, efficient and ergonomic immutable list motivated me to write this library. Writing Java has become much better (more done with more readability with less characters and less bugs) as well as more enjoyable.

---
#### Accessing and using this library

The coordinates on maven central are `com.github.maumay:jflow:0.7.0`. If you want something to copy and paste into your build script then please click on the maven badge at the top of this file. Javadoc can be accessed via the corresponding badge link at the top of this readme, additional documentation is given below.

---
#### API examples

Learning by example is always a useful method, below are some links to files which demonstrate some of the functionality on offer. If you are already familiar with the stream api then most of it should be fairly self explanatory. These files are intended to be read in the listed order.

 - [Vec](docs/Vec-examples.md)
 - [RichIterator](docs/RichIterator-examples.md)
 - [Iter](docs/Iter-examples.md)

While these files don't demonstrate everything they do show some useful functionality and allow you to start using the library straight away.

---
#### Sized iterators

An aim of this library is that the use of an iterator over a sequential stream should not negatively affect the runtime performance. Many operations which terminate a lazy sequence of data (such as predicate matching) essentially just boil down to a single loop and so the difference between using the two data structures is negligible. Others (such as collecting the elements into some collection) can be optimized when we have information about the number of elements in the sequence.

One aspect of the iterator enhancement has been to add a notion of size to an iterator. Of course we cannot know the exact size of an iterator in all situations but in a lot of cases we may make deductions about the range of values it's size can take. One example would be to take an iterator and then apply a filtering operation. Since they are lazy even if the size of the input was known exactly we could not deduce the exact size of the filter result however we can clearly see that the size must lie in a bounded range. This information is used to optimize the terminal collection operations. The size of an iterator can be in one of four states:
 
 - Known exactly
 - Infinite
 - Bounded
 - Bounded below

Clearly all iterators have a size which is bounded below by zero and so this is the 'weakest' knowledge state. Conversely knowing the size exactly (which includes an infinite size although we treat this separately) is the strongest state. An example of the possible optimization is when allocating the elements of an iterator into a fixed size array (which can fit the number of elements exactly), i.e. when we convert an iterator into a new `Vec` instance. If we know the exact size beforehand then this is beneficial for performance as it means no copying is required. Obviously an infinite size iterator is not viable for this operation but knowing that it has infinite size is still beneficial, it means we can throw an exception immediately instead of entering an infinite loop. Knowing an iterator has bounded size is beneficial here too, it means that at most one copy operation is needed to fit the elements of an iterator perfectly into a fixed size array. A `Spliterator` has the notion of 'estimated size' which is just one long value. The sizing for these iterators is more precise and intermediate iterator operations change the size in such a way to maximise the size information retention along a data pipeline.

A toy example: Generating an immutable array (vec) of 5 random 2-d points contained in the set `(0, 1) x (0, 1)`.
```Java
class Point {
    static final Random PRNG = new Random();
    final double x, y;
    Point() {
        x = PRNG.nextDouble();
        y = PRNG.nextDouble();
    }
}

Vec<Point> fiveRandom = Iter.call(Point::new).take(5).toVec();
```

Here we created an infinite iterator of random points (possible because of laziness) and took 5 of them from the head. Since we knew the initial iterator had infinite size we deduce that the iterator formed by the take operation must have exactly five elements. We can then create an array of size 5 whose direct reference is encapsulated in the function call stack, allocate the points into it and return an immutable wrapper around it without any copying taking place, wasting no space and benefiting from complete immutability.


---
#### Mutability and ownership

A stream and an iterator are different in that the former is (to all intents and purposes) *immutable* and the latter is *mutable* and supports more fine grained consumption (although a stream can be converted to a vanilla iterator. We have already seen this to be useful when we implement custom collectors (see here ...). Let's see an example of something we can do with an iterator which isn't possible with a stream.

```Java
RichIterator<String> iterator = Iter.over("a", "b", "c");
String head = iterator.next();
RichIterator<String> iterator2 = iterator.append(head);

// Should this be allowed?
iterator.next();
```

So we took off the head and then adapted the iterator by putting this element at the end. If you read the previous section about adding a notion of size to iterators and spotted the potential for subtle bugs here then well done. For those who didn't spot it (like myself initially) let me break it down. The first iterator has a known size of three, when we call the `next` method we remove the current head and so the iterator now has a known size of two. We then create a new iterator with a known size of three by appending one element. What if we now call `next` again on the initial iterator? How would the second know this had happened and register that it must also decrease it's size by one? The short answer is that calling `next` again on the first iterator will throw an exception, more specifically an `IteratorOwnershipException`.

Introducing a concept of ownership was the best and most performant way to solve this problem I could think of and is similar to the behaviour exhibited by streams. Essentially at the point of creation each iterator possesses ownership of itself and only those iterators which have their ownership intact allow you calls methods directly on them. Calling an adaption method which produces a new iterator (like `append`) will force the old iterator to relinquish the ownership of it's methods. Therefore the only way to access the elements traversed by the old iterator is indirectly through the new one. In this way we remove the potential danger of subtle bugs arising from allowing mutation in this manner.




---
#### Acknowledgements

I would like to thank [Lhasa Limited](https://www.lhasalimited.org/) (my employer at the time this library was created) for their contribution. This started as a personal project of mine to improve the quality of my own work but after recognising the potential usefulness I was permitted to spend some time at work tying everything together into a well tested, well documented and high quality package before being allowed to release it as unaffiliated (to the company) OSS.


---
#### Collecting, consuming and adapting

... still need to write stuff
