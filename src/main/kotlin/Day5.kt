object Day5 {

    fun sumOfCorrectRules(input: List<String>): Int =
        parse(input).let { (rules, updates) ->
            updates
                .filter { rules.isCorrect(it) }
                .sumOf { it[it.size / 2] }
        }

    fun sumOfIncorrectRules(input: List<String>): Int =
        parse(input).let { (rules, updates) ->
            updates
                .filterNot { rules.isCorrect(it) }
                .map { it.sortedWith { a, b -> rules.compare(a, b) } }
                .sumOf { it[it.size / 2] }
        }

    private fun Rules.compare(a: Int, b: Int): Int =
        if (a in this.mustBeAfter(b)) 1 else -1

    private fun Rules.isCorrect(update: List<Int>): Boolean =
        update.foldIndexed(true) { i, valid, page ->
            valid && update.slice(0..i).none { it -> it in this.mustBeAfter(page) }
        }

    private fun Rules.mustBeAfter(page: Int) =
        this.filter { it.first == page }.map { it.second }

    private fun parse(input: List<String>): Pair<Rules, Updates> =
        (input.takeWhile(String::isNotBlank).let(::parseRules)
                to input.takeLastWhile(String::isNotBlank).let(::parseUpdates))

    private fun parseRules(input: List<String>): Rules =
        input.map {
            it.split('|').map(String::toInt).let { it.first() to it.last() }
        }

    private fun parseUpdates(input: List<String>): Updates =
        input.map { it -> it.split(',').map(String::toInt) }
}


private typealias Updates = List<List<Int>>
private typealias Rules = List<Pair<Int, Int>>
