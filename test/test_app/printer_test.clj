(ns test-app.printer-test
  (:require [clojure.test :refer :all]
            [test-app.printer :refer :all]))

(deftest print-person-test
  (is (= "McPerson Person Male 12/12/1980 Blue")
      (print-person
       {:firstname "Person" :lastname "McPerson" :color "Blue" :middleinitial "R" :other-random-key "rando" :dob "12/12/190" :gender "Male"})))

(deftest print-people-test
  (is (= "McPerson Person Male 12/12/1980 Blue \n Person Other Female 1/1/2000 Red")
      (print-people
       [{:firstname "Person" :lastname "McPerson" :color "Blue" :middleinitial "R" :other-random-key "rando" :dob "12/12/190" :gender "Male"}
        {:lastname "Person" :firstname "Other" :dob "1/1/2000" :color "Red" :gender "Female"}])))

