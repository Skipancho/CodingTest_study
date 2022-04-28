package baekjoon
/*
1004번 어린왕자
https://www.acmicpc.net/problem/1004
 */
import java.util.*

class LittlePrince_1004 {
    fun main(){
        val sc = Scanner(System.`in`)
        val T = sc.nextInt();
        for (i in 0 until T){
            val (x1,y1,x2,y2) = intArrayOf(
                sc.nextInt(),
                sc.nextInt(),
                sc.nextInt(),
                sc.nextInt()
            )
            val n = sc.nextInt()
            var cnt = 0
            for (j in 0 until n){
                val (cx,cy,r) = intArrayOf(
                    sc.nextInt(),
                    sc.nextInt(),
                    sc.nextInt()
                )
                val dist1 = (cx - x1) * (cx - x1) + (cy - y1) * (cy - y1)
                val dist2 = (cx - x2) * (cx - x2) + (cy - y2) * (cy - y2)
                val (b1,b2) = booleanArrayOf(
                    dist1 > r * r,
                    dist2 > r * r
                )
                if (b1 != b2) cnt++
            }
            println("$cnt")
        }
    }
}