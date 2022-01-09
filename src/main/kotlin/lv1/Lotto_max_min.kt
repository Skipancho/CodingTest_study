package lv1

class Lotto_max_min {
    fun solution(lottos: IntArray, win_nums: IntArray): IntArray {
        val win = win_nums.toHashSet()
        val rank = intArrayOf(6,6,5,4,3,2,1)
        var max = 0
        var min = 0

        lottos.forEach {
            if(it == 0)
                max++
            else if(win.contains(it)) {
                min++
                max++
            }
        }
        return intArrayOf(rank[max], rank[min])
    }
}