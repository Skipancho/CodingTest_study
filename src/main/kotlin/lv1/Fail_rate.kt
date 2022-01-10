package lv1

class Fail_rate {
    fun solution(N: Int, stages: IntArray): IntArray {
        val clearStage = IntArray(N+1) { 0 }
        val stageObj = ArrayList<Obj>()
        for (stage in stages){
            for (i in 0 until stage){
                clearStage[i]++
            }
        }
        for ( i in 1..N){
            var rate = 0.0
            if (clearStage[i-1] != 0){
                rate = (clearStage[i-1]-clearStage[i])/clearStage[i-1].toDouble()
            }
            val obj = Obj(i,rate)
            stageObj.add(obj)
        }
        val list = stageObj.sortedByDescending { it.rate }

        return list.map { it.stage }.toIntArray()
    }

    class Obj(
        val stage : Int,
        val rate : Double
    )
}