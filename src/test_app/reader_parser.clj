(ns test-app.reader_parser
  (:require [clojure.string :as str])
  (:gen-class))

(defn read-file [file] (str/split (slurp file) #"\n"))

(defn determine-delimiter [line]
  (cond
    (re-find #"," line) :comma
    (re-find #"\|" line) :pipe
    :else :space))

(defmulti parse-line determine-delimiter)

(defmethod parse-line :comma [line]
  (zipmap [:lastname :firstname :gender :color :dob]
          (map str/trim (str/split line #","))))

(defmethod parse-line :pipe [line]
  (zipmap [:lastname :firstname :middleinitial :gender :color :dob]
          (map str/trim (str/split line #"\|"))))

(defmethod parse-line :space [line]
  (zipmap [:lastname :firstname :middleinitial :gender :dob :color]
          (map str/trim (str/split line #" "))))

(defn read-and-parse [files]
  (let [raw-data (flatten (map read-file files))] (map parse-line raw-data)))