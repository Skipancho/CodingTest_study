package lv2

class Joystick {
    fun solution(name: String): Int {
        var answer = 0
        val size = name.length
        val n = name.toCharArray()
        var loc = 0
        var cnt = 0

        for (c in name){
            if (c == 'A') cnt++
        }

        while (cnt < size){
            if (n[loc] != 'A'){
                answer += counting(n[loc])
                n[loc] = 'A'
                cnt++
            }
            if (cnt == size) break
            var right = if (loc+1 == size) 0 else loc + 1
            while(right != loc){
                if (n[right] != 'A') break
                right = if (right+1 == size) 0 else right+1
            }
            var left = if (loc == 0) size - 1 else loc - 1
            while (right != loc){
                if (n[left] != 'A') break
                left = if (left == 0) size - 1 else left - 1
            }
            val distR = distance(loc,right,size)
            val distL = distance(loc,left,size)

            if (distR >= distL) {
                loc = left
                answer += distL
            }else{
                loc = right
                answer += distR
            }
        }
        return answer
    }
    private fun counting(c : Char) : Int{
        val a : Int = c - 'A'
        val b : Int = ('Z'- c + 1)
        return minOf(a,b)
    }
    private fun distance(start : Int, end : Int, size : Int) : Int{
        val a = if (start > end) start - end else end - start
        val b = if (start > end){
            size - start + end
        }else{
            size - end + start
        }
        return minOf(a,b)
    }
}