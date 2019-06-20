(ns test-app.core
  (:require [clojure.string :as str]
            [test-app.normalizer :refer :all]
            [test-app.reader_parser :refer :all]
            [test-app.sorter :refer :all]
            [test-app.printer :refer :all])
  (:gen-class))


(defn -main
  [& files]
  (->> files
       read-and-parse
       (map normalize)
       do-sort
       (map print-people)
       (map println)
       doall))