package algorithm

/**
 * 크루스칼 알고리즘(Kruskal Algorithm)
 * 크루스칼 알고리즘은 그래프에서 최소 비용 신장 부분 트리
 * (최소 신장 트리 : Minimum Spanning Tree(MST))를 찾는 알고리즘이다.
 *
 * 1. 주어진 그래프의 모든 간선을 연결비용이 낮은 순으로 오름차순 정렬
 * 2. 정렬된 간선 순서대로 간선 양 끝 노드를 Union,
 *    이때 양끝 노드가 이미 같은 집합에 속해 있다면 사이클이 존재하므로 Union 하지 않는다.
 */
class Kruskal {
    private lateinit var parent : IntArray

    private fun union(a : Int, b : Int){
        val ap = find(a)
        val bp = find(b)
        if (ap > bp) parent[ap] = bp
        else parent[bp] = ap
    }

    private fun find(x : Int):Int =
        if (parent[x]==x) x else find(parent[x])

    /**
     * @param n : 노드 개수
     * @param edges : 간선 정보 (a , b , cost)
     */
    fun kruskal(n: Int, edges: Array<IntArray>): Int {
        var totalCost = 0
        val graph = edges.sortedBy { it[2] }
        parent = IntArray(n){i -> i}
        for(g in graph){
            if (find(g[0]) != find(g[1])){
                union(g[0],g[1])
                totalCost += g[2]
            }
        }
        return totalCost
    }
}