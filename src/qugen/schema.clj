(ns qugen.schema
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]))

['[:q-1 H [CNOT :q-2] [CNOT :q-3] M]
 '[:q-2 - [CNOT :q-1] -           M]
 '[:q-3 - -           [CNOT :q-1] M]]

(defn dreg->mreg [[qubit & operators]]
  {::qubit qubit
   ::operators operators})

(s/def ::qubit keyword?)

(s/def ::operator (s/or :mult (s/coll-of (s/or :op symbol? :q ::qubit))
                        :sing symbol?))

(s/def ::operators (s/coll-of ::operator))

(s/def ::register (s/keys :req [::qubit ::operators]))

(s/valid? ::register (dreg->mreg '[:q-1 H [CNOT :q-2] M]))

;; (s/def ::alorithm s/*
;;   ,,,
;;   )
