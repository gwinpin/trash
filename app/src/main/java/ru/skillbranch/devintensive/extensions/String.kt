package ru.skillbranch.devintensive.extensions

fun String.truncate(length: Int = 16): String {
    val lengthRange = 0 until length
    if (this.length < length - 1) return this
    return this.slice(lengthRange).trim() + if (this.trim().length > length) "..." else ""

}

fun String.stripHtml(): String {

    val html = """<[\/\!]*?[^<>]*?>""".toRegex()
    val escape = "\\s+".toRegex()
    var str = html.replace(this, "")
    str = escape.replace(str, " ")

    return str
}