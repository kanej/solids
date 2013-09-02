(ns solids
  (:require [goog.dom :as dom]
            [goog.graphics.CanvasGraphics :as cg]
            [goog.graphics.Stroke :as Stroke]
            [goog.graphics.SolidFill :as SolidFill]
            [goog.graphics.Path :as Path]
            [goog.graphics :as graphics]))

(def purple-fill
  (goog.graphics.SolidFill. "#66b" 0.40))

(def purple-stroke (goog.graphics.Stroke. 4 "#66b"))

(defn draw-line [canvas [[x1 y1] [x2 y2] :as line]]
  (let [path (-> (graphics/Path.)
                 (.moveTo (+ 100 x1) (+ 100 y1))
                 (.lineTo (+ 100 x2) (+ 100 y2)))]
    (.drawPath canvas path purple-stroke nil)
    canvas))

(defn draw-lines [canvas lines]
  (doseq [line lines]
    (draw-line canvas line)))

(defn sqr [x]
  (* x x))

(defn distance-between [[x1 y1] [x2 y2]]
  (Math/sqrt (+ (sqr (- x2 x1)) (sqr (- y2 y1)))))

(defn transform [[x y] [x-offset y-offset]]
  [(+ x x-offset) (+ y y-offset)])

(defn make-cube [[x1 y1 :as p1] [x2 y2 :as p2]]
  (let [length (distance-between p1 p2)
        p3 (transform p1 [length length])
        p4 (transform p1 [0 length])
        p5 (transform p1 [40 -40])
        p6 (transform p5 [length 0])
        p7 (transform p5 [length length])
        p8 (transform p5 [0 length])]
    [[p1 p2]
     [p2 p3]
     [p3 p4]
     [p4 p1]

     [p5 p6]
     [p6 p7]
     [p7 p8]
     [p8 p5]

     [p1 p5]
     [p2 p6]
     [p3 p7]
     [p4 p8]]))

(defn main []
  (let [cube-element (dom/getElement "cube")
        cube-lines (make-cube [0 0] [100 0])]
    (doto (graphics/createGraphics 600 400)
      (draw-lines cube-lines)
      (.render (dom/getElement "cube")))))

