;; Implementation of the DF command
(in-ns 'closh.utils)

(import [java.io File]
        [java.nio.file Files]
        [java.net URI])

(defn kb [bytes]
  (/ bytes 1024))

(defn- df- [store]
  ;; Filesystem Size Used Avail Use% Mounted on
  (format "%s %d %d %d"
          (.name store)
          (kb (.getTotalSpace store))
          (kb (- (.getTotalSpace store) (.getUnallocatedSpace store)))
          (kb (.getUsableSpace store))))

(defn df
  ([] (df ["/"]))
  ([paths]
     (do
       (println "Filesystem Size Used Avail")
       (println
        (str/join \newline
                  (map #(df- (Files/getFileStore (.toPath (File. %)))) paths))))))