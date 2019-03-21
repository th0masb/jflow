# JFlow
### Complementing Java streams with enhanced iterator functionality

[![Build Status](https://travis-ci.org/maumay/JFlow.svg?branch=master)](https://travis-ci.org/maumay/JFlow)

---
#### Overview
JFlow is a lightweight Java library which complements the stream library by simplifying and tweaking the API in a sequential context using iterators as well as supplementing the API with extra functionality not available when using streams. What is offered can be summarized as follows:

 - An extension to the existing `Iterator` interface (as well as primitive specializations) called `RichIterator` which adds functionality in the style of the `Stream` interface. 
 - A multitude of static factory methods for constructing `RichIterator`s from various sources.
 - A simpler collecting mechanism for converting lazy sequences into custom data structures within a non-parallel context.
 - A mechanism for consuming lazy sequences by performing side effects.
 - A mechanism for implementing custom intermediate (in stream terminology) operations.
 - An immutable alternative to `List` (along with primitive specializations) called `Vec` which is space efficient, comes with a myriad of useful functionality and can construct streams well suited to performing computations in parallel.

The rest of this file along will provide more detail and justification for the points above which will hopefully demonstrate the value which I believe is offered by this library. This will be communicated via a mixture of simple API examples as well as longer and more in-depth code examples.



---
#### API examples

Learning by example is always a useful method, below are some links to files which demonstrate some of the functionality on offer. If you are already familiar with the stream api then most of it should be fairly self explanatory. These files are intended to be read in the listed order.

 - [Vec](docs/Vec-examples.md)
 - [RichIterator](docs/EnhancedIterator-examples.md)
 - [Iter](docs/Iter-examples.md)

While these files don't demonstrate everything they contain the most useful functionality and allow you to start using the library straight away.

---
#### Collecting, consuming and adapting

---
#### Accessing and using this library



---
#### A note on performance

An aim of this library is that the use of an iterator over a sequential stream should not negatively affect the runtime performance. Many operations which terminate a lazy sequence of data (such as predicate matching) essentially just boil down to a single loop and so the difference between using the two data structures is negligible. Others (such as collecting the elements into some collection) can be optimized when we have information about the number of elements in the sequence.

One aspect of the iterator enhancement has been to add a notion of size to an iterator. Of course we cannot know the exact size of an iterator in all situations but in a lot of cases we may make deductions about the range of values it's size can take. One example would be to take an iterator and then apply a filtering operation. Since they are lazy even if the size of the input was known exactly we could not deduce the exact size of the filter result however we can clearly see that the size must lie in a bounded range. This information is used to optimize the terminal collection operations. The size of an iterator can be in one of four states:
 
 - Known exactly
 - Infinite
 - Bounded
 - Bounded below

Clearly all iterators have a size which is bounded below by zero and so this is the 'weakest' knowledge state. Conversely knowing the size exactly (which includes an infinite size) is the strongest state. An example of the possible optimization is when allocating the elements of an iterator into a fixed size array (which can fit the number of elements exactly), if we know the exact size beforehand then this is beneficial for performance as it reduces copying. Obviously an infinite size iterator is not viable for this operation but knowing that it has infinite size is still beneficial, it means we can throw an exception immediately instead of entering an infinite loop. Knowing an iterator has bounded size is beneficial here too, it means that at most one copy operation must take place to fit the elements of an iterator perfectly into a fixed size array. Streams have a notion of 'estimated size' which is just one long value. The sizing for these iterators is more precise and intermediate iterator operations change the size in such a way to maximise the size information retention along a data pipeline.

A toy example: Generating an immutable array (vec) of 5 random 2-d points contained in the set `(0, 1) x (0, 1)`.
```Java
class Point {
    static final Random prng = new Random();
    final double x, y;
    Point() {
        x = prng.nextDouble();
        y = prng.nextDouble();
    }
}

Vec<Point> fiveRandom = Repeatedly.call(Point::new).take(5).toVec();
```

Here we created an infinite iterator of random points (possible because of laziness) and took 5 of them from the head. Since we knew the initial iterator had at least five elements we deduce that the iterator formed by the take operation must have exactly five elements. We can then create an array of size 5 on the stack, allocate the points into it and return a wrapper around it without any copying taking place, wasting no space and retaining complete immutability.


---
#### Acknowledgements

I would like to thank [Lhasa Limited](https://www.lhasalimited.org/) (my employer at the time this library was created) for their contribution. This started as a personal project of mine to improve the quality of my own work but after recognising the potential usefulness I was permitted to spend some time at work tying everything together into a well tested, well documented and high quality package before being allowed to release it as unaffiliated (to the company) OSS.