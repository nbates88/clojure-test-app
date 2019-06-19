(ns test-app.sorter
  (:gen-class))

(defn dob-sort [p1, p2]
  (let [df (java.text.SimpleDateFormat. "MM/dd/yyyy")
        pd1 (.parse df (:dob p1))
        pd2 (.parse df (:dob p2))
        outcome (compare pd1 pd2)]
    (cond
      (= outcome 0) (compare (:lastname p1) (:lastname p2))
      :else outcome)))

(defn gender-sort [people]
  (sort-by (juxt :gender :lastname) people))

(defn name-sort [people] (sort-by :lastname #(compare %2 %1) people))
