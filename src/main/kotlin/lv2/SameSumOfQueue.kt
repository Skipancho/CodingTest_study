package lv2
/*
두 큐 합 같게 만들기
https://school.programmers.co.kr/learn/courses/30/lessons/118667
 */
import java.util.*

class SameSumOfQueue {
    fun solution(queue1: IntArray, queue2: IntArray): Int {
        var answer: Int = 0
        var sum1 = queue1.sumOf { it.toLong() }
        var sum2 = queue2.sumOf { it.toLong() }
        if ((sum1 + sum2) % 2 != 0L) return -1
        val max = queue1.size + queue2.size
        val q1: Queue<Int> = LinkedList()
        val q2: Queue<Int> = LinkedList()
        q1.addAll(queue1.toList())
        q2.addAll(queue2.toList())
        while (answer < 2 * max) {
            when {
                sum1 == sum2 -> return answer
                sum1 > sum2 -> {
                    val n = q1.poll()
                    q2.add(n)
                    sum1 -= n
                    sum2 += n
                }
                sum1 < sum2 -> {
                    val n = q2.poll()
                    q1.add(n)
                    sum1 += n
                    sum2 -= n
                }
            }
            answer++
        }
        return -1
    }
}