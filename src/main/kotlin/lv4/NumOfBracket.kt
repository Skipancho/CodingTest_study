package lv4
/*
https://programmers.co.kr/learn/courses/30/lessons/12929

올바른 괄호의 갯수
문제 설명
올바른 괄호란 (())나 ()와 같이 올바르게 모두 닫힌 괄호를 의미합니다. )(나 ())() 와 같은 괄호는 올바르지 않은 괄호가 됩니다. 괄호 쌍의 개수 n이 주어질 때, n개의 괄호 쌍으로 만들 수 있는 모든 가능한 괄호 문자열의 갯수를 반환하는 함수 solution을 완성해 주세요.

제한사항
괄호 쌍의 개수 N : 1 ≤ n ≤ 14, N은 정수
입출력 예
n	result
2	2
3	5
입출력 예 설명
입출력 예 #1
2개의 괄호쌍으로 [ "(())", "()()" ]의 2가지를 만들 수 있습니다.
입출력 예 #2
3개의 괄호쌍으로 [ "((()))", "(()())", "(())()", "()(())", "()()()" ]의 5가지를 만들 수 있습니다.
 */
class NumOfBracket {
    val set = HashSet<String>()

    fun solution(n: Int): Int {
        dfs("",n,0,0)
        return set.size
    }

    private fun dfs(pos : String, n : Int, l : Int, r : Int){
        if (l+r == 2*n){
            set.add(pos)
            return
        }
        if (r - l + 1 <= 0) dfs("$pos)",n,l,r+1)
        if (l < n) dfs("$pos(",n,l+1,r)
    }
}