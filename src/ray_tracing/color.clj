(ns ray-tracing.color)

(def color-max-level 255)

(defn normalize [color]
  (->> color
       (map (fn [[k v]] [k (/ v color-max-level)]))
       (into {})))

(defn colorize [color]
    (->> color
       (map (fn [[k v]] [k (* v color-max-level)]))
       (into {}))
