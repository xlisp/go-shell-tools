## Go语言写的日常笔记整理脚本, 代码搜索/生成脚本
* 第一版会Clojure来写(babashka), 然后翻译为Go语言高性能版本

---

- [x] 写一个zshrc记录笔记解析为大纲markdown的脚本
  - [x] Clojure版本已经完成: parse_zshrc_note_to_md_outline.clj (lambda化快速演算) => bb 版本：`./zshrc2md.clj`
  - [x] Go版本： 第一个实用的go写的zshrc笔记解析为markdown大纲 zshrc2md.go

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
