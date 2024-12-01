object Input {
    fun linesFrom(filename: String) =
        object {}.javaClass.getResourceAsStream(filename)?.bufferedReader()?.readLines() ?: emptyList()
}
