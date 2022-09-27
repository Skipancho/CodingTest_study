package test

import kotlin.math.abs

class Test6 {
    private var min = ""
    private val dx = intArrayOf(1, 0, 0, -1)//d,l,r,u
    private val dy = intArrayOf(0, -1, 1, 0)
    private val d = charArrayOf('d', 'l', 'r', 'u')

    fun solution(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
        val sub = abs(x - r) + abs(y - c)
        if (sub % 2 != k % 2) return "impossible"
        dfs("", n, m, k, x, y, r, c)
        if (min == "") return "impossible"
        return min
    }

    private fun dfs(route: String, n: Int, m: Int, k: Int, x: Int, y: Int, r: Int, c: Int) {
        if (min != "" && route > min) return
        val sub = abs(x - r) + abs(y - c)
        if (sub > k) return
        if (k == 0) {
            if (x == r && y == c) min = route
            return
        }
        for (i in 0..3) {
            val nextX = x + dx[i]
            val nextY = y + dy[i]
            if (nextX < 1 || nextX > n || nextY < 1 || nextY > m) continue
            dfs("${route}${d[i]}", n, m, k - 1, nextX, nextY, r, c)
        }
    }
}