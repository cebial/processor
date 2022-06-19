package processor

fun readNums() = readln().split(" ").map { it.toInt() }

fun main() {
    val (rows, cols) = readNums()
    val matrix = List(rows) { readNums() }

    val multiplier = readln().toInt()

    for (y in 0 until rows) {
        for (x in 0 until cols) {
            print("${multiplier * matrix[y][x]} ")
        }
        println()
    }
}
