package main

import (
	"fmt"
	"strings"
)

func main() {
	var input string
	fmt.Println("请输入要解析的字符串：")
	fmt.Scanln(&input)

	output := parseToMarkdown(input)
	fmt.Println(output)
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
