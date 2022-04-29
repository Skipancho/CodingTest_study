package baekjoon
/*
1005번 ACM Craft
https://www.acmicpc.net/problem/1005
 */
import java.util.*

class ACM_Craft_1005 {
    fun main(){
        val sc = Scanner(System.`in`)
        val T = sc.nextInt()
        for (t in 0 until T){
            val (N,K) = intArrayOf(
                sc.nextInt(),
                sc.nextInt()
            )
            val indegree = IntArray(N){0}
            val cost = IntArray(N)
            val max = IntArray(N)
            val graph = Array(N){ arrayListOf<Int>() }
            for (i in 0 until N){
                val c = sc.nextInt()
                cost[i] = c
                max[i] = c
            }
            for (i in 0 until K){
                val (X,Y) = intArrayOf(
                    sc.nextInt()-1,
                    sc.nextInt()-1
                )
                graph[X].add(Y)
                indegree[Y]++
            }
            val W = sc.nextInt()-1
            val q : Queue<Int> = LinkedList()
            for (i in indegree.indices){
                if (i == W) continue
                if (indegree[i] == 0) q.add(i)
            }
            while (q.isNotEmpty()){
                val cur = q.poll()
                for (n in graph[cur]){
                    max[n] = maxOf(max[n],max[cur] + cost[n])
                    indegree[n]--
                    if (indegree[n] == 0) q.add(n)
                }
            }
            println("${max[W]}")
        }
    }
}