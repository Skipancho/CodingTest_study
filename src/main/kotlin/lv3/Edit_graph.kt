package lv3

import java.util.*
import kotlin.collections.ArrayList

class Edit_graph {
    private var stack = Stack<Int>()
    private var selected = 0
    private var arrayList = ArrayList<Int>()
    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        val charArray  = CharArray(n){'O'}
        for (i in 0 until n) arrayList.add(i)
        selected = k
        for (cm in cmd){
            val c = cm.split(" ")
            when(c[0]){
                "D" -> {
                    select(c[1].toInt())
                    println("D ${c[1]}")
                }
                "U" -> {
                    select(-c[1].toInt())
                    println("U ${c[1]}")
                }
                "C" -> {
                    clear()
                }
                "Z" -> rewrite()
            }
        }
        stack.forEach { charArray[it] = 'X' }
        return String(charArray)
    }
    fun select(x : Int){
        val sum = selected + x
        selected = if (sum < 0) 0
        else if (sum >= arrayList.size) arrayList.lastIndex
        else sum
    }
    fun clear(){
        println("C ${arrayList[selected]}")
        stack.push(arrayList[selected])
        arrayList.removeAt(selected)
        if (selected >= arrayList.lastIndex)
            selected = arrayList.lastIndex
    }
    fun rewrite(){
        if (stack.isEmpty()) return
        val num = stack.pop()
        val now = arrayList[selected]
        arrayList.add(num)
        arrayList.sort()
        selected = arrayList.indexOf(now)
    }

}