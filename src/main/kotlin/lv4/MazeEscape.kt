package lv4

import java.util.*

class MazeEscape {
    fun solution(n: Int, start: Int, end: Int, roads: Array<IntArray>, traps: IntArray): Int {
        val graph = Array(n+1){ arrayListOf<Edge>() }
        for ((p,q,s) in roads){
            val edge = Edge(p,q,s)
            graph[p].add(edge)
            graph[q].add(edge)
        }
        val dist = Array(n+1){IntArray(1.shl(traps.size+1)){1000000} }
        val q : Queue<Node> = LinkedList()
        val visited = Array(n+1){BooleanArray(1.shl(traps.size+1))}
        q.add(Node(start,0))
        dist[start][0] = 0
        visited[start][0] = true
        while (q.isNotEmpty()){
            val (num, state) = q.poll()
            var nextState = state
            if (num == end) return dist[num][state]
            if (traps.contains(num)){
                nextState = nextState.xor(1.shl(traps.indexOf(num)))
                graph[num].forEach { it.trap() }
            }
            for (edge in graph[num]){
                if (edge.start != num) continue
                if (visited[edge.end][nextState]) continue
                dist[edge.end][nextState] = minOf(dist[edge.end][nextState],dist[num][state]+edge.time)
                q.add(Node(edge.end, nextState))
            }
        }
        return -1
    }
    data class Node(
        val num : Int,
        val state : Int
    )
    data class Edge(
        var start: Int,
        var end : Int,
        val time : Int
    ){
        fun trap(){
            val tmp = start
            start = end
            end = tmp
        }
    }
}