(ns ye.core
  (:require
   [clojure.tools.cli :as cli]
   [clojure.string :as string]
   [clojure.java.io :as io]
   [clojure.edn :as edn]
   [yaml.core :as yaml]
   [jsonista.core :as jsonista]
   [clojure.spec.alpha :as spec]
   [expound.alpha :as expound])
  (:gen-class))

(set! *warn-on-reflection* true)

(def cli-options
  [["-f" "--from TYPE" "From type"
    :id :from-type
    :parse-fn #(keyword (string/trim %))
    :default :yaml]
   ["-t" "--to TYPE" "To type"
    :id :to-type
    :parse-fn #(keyword (string/trim %))
    :default :edn]
   ["-h" "--help" :id :help?]])

(spec/def ::allowed-types #{:edn :yaml :json})
(spec/def ::help? boolean?)
(spec/def ::from-type ::allowed-types)
(spec/def ::to-type ::allowed-types)
(spec/def ::options
  (spec/keys
   :opt-un [::from-type ::to-type ::help?]))
(spec/def ::arguments
  (spec/coll-of string? :kind vector? :count 1))
(spec/def ::summary string?)
(spec/def ::errors
  (spec/or
   :nil nil?
   :vec vector?))
(spec/def ::parsed-result
  (spec/keys
   :req-un [::options ::arguments]
   :opt-un [::summary ::errors]))

(defn parse-string-fn
  [from-type]
  (from-type
   {:edn edn/read-string
    :yaml #(yaml/parse-string % :keywords true)
    :json jsonista/read-value}))

(defn generate-string-fn
  [to-type]
  (to-type
   {:edn identity
    :yaml yaml/generate-string
    :json jsonista/write-value-as-string}))

(defn safe-read
  [file]
  (when (.exists (io/file file))
    (slurp file)))

(defn process
  [from-type to-type file]
  (let [body (safe-read file)
        parse-string (parse-string-fn from-type)
        generate-string (generate-string-fn to-type)]
    (-> body
        (parse-string)
        (generate-string))))

(defn main
  [{:keys [options arguments summary errors] :as parsed-result}]
  (let [{:keys [from-type to-type help?]} options]
    (cond
      help?
      (println summary)
      (spec/valid? ::parsed-result parsed-result)
      (-> (process from-type to-type (first arguments))
          (println))
      :else
      (do
        (println "Invalid arguments or options")
        (expound/expound ::parsed-result parsed-result)
        (println "Usage:")
        (println summary)
        (System/exit 1)))))

(defn -main
  [& args]
  (-> args
      (cli/parse-opts cli-options)
      (main)))

(comment
  (-> ["-t yaml" "filename"]
      (cli/parse-opts cli-options)
      (main))
  )
