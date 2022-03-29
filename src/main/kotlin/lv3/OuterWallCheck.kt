package lv3

class OuterWallCheck {
    private lateinit var wall : IntArray
    fun solution(n: Int, weak: IntArray, dist: IntArray): Int {
        var answer = -1
        wall = IntArray(2*weak.size - 1){i -> weak[i%weak.size] + n*(i/weak.size)}
        val dist_perm = permutation(dist)
        for (d in dist_perm){
            val cnt = check(d,weak.size)
            answer = if (answer == -1) cnt
            else if (cnt == -1) answer
            else minOf(cnt,answer)
        }
        return answer
    }

    private fun permutation(dist: IntArray, finish:List<Int> = listOf(), sub : List<Int> = dist.toList()): List<List<Int>>{
        return if (sub.isEmpty()) listOf(finish)
        else sub.flatMap { permutation(dist,finish + it,sub-it)}
    }

    private fun check(dist : List<Int>, size : Int) : Int{
        var min = -1
        for (i in 0 until size){
            var start = i
            var cur = 0
            val set = hashSetOf<Int>()
            for (j in i until i+size){
                if (wall[j] - wall[start] > dist[cur]){
                    start = j
                    cur++
                }
                set.add(wall[j%size])
                if (cur == dist.size) break
            }
            if (set.size == size){
                min = if (min == -1) cur+1 else minOf(min,cur+1)
            }
        }
        return min
    }
}