package lv2.distance_check

class Distance_check2 {
    fun solution(places: Array<Array<String>>): IntArray {
        val answer = IntArray(places.size)
        for (placeNum in places.indices) {
            val place = places[placeNum]
            var flag = true
            for (i in place.indices) {
                for (j in place[0].indices) {
                    if (!flag)
                        break
                    if (place[i][j] == 'P')
                        flag = checkPlace(place, i, j, 0, 0)
                }
                if (!flag)
                    break
            }
            answer[placeNum] = if (flag) 1 else 0
        }
        return answer
    }

    fun checkPlace(place: Array<String>, i: Int, j: Int, skip: Int, depth: Int): Boolean {
        //아래
        var result = true
        if (result && skip != 1 && i < place.lastIndex)
            when (place[i + 1][j]) {
                'P' -> return false
                'O' -> if (depth == 0) result = checkPlace(place, i + 1, j, 0, 1)
            }
        //왼쪽
        if (result && skip != 2 && j > 0)
            when (place[i][j - 1]) {
                'P' -> return false
                'O' -> if (depth == 0) result = checkPlace(place, i, j - 1, 3, 1)
            }
        //오른쪽
        if (result && skip != 3 && j < place[0].lastIndex)
            when (place[i][j + 1]) {
                'P' -> return false
                'O' -> if (depth == 0) result = checkPlace(place, i, j + 1, 2, 1)
            }
        //전부 아닐시
        return result
    }
}