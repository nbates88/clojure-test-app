(ns test-app.reader_parser
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

(defn determine-delimiter [line]
  (cond
    (re-find #"," line) :comma
    (re-find #"\|" line) :pipe
    :else :space))

(defn parse-line [line]
  (let [delimiter (determine-delimiter line)
        file-info (get file-types delimiter)]
    (zipmap (:keys file-info)
            (map str/trim (str/split line (:regexp file-info))))))

(defn read-and-parse [files]
  (let [raw-data (flatten (map read-file files))] (map parse-line raw-data)))