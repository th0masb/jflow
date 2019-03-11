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

The rest of this file along will provide more detail and justification for the points above which will hopefully demonstrate the value which I believe is offered by this library. This will be communicated via a mixture of simple API examples as well as longer and more in-depth code examples. Instructions for installation can be found in the last section of this file.

---
#### API examples

Learning by example is always a useful method, below are some links to files which demonstrate some of the functionality on offer. If you are already familiar with the stream api then it should hopefully be fairly self explanatory. These files are intended to be read in the listed order.

 - [Vec](docs/Vec-examples.md)