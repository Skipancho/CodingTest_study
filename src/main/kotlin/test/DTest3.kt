package test

class DTest3 {
    private var cnt = 0L

    fun solution(k: Int): Long {
        val dictionary = intArrayOf(
            6, 2, 5, 5, 4, 5, 6, 3, 7, 6
        )
        if (k == 6) cnt++
        for (i in 1..9){
            dfs(dictionary[i],k,dictionary)
        }
        return cnt
    }

    private fun dfs(n : Int, k : Int, dictionary: IntArray){
        if (n == k){
            cnt++
            return
        }
        if (n > k) return
        for (d in dictionary){
            if (n + d > k) continue
            dfs(n+d,k,dictionary)
        }
    }
}