package lv3.road_make

import java.util.*

class RoadMake_bfs {
    fun solution(board: Array<IntArray>): Int {
        val dx = intArrayOf(1,0,-1,0)//오,밑,왼,위
        val dy = intArrayOf(0,1,0,-1)
        val dp = Array(board.size){ i ->
            Array(board.size){ j ->
                IntArray(4){
                    if (i==0 && j==0) 0
                    else Int.MAX_VALUE
                }
            }
        }
        val q : Queue<Pos> = LinkedList()
        q.add(Pos(0,0,0,-1))

        while(q.isNotEmpty()){
            val cur = q.poll()
            for (nd in 0..3){
                val nx = cur.x + dx[nd]
                val ny = cur.y + dy[nd]

                if (nx < 0 || ny < 0 || nx > board.lastIndex || ny > board.lastIndex || board[nx][ny] == 1)
                    continue

                val nc = if (cur.x == 0 && cur.y == 0) 100
                else if (nd == cur.dir) cur.cost + 100
                else cur.cost + 600

                if (dp[nx][ny][nd] > nc){
                    dp[nx][ny][nd] = nc
                    q.add(Pos(nx,ny,nc,nd))
                }
            }
        }
        return dp.last().last().minOrNull()!!
    }

    data class Pos(
        val x : Int,
        val y : Int,
        val cost : Int,
        val dir : Int
    )
}