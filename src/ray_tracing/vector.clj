(ns ray-tracing.vector)

(defn length [{x :x y :y z :z}]
  (let [square (fn [a] (* a a))
        sum-of-sqrs (+ (square x) (square y ) (square z))]
    (Math/sqrt sum-of-sqrs)))

(defn ->unit-vector [vec]
  (let [len (length vec)]
    (->> vec
         (map (fn [[key val]] [key (/ val len)]))
         (into {}))))
