package processor

class Processor {
    private var doingDouble = false

    inner class Matrix {
        private var matrix: List<List<Double>>
        val rows: Int get() = matrix.size
        val cols: Int get() = matrix[0].size

        constructor(rows: Double) {
            matrix = List(rows.toInt()) { readln().also { doingDouble = '.' in it }.split(" ").map { it.toDouble() } }
        }

        constructor(rows: Int, cols: Int) {
            matrix = List(rows) { MutableList(cols){ 0.0 } }
        }

        operator fun get(row: Int, col: Int) = matrix[row][col]
        fun isEqualSize(other: Matrix) = rows == other.rows && cols == other.cols
        fun canCompose(other: Matrix) = cols == other.rows
        fun printTransformed(doThis: (x: Int, y: Int) -> Number) {
            println("The result is:")
            for (y in 0 until rows) {
                for (x in 0 until cols) {
                    print("${if (doingDouble) doThis(x, y) else doThis(x, y).toInt()} ")
                }
                println()
            }
        }
    }

    private fun readTwoMatrices() = readMatrix("first ") to readMatrix("second ")
    private fun readMatrix(prefix: String = "") : Matrix {
        print("Enter size of ${prefix}matrix:")
        val rows = readln().split(" ").map { it.toDouble() }[0]

        println("Enter ${prefix}matrix:")
        return Matrix(rows)
    }

    private fun addMatrices() {
        val (matrix1, matrix2) = readTwoMatrices()
        if (!matrix1.isEqualSize(matrix2)) { println("The operation cannot be performed."); return }

        matrix1.printTransformed { x, y -> matrix1[y, x] + matrix2[y, x] }
    }
    private fun scaleMatrix() {
        val matrix = readMatrix()

        print("Enter constant:")
        val multiplier = readln().toInt()

        matrix.printTransformed { x, y -> multiplier * matrix[y, x] }
    }

    private fun composeMatrices() {
        val (matrix1, matrix2) = readTwoMatrices()
        if (!matrix1.canCompose(matrix2)) { println("The operation cannot be performed."); return }

        println("The result is:")
        for (y in 0 until matrix1.rows) {
            for (x in 0 until matrix2.cols) {
                var product = 0.0
                for (t in 0 until matrix1.cols) {
                    product += matrix1[y, t] * matrix2[t, x]
                }
                print("${if (doingDouble) product else product.toInt()} ")
            }
            println()
        }   
    }

    private fun showMenu() = print(
        """ 
        |1. Add matrices
        |2. Multiply matrix by a constant
        |3. Multiply matrices
        |0. Exit
        |Your choice:
        """.trimMargin()
    )

    fun run() {
        while (true) {
            showMenu()
            when (readln().toInt()) {
                1 -> addMatrices()
                2 -> scaleMatrix()
                3 -> composeMatrices()
                else -> return
            }
            println()
        }
    }
}

fun main() = Processor().run()
