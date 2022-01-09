package lv1

class NewId {
    fun solution(new_id: String): String {
        var text: String = new_id.toLowerCase()
            .replace("[^a-z0-9/./_/-]".toRegex(),"")
            .replace("[/.]{2,}".toRegex(),".")
            .removePrefix(".").removeSuffix(".")
            .let { if (it.isEmpty())"a" else it }
            .let { if (it.length >= 16) it.substring(0 until 15) else it}
            .removeSuffix(".")

        if (text.length <= 2){
            val end_char = text.last()
            while (text.length < 3){
                text += end_char
            }
        }
        return text
    }
}