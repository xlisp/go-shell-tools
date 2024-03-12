#!/usr/bin/env bb

(defn zshrc-file->list
  [file]
  (->>
    (slurp file)
    clojure.string/split-lines
    (filter #(re-find #"^##" % ))
    (map
      (fn [stri]
        (map
          clojure.string/trim
          (->
            stri
            (clojure.string/replace-first #"##" "")
            (clojure.string/split  #"=>|=》")))))))

(defn md-list->print-outline
  [md-list]
  (doseq [li md-list]
    (let [lis  (map-indexed vector li)]
      (doseq [[n l] lis]
        (println
          (str  (clojure.string/join "" (repeat n "    ")) "- " l))))))

(md-list->print-outline (zshrc-file->list "/Users/lisp/.zshrc"))

