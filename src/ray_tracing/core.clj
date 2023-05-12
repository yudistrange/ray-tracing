(ns ray-tracing.core)

(def header ["P3" "255 55" "255"])
(def pixels (for [x (range 255) y (range 55)] (str x " " 63 " " 0)))

(def fancy-pixels (for [x (range 255) y (range 255)] (str x " " y " " (int  63.05))))

(defn write-content [file-name content option]
  (let [append?             (:append? option false)
        stringified-content (->> content
                                 (map (fn [s] (str s "\n")))
                                 (apply str))]
    (spit file-name stringified-content :append append?)))

(defn create-ppm-file [file-name headers pixels]
  (do
    (write-content file-name headers {})
    (write-content file-name pixels {:append? true})))

(create-ppm-file "/tmp/test.ppm" header pixels)

(defrecord Color [r g b])

(defrecord Pixel3D [x y z])

(defrecord Vector [x y z])

(defrecord Direction [x y z])

(defmulti add (fn [a b] [(type a) (type b)]))

(defmethod add Color Color [c1 c2]
  (->Color (+ (:x c1) (:x c2))
           (+ (:y c1) (:y c2))
           (+ (:z c1) (:z c2))))
