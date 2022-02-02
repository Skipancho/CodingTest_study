package algorithm

class BinarySearch {
    fun binarySearch(n : Int, array : IntArray) : Pair<Boolean,Int>{
        val sortedArray = array.sortedArray()
        var start = 0
        var end = array.size - 1
        while (start <= end){
            val mid = (start + end) / 2
            if (n == sortedArray[mid]){
                return Pair(true,mid)
            }
            else if (n > sortedArray[mid])
                start = mid + 1
            else
                end = mid - 1
        }
        return Pair(false,end)
    }
}