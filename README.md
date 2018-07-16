# qugen

A library for writing, validating and compiling quantum algorithm.

## Idea
This clojure library makes heavy use of clojure.spec to model the workings of a quantum algorithm. The power of spec is that it is not only able to validate the schema of a given data structure but can generate new examples.
This means that we can use the machine to generate quantum algorithms for us that we then plug into something like a genetic learning algorithm to mutate and optimise.
This in turn can be used for a variety of problems:

- Decompose gates
- Find quantum algorithms that solve problems
- Compile to current architectures

As of now validation works for an arbitrary complex algorithm, the only thing that is missing is referencing qubits in multi qubit gates. It generates valid algorithms but only for single qubit gates thus far. 

## Why Clojure
Functional programming is, at least in my humble opinion, the perfect tool to write quantum algorithms and solve problems in that domain. It leverages on data, is able to simply extend the syntax to fit our problem and makes you think in the right way.
Due to its homoiconicity we are able to meta program the language itself. So we are able to express our problems in quantum computing in a more concise and fast way. 

Clojure makes it simple for the user to express problems in really simple terms which decrease the cognitive load and lets us focus on the important things.

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
