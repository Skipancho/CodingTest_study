package lv4
/*
행렬과 연산
https://school.programmers.co.kr/learn/courses/30/lessons/118670
 */
class MatrixAndCalculation {

    private var r = 0
    private var c = 0
    private val col1 by lazy { ArrayDeque<Int>() }
    private val col2 by lazy { ArrayDeque<Int>() }
    private val rows by lazy { mutableListOf<ArrayDeque<Int>>() }

    fun solution(rc: Array<IntArray>, operations: Array<String>): Array<IntArray> {
        initData(rc)
        for (op in operations) {
            when (op) {
                "Rotate" -> rotate()
                else -> shiftRow()
            }
        }
        return getArray()
    }

    private fun initData(rc: Array<IntArray>) {
        r = rc.size
        c = rc[0].size

        for (i in rc.indices) {
            col1.add(rc[i][0])
            col2.add(rc[i][c - 1])
        }

        for (i in rc.indices) {
            val tmp = ArrayDeque<Int>()
            for (j in 1 until c - 1) {
                tmp.add(rc[i][j])
            }
            rows.add(tmp)
        }
    }

    private fun rotate() {
        rows.first().addFirst(col1.removeFirst())
        col2.addFirst(rows.first().removeLast())
        rows.last().addLast(col2.removeLast())
        col1.addLast(rows.last().removeFirst())
    }

    private fun shiftRow() {
        val tmp = rows.removeAt(rows.lastIndex)
        rows.add(0,tmp)
        col1.addFirst(col1.removeLast())
        col2.addFirst(col2.removeLast())
    }

    private fun getArray(): Array<IntArray> {
        val array = Array(r) { IntArray(c) }
        for (i in 0 until r){
            array[i][0] = col1.removeFirst()
            array[i][c-1] = col2.removeFirst()
        }
        for (i in 0 until r){
            val row = rows[i]
            for (j in 1 until c-1){
                array[i][j] = row.removeFirst()
            }
        }
        return array
    }
}