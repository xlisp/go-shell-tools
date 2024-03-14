## Go语言写的日常笔记整理脚本, 代码搜索/生成脚本
* 第一版会Clojure来写(babashka), 然后翻译为Go语言高性能版本

---

- [x] TODO1 写一个zshrc记录笔记解析为大纲markdown的脚本
  - [x] Clojure版本: parse_zshrc_note_to_md_outline.clj (lambda化快速演算) => bb 版本：`./zshrc2md.clj`
  - [x] Go版本： 第一个实用的go写的zshrc笔记解析为markdown大纲 zshrc2md.go
- [ ] TODO2 写一个outline文本解析为hash tree的格式
  - [] Clojure版本：递归函数式来完成parse_outline.clj
  - [] Go版本：for循环过程式来表达


--- 
* TODO1: 补充
格式如：
```bash
## aaa
## aaabbb => bbb => ccc
## ee => fff
```
=>

```md
- aaa
- aaabbb
    - bbb
        - ccc
- ee
    - fff
```
* TODO2: 补充
```md
- aaabbb
    - bbb
        - ccc
- ee
    - fff
```
=>
```clojure
[{:text "aaabbb" :child {:text "bbb" :child {:text "ccc" :child {}}}}
 {:text "ee" :child {:text "fff" :child {}}}]
```
