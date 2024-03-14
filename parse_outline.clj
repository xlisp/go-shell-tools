(def factorial
  (fn [n]
    (loop [cnt n
           acc 1]
      (if (zero? cnt)
        acc
        (recur (dec cnt) (* acc cnt))))))

;; 如果下一个比上一个level值更高，那就swap加到它的子集里面，如果小于它就不加进去，同级也不加进去。
"
a
 b
  c
"


(defn parse-outline [text]
  (let [lines (clojure.string/split text #"\n")
        outline (into [] (for [line lines]
                           (let [indent-level (count (re-find #"^(\s*)" line))
                                 content (clojure.string/trim (subs line indent-level))]
                             {:indent indent-level :content content})))
        formatted-outline {}
        current-parent nil]

    (loop [item outline]
      (if (nil? current-parent)
        (if (= (:indent item) 0)
          (assoc formatted-outline (:content item) {})
          (throw (Exception. "Invalid outline format")))
        (if (> (:indent item) (:indent current-parent))
          (let [new-child (assoc {} (:content item) {})
                updated-parent (update-in formatted-outline (conj (:content current-parent) (:content item)) merge new-child)]
            (recur formatted-outline updated-parent))
          (recur formatted-outline (:content item))))

      formatted-outline)))
