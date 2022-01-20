package lv3.sheep_wolf

import kotlin.math.max

class Sheep_wolf2 {
    private lateinit var treeMap: Map<Int, List<Int>>

    private var answer = 0

    fun solution(info: IntArray, edges: Array<IntArray>): Int {
        treeMap = initTree(info, edges)
        dfs(1, 0, treeMap[0] ?: emptyList(), info)
        return answer
    }

    private fun dfs(sheep: Int, wolf: Int, visitableNodes: List<Int>, info: IntArray) {
        answer = max(sheep, answer)

        for (node in visitableNodes) {
            if (sheep <= wolf) {
                return
            } else {
                dfs(sheep + (info[node] xor 1), wolf + info[node], getNextNode(node, visitableNodes), info)
            }
        }
    }

    private fun initTree(info: IntArray, edges: Array<IntArray>): Map<Int, List<Int>> {
        val map = info.indices.associateWith { mutableListOf<Int>() }.toMutableMap()

        for (edge in edges) {
            if (map[edge[0]] == null) {
                map[edge[0]] = mutableListOf(edge[1])
            } else {
                map[edge[0]]?.add(edge[1])
            }
        }
        return map
    }

    private fun getNextNode(node: Int, nodes: List<Int>): List<Int> {
        val result = mutableListOf<Int>()

        for (i in treeMap[node]?.plus(nodes) ?: emptyList()) {
            if (i == node)
                continue
            result.add(i)
        }
        return result
    }
}