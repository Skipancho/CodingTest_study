package algorithm

import java.util.*

class Dijkstra {

    fun dijkstra(n : Int, start : Int, end : Int, edges : Array<IntArray>) : Int{
        val dist = IntArray(n){2000000}
        val route = Array(n){ArrayList<Node>()}
        val queue = PriorityQueue<Node>()

        for (edge in edges){
            route[edge[0]].add(Node(edge[1],edge[2]))
            // 양방향일 경우
            //route[edge[1]].add(Node(edge[0],edge[2]))
        }
        dist[start] = 0
        queue.add(Node(start, 0))
        while (queue.isNotEmpty()){
            val node = queue.poll()
            val cur_idx = node.idx
            val cur_dist = node.dist
            if (dist[cur_idx] < cur_dist) continue

            for (i in route[cur_idx]){
                val next_dist = cur_dist + i.dist
                if (next_dist < dist[i.idx]){
                    dist[i.idx] = next_dist
                    queue.add(Node(i.idx, next_dist))
                }
            }
        }
        return dist[end]
    }
    data class Node(
        val idx : Int,
        val dist : Int
    ) : Comparable<Node>{
        override fun compareTo(other: Node) = dist - other.dist
    }
}