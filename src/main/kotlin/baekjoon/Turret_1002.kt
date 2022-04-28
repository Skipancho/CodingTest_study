package baekjoon
/*
1002번 터렛
https://www.acmicpc.net/problem/1002
 */
import java.util.*
import kotlin.math.sqrt

class Turret_1002 {
    fun main(){
        val sc = Scanner(System.`in`)
        val T = sc.nextInt();
        for (i in 0 until T){
            val (x1,y1,r1) = doubleArrayOf(
                sc.nextInt().toDouble(),
                sc.nextInt().toDouble(),
                sc.nextInt().toDouble()
            )
            val (x2,y2,r2) = doubleArrayOf(
                sc.nextInt().toDouble(),
                sc.nextInt().toDouble(),
                sc.nextInt().toDouble()
            )
            val dist = sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1))
            val sumR = r1 + r2
            if (dist == 0.0){
                if (r1 == r2) println("-1")
                else println("0")
            }else{
                if (sumR == dist || dist + r1 == r2 || dist + r2 == r1) println("1")
                else if (dist > sumR ||r1 > dist + r2 || r2 > dist + r1) println("0")
                else println("2")
            }
        }
    }
}