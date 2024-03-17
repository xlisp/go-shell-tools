(comment

  ;;
  (def t1
    "a
    b
        c
")

  ;; === Yang： 只要你能描述清楚逻辑，已经完成了80%的工作了，剩下是20%写代码

  ;; 1. 取出来缩进每行是多少。
  (def outline
    (let [text t1]
      (let [lines (clojure.string/split text #"\n")
            outline (into [] (for [line lines]
                               (let [indent-level (count (re-seq #" " line))
                                     ;; content (clojure.string/trim (subs line indent-level))
                                     content (clojure.string/trim line)]
                                 ;; 最先数列中找到通项公式： 从不断列出高阶化中找到前一个和后一个的变换关系， 如 n1 = X * n0， x
                                 {:indent indent-level
                                  :content content})))
            formatted-outline {}
            current-parent nil]
        outline)))
  ;; => [{:indent 0, :content "a"}
  ;;     {:indent 4, :content "b"}
  ;;     {:indent 8, :content "c"}]

  ;; 2. 插入下一级child，还是同级？ => indent 递增就是插入子级，大于它就是另外的级别。=》 如果一直连续的递增，那就是递归下去了。如果大于当前indent就是跳出递归了。

  ((fn [n]
     ;; 递归起始条件
     (loop
         [cnt n
          acc {}]
         (if (zero? (count cnt)) ;; 递归停止条件是什么: 递增和递减是怎样的，才能到递归停止条件输出。 => 让他爆了你才能知道出了什么问题停不下来
           acc
           ;; 递归下去什么：递减了什么（损了什么）
           #_(recur
               ;; (dec cnt) ;; 损了什么
               (next outline)
               ;; (* acc cnt) ;; 增了什么，转移
               (assoc cnt {:content (:content (first outline))})
               )
           (let [ffirst (first outline)
                 llast (next outline)
                 current-parent (first acc)]
             (if (= (:indent ffirst) 0)
               (recur
                 llast
                 (assoc acc :content (:content ffirst) :child {}))
               (let [new-child ;; (assoc {} (:content item) {})
                     {:content (:content ffirst) :child {}}
                     updated-parent (update-in acc
                                      (conj (:content current-parent)
                                        (:content ffirst)) merge new-child)
                     _ (prn updated-parent "-----" new-child)]
                 (recur llast updated-parent)
                 )
               )
             )
           )))
   outline
   )
  ;; (first outline)
  ;; => 120

  (defn parse-outline [text]
    (let [lines (clojure.string/split text #"\n")
          outline (into [] (for [line lines]
                             (let [indent-level (count (re-seq #" " line))
                                   content (clojure.string/trim line)]
                               {:indent indent-level
                                :content content})))
          
          current-parent nil]
      (loop [ ;; item outline
             formatted-outline {}
             item (first outline)]
        (if (nil? current-parent)
          (if (= (:indent item) 0)
            (recur (assoc formatted-outline (:content item) {})
              (next outline))
            (throw (Exception. "Invalid outline format")))
          (if (> (:indent item) (:indent current-parent))
            (let [new-child (assoc {} (:content item) {})
                  updated-parent (update-in formatted-outline (conj (:content current-parent) (:content item)) merge new-child)]
              (recur formatted-outline updated-parent))
            (recur formatted-outline (:content item))))

        ;; formatted-outline
        )))

  (parse-outline t1)
  ;; 
  
  )
