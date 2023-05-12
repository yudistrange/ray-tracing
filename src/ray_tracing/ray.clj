(ns ray-tracing.ray
  (:require [ray-tracing.vector :as v]))

(def aspect-ratio (/ 16 9))
(def width 200)
(def height (int (/ width aspect-ratio)))

(def viewport-height 2)
(def viewport-width (int (* viewport-height aspect-ratio)))

(defn pixel-rays []
  (for [x    (range (- width) width)
        y    (range (- height) height)
        :let [u (/ x width 2)
              v (/ y height 2)
              z 1]]
    {:x u :y v :z z}))

(defstruct Vector :x :y :z)
(defstruct Color :red :blue :green)
(defstruct Ray :origin :direction)

(defprotocol Body
  (hit? [this ray]))

(defrecord Sphere [x y z r]
  Body
  (hit? [sphere ray]
    false))


(defn direction [{x1 :x y1 :y z1 :z} {x2 :x y2 :y z2 :z}]
  (let [dx (- x1 x2)
        dy (- y1 y2)
        dz (- z1 z2)
        modulus (Math/sqrt (+ (* dx dx) (* dy dy) (* dz dz)))]
    {:x (/ dx modulus) :y (/ dy modulus) :z (/ dz modulus)}))

(defn generate-viewport-rays
  "Generates a vector of rays going from viewport to the canvas"
  [viewport-height viewport-width canvas-height canvas-width canvas-depth]
  (for [vx (range (- viewport-width) viewport-width)
        vy (range (- viewport-height) viewport-height)
        :let [dir (direction {:x vx :y vy :z 0} {:x canvas-width :y canvas-height :z canvas-depth})]]
    (struct Ray {:x vx :y vy :z 0} dir)))


(defn ray-color [ray ]
  )

(defn pixel-colors []
  (let [rays (pixel-rays)]
    (map (fn [ray]
           (let [normailzed-ray (v/->unit-vector ray)
                 y-coord        (:x normailzed-ray)
                 factor         (* 0.5 (+ y-coord 1))]
             (str (int (* 255 (+ (- 1.0 factor)
                                 (* factor 0.5))))
                  " "
                  (int (* 255 (+ (- 1.0 factor)
                                 (* factor 0.7))))
                  " "
                  (int (* 255 (+ (- 1.0 factor)
                                 factor))))))
         rays)))
