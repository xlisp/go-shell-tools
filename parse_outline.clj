;; 最先数列中找到通项公式： 从不断列出高阶化中找到前一个和后一个的变换关系， 如 n1 = X * n0， x1 = X * ( X * n0) ...
(def factorial
  (fn [n]
    ;; 递归起始条件
    (loop
        [cnt n 
         acc 1]
        (if (zero? cnt) ;; 递归停止条件是什么
          acc
          ;; 递归下去什么
          (recur
            (dec cnt)
            (* acc cnt))))))

;; 如果下一个比上一个level值更高，那就swap加到它的子集里面，如果小于它就不加进去，同级也不加进去。
(def t1
"a
    b
        c
")


(let [text t1]
  (let [lines (clojure.string/split text #"\n")
        _ (prn lines)
        outline (into [] (for [line lines]
                           (let [indent-level (count (re-find #"(\s){3}" line))
                                 ;; content (clojure.string/trim (subs line indent-level))
                                 content (clojure.string/trim line)]
                             {:indent indent-level
                              :content content})))
        formatted-outline {}
        current-parent nil]
    outline
    ))



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
