(ns clj-config-ini.core
  (:require [clojure.string :as string]
            [clojure.tools.logging :use [debugf]])
  (:import (org.apache.commons.configuration HierarchicalINIConfiguration)
           (java.io StringWriter)
  ))

(declare fill-config fill-section get-section set-property set-comment)

(defn serialize
  "Formats a config map as a hierarchical ini file"
  [params-map]
  (let [config (HierarchicalINIConfiguration.)
        writer (StringWriter.)]
    (do
      (fill-config config params-map)
      (.save config writer)
      (.toString writer))))

(defn parse []
  "")

(defn- fill-config
  "Fills a hiearchical ini config with data from a nested vector"
  [config params-doc]
  (doseq [entry params-doc]
    (cond
      (vector? entry)
          (let [[k v] entry]
            (if (vector? v) (fill-section (get-section config k) v)
                (set-property config k v)))
      (list? entry) (set-comment config entry))))

(defn- fill-section
  "Fills the given ini section with properties from a nested vector"
  [section params-doc]
  (doseq [entry params-doc]
    (cond
    (vector? entry) (let [[k v] entry] (set-property section k v))
    (list? entry) (set-comment section entry))))

(defn- get-section [config name]
  (let [section-name (str name)]
  (.getSection config section-name)))

(defn- set-comment [config comment-form]
  (let [comment (string/join " " comment-form)]
  (.setComment config comment)))

(defn- set-property [config key value]
  (let [property-name (str key)]
    (.setProperty config property-name value)))
