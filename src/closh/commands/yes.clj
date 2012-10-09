;; Implementation of YES

(in-ns 'closh.utils)

(defn cmd:yes
  ([] (cmd:yes "y"))
  ([s] (repeat s)))
