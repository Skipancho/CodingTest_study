package lv3
/*
코딩 테스트 공부
https://school.programmers.co.kr/learn/courses/30/lessons/118668
 */
class CodingTestStudy {
    private var answer: Int = 0
    private var targetAp : Int = 0
    private var targetCp : Int = 0
    private lateinit var ps: Array<IntArray>
    private lateinit var dp : Array<IntArray>
    fun solution(alp: Int, cop: Int, problems: Array<IntArray>): Int {
        targetAp = alp
        targetCp = cop
        ps = problems
        for (p in problems){
            targetAp = maxOf(targetAp,p[0])
            targetCp = maxOf(targetCp,p[1])
        }
        answer = targetAp + targetCp - alp - cop
        dp = Array(750){IntArray(750){answer}}
        dfs(alp,cop,0)
        return answer
    }
    private fun dfs(ap : Int, cp : Int, cost : Int){
        if (cost >= answer) return
        if (dp[ap][cp] <= cost) return
        dp[ap][cp] = cost
        if (ap >= targetAp && cp >= targetCp){
            answer = minOf(cost, answer)
            return
        }
        dfs(ap+1,cp, cost+1)
        dfs(ap,cp+1,cost+1)
        for (p in ps){
            if (p[0] > ap || p[1] > cp) continue
            if (ap >= targetAp && p[3] == 0) continue
            if (cp >= targetCp && p[2] == 0) continue
            dfs(ap+p[2],cp+p[3],cost+p[4])
        }
    }
}