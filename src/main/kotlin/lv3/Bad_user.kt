package lv3

class Bad_user {
    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        var answer = 1
        val cntMap = HashMap<String,Int>()
        val caseMap = HashMap<String,Int>()
        val userMap = HashMap<String,Int>()

        for(ban in banned_id){
            var case = 0
            if (caseMap[ban] != null){
                val cnt = cntMap.getOrDefault(ban,0)
                cntMap[ban] = cnt + 1
                continue
            }
            for(user in user_id){
                if (idCheck(user,ban)){
                    val cnt = userMap.getOrDefault(user,0)
                    userMap[user] = cnt + 1
                    case++
                }
            }
            cntMap[ban] = 1
            caseMap[ban] = case
        }

        cntMap.forEach { (t, u) ->
            var case = caseMap.getOrDefault(t,1)
            if (u > 1){
                var tmp = factorial(case,u,1)
                tmp /= factorial(u,u,1)
                case = tmp
            }
            answer *= case
        }
        userMap.forEach { (t, u) ->
            if (u > 1){
                val tmp = dupleCheck(u)
                answer -= tmp
            }
        }

        return answer
    }
    fun idCheck(id : String, ban : String ) : Boolean{
        if (id.length != ban.length){
            return false
        }
        for (i in ban.indices){
            if (ban[i] != '*' && ban[i] != id[i]){
                return false
            }
        }
        return true
    }

    fun dupleCheck(n : Int) : Int{
        var temp = 0
        for (i in 2..n){
            var temp2 = factorial(n,i,1) / factorial(i,i,1)
            temp += temp2
        }
        return temp
    }

    fun factorial(n:Int, cnt:Int,result : Int) : Int{
        return if (cnt == 0){
            result
        }else{
            factorial(n-1,cnt-1,result*n)
        }
    }
}