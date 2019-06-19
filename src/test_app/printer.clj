(ns test-app.printer
  (:require [clojure.string :as str])
  (:gen-class))


(defn print-person [person]
  (str/join " " (vals (select-keys person [:lastname :firstname :gender :dob :color]))))

(defn print-people [people]
  (str/join "\n" (map print-person people)))
