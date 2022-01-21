package lv3

class Disappearing_floor {

    var max = 0

    fun solution(board: Array<IntArray>, aloc: IntArray, bloc: IntArray): Int {
        moveA(board,aloc,bloc,0)
        board.forEach {
            it.forEach { print("$it ") }
            println()
        }
        return max
    }

    fun moveA(board : Array<IntArray>, aloc: IntArray, bloc: IntArray ,cnt : Int){
        val i = aloc[0]
        val j = aloc[1]
        val newBoard = board.map { it.clone() }.toTypedArray()

        max = kotlin.math.max(cnt, max)

        if (board[i][j]==0) return

        newBoard[i][j] = 0
        if (i > 0 && board[i-1][j] == 1){
            moveB(newBoard, intArrayOf(i-1,j),bloc,cnt+1)
        }
        if (i < board.lastIndex && board[i+1][j] == 1){
            moveB(newBoard, intArrayOf(i+1,j),bloc,cnt+1)
        }
        if (j > 0 && board[i][j-1] == 1){
            moveB(newBoard, intArrayOf(i,j-1),bloc,cnt+1)
        }
        if (j<board[i].lastIndex&& board[i][j+1] == 1){
            moveB(newBoard, intArrayOf(i,j+1),bloc, cnt+1)
        }
    }

    fun moveB(board: Array<IntArray>,aloc: IntArray, bloc: IntArray, cnt: Int ){
        val i = bloc[0]
        val j = bloc[1]
        max = kotlin.math.max(cnt, max)
        val newBoard = board.map { it.clone() }.toTypedArray()

        if (board[i][j]==0) return
        newBoard[i][j] = 0
        if (i > 0 && board[i-1][j] == 1){
            moveA(newBoard, aloc ,intArrayOf(i-1,j),cnt+1)
        }
        if (i < board.lastIndex && board[i+1][j] == 1){
            moveA(newBoard, aloc, intArrayOf(i+1,j),cnt+1)
        }
        if (j > 0 && board[i][j-1] == 1){
            moveA(newBoard, aloc,intArrayOf(i,j-1),cnt+1)
        }
        if (j<board[i].lastIndex&& board[i][j+1] == 1){
            moveA(newBoard, aloc,intArrayOf(i,j+1), cnt+1)
        }
    }
}