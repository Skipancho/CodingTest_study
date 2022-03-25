package lv3
/*
문제 설명
가로, 세로 길이가 n인 정사각형으로된 체스판이 있습니다. 체스판 위의 n개의 퀸이 서로를 공격할 수 없도록 배치하고 싶습니다.

예를 들어서 n이 4인경우 다음과 같이 퀸을 배치하면 n개의 퀸은 서로를 한번에 공격 할 수 없습니다.

Imgur
Imgur

체스판의 가로 세로의 세로의 길이 n이 매개변수로 주어질 때, n개의 퀸이 조건에 만족 하도록 배치할 수 있는 방법의 수를 return하는 solution함수를 완성해주세요.

제한사항
퀸(Queen)은 가로, 세로, 대각선으로 이동할 수 있습니다.
n은 12이하의 자연수 입니다.
입출력 예
n	result
4	2
 */
import kotlin.math.abs

class N_Queen {
    private val set = hashSetOf<IntArray>()
    fun solution(n: Int): Int {
        dfs(IntArray(n),0 ,BooleanArray(n){false},n)
        set.forEach {
            it.forEach { print("$it ") }
            println()
        }
        return set.size
    }
    private fun dfs(pos : IntArray,d : Int,visit : BooleanArray,n : Int){
        if (d == n){
            set.add(pos)
            return
        }
        for (i in 1..n){
            if (!visit[i-1]){
                var check = true
                for (j in 0 until d){
                    if (abs(i - pos[j]) == abs(d - j)){
                        check = false
                        break
                    }
                }
                if (check){
                    val newVisit = visit.clone()
                    newVisit[i-1] = true
                    val newArray = pos.clone()
                    newArray[d] = i
                    dfs(newArray,d+1,newVisit,n)
                }
            }
        }
    }
}