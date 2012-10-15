;; Implementation of the DIRNAME command
(in-ns 'closh.utils)

(import [java.nio.file Paths])

(def ^:private empty-string-array
  (into-array String []))

(defn cmd:dirname [^String name]
  (if-let [parent (.getParent (Paths/get name empty-string-array))]
    (.toString parent)
    "."
  ))
