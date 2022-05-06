package lv4

import java.util.*

class MazeEscape {
    /*fun solution2(n: Int, start: Int, end: Int, roads: Array<IntArray>, traps: IntArray): Int {
        val graph = Array(n+1){ IntArray(n+1){ MAX } }
        val nodes = Array<Node>(n+1){Node(it,0,false)}
        val trapMap = hashMapOf<Int,Int>()
        for ((p,q,s) in roads){
            graph[p][q] = minOf(graph[p][q],s)
        }
        for (i in 1..n){
            graph[i][i] = 0
        }
        for (i in traps.indices){
            nodes[traps[i]].isTrap = true
            trapMap[traps[i]] = i
        }
        val dist = Array(n+1){IntArray(1.shl(traps.size+1)){ MAX } }
        val q : Queue<Node> = LinkedList()
        val visited = Array(n+1){BooleanArray(1.shl(traps.size+1))}
        q.add(nodes[start])
        dist[start][0] = 0
        visited[start][0] = true
        while (q.isNotEmpty()){
            val (num, state ,isTrap) = q.poll()
            var nextState = state
            if (num == end) break
            if (isTrap){
                nextState = nextState.xor(1.shl(trapMap[num]!!))
                for (i in 1..n){
                    val tmp = graph[num][i]
                    graph[num][i] = graph[i][num]
                    graph[i][num] = tmp
                }
            }
            for (i in 1..n){
                if (i == num) continue
                if (graph[num][i] == MAX) continue
                if (visited[i][nextState]) continue
                visited[i][nextState] = true
                dist[i][nextState] = minOf(dist[i][nextState],dist[num][state] + graph[num][i])
                nodes[i].state = nextState
                q.add(nodes[i])
            }
        }
        return dist[end].minOf { it }
    }
    data class Node(
        val num : Int,
        var state : Int,
        var isTrap : Boolean
    )*/
    companion object{
        val MAX = 10000000
    }


    fun solution(n: Int, start: Int, end: Int, roads: Array<IntArray>, traps: IntArray): Int {
        val graph = Array(n+1){ arrayListOf<Edge>() }
        for ((p,q,s) in roads){
            val edge = Edge(p,q,s)
            graph[p].add(edge)
            graph[q].add(edge)
        }
        val dist = Array(n+1){IntArray(1.shl(traps.size+1)){ MAX } }
        val q : Queue<Node> = LinkedList()
        val visited = Array(n+1){BooleanArray(1.shl(traps.size+1))}
        q.add(Node(start,0))
        dist[start][0] = 0
        visited[start][0] = true
        while (q.isNotEmpty()){
            val (num, state) = q.poll()
            var nextState = state
            if (num == end) return break
            if (traps.contains(num)){
                nextState = nextState.xor(1.shl(traps.indexOf(num)))
                graph[num].forEach { it.trap() }
            }
            for (edge in graph[num]){
                if (edge.start != num) continue
                if (visited[edge.end][nextState]) continue
                visited[edge.end][nextState] = true
                dist[edge.end][nextState] = minOf(dist[edge.end][nextState],dist[num][state]+edge.time)
                q.add(Node(edge.end, nextState))
            }
        }
        return dist[end].minOf { it }
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