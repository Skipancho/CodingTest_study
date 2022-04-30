package baekjoon
/*
1016번 제곱ㄴㄴ수
https://www.acmicpc.net/problem/1016
 */
import java.util.*

class NotSquaredNum_1016 {
    fun main(){
        var cnt = 0
        val checkArray = BooleanArray(1000001)

        val sc = Scanner(System.`in`)
        val (min, max) = longArrayOf(
            sc.nextLong(),
            sc.nextLong()
        )
        var i = 2L
        while (i*i <= max){
            val r = min % (i * i)
            var q = min / (i * i)
            if (r != 0L) q++
            for (j in q*i*i..max step i*i){
                checkArray[(j - min).toInt()] = true
            }
            i++
        }
        for (k in 0..(max-min).toInt()){
            if (!checkArray[k]) cnt++
        }
        println("$cnt")
    }
}