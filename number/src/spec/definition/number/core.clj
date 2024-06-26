(ns spec.definition.number.core
  (:refer-clojure :exclude [zero? integer?])
  (:require
   [clojure.spec.gen.alpha :as gen]

   [datatype.number.core :as dt-number]
   [datatype.number.gen :as dt-number-gen]

   [spec.definition.core :as sd]))

(sd/def-spec :datatype.number/integer-number
  {:pred clojure.core/integer?
   :gen  #(gen/gen-for-pred clojure.core/integer?)
   :req  :must-be-an-integer})

(sd/def-spec :datatype.number/integer-string
  {:pred dt-number/integer-string?
   :gen  dt-number-gen/gen-integer-string
   :req  :must-be-an-integer-string})

(sd/def-spec :datatype.number/positive-integer-string
  {:pred dt-number/positive-integer-string?
   :gen  dt-number-gen/gen-positive-integer-string
   :req  :must-be-a-positive-integer-string})

(sd/def-spec :datatype.number/negative-integer-string
  {:pred dt-number/negative-integer-string?
   :gen  dt-number-gen/gen-negative-integer-string
   :req  :must-be-a-negative-integer-string})

(sd/def-spec :datatype.number/floating-point-number
  {:pred clojure.core/float?
   :gen  #(gen/gen-for-pred clojure.core/float?)
   :req  :must-be-a-floating-point-number})

(sd/def-spec :datatype.number/decimal-string
  {:pred dt-number/decimal-string?
   :gen  dt-number-gen/gen-decimal-string
   :req  :must-be-a-decimal-string})

(sd/def-spec :datatype.number/positive-decimal-string
  {:pred dt-number/positive-decimal-string?
   :gen  dt-number-gen/gen-positive-decimal-string
   :req  :must-be-a-positive-decimal-string})

(sd/def-spec :datatype.number/negative-decimal-string
  {:pred dt-number/negative-decimal-string?
   :gen  dt-number-gen/gen-negative-decimal-string
   :req  :must-be-a-negative-decimal-string})

(sd/def-spec :datatype.number/positive-number
  {:pred dt-number/positive?
   :gen  dt-number-gen/gen-positive-number
   :req  :must-be-a-positive-number})

(sd/def-spec :datatype.number/negative-number
  {:pred dt-number/negative?
   :gen  dt-number-gen/gen-negative-number
   :req  :must-be-a-negative-number})

(sd/def-spec :datatype.number/zero-number
  {:pred dt-number/zero?
   :gen  dt-number-gen/gen-zero-number
   :req  :must-be-zero})
