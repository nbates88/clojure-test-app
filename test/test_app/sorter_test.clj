(ns test-app.sorter-test
  (:require [clojure.test :refer :all]
            [test-app.sorter :refer :all]))

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
