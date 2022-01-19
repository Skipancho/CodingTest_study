package lv3

import java.util.*

class Sheep_wolf {
    fun solution(info: IntArray, edges: Array<IntArray>): Int {
        val tree = makeTree(edges,info.size)
        return bfs(tree,info)
    }

    fun makeTree(edges: Array<IntArray>, size : Int) : Array<IntArray>{
        val tree = Array<IntArray>(size){ intArrayOf()}
        for (edge in edges){
            tree[edge[0]] = tree[edge[0]].plus(edge[1])
            tree[edge[1]] = tree[edge[1]].plus(edge[0])
        }
        return tree
    }

    fun bfs(tree : Array<IntArray> , info : IntArray) : Int{
        val queue : Queue<Int> = LinkedList()
        val visited = Array<Boolean>(tree.size){false}
        var sheep = 0
        var wolf = 0
        queue.add(0)
        loop@ while (!queue.isEmpty()){
            val node = queue.poll()
            //println("$node")

            if (node == 0 || sheep > wolf){
                when(info[node]){
                    0 -> sheep++
                    1 -> {
                        if (sheep > wolf + 1 && tree[node].size > 1)
                            wolf++
                        else{
                            if (tree[node].size > 1){
                                queue.add(node)
                            }else{
                                visited[node] = true
                            }
                            continue@loop
                        }
                    }
                }
                visited[node] = true
            }else{
                if (tree[node].size > 1){
                    queue.add(node)
                    continue@loop
                }else{
                    visited[node] = true
                }
            }
            val q = if (queue.isEmpty()) null else queue.poll()
            for (n in tree[node]){
                if (!visited[n])
                    queue.add(n)
            }
            if (q != null){
                queue.add(q)
            }
            println("$node : $sheep , $wolf")
        }

        return sheep
    }
}