package me.kzv.legacyboard

import java.util.regex.Matcher
import java.util.regex.Pattern


fun main() {
    var content = "<h1>extract img src test</h1> <p>hello world! <br/> <img src=\"www.naver.com/img.jpg\" alt=\"이미지 태그\"></p>"
    for(i:Int in 1..10) content += content
    extractImgSrc(content)
}

fun extractImgSrc(content: String) {
    val pattern: Pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>")
//        val pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>")
    val matcher: Matcher = pattern.matcher(content)
    while (matcher.find()) {
        println(matcher.group(1))
    }
}
