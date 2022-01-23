package lv3.disappearing_floor

class Disappearing_floor_2 {

    fun solution(
        board: Array<IntArray>,
        aloc: IntArray,
        bloc: IntArray
    ) = dfs(board,Player.A,aloc,bloc).second

    fun dfs(board : Array<IntArray>, player: Player, p1_loc:IntArray , p2_loc : IntArray) : Pair<Player,Int>{
        val p2 = if (player == Player.A) Player.B else Player.A
        var pair = Pair(p2,0)
        if (board[p1_loc[0]][p1_loc[1]] == 1){
            for (d in 0 until 4){
                val nx = p1_loc[0] + ("1012"[d] - '1')
                val ny = p1_loc[1] + ("2101"[d] - '1')
                if (nx < 0 || nx >= board.size || ny < 0 || ny >= board[0].size || board[nx][ny]==0)
                    continue
                board[p1_loc[0]][p1_loc[1]] = 0
                var pair2 = dfs(board, p2, p2_loc, intArrayOf(nx,ny))
                board[p1_loc[0]][p1_loc[1]] = 1
                if (pair2.first == Player.A){
                    if (pair.first == player) pair = Pair(p2,minOf(pair.second, pair2.second + 1))
                    else pair = Pair(pair2.first,pair2.second + 1)
                }
                else if (pair.first == p2)
                    pair = Pair(pair.first, maxOf(pair.second, pair2.second + 1))
            }
        }
        return pair
    }

    enum class Player{
        A, B
    }

}