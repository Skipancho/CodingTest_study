package test

class DTest2 {
    private val dr = intArrayOf(-1, 0, 1, 0)
    private val dc = intArrayOf(0, 1, 0, -1)
    private var totalMap = HashMap<Char, Int>()
    private var cMap = HashMap<Char, Int>()
    private lateinit var visited: Array<BooleanArray>
    fun solution(maps: Array<String>): Int {
        visited = Array(maps.size){ BooleanArray(maps[0].length){false}}
        for ( i in maps.indices){
            for ( j in maps[0].indices){
                if (maps[i][j] != '.' && !visited[i][j]){
                    dfs(i,j,maps)
                    var max = 0
                    var maxC = ' '
                    var cnt = 0
                    for ((k,v) in cMap){
                        if (v > max) {
                            maxC = k
                            max = v
                        }else if (v == max){
                            if (maxC < k){
                                maxC = k
                            }
                        }
                    }
                    for ((k,v) in cMap){
                        if (v == max){
                            totalMap[k] = totalMap.getOrDefault(k,0) + max
                        }
                        if (v < max){
                            cnt += v
                        }
                    }
                    totalMap[maxC] = totalMap.getOrDefault(maxC,0) + cnt
                    cMap = HashMap()
                }
            }
        }
        totalMap.forEach { (t, u) ->
            println("$t : $u")
        }
        var max = 0
        totalMap.forEach { (_, u) ->
            if (max < u) max = u
        }

        return max
    }

    private fun dfs(i: Int, j: Int, map: Array<String>) {
        visited[i][j] = true
        cMap[map[i][j]] = cMap.getOrDefault(map[i][j], 0) + 1
        for (nd in 0..3) {
            val nr = i + dr[nd]
            val nc = j + dc[nd]
            if (nr < 0 || nr >= map.size || nc < 0 || nc >= map[0].length) continue
            if (visited[nr][nc] || map[nr][nc] == '.') continue
            dfs(nr, nc, map)
        }
    }
}

fun main() {
   val result = DTest2().solution(arrayOf("AABCA.QA", "AABC..QX", "BBBC.Y..", ".A...T.A", "....EE..", ".M.XXEXQ", "KL.TBBBQ"))
    println(result)
}