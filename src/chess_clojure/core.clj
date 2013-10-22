(ns chess-clojure.core
  (:require [clojure.math.numeric-tower :refer [abs]]
            [clojure.string :refer [join]]))

(defn takes? [piece [dx dy]]
  (case piece
    :K (and (<= dx 1) (<= dy 1))
    :Q (or (zero? dx) (zero? dy) (= dx dy))
    :R (or (zero? dx) (zero? dy))
    :B (= dx dy)
    :N (or (and (= dx 1) (= dy 2)) (and (= dx 2) (= dy 1)))))

(defn allows? [piece [px py] [x y]]
  (let [delta [(abs (- px x)) (abs (- py y))]]
    (and (not (and (= px x) (= py y))) (not (takes? piece delta)))))

(defn allowed? [candidate [x y] solution]
  (every?
    (fn [[piece pos]] 
      (and (allows? piece pos [x y]) (allows? candidate [x y] pos)))
    solution))

(defn solve [rows cols pieces]
  (if (empty? pieces)
    #{#{}}
    (set 
      (let [candidate (first pieces)]
        (for [solution (solve rows cols (rest pieces))
              x (range 0 cols)
              y (range 0 rows)
              :when (allowed? candidate [x y] solution)]
          (conj solution [candidate [x y]]))))))

(defn -main [& args]
  ; (let [solution (solve 3 3 [:K :K :R])]
  ; (let [solution (solve 4 4 [:R :R :N :N :N :N])]
  (let [solution (solve 6 9 [:K :K :N :R :B :Q])]
    ; (println (join "\n" solution))
    (println (count solution))))
