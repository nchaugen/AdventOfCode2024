object Input {
    fun fromFile(filename: String) =
        object {}.javaClass.getResourceAsStream(filename)?.bufferedReader()?.readLines() ?: emptyList()

    fun fromText(text: String): List<String> = text.trimIndent().split("\n")
}
