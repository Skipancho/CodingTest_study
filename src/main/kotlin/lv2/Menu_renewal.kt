package lv2

class Menu_renewal {

    private val map = HashMap<String,Int>()

    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        var answer: Array<String> = arrayOf<String>()

        for (order in orders){
            val sorted_order = order.split("").sorted().joinToString("")
            dfs("",0,sorted_order)
        }

        for (c in course){
            val keys = ArrayList<String>()
            var max_v = 0
            map.forEach { (t, u) ->
                if (t.length == c){
                    keys.add(t)
                    if (max_v < u){
                        max_v = u
                    }
                }
            }
            if (max_v == 1){
                continue
            }
            for (key in keys){
                if (map[key] == max_v){
                    answer = answer.plus(key)
                }
            }
        }
        answer.sort()

        return answer
    }

    fun dfs(pos : String, d : Int, menu : String){
        if (d == menu.length){
            if (!map.containsKey(pos)){
                map[pos] = 1
            }else{
                map[pos] = map[pos]!! + 1
            }
            return
        }
        dfs(pos,d+1,menu)
        dfs(pos+menu[d],d+1, menu)
    }
}