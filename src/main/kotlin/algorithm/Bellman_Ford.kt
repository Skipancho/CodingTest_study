package algorithm

class Bellman_Ford {
    fun bellman_ford(n : Int, start : Int, end : Int, edges : Array<IntArray>) : Int{
        val dist = IntArray(n) { MAX_VALUE }
        val route = Array(n) { IntArray(n) { MAX_VALUE } }
        for (edge in edges) {
            route[edge[0]][edge[1]] = edge[2]
            //양방향일 경우
            //route[edge[1]][edge[0]] = edge[2]
        }
        for (i in 0 until n) {
            route[i][i] = 0
        }
        dist[start] = 0

        for (i in 0 until n){
            for (j in 0 until n){
                if (route[i][j] != MAX_VALUE){
                    dist[j] = minOf(dist[j],dist[i] + route[i][j])
                }
            }
        }
        return dist[end]
    }

    companion object{
        const val MAX_VALUE = 2000000
    }
}