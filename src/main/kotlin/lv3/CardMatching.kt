package lv3

import java.util.*

class CardMatching {

    private val dr = intArrayOf(-1, 0, 1, 0)
    private val dc = intArrayOf(0, 1, 0, -1)
    private val cards = Array(7){ arrayListOf<Pos>() }
    private lateinit var board : Array<IntArray>

    fun solution(board: Array<IntArray>, r: Int, c: Int): Int {
        var answer = 2000000
        this.board = board
        val set = hashSetOf<Int>()
        for (i in board.indices){
            for (j in board.indices){
                if (board[i][j] != 0){
                    cards[board[i][j]].add(Pos(i,j,board[i][j]))
                    set.add(board[i][j])
                }
            }
        }
        val orders = permutation(set.toList())
        val start = Pos(r,c)
        for (order in orders){
            answer = minOf(answer,move(start,order,0))
        }
        return answer
    }

    private fun move(start : Pos , order : List<Int> , n : Int) : Int{
        if (n == order.size) return 0
        var cnt = 0
        val i = order[n]
        val a = bfs(start,cards[i][0]) + bfs(cards[i][0],cards[i][1])
        val b = bfs(start,cards[i][0]) + bfs(cards[i][1],cards[i][0])
        for (c in cards[i]){
            board[c.r][c.c] = 0
        }
        cnt = minOf(a + move(cards[i][1],order,n+1), b + move(cards[i][0],order,n+1))
        for (c in cards[i]){
            board[c.r][c.c] = i
        }
        return cnt
    }

    private fun bfs(start : Pos, end : Pos) : Int {
        val q : Queue<Pos> = LinkedList()
        val visited = Array(4){BooleanArray(4){false} }
        q.add(start)
        visited[start.r][start.c] = true
        while (q.isNotEmpty()){
            val cur = q.poll()

            if (cur.r == end.r && cur.c == end.c) return cur.cnt

            for (i in 0..3){
                var nr = cur.r + dr[i]
                var nc = cur.c + dc[i]

                if (!(nr in 0..3 && nc in 0..3)) continue

                if (!visited[nr][nc]){
                    visited[nr][nc] = true
                    q.add(Pos(nr,nc,cur.cnt + 1))
                }
                for (j in 0..2){
                    if (!(nr+dr[i] in 0..3 && nc+dc[i] in 0..3)) break
                    if (board[nr][nc] != 0) break
                    nr += dr[i]
                    nc += dc[i]
                }
                if (!visited[nr][nc]){
                    visited[nr][nc] = true
                    q.add(Pos(nr,nc,cur.cnt + 1))
                }
            }
        }
        return -1
    }

    private fun permutation(dist: List<Int>, finish:List<Int> = listOf(), sub : List<Int> = dist): List<List<Int>> =
        if (sub.isEmpty()) listOf(finish)
        else sub.flatMap { permutation(dist,finish + it,sub-it)}

    data class Pos(
        val r : Int,
        val c : Int,
        val cnt : Int = 0
    )

}