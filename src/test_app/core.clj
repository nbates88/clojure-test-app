(ns test-app.core
  (:require [clojure.string :as str])
  (:gen-class))

(defn read-file [file] (str/split (slurp file) #"\n"))

(def file-types
  {:comma {:regexp #","
           :keys [:lastname :firstname :gender :color :dob]}
   :pipe {:regexp #"\|"
          :keys [:lastname :firstname :middleinitial :gender :color :dob]}
   :space {:regexp #" "
           :keys [:lastname :firstname :middleinitial :gender :dob :color]}})

(defn determine-delimeter [line]
  (cond
    (re-find #"," line) :comma
    (re-find #"\|" line) :pipe
    :else :space))

(defn parse-line [line]
  (let [delimeter (determine-delimeter line)
        file-info (get file-types delimeter)]
    (zipmap (:keys file-info)
            (map str/trim (str/split line (:regexp file-info))))))

(defn dob-sort [p1, p2]
  (let [df (java.text.SimpleDateFormat. "MM/dd/yyyy")
        pd1 (.parse df (:dob p1))
        pd2 (.parse df (:dob p2))
        outcome (compare pd1 pd2)]
    (cond
      (= outcome 0) (compare (:lastname p1) (:lastname p2))
      :else outcome)))


(defn normalize-gender [gender]
  (cond
    (= gender "F") "Female"
    (= gender "M") "Male"
    :else gender))

(defn normalize-date [date] (str/replace date #"-" "/"))

(defn normalize [person]
  (update
   (update person :gender normalize-gender) :dob normalize-date))

(defn print-person [person]
  (str/join " " (vals (select-keys person [:lastname :firstname :gender :dob :color]))))

(defn print-people [people]
  (str/join "\n" (map print-person people)))


(defn -main
  [& files]
  (let [raw-data (flatten (map read-file files))
        parsed-data (map parse-line raw-data)
        normalized-data (map normalize parsed-data)
        name-sorted (sort-by :lastname #(compare %2 %1) normalized-data)
        gender-sorted (sort-by (juxt :gender :lastname) normalized-data)
        dob-sorted (sort dob-sort normalized-data)]
    (doall (map println
                (map print-people (list name-sorted gender-sorted dob-sorted))))))


