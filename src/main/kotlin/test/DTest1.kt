package test

class DTest1 {
    fun solution(registered_list: Array<String>, new_id: String): String {
        var answer = new_id
        val list = registered_list.sortedWith(){ o1, o2 ->
            val t1 = trans(o1)
            val t2 = trans(o2)
            if (t1.first == t2.first){
                val n1 = if (t1.second.isNotEmpty())t1.second.toInt() else 0
                val n2 = if (t2.second.isNotEmpty())t2.second.toInt() else 0
                n1 - n2
            }else{
                t1.first.compareTo(t2.first)
            }
        }
        var cacheIdx = 0
        val t = trans(answer)
        var (s,n) = Pair(t.first,if (t.second.isNotEmpty())t.second.toInt() else 0)
        while (true){
            var pass = true
            for (i in cacheIdx..list.lastIndex){
                if (list[i] == answer){
                    cacheIdx = i
                    pass = false
                    break
                }
            }
            if (pass) break
            n++
            answer = "$s$n"
        }
        return answer
    }

    private fun trans(id : String) : Pair<String,String>{
        var s = id
        var n = ""
        for (i in id.indices){
            if (id[i] in '0'..'9'){
                s = id.substring(0,i)
                n = id.substring(i)
                break
            }
        }
        return Pair(s,n)
    }

    private fun check(id : String) : Boolean {
        val pair = trans(id)
        if (!pair.first.matches("^[a-z]+$".toRegex())) return false
        if (pair.first.length !in 3..6) return false
        if (pair.second.length > 6) return false
        if (pair.second.isNotEmpty() && pair.second[0] == '0') return false
        return true
    }

}

fun main(){

}