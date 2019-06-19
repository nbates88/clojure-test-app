(ns test-app.core-test
  (:require [clojure.test :refer :all]
            [test-app.core :refer :all]))

(deftest dob-sort-test
  (is (= 0 (dob-sort {:dob "10/12/2019"} {:dob "10/12/2019"})))
  (is (= -1 (dob-sort {:dob "10/12/2017"} {:dob "10/12/2019"})))
  (is (= 1 (dob-sort {:dob "10/12/2019"} {:dob "10/12/2017"})))
  (is (= 1 (dob-sort {:dob "10/12/2019" :lastname "Burger"} {:dob "10/12/2019" :lastname "Apple"})))
  (is (= -1 (dob-sort {:dob "10/12/2019" :lastname "Apple"} {:dob "10/12/2019" :lastname "Burger"}))))

(deftest gender-sort-test
  (is (= [{:gender "Female"} {:gender "Male"}] (gender-sort [{:gender "Male"} {:gender "Female"}])))
  (is (= [{:gender "Female"} {:gender "Male"}] (gender-sort [{:gender "Female"} {:gender "Male"}])))
  (is (= [{:gender "Female" :lastname "Apple"} {:gender "Female" :lastname "Burger"}]
         (gender-sort [{:gender "Female" :lastname "Burger"} {:gender "Female" :lastname "Apple"}]))))

(deftest name-sort-test
  (is (= [{:lastname "Burger"} {:lastname "Apple"}] (name-sort [{:lastname "Burger"} {:lastname "Apple"}])))
  (is (= [{:lastname "Burger"} {:lastname "Apple"}] (name-sort [{:lastname "Apple"} {:lastname "Burger"}]))))

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

(deftest print-person-test
  (is (= "McPerson Person Male 12/12/1980 Blue")
      (print-person
       {:firstname "Person" :lastname "McPerson" :color "Blue" :middleinitial "R" :other-random-key "rando" :dob "12/12/190" :gender "Male"})))

(deftest determine-delimiter-test
  (is (= :comma (determine-delimiter "This,is,comma,delimited")))
  (is (= :pipe (determine-delimiter "This|is|pipe|delimited")))
  (is (= :space (determine-delimiter "This is space delimited"))))


