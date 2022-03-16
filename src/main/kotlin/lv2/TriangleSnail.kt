package lv2
/*
문제 설명
정수 n이 매개변수로 주어집니다. 다음 그림과 같이 밑변의 길이와 높이가 n인 삼각형에서 맨 위 꼭짓점부터 반시계 방향으로 달팽이 채우기를 진행한 후, 첫 행부터 마지막 행까지 모두 순서대로 합친 새로운 배열을 return 하도록 solution 함수를 완성해주세요.

examples.png

제한사항
n은 1 이상 1,000 이하입니다.
입출력 예
n	result
4	[1,2,9,3,10,8,4,5,6,7]
5	[1,2,12,3,13,11,4,14,15,10,5,6,7,8,9]
6	[1,2,15,3,16,14,4,17,21,13,5,18,19,20,12,6,7,8,9,10,11]
 */
class TriangleSnail {
    fun solution(n: Int): IntArray {
        val answer = arrayListOf<Int>()
        val snail = Array(n){IntArray(it+1){0}}
        var cnt = 0
        val max = (n*(n+1))/2
        var r = -1
        var c = 0
        var d = 0 // d , r , u
        while (cnt < max){
            when(d){
                0 -> r++
                1 -> c++
                else -> {
                    r--
                    c--
                }
            }
            snail[r][c] = ++cnt
            if (d == 0 && (r == n-1 || snail[r+1][c] != 0)) d = 1
            else if (d == 1 && (c == snail[r].lastIndex || snail[r][c+1] != 0 )) d = 2
            else if (d == 2 && snail[r-1][c-1] != 0) d = 0
        }
        snail.forEach { array -> array.forEach { answer.add(it) } }
        return answer.toIntArray()
    }
}