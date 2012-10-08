;; Implementation of YES

(in-ns 'closh.utils)

(defn yes
  ([] (yes "y"))
  ([s] (repeat s)))
