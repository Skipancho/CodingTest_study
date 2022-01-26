package lv3

class Jewel_Shopping {
    fun solution(gems: Array<String>): IntArray {
        val set = HashSet<String>()
        set.addAll(gems)
        var size = set.size
        var start = 0
        var end = size - 1
        while (size < gems.size){
            val miniset = HashSet<String>()
            for (i in start..end){
                miniset.add(gems[i])
            }
            if (miniset == set){
                return intArrayOf(start+1,end+1)
            }
            if (end==gems.size-1){
                size++
                start = 0
                end = size -1
            }else{
                start++
                end = start + size - 1
            }
        }
        return intArrayOf(start+1, end+1)
    }
}