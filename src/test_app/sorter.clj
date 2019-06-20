(ns test-app.sorter
  (:gen-class))

(defn dob-comparator [p1, p2]
  (let [df (java.text.SimpleDateFormat. "MM/dd/yyyy")
        pd1 (.parse df (:dob p1))
        pd2 (.parse df (:dob p2))
        outcome (compare pd1 pd2)]
    (if (= outcome 0) (compare (:lastname p1) (:lastname p2)) outcome)))

(defn dob-sort [people]
  (sort dob-comparator people))

(defn gender-sort [people]
  (sort-by (juxt :gender :lastname) people))

(defn name-sort [people] (sort-by :lastname #(compare %2 %1) people))

(defn do-sort [people]
  (let [name-sorted (name-sort people)
        gender-sorted (gender-sort people)
        dob-sorted (dob-sort people)]
    (list name-sorted gender-sorted dob-sorted)))
