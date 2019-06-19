(ns test-app.reader-parser-test
  (:require [clojure.test :refer :all]
            [test-app.reader_parser :refer :all]))


(deftest determine-delimiter-test
  (is (= :comma (determine-delimiter "This,is,comma,delimited")))
  (is (= :pipe (determine-delimiter "This|is|pipe|delimited")))
  (is (= :space (determine-delimiter "This is space delimited"))))

(deftest parse-line-test
  (is (=
       {:lastname "Johnson" :firstname "Ron" :gender "Male" :color "Blue" :dob "12/12/2012"}
       (parse-line "Johnson,Ron,Male,Blue,12/12/2012")))
  (is (=
       {:lastname "Johnson" :firstname "Ron" :middleinitial "C" :gender "Male" :color "Blue" :dob "12/12/2012"}
       (parse-line "Johnson|Ron|C|Male|Blue|12/12/2012")))
  (is (=
       {:lastname "Johnson" :firstname "Ron" :middleinitial "C" :gender "Male" :dob "12/12/2012" :color "Blue"}
       (parse-line "Johnson Ron C Male 12/12/2012 Blue"))))