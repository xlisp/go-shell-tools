
(comment
  (def teststri "## aaa
## aaabbb => bbb => ccc
## ee => fff" )

  (def md-list
    (->>
      teststri
      clojure.string/split-lines
      (map
        (fn [stri]
          (map
            clojure.string/trim
            (->
              stri
              (clojure.string/replace-first #"##" "")
              (clojure.string/split  #"=>")))))))



  (doseq [li md-list]
    (let [lis  (map-indexed vector li)]
      (doseq [[n l] lis]
        ;; (prn l)
        (println
          (str  (clojure.string/join "" (repeat n "    "))
            "- "
            l))
        )
      )
    )
  ;; ===>>>
  ;; - aaa
  ;; - aaabbb
  ;;     - bbb
  ;;         - ccc
  ;; - ee
  ;;     - fff

  )
