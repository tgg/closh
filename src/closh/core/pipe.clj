(ns closh.core
  (:require [clojure.java.io :as io]))

;; TOSEE: for some reason I was not able to use io/copy
(defn pipe [left right]
  (.start (Thread. (fn []
                     (with-open [in (.getInputStream left)
                                 out (.getOutputStream right)]
                       (loop []
                         (let [byte (.read in)]
                           (when (not (= -1 byte))
                             (do (.write out byte)
                                 (recur))))))))))

(defmacro | [left right]
  `(let [left# (ProcessBuilder. (into-array String [~@(map #(str %) left)]))
         right# (ProcessBuilder. (into-array String [~@(map #(str %) right)]))]
     (.redirectInput left# java.lang.ProcessBuilder$Redirect/INHERIT)
     (.redirectError left# java.lang.ProcessBuilder$Redirect/INHERIT)
     (.redirectOutput right# java.lang.ProcessBuilder$Redirect/INHERIT)
     (.redirectError right# java.lang.ProcessBuilder$Redirect/INHERIT)
     (pipe (.start left#) (.start right#))))


(comment (| (ls "/home/tgg") (grep deb)))

;; use bindings?