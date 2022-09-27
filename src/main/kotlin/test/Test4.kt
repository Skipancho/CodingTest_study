package test

class Test4 {
    fun solution(numbers: LongArray): IntArray {
        val answer = IntArray(numbers.size)
        for (i in numbers.indices) {
            if (numbers[i] == 0L) {
                answer[i] = 0
                continue
            }
            val binary = toBinary(numbers[i])
            answer[i] = if (isValid(binary)) 1 else 0
        }
        return answer
    }

    private fun toBinary(n: Long): String {
        var result = ""
        var temp = n
        while (temp > 0L) {
            result = "${temp % 2L}$result"
            temp /= 2L
        }
        while (result.length.and(result.length+1) != 0) {
            result = "0$result"
        }
        return result
    }

    private fun isValid(binary: String): Boolean {
        val rootIdx = binary.length / 2 + 1
        if (rootIdx == 1) return true
        val left = rootIdx / 2
        val right = rootIdx + left
        if (binary[left - 1] == '1' || binary[right - 1] == '1') {
            if (binary[rootIdx - 1] == '0')
                return false
        }
        val leftString = binary.substring(0, rootIdx - 1)
        val rightString = binary.substring(rootIdx, binary.length)

        if (!isValid(leftString) || !isValid(rightString)) {
            return false
        }
        return true
    }
}

fun main() {
    Test4().solution(longArrayOf(6123123123134123123))
}