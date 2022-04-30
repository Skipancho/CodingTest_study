package baekjoon
/*
1010번 다리 놓기
https://www.acmicpc.net/problem/1010
 */
import java.util.*

class MakeBridge_1010 {
    private val comb = Array(31) { IntArray(31) }
    fun main(){
        val sc = Scanner(System.`in`)
        val T = sc.nextInt()
        combination()
        for (t in 0 until T){
            val (N , M) = intArrayOf(
                sc.nextInt(),
                sc.nextInt()
            )
            println("${comb[M][N]}")
        }
    }
    private fun combination() {
        comb[0][0] = 1
        for (i in 1..30) {
            for (j in 0..i) {
                if (j == 0) {
                    comb[i][j] = 1
                } else {
                    comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j])
                }
            }
        }
    }
    /*
    private val dp = Array(30){IntArray(30){-1}}
    private fun comb(m : Int, n : Int) : Int{
        if (dp[m][n] != -1) return dp[m][n]
        if (m == n || n == 0) {
            dp[m][n] = 1
            return 1
        }
        return comb(m-1,n-1) + comb(m-1,n)
    }*/
}