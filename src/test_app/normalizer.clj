(ns test-app.normalizer
  (:require [clojure.string :as str])
  (:gen-class))

(defn normalize-gender [gender]
  (let [g (str/upper-case (str/trim gender))]
    (case g
      "F" "Female"
      "M" "Male"
      gender)))

(defn normalize-date [date] (str/replace date #"-" "/"))

(defn normalize [person]
  (update
   (update person :gender normalize-gender) :dob normalize-date))


