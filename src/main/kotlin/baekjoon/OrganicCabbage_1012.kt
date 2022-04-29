package baekjoon
/*
1012번 유기농 배추
https://www.acmicpc.net/problem/1012
 */
import java.util.*

class OrganicCabbage_1012 {
    private val dr = intArrayOf(-1, 0, 1, 0)
    private val dc = intArrayOf(0, 1, 0, -1)
    private lateinit var visited : Array<BooleanArray>
    fun main(){
        val sc = Scanner(System.`in`)
        val T = sc.nextInt()
        for (t in 0 until T){
            var cnt = 0
            val (M,N) = intArrayOf(
                sc.nextInt(),
                sc.nextInt()
            )
            val map = Array(M){ IntArray(N){0} }
            visited = Array(M){ BooleanArray(N){false}}
            val K = sc.nextInt()
            for (k in 0 until K){
                val (x,y) = intArrayOf(
                    sc.nextInt(),
                    sc.nextInt()
                )
                map[x][y] = 1
            }
            for ( i in 0 until M){
                for ( j in 0 until N){
                    if (map[i][j] != 0 && !visited[i][j]){
                        cnt++
                        visited[i][j]
                        dfs(i,j,map)
                    }
                }
            }
            println("$cnt")
        }
    }
    private fun dfs(i: Int, j: Int, map : Array<IntArray>) {
        for (nd in 0..3) {
            val nr = i + dr[nd]
            val nc = j + dc[nd]
            if (nr < 0 || nr >= map.size || nc < 0 || nc >= map[0].size) continue
            if (visited[nr][nc]|| map[nr][nc] == 0) continue
            visited[nr][nc] = true
            dfs(nr, nc, map)
        }
    }
}