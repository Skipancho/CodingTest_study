package test

class Test1 {
    fun solution(today: String, terms: Array<String>, privacies: Array<String>): IntArray {
        val answer = mutableListOf<Int>()
        val todayDate = toDate(today)
        val tMap = mutableMapOf<String,Int>()
        terms.forEach {
            val split = it.split(" ")
            tMap[split[0]] = split[1].toInt()
        }
        for (i in privacies.indices){
            val privacy = privacies[i].split(" ")
            val term = tMap[privacy[1]]!!
            val date = toDate(privacy[0]).sum(term)
            if (!todayDate.isValid(date)) answer.add(i+1)
        }
        return answer.toIntArray()
    }

    private fun toDate(date:String): Date{
        val s = date.split(".")
        return Date(
            s[0].toInt(),
            s[1].toInt(),
            s[2].toInt()
        )
    }

    data class Date(
        val year:Int,
        val month:Int,
        val day:Int
    ){
        fun sum(month: Int) : Date{
            var m = this.month + month
            var y = this.year
            var d = this.day - 1
            if (m > 12){
                y++
                m -= 12
            }
            if (d <= 0){
                m--
                d = 28
            }
            return Date(y,m,d)
        }

        fun isValid(date: Date) : Boolean{
            if (year < date.year){
                return true
            }else if (year == date.year){
                if (month < date.month){
                    return true
                }else if (month == date.month){
                    if (day <= date.day){
                        return true
                    }
                }
            }
            return false
        }
    }
}