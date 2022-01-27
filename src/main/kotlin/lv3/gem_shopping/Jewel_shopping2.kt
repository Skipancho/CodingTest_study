package lv3.gem_shopping

class Jewel_shopping2 {
    fun solution(gems: Array<String>): IntArray {
        val answer = intArrayOf(1, gems.size)
        val tokenKindSize = gems.toSet().size
        gems.foldIndexed(LinkedHashMap<String, Int>()) { index, gemMap, gem ->
            gemMap.remove(gem)
            gemMap[gem] = index

            if (gemMap.size == tokenKindSize) {
                val start = gemMap.values.first()
                val end = gemMap.values.last()
                if (end - start < answer[1] - answer[0]) {
                    answer[0] = start + 1
                    answer[1] = end + 1
                }
            }
            gemMap
        }
        return answer
    }
}