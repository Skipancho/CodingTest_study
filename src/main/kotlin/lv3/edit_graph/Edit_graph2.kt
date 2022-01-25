package lv3.edit_graph

import java.util.*

class Edit_graph2 {
    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        var idx = k
        val remove = Stack<Int>()
        var table_size = n
        for (i in cmd.indices) {
            val c = cmd[i][0]
            if (c == 'U') {
                idx -= Integer.valueOf(cmd[i].substring(2))
            } else if (c == 'D') {
                idx += Integer.valueOf(cmd[i].substring(2))
            } else if (c == 'C') {
                remove.push(idx)
                table_size -= 1
                if (idx == table_size) idx -= 1
            } else {
                val r = remove.pop()
                if (idx >= r) idx += 1
                table_size += 1
            }
        }
        val sb = StringBuilder()
        for (i in 0 until table_size) {
            sb.append('O')
        }
        while (!remove.isEmpty()) {
            sb.insert(remove.pop().toInt(), 'X')
        }
        return sb.toString()
    }
}