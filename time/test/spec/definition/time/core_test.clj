(ns spec.definition.time.core-test
  (:require
   [clojure.spec.alpha :as spec]
   [clojure.spec.gen.alpha :as gen]
   [clojure.test :refer [deftest is testing]]

   [cljc.java-time.offset-date-time :as jt-odt]

   [spec.validate.core :as sv-core]
   [spec.definition.time.core]

   [datatype.testing.cases :as dt-test-cases]))

(deftest iso8601-zoned-datetime-string-spec-validation
  (dt-test-cases/assert-cases-satisfied-by
    (partial spec/valid? :datatype.time/iso8601-zoned-datetime-string)
    (dt-test-cases/true-case
      "any extended format UTC offset datetime string"
      :samples ["1986-09-20T16:34:56.123Z"
                "2025-11-10T11:14:56Z"
                "2036-10-15T01:00Z"
                "+12050-12-25T13:00:00.000000Z"])
    (dt-test-cases/true-case
      "any extended format non-UTC offset datetime string"
      :samples ["1986-09-20T16:34:56.123+03"
                "2025-11-10T11:14:56+10:00"
                "2036-10-15T01:00+00:00"
                "2036-10-15T01:00:30-08"
                "+12050-12-25T13:00:00.000000-11:00"])
    (dt-test-cases/false-case "any basic format offset datetime string"
      :samples ["19860920T163456.123Z"
                "20251110T111456Z"
                "20361015T0100Z"
                "+120501225T130000.000000Z"
                "19860920T163456.123+03"
                "20251110T111456+1000"
                "20361015T0100+0000"
                "20361015T010030-08"
                "+120501225T130000.000000-1100"])
    (dt-test-cases/false-case "any extended format local datetime string"
      :samples ["1986-09-20T16:34:56.123"
                "2025-11-10T11:14:56"
                "2036-10-15T01:00"
                "+12050-12-25T13:00:00.000000"])
    (dt-test-cases/false-case "any basic format local datetime string"
      :samples ["19860920T163456.123"
                "20251110T111456"
                "20361015T0100"
                "+120501225T130000.000000"])
    (dt-test-cases/false-case "any extended format zoned datetime string"
      :samples ["1986-09-20T16:34:56.123Z[Etc/UTC]"
                "2025-11-10T11:14:56+03:00[Europe/Athens]"
                "2036-10-15T01:00-04:00[America/New_York]"
                "+12050-12-25T13:00:00.000000+12:00[Pacific/Auckland]"])
    (dt-test-cases/false-case "any time only string"
      :samples ["T16:34:56.123Z"
                "T11:14:56Z"
                "T01:00Z"
                "T13:00:00.000000Z"
                "T16:34:56.123+03"
                "T11:14:56+10:00"
                "T01:00+00:00"
                "T01:00:30-08"
                "T13:00:00.000000-11:00"])
    (dt-test-cases/false-case "any date only string"
      :samples ["1986-09-20"
                "2025-11-10"
                "2036-10-15"
                "+12050-12-25"
                "1986-09-20"
                "2025-11-10"
                "2036-10-15"
                "2036-10-15"
                "+12050-12-25"])
    (dt-test-cases/false-case "any non-datetime string"
      :samples ["spinach" "hello world" "<h2>wat</h2>"])
    (dt-test-cases/false-case "any non-string"
      :samples [true false 22 25.45 #{1 2 3}])
    (dt-test-cases/false-case "nil" :sample nil)))

(deftest iso8601-zoned-datetime-string-spec-generation
  (testing "does not generate nil"
    (is (every? false?
          (map nil?
            (gen/sample
              (spec/gen :datatype.time/iso8601-zoned-datetime-string)
              100)))))
  (testing "does not generate empty strings"
    (is (every? false?
          (map #(= "" %)
            (gen/sample
              (spec/gen :datatype.time/iso8601-zoned-datetime-string)
              100)))))
  (testing "generates some past dates"
    (is (some?
          (map
            (fn [sample]
              (let [parsed (jt-odt/parse sample)
                    now (jt-odt/now)]
                (or
                  (jt-odt/is-before parsed now)
                  (jt-odt/is-equal parsed now))))
            (gen/sample
              (spec/gen :datatype.time/iso8601-zoned-datetime-string)
              100)))))
  (testing "generates some future dates"
    (is (some?
          (map
            (fn [sample]
              (let [parsed (jt-odt/parse sample)
                    now (jt-odt/now)]
                (or
                  (jt-odt/is-after parsed now)
                  (jt-odt/is-equal parsed now))))
            (gen/sample
              (spec/gen :datatype.time/iso8601-zoned-datetime-string)
              100)))))
  (testing "generates dates with various offsets"
    (let [offsets
          (into #{}
            (map
              (comp jt-odt/get-offset jt-odt/parse)
              (gen/sample
                (spec/gen :datatype.time/iso8601-zoned-datetime-string)
                100)))]
      (is (> (count offsets) 1)))))

(deftest iso8601-zoned-datetime-string-pred-requirement
  (is (= :must-be-an-iso8601-zoned-datetime-string
        (sv-core/pred-requirement
          'datatype.time.core/iso8601-zoned-datetime-string?))))
