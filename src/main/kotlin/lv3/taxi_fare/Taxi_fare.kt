package lv3.taxi_fare

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Taxi_fare {
    private lateinit var dist : Array<Int>
    private val queue = PriorityQueue<Node>()
    private val routeMap = HashMap<Int,Node>()
    private lateinit var route : Array<ArrayList<Node>>
    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        var answer: Int = 0
        val aRoute = Stack<Int>()
        val bRoute = Stack<Int>()
        dist = Array(n+1){ Int.MAX_VALUE}
        route = Array(n+1){ArrayList()}
        for (fare in fares){
            route[fare[0]].add(Node(fare[1],fare[0],fare[2]))
            route[fare[1]].add(Node(fare[0],fare[1],fare[2]))
        }

        dijkstra(s)

        var a_r = a
        while (a_r != s){
            val pref = routeMap[a_r]!!.pref
            aRoute.push(pref)
            a_r = pref
        }
        var b_r = b
        while (b_r != s){
            val pref = routeMap[b_r]!!.pref
            bRoute.push(pref)
            b_r = pref
        }
        answer = dist[a] + dist[b]

        var lastSame = s
        while (aRoute.isNotEmpty()&& bRoute.isNotEmpty()){
            val c_a = aRoute.pop()
            val c_b = bRoute.pop()
            if (c_a==c_b) lastSame = c_a
            else break
        }

        answer -= dist[lastSame]

        return answer
    }

    private fun dijkstra(start : Int){
        dist[start] = 0
        queue.add(Node(start,0,0))

        while (queue.isNotEmpty()){
            val no = queue.peek().no
            val fare = queue.peek().fare
            if (routeMap.containsKey(no)){
                if (routeMap[no]!!.fare > fare){
                    routeMap[no] = queue.poll()
                }else{
                    queue.poll()
                }
            }else{
                routeMap[no] = queue.poll()
            }

            if (dist[no] < fare) continue

            for (i in route[no].indices){
                val nextNo = route[no][i].no
                val nextFare = fare + route[no][i].fare

                if (nextFare < dist[nextNo]){
                    dist[nextNo] = nextFare
                    queue.add(Node(nextNo,no,nextFare))
                }
            }
        }
    }

    data class Node(
        val no : Int,
        var pref : Int,
        var fare : Int
    ) : Comparable<Node>{
        override fun compareTo(other: Node) = fare - other.fare
    }
}