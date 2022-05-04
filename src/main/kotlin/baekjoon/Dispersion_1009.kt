package baekjoon
/*
1009번 분산처리
https://www.acmicpc.net/problem/1009
 */
import java.util.*

class Dispersion_1009 {
    fun main(){
        val sc = Scanner(System.`in`)
        val T = sc.nextInt()
        for(t in 0 until T){
            val (a,b) = intArrayOf(
                sc.nextInt(),
                sc.nextInt()
            )
            var tmp = 1
            for (i in 0 until b){
                tmp  = (tmp*a)%10
            }
            if (tmp == 0) println("10")
            else println("$tmp")
        }
    }
}