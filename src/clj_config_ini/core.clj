(ns clj-config-ini.core
  (:import (org.apache.commons.configuration HierarchicalINIConfiguration)
           (java.io StringWriter)
  ))

(declare fill-config fill-section get-section set-property)

(defn format-config
  "Formats a config map as a hierarchical ini file"
  [params-map]
  (let [config (HierarchicalINIConfiguration.)
        writer (StringWriter.)]
    (do
      (fill-config config params-map)
      (.save config writer)
      (.toString writer))))

(defn- fill-config
  "Fills a hiearchical ini config with data from a nested map"
  [config params-map]
  (doseq [[k v] params-map]
    (if (map? v) (fill-section (get-section config k) v)
      (set-property config k v))))

(defn- fill-section
  "Fills the given ini section with properties from a map"
  [section params-map]
  (doseq [[k v] params-map]
    (set-property section k v)))

(defn- get-section [config name]
  (let [section-name (str name)]
  (.getSection config section-name)))

(defn- set-property [config key value]
  (let [property-name (str key)]
    (.setProperty config property-name value)))
