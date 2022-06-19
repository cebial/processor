package processor

fun readNums() = readln().split(" ").map { it.toInt() }

fun main() {
    val (rows1, cols1) = readNums()
    val matrix1 = List(rows1) { readNums() }

    val (rows2, cols2) = readNums()
    val matrix2 = List(rows2) { readNums() }

    if (rows1 != rows2 || cols1 != cols2) { print("ERROR"); return }

    for (y in 0 until rows1) {
        for (x in 0 until cols1) {
            print("${matrix1[y][x] + matrix2[y][x]} ")
        }
        println()
    }
}