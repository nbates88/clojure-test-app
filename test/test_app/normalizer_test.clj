(ns test-app.normalizer-test
  (:require [clojure.test :refer :all]
            [test-app.normalizer :refer :all]))

(deftest normalize-gender-test
  (is (= "Female" (normalize-gender "F")))
  (is (= "Female" (normalize-gender "Female")))
  (is (= "Male" (normalize-gender "M")))
  (is (= "Male" (normalize-gender "Male")))
  (is (= "Female" (normalize-gender "f")))
  (is (= "Male" (normalize-gender "m "))))

(deftest normalize-date-test
  (is (= "12/14/2019") (normalize-date "12-14-2019"))
  (is (= "12/14/2019") (normalize-date "12/14/2019")))