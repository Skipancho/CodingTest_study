package lv4

import java.util.*
import kotlin.collections.HashSet

class Caving {
    fun solution(n: Int, path: Array<IntArray>, order: Array<IntArray>): Boolean {
        val graph = Array(n){ hashSetOf<Int>() }
        val pre = IntArray(n){0}
        for (p in path){
            graph[p[0]].add(p[1])
            graph[p[1]].add(p[0])
        }
        for (ord in order){
            pre[ord[1]] = ord[0]
        }
        val check = bfs(graph,pre)
        for (c in check){
            if (!c) return false
        }
        return true
    }
    private fun bfs(graph : Array<HashSet<Int>>, pre : IntArray) : BooleanArray{
        val check = BooleanArray(pre.size)
        val ns = sortedMapOf<Int,Int>()
        val q : Queue<Int> = LinkedList()
        q.add(0)
        check[0] = true
        while (q.isNotEmpty()){
            val cur = q.poll()
            for (next in graph[cur]){
                if (check[next]) continue
                if (!check[pre[next]]) {
                    ns[pre[next]] = next
                    continue
                }
                q.add(next)
                check[next] = true
                if (ns.containsKey(next) && ns[next] != ns.values.last()){
                    q.add(ns[next])
                    check[ns[next]!!] = true
                }
            }
        }
        return check
    }
}