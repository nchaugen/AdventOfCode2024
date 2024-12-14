fun List<Int>.product(): Int = this.reduce { a, b -> a * b }
fun List<Long>.product(): Long = this.reduce { a, b -> a * b }
