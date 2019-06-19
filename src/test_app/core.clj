(ns test-app.core
  (:require [clojure.string :as str]
            [test-app.normalizer :refer :all]
            [test-app.reader_parser :refer :all]
            [test-app.sorter :refer :all]
            [test-app.printer :refer :all])
  (:gen-class))

(defn -main
  [& files]
  (let [parsed-data (read-and-parse files)
        normalized-data (map normalize parsed-data)
        name-sorted (name-sort normalized-data)
        gender-sorted (gender-sort normalized-data)
        dob-sorted (sort dob-sort normalized-data)]
    (doall (map println
                (map print-people (list name-sorted gender-sorted dob-sorted))))))


;; use ->> here instead?