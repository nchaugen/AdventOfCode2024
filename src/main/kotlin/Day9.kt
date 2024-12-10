import kotlin.collections.plus

object Day9 {

    fun compactBlocks(input: Input): Long =
        computeChecksum(compactBlocks(parse(input)))

    fun compactFiles(input: Input): Long =
        parseSpans(parse(input)).let { (files, freeSpans) ->
            computeChecksum(compactFiles(files, freeSpans))
        }

    private fun compactFiles(files: List<Span>, freeSpans: List<Span>): List<Block> =
        files.fold(emptyList<Block>() to freeSpans) { (compacted, free), (_, fileBlocks) ->
            free.findIndexOfFit(fileBlocks)
                ?.let { index ->
                    compacted + fileBlocks.moveTo(free[index]) to free.dropBlocks(index, fileBlocks.count())
                } ?: (compacted + fileBlocks to free)
        }.first

    private fun List<Block>.moveTo(free: Span): List<Block> =
        zip(free.blocks) { fileBlock, freeBlock -> freeBlock.position to fileBlock.fileNumber }

    private fun List<Span>.findIndexOfFit(fileBlocks: List<Block>): Int? =
        indexOfFirst {
            it.blocks.count() >= fileBlocks.count() && it.blocks.first().position < fileBlocks.first().position
        }.let { if (it == -1) null else it }

    private fun List<Span>.dropBlocks(index: Int, count: Int): List<Span> =
        take(index) + this[index].let { it.id to it.blocks.drop(count) } + takeLast(lastIndex - index)

    private fun compactBlocks(blocks: List<Block>): List<Block> =
        blocks.gaps().let { gaps ->
            blocks.data().let { data ->
                data.reversed()
                    .zip(gaps)
                    .mapNotNull { (data, gap) ->
                        if (data.position > gap.position) gap.position to data.fileNumber else null
                    }.let { movedBlocks ->
                        data.dropLast(movedBlocks.count()) + movedBlocks
                    }
            }
        }

    private fun computeChecksum(blocks: List<Block>): Long =
        blocks.fold(0L) { checksum, block ->
            if (block.fileNumber >= 0) checksum + (block.position * block.fileNumber)
            else checksum
        }

    private fun parseSpans(blocks: List<Block>): Pair<List<Span>, List<Span>> =
        blocks.groupBy { block -> block.fileNumber }.let { diskMap ->
            diskMap.filterKeys { fileNumber -> fileNumber >= 0 }
                .entries.sortedByDescending { it.key }
                .map { it.key to it.value }
                .let { files ->
                    diskMap.filterKeys { fileNumber -> fileNumber < 0 }
                        .entries.sortedByDescending { it.key }
                        .map { it.key to it.value }
                        .let { freeSpans -> files to freeSpans }
                }
        }

    private fun parse(input: Input): List<Block> =
        input.flatMap {
            it.map { it.digitToInt() }
                .mapIndexed { index, digit -> if (index % 2 == 0) (index / 2) to digit else -((index / 2) + 1) to digit }
                .flatMap { (fileNumber, digit) -> List(digit) { i -> fileNumber } }
                .mapIndexed { position, fileNumber -> position to fileNumber }
        }

    private fun List<Block>.gaps(): List<Block> = this.filter { it.fileNumber < 0 }
    private fun List<Block>.data(): List<Block> = this.filter { it.fileNumber >= 0 }
}

private typealias Block = Pair<Int, Int>

private val Block.position: Int
    get() = this.first

private val Block.fileNumber: Int
    get() = this.second

private typealias Span = Pair<Int, List<Block>>

private val Span.id: Int
    get() = this.first

private val Span.blocks: List<Block>
    get() = this.second
