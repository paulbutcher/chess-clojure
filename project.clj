(defproject chess-clojure "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/math.numeric-tower "0.0.2"]]
  :main chess-clojure.core
  :jvm-opts ["-Xms4G" "-Xmx4G" "-XX:NewRatio=8"])
