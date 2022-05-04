package algorithm

import java.util.*
import kotlin.collections.ArrayList

class TopologySort {
    fun topology(inDegree : IntArray, n : Int, graph : Array<ArrayList<Int>>){
        val q : Queue<Int> = LinkedList()
        for (i in inDegree.indices){
            if (inDegree[i] == 0) q.add(i)
        }
        while (q.isNotEmpty()){
            val cur = q.poll()
            print("$cur ")
            for (next in graph[cur]){
                if (--inDegree[next] == 0)
                    q.add(next)
            }
            if (q.isEmpty()){
                println("사이클 발생")
                return
            }
        }
    }
}