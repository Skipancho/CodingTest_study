package algorithm

class DFS {
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
        dfs_base(0)
    }

    fun dfs_base(cur : Int){
        visited[cur] = true
        println("$cur")
        for (i in edge.indices){
            if (visited[i]||!edge[cur][i])
                continue
            dfs_base(i)
        }

    }
}