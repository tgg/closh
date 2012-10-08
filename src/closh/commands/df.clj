;; Implementation of the DF command
(in-ns 'closh.utils)

(import [java.io File]
        [java.nio.file Files FileSystems]
        [java.net URI])

(defn kb [bytes]
  (/ bytes 1024))

(defn- storemap [store]
  ;; Filesystem Size Used Avail Use% Mounted on
  {:fs (.name store)
   :size (kb (.getTotalSpace store))
   :used (kb (- (.getTotalSpace store) (.getUnallocatedSpace store)))
   :avail (kb (.getUsableSpace store))})

(defn df
  ([]
     (map storemap (.. FileSystems getDefault getFileStores)))
  ([& paths]
     (map #(storemap (Files/getFileStore (.toPath (File. %)))) paths)))
