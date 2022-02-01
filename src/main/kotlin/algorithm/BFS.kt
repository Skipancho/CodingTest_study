package algorithm

import java.util.*

class BFS {
    lateinit var edge : Array<Array<Boolean>>
    lateinit var visited : Array<Boolean>

    /**
     * @param n : 정점 개수
     * @param edges : 간선 (시작점 , 도착점)
     */
    fun solution(n : Int, edges : Array<IntArray>){
        edge = Array(n){Array(n){false}}
        visited = Array(n){false}
        for (e in edges){
            edge[e[0]][e[1]] = true
        }
        bfs_base(0)
    }

    fun bfs_base(start : Int){
        val que : Queue<Int> = LinkedList()
        visited[start] = true
        que.add(start)
        while (que.isNotEmpty()){
            val cur = que.poll()
            println("$cur")
            for (i in edge.indices){
                if (visited[i]||!edge[cur][i])
                    continue
                visited[i] = true
                que.add(i)
            }
        }
    }
}