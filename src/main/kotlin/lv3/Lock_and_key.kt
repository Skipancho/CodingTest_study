package lv3

class Lock_and_key {
    var minX = 19
    var maxX = 0
    var minY = 19
    var maxY = 0

    fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        val lockSize = lock.size
        val keySum = key.fold(0){acc, ints -> acc + ints.sum()}
        val lockSum = lock.fold(0){acc, ints -> acc + ints.sum()}
        if (keySum+lockSum < lockSize*lockSize){
            return false
        }
        if (lockSum == lockSize*lockSize) return true

        val keySize = key.size
        val keys = makeKeys(key)
        val sublock = makeSubLock(lock)
        val sublockSum = sublock.fold(0){acc, ints -> acc + ints.sum()}

        sublock.forEach {
            it.forEach { print("$it ") }
            println()
        }
        println()

        if (sublock.size > keySize || sublock[0].size > keySize) return false

        for (k in keys){
            println("key")
            k.forEach {
                it.forEach { print("$it ") }
                println()
            }
            println()
            for (i in 0..keySize-sublock.size){
                for (j in 0..keySize-sublock[0].size){
                    var isThisKey = true
                    for (r in sublock.indices){
                        for(c in sublock[r].indices){
                            if (k[i+r][j+c] + sublock[r][c] != 1){
                                isThisKey = false
                                break
                            }
                        }
                        if (!isThisKey) break
                        else return true
                    }
                }
            }
        }
        return false
    }

    fun makeSubLock(lock : Array<IntArray>) : Array<IntArray>{
        for (x in lock.indices){
            for (y in lock[x].indices){
                if (lock[x][y]==0){
                    minX = minOf(minX,x)
                    minY = minOf(minY,y)
                    maxX = maxOf(maxX,x)
                    maxY = maxOf(maxY,y)
                }
            }
        }
        var temp = arrayOf<IntArray>()
        for (i in minX..maxX){
            var intArray = intArrayOf()
            for (j in minY..maxY){
                intArray = intArray.plus(lock[i][j])
            }
            temp = temp.plus(intArray)
        }
        return temp
    }

    fun rotateMatrix(key : Array<IntArray>) : Array<IntArray>{
        val size = key.size
        val temp = Array(size){IntArray(size){0} }
        for (i in 0 until size) {
            for (j in 0 until size) {
                temp[j][size - i - 1] = key[i][j]
            }
        }
        return temp
    }

    fun makeKeys(key: Array<IntArray>) : Array<Array<IntArray>>{
        var array = arrayOf(key)
        var temp = key
        for (i in 0..3){
            array = array.plus(rotateMatrix(temp))
            temp = rotateMatrix(temp)
        }
        return array
    }
}