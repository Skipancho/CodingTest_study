package lv3

class Jewel_Shopping {
    fun solution(gems: Array<String>): IntArray {
        val set = HashSet<String>()
        set.addAll(gems)
        val size = set.size
        val map = mutableMapOf<String,Int>()
        var gemArray = arrayOf<IntArray>()
        gems.mapIndexed { idx, gem ->
            map.remove(gem)
            map[gem] = idx
            if (map.size == size){
                gemArray = gemArray.plus(intArrayOf(map.values.iterator().next() + 1,idx + 1))
            }
        }
        gemArray.sortWith(Comparator { o1, o2 ->
            if (o1[1]-o1[0] == o2[1]-o2[0]){
                o1[1] - o2[1]
            }
            (o1[1]-o1[0])-(o2[1]-o2[0])
        })
        return gemArray[0]
    }
}