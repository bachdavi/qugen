# qugen

A library for writing, validating and compiling quantum algorithm.

## Idea
This clojure library makes heavy use of clojure.spec to model the workings of a quantum algorithm. The power of spec is that it is not only able to validate the schema of a given data structure but can generate new examples.
This means that we can use the machine to generate quantum algorithms for us that we then plug into something like a genetic learning algorithm to mutate and optimize.
This in turn can be used for a variety of problems:

- Decompose gates
- Find quantum algorithms that solve problems
- Compile to current architectures

## Why Clojure
Functional programming is, at least in my humble opinion, the perfect tool to write quantum algorithms. It leverages on data and is able to simply extend the syntax to fit our problems.

## Examples

Example for a data structure that resembles a quantum algorithm in qugen:

```
[
 '[:q-1 H [CNOT :q-2] [CNOT :q-3] M]
 '[:q-2 - [CNOT :q-1] -           M]
 '[:q-3 - -           [CNOT :q-1] M]
 ]
```


## License

Copyright © 2018 David Bach

Distributed under the Eclipse Public License 1.0, the same as Clojure.
