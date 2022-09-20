package lv1
/*
성격 유형 검사하기
https://school.programmers.co.kr/learn/courses/30/lessons/118666
 */
class Kakao_mbti {
    fun solution(survey: Array<String>, choices: IntArray): String {
        var answer: String = ""
        var (RT, CF, JM, AN) = intArrayOf(0,0,0,0)
        for (i in survey.indices){
            val type = survey[i][0]
            val choice = choices[i] - 4
            when(type){
                'R' -> {
                    RT -= choice
                }
                'T' -> {
                    RT += choice
                }
                'C' -> {
                    CF -= choice
                }
                'F' -> {
                    CF += choice
                }
                'J' -> {
                    JM -= choice
                }
                'M' -> {
                    JM += choice
                }
                'A' -> {
                    AN -= choice
                }
                'N' -> {
                    AN += choice
                }
            }
        }
        answer += if (RT >= 0) 'R' else 'T'
        answer += if (CF >= 0) 'C' else 'F'
        answer += if (JM >= 0) 'J' else 'M'
        answer += if (AN >= 0) 'A' else 'N'
        return answer
    }
}