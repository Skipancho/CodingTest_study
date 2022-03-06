package lv2

class Min_Max {
    fun solution(s: String): String =
        s.split(" ").map { it.toInt()}.sorted().let { "${it[0]} ${it.last()}" }
}