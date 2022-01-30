package algorithm

class Floyd_Warshall {
    fun floyd_warshall(n : Int, start : Int, end : Int ,edges : Array<IntArray>) : Int{
        val dist = Array(n){ IntArray(n){ 20000000 } }
        for (i in 0 until n){
            dist[i][i] = 0
        }
        for (edge in edges){
            dist[edge[0]][edge[1]] = edge[2]
            //양방향일 경우
            //dist[edge[1]][edge[0]] = edge[2]
        }
        for (i in 0 until n){
            for (j in 0 until n){
                for (k in 0 until n){
                    if (dist[j][k] > dist[j][i] + dist[i][k])
                        dist[j][k] = dist[j][i] + dist[i][k]
                }
            }
        }
        return dist[start][end]
    }
}