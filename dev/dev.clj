(ns dev
  (:require
    [clojure.java.io :as io]
    [clojure.pprint :refer [pprint]]
    [clojure.tools.namespace.repl :as repl :refer [refresh refresh-all]]
    [clojure.spec.alpha :as spec]
    [clojure.spec.gen.alpha :as gen]
    [orchestra.spec.test :as stest]
    [ye.core :as core]))

(stest/instrument)

(defn reset []
  (refresh))

(comment
  (reset))
