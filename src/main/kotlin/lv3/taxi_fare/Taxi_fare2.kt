package lv3.taxi_fare

class Taxi_fare2 {
    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        val dist = Array(n){ IntArray(n){ 20000000 } }
        for (i in 0 until n){
            dist[i][i] = 0
        }
        for (fare in fares){
            dist[fare[0]-1][fare[1]-1] = fare[2]
            dist[fare[1]-1][fare[0]-1] = fare[2]
        }
        for (i in 0 until n){
            for (j in 0 until n){
                for (k in 0 until n){
                    if (dist[j][k] > dist[j][i] + dist[i][k])
                        dist[j][k] = dist[j][i] + dist[i][k]
                }
            }
        }
        var min = Int.MAX_VALUE
        for (i in 0 until n){
            min = minOf(min,dist[s-1][i] + dist[i][a-1] + dist[i][b-1])
        }
        return min
    }
}