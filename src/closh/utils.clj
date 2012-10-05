(ns closh.utils
  (:require [clojure.string :as str])
  (:import [java.nio.file FileSystems]
           [java.net URI]))

(defn kb [bytes]
  (/ bytes 1024))

(defn df [path]
  (let [fs (FileSystems/getFileSystem (URI. (str "file://" path)))]
    (str/join \newline
          (map #(format "%s %d %d" (.name %) (kb (.getUnallocatedSpace %)) (kb (.getTotalSpace %)))
               (.getFileStores fs)))))
