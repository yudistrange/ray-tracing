(ns ray-tracing.first-camera)

(defn vector-length [])

(defn ray-color [ray]
  (let [{origin :origin direction :direction} ray]
    (prn origin)
    (prn direction)))
