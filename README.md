# JFlow
### Complementing Java streams with enhanced iterator functionality

[![Build Status](https://travis-ci.org/maumay/JFlow.svg?branch=master)](https://travis-ci.org/maumay/JFlow)

---
#### Overview
JFlow is a lightweight Java library which complements the stream library by simplifying and tweaking the API in a sequential context using iterators as well as supplementing the API with extra functionality not available when using streams. What is offered can be summarized as follows:

 - An extension to the existing `Iterator` interface (as well as primitive specializations) called `EnhancedIterator` which adds functionality in the style of the `Stream` interface. 
 - A multitude of static factory methods for constructing these `EnhancedIterator`s from various sources.
 - A simpler collecting mechanism for converting lazy sequences of data into custom data structures in a non-parallel context.
 - An immutable alternative to `List` (along with primitive specializations) called `Vec` which is space efficient, comes with a myriad of useful functionality and can construct streams well suited to performing computations in parallel.

The rest of this file along will provide more detail and justification for the points above which will hopefully demonstrate the value which I believe is offered by this library. This will be communicated via a mixture of simple API examples as well as longer and more in-depth code examples.



---
#### API examples

Learning by example is always a useful method, below are some links to files which demonstrate some of the functionality on offer. If you are already familiar with the stream api then most of it should be fairly self explanatory. These files are intended to be read in the listed order.

 - [Vec](docs/Vec-examples.md)
 - [EnhancedIterator](docs/EnhancedIterator-examples.md)
 - [Iter](docs/Iter-examples.md)

While these files don't demonstrate everything they contain the most useful functionality and allow you to start using the library straight away.

---
#### Changes to the stream collecting mechanism

---
#### Accessing and using this library

---
#### Acknowledgements

I would like to thank [Lhasa Limited](https://www.lhasalimited.org/) (my employer at the time this library was created) for their contribution. This started as a personal project of mine to improve the quality of my own work but after recognising the potential usefulness I was permitted to spend some time at work tying everything together into a well tested, well documented and high quality package before being allowed to release it as unaffiliated (to the company) OSS.

---
#### A note on performance

An aim of this library is that the use of an enhanced iterator over a sequential stream should not negatively affect the runtime performance. Many operations which terminate a lazy sequence of data (such as predicate matching) essentially just boil down to a single loop and so the difference between using the two data structures is negligible. Others (such as collecting the elements into some collection) can be optimized when the number of elements in the sequence is known. It is very common to construct an iterator from a source whose size is known and then apply some common transformations from which the size of the new iterator can be calculated exactly (e.g. mapping, appending). 

One aspect of the iterator enhancement (hidden to the user) has been to add a notion of 'optional sizing', that is the number of elements may be finite and known, infinite or unknown. Transforming an iterator manipulates this size as appropriate so that the knowledge can be taken advantage of when it comes to terminating the sequence. This could be improved further by adding size bounds when we cannot calculate the size exactly. For example if you start with an iterator of known size and apply a filter operation the size of the new iterator cannot be known exactly (because of laziness) but we have an upper bound from which we can optimize certain terminal operations. This is a feature for a future version.

Anecdotally I replaced all usage of `Stream` and `List` in the chess engine I wrote with `EnhancedIterator` and `Vec` and noticed no performance difference. I also use this library extensively in the applications I write at work and have not noticed any performance impact. I would like to write some benchmarks to get some empirical evidence but have not found the time yet.