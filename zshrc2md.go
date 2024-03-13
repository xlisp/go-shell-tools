package main

import (
	"fmt"
	"strings"
)

func mapStr(arr []string, fn func(it string) string) []string {
	var newArray = []string{}
	for _, it := range arr {
		newArray = append(newArray, fn(it))
	}
	return newArray
}

func parseToMarkdown(input string) string {
	lines := strings.Split(input, "\n")
	var output strings.Builder

	for _, line := range lines {
		line = strings.TrimSpace(line)
		if strings.HasPrefix(line, "# ") {
			output.WriteString(fmt.Sprintf("#%s\n\n", line[2:]))
		} else if strings.HasPrefix(line, "## ") {
			output.WriteString(fmt.Sprintf("##%s\n\n", line[3:]))
		} else if strings.HasPrefix(line, "* ") {
			output.WriteString(fmt.Sprintf("*%s\n", line[2:]))
		} else {
			output.WriteString(fmt.Sprintf("%s\n", line))
		}
	}

	return output.String()
}

func main() {
	//	var input string
	//	fmt.Println("请输入要解析的字符串：")
	//	fmt.Scanln(&input)
	//
	//	output := parseToMarkdown(input)
	//	fmt.Println(output)

	var stri = "## aaa\n## aaabbb => bbb => ccc\n## ee => fff "

	//var list = []string{"Orange", "Apple", "Banana", "Grape"}
	//var out = mapStr(list, func(it string) string {
	//	return it + "==="
	//})
	lines := strings.Split(stri, "\n")

	//fmt.Println(lines)
	for _, line := range lines {
		outlines := strings.Split(strings.Replace(line, "##", "", 1), "=>")
		//fmt.Println(len(outline)) // 1, 3, 2
		for num, outline := range outlines {
			//fmt.Println(num)
			//fmt.Println(outline)
			// := 是类型推导的意思，赋值。
			oline := strings.TrimSpace(outline)
			
			if num == 0 {
				fmt.Println("- " + oline)
			} else if num == 1 {
				fmt.Println("- " + "    " + oline)
			} else if num == 2 {
				fmt.Println("- " + "        " + oline)
			}
		}
	}
	// - aaa
	// - aaabbb
	// -     bbb
	// -         ccc
	// - ee
	// -     fff
	// 	

}

