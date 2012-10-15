;; Implementation of the BASENAME command
(in-ns 'closh.utils)

(import [java.nio.file Paths])

(def ^:private empty-string-array
  (into-array String []))

(defn cmd:basename
  ([^String name] (cmd:basename name ""))
  ([^String name ^String suffix]
     (let [filename (.. (Paths/get name empty-string-array) getFileName toString)]
       (if (or (str/blank? suffix) (not (.endsWith filename suffix)))
         filename
         (.substring filename 0 (- (.length filename) (.length suffix)))))))
