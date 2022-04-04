package lv3
/*
https://programmers.co.kr/learn/courses/30/lessons/77886

110 옮기기
문제 설명
0과 1로 이루어진 어떤 문자열 x에 대해서, 당신은 다음과 같은 행동을 통해 x를 최대한 사전 순으로 앞에 오도록 만들고자 합니다.

x에 있는 "110"을 뽑아서, 임의의 위치에 다시 삽입합니다.
예를 들어, x = "11100" 일 때, 여기서 중앙에 있는 "110"을 뽑으면 x = "10" 이 됩니다. 뽑았던 "110"을 x의 맨 앞에 다시 삽입하면 x = "11010" 이 됩니다.

변형시킬 문자열 x가 여러 개 들어있는 문자열 배열 s가 주어졌을 때, 각 문자열에 대해서 위의 행동으로 변형해서 만들 수 있는 문자열 중 사전 순으로 가장 앞에 오는 문자열을 배열에 담아 return 하도록 solution 함수를 완성해주세요.

제한사항
1 ≤ s의 길이 ≤ 1,000,000
1 ≤ s의 각 원소 길이 ≤ 1,000,000
1 ≤ s의 모든 원소의 길이의 합 ≤ 1,000,000
입출력 예
s	result
["1110","100111100","0111111010"]	["1101","100110110","0110110111"]
 */

import java.util.*

class Move110 {
    fun solution(s: Array<String>): Array<String> {
        val answer = Array(s.size){""}
        for(i in s.indices){
            val str = s[i]
            val stack = Stack<Char>()
            var cnt = 0

            for (c in str){
                stack.push(c)
                if (stack.size >= 3){
                    val check = CharArray(3){stack.pop()}
                    if (check[0]=='0'&&check[1]=='1'&&check[2]=='1')
                        cnt++
                    else {
                        stack.push(check[2])
                        stack.push(check[1])
                        stack.push(check[0])
                    }
                }
            }

            if (cnt == 0){
                answer[i] = str
                continue
            }
            val sb = StringBuilder()
            var idx = stack.size

            while (idx > 0 && stack.peek() == '1'){
                sb.insert(0,stack.pop())
                idx--
            }

            while (stack.isNotEmpty())
                sb.insert(0,stack.pop())

            sb.insert(idx,"110".repeat(cnt))
            answer[i]= sb.toString()
        }
        return answer
    }
}