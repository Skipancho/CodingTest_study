package lv3

class RoadMake {

    private val dx = intArrayOf(1,0,-1,0)//오,밑,왼,위
    private val dy = intArrayOf(0,1,0,-1)
    private var answer = Int.MAX_VALUE
    private lateinit var newBoard : Array<IntArray>

    fun solution(board: Array<IntArray>): Int {
        newBoard = Array(board.size + 2){IntArray(board.size + 2)}
        for (i in newBoard.indices){
            for (j in newBoard[0].indices){
                if (i == 0 || j==0 || i == newBoard.lastIndex||j == newBoard.lastIndex ){
                    newBoard[i][j] = 1
                }else{
                    newBoard[i][j] = board[i-1][j-1]
                }
            }
        }
        move(1,1, Array(board.size+2){ BooleanArray(board.size+2){false} },0,0)
        return answer
    }

    private fun move(x : Int, y : Int, visited : Array<BooleanArray>,cost : Int, d : Int){
        val newVisit = visited.map { it.clone() }.toTypedArray()
        newVisit[x][y] = true

        if (x == visited.lastIndex-1 && y == visited.lastIndex-1){
            if (cost < answer){
                answer = cost
            }
            return
        }

        if (newBoard[x+dx[d]][y+dy[d]] == 0 && !visited[x+dx[d]][y+dy[d]]){
            move(x+dx[d],y+dy[d],newVisit,cost+100,d)
        }
        for (nd in 0..3){
            if (nd != d){
                if (!visited[x+dx[nd]][y+dy[nd]] && newBoard[x+dx[nd]][y+dy[nd]] == 0){
                    if (x == 1 && y == 1) move(x+dx[nd],y+dy[nd],newVisit,cost+100,nd)
                    else move(x+dx[nd],y+dy[nd],newVisit,cost+600,nd)
                }
            }
        }
    }
}