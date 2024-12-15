fun inputFromFile(filename: String): Input =
    object {}.javaClass.getResourceAsStream(filename)?.bufferedReader()?.readLines() ?: emptyList()

fun inputFromText(text: String): Input = text.trimIndent().split("\n")

typealias Input = List<String>
fun Input.beforeBlankLine(): Input = this.takeWhile { it.isNotBlank() }
fun Input.afterBlankLine(): Input = this.dropWhile { it.isNotBlank() }.drop(1)
