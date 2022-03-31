package lv3.road_make

class RoadMake {
    private val dx = intArrayOf(1,0,-1,0)//오,밑,왼,위
    private val dy = intArrayOf(0,1,0,-1)
    private var answer = Int.MAX_VALUE
    private lateinit var dp : Array<Array<IntArray>>
    private lateinit var board : Array<IntArray>

    fun solution(board: Array<IntArray>): Int {
        if (board.sumOf { it.sum() } == 0)
            return (board.size-1)*200 + 500
        this.board = board
        dp = Array(board.size){Array(board.size){IntArray(4){ Int.MAX_VALUE}}}
        move(0,0, Array(board.size){ BooleanArray(board.size){false} },0,0)
        return answer
    }

    private fun move(x : Int, y : Int, visited : Array<BooleanArray>,cost : Int, d : Int){
        if (cost > answer) return

        if (x == board.lastIndex && y == board.lastIndex){
            if (cost < answer){
                answer = cost
            }
            return
        }

        val newVisit = visited.map { it.clone() }.toTypedArray()
        newVisit[x][y] = true

        for(nd in 0..3){
            val nx = x + dx[nd]
            val ny = y + dy[nd]

            if (nx < 0 || ny < 0 || nx > board.lastIndex
                || ny > board.lastIndex || board[nx][ny] == 1 || newVisit[nx][ny])
                continue

            val newCost = if (nd == d) cost + 100
            else if (x == 0 && y == 0) 100
            else cost + 600

            if (dp[nx][ny][nd] > newCost){
                println("$nx : $ny")
                dp[nx][ny][nd] = newCost
                move(nx,ny, newVisit, newCost, nd)
            }
        }
    }
}