;; Implementation of YES

(in-ns 'closh.utils)

(defn cmd:yes
  ([] (yes "y"))
  ([s] (repeat s)))
