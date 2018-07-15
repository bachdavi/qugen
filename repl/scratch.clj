(+ 1 1)

(ns qugen.schema
  (:require [clojure.spec.alpha :as s]))

(transduce identity (fn
                      ([] 0)
                      ([acc] (Math/sqrt acc))
                      ([acc x] (+ acc (* x x))))
           [5 5])

(defn qubit? [coll]
  (= 1.0 (square coll)))

(s/def ::test qubit?)

(s/conform ::test [1 1])

'[CNOT [H]]

(s/conform ::qubit :q-1)

(s/conform ::operator [:q-2 :q-1])

(type [:q-2 :q-1])

(s/valid? symbol? :q-1)

(s/valid? ::operator 'H)

'[:q-1 H [CNOT :q-2]]

(def :q-1)

(s/conform (s/coll-of (s/or :q ::qubit :op ::operator)) '[:q-1 H [CNOT :q-2] M])

(s/valid? ::register '[:q-1 H [CNOT :q-2] M])

(require '[clojure.spec.gen.alpha :as gen])

(gen/generate (s/gen ::register))

(defn register? [[qubit & operators]]
  (s/and
   ::qubit qubit
   (s/coll-of ::operator) operators))

(register? '[:q H [CNOT :q-1]])

(s/valid? (s/and (s/coll-of (s/or :q ::qubit :op ::operator))
                 (s/valid? ::qubit (first)))
          '[:q-1 H [CNOT :q-2] M])

(s/valid? register? '[:q-1 H [CNOT :q-2] :q2 M])

(s/valid? (s/cat ::qubit (s/coll-of ::operator)) '[:q-1 H [CNOT :q-2] M])

(s/explain (s/cat ::qubit (s/coll-of ::operator)) '[:q-1 H [CNOT :q-2] M])

(defn register? [[qubit & operators]]
  (and
   (s/valid? ::qubit qubit)
   (s/valid? (s/coll-of ::operator) operators)))

(s/explain register? '[:q-1 H [CNOT :q-2] :q2 M])

(s/def ::operator (s/or :mult (s/coll-of (s/or :op symbol? :q ::qubit))
                        :sing symbol?))

(s/def ::operators (s/coll-of ::operator))

(s/conform (s/keys :req [::qubit ::operators]) {::qubit :q-1 ::operators '[H [CNOT :q-2] M]})

(s/def ::register (s/keys :req [::qubit ::operators]))

(gen/generate (s/gen ::register))

(defn dreg->mreg [[qubit & operators]]
  {::qubit qubit
   ::operators operators})

(s/explain (s/valid? ::register (dreg->mreg '[:q-1 H [CNOT :q-2] M])))
