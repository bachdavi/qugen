(ns qugen.schema
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]))

(def test-data ['[:q-1 H [CNOT :q-2] [CNOT :q-3] M]
                '[:q-2 - [CNOT :q-1] -           M]
                '[:q-3 - -           [CNOT :q-1] M]])

(defn dreg->mreg [[qubit & operators]]
  {::qubit qubit
   ::operators operators})

(defn qubit-gen []
  (->> (s/gen (s/int-in 0 100))
       (gen/fmap #(keyword (str "q-" (rand-int (+ 100 %)))))))

(defn s-operator-gen []
  (->> (s/gen (s/int-in 0 100))
       (gen/fmap #(nth (cycle '[H CNOT T M]) (rand-int (+ 100 %))))))

(defn m-operator-gen []
  (->> (s/gen (s/int-in 0 100))
       (gen/fmap #('[(nth (cycle '[H CNOT T M]) %)]))))

(s/def ::qubit
  (s/spec (s/and keyword?
                 #(re-find #"^[q]-" (name %)))
          :gen qubit-gen))

(s/def ::m-operator (s/spec (s/coll-of (s/or :op #(contains? '#{H CNOT T M} %) :q ::qubit))
                            :gen m-operator-gen))

(s/def ::s-operator (s/spec (s/and symbol?
                                   #(contains? '#{H CNOT T M} %))
                            :gen s-operator-gen))

(s/def ::operators (s/coll-of ::s-operator))

(s/def ::register (s/keys :req [::qubit ::operators]))

(s/def ::algorithm (s/coll-of ::register))
