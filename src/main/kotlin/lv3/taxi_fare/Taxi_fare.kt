package lv3.taxi_fare

import java.util.*
import kotlin.collections.ArrayList

class Taxi_fare {

    private lateinit var route : Array<ArrayList<Node>>
    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        route = Array(n+1){ArrayList<Node>()}
        for (fare in fares){
            route[fare[0]].add(Node(fare[1],fare[2]))
            route[fare[1]].add(Node(fare[0],fare[2]))
        }
        val list = ArrayList<Int>()
        for ( c in 1..n){
            val fare = if (c == s) dijkstra(n,s,a) + dijkstra(n,s,b)
            else dijkstra(n,s,c)+dijkstra(n,c,a)+dijkstra(n,c,b)
            list.add(fare)
        }
        return Collections.min(list)
    }

    fun dijkstra(n : Int, start : Int, end : Int) : Int{
        val dist = IntArray(n+1){2000000}
        val queue = PriorityQueue<Node>()
        dist[start] = 0
        queue.add(Node(start,0))
        while (queue.isNotEmpty()){
            val cur_idx = queue.peek().idx
            val cur_dist = queue.peek().dist
            queue.poll()
            if (dist[cur_idx] < cur_dist) continue

            for (i in route[cur_idx]){
                val next_dist = cur_dist + i.dist
                if (next_dist < dist[i.idx]){
                    dist[i.idx] = next_dist
                    queue.add(Node(i.idx,next_dist))
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