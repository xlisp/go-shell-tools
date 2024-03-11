## Go 语言写的日常笔记和代码搜索/生成脚本
* 第一版会Clojure来写(babashka), 然后翻译为Go语言高性能版本

- [ ] 写一个zshrc记录笔记解析为大纲markdown的脚本

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

