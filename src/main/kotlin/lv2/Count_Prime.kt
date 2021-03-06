package lv2

import java.math.BigInteger

/*
양의 정수 n이 주어집니다. 이 숫자를 k진수로 바꿨을 때, 변환된 수 안에 아래 조건에 맞는 소수(Prime number)가 몇 개인지 알아보려 합니다.

0P0처럼 소수 양쪽에 0이 있는 경우
P0처럼 소수 오른쪽에만 0이 있고 왼쪽에는 아무것도 없는 경우
0P처럼 소수 왼쪽에만 0이 있고 오른쪽에는 아무것도 없는 경우
P처럼 소수 양쪽에 아무것도 없는 경우
단, P는 각 자릿수에 0을 포함하지 않는 소수입니다.
예를 들어, 101은 P가 될 수 없습니다.
예를 들어, 437674을 3진수로 바꾸면 211020101011입니다. 여기서 찾을 수 있는 조건에 맞는 소수는 왼쪽부터 순서대로 211, 2, 11이 있으며, 총 3개입니다. (211, 2, 11을 k진법으로 보았을 때가 아닌, 10진법으로 보았을 때 소수여야 한다는 점에 주의합니다.) 211은 P0 형태에서 찾을 수 있으며, 2는 0P0에서, 11은 0P에서 찾을 수 있습니다.

정수 n과 k가 매개변수로 주어집니다. n을 k진수로 바꿨을 때, 변환된 수 안에서 찾을 수 있는 위 조건에 맞는 소수의 개수를 return 하도록 solution 함수를 완성해 주세요.

제한사항
1 ≤ n ≤ 1,000,000
3 ≤ k ≤ 10
입출력 예
n	k	result
437674	3	3
110011	10	2
입출력 예 설명
입출력 예 #1

문제 예시와 같습니다.

입출력 예 #2

110011을 10진수로 바꾸면 110011입니다. 여기서 찾을 수 있는 조건에 맞는 소수는 11, 11 2개입니다. 이와 같이, 중복되는 소수를 발견하더라도 모두 따로 세어야 합니다.

제한시간 안내
정확성 테스트 : 10초
 */
class Count_Prime {
    fun solution(n: Int, k: Int): Int {
        var answer: Int = 0
        val nums = numbers(n,k).split("0")
        for(num in nums){
            if (num.isNotBlank() && isPrime(num.toLong()))
                answer++
        }
        return answer
    }

    fun numbers(n : Int, k : Int) : String{
        var result = ""
        var temp = n
        while(temp > 0){
            result = "${temp%k}$result"
            temp /= k
        }
        return result
    }
    fun isPrime(n : Long) : Boolean{
        if (n == 1.toLong()) return false
        var i = 2.toLong()
        while (i*i <= n){
            if (n%i==0.toLong()) return false
            i++
        }
        return true
    }

    fun solution2(n: Int, k: Int): Int {
        var answer =0
        val newN = n.toString(k).split("0")
        for(i in newN) {
            if(i == "" || i == "0" || i == "1") continue
            if(BigInteger(i).isProbablePrime(1)) answer ++
        }
        return answer
    }
}