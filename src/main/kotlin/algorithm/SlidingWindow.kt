package algorithm

class SlidingWindow {
    //슬라이딩 윈도우를 이용한 구간 누적합의 최대값
    fun sliding_window(size : Int, array:IntArray){
        var sum = 0
        var max = 0
        for (i in array.indices){
            if (i<size) sum += array[i]
            else sum = sum - array[i-size]+array[i]
            max = maxOf(sum,max)
        }
        println("$max")
    }
}