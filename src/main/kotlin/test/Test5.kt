package test

class Test5 {
    fun solution(commands: Array<String>): Array<String> {
        val answer = mutableListOf<String>()
        var mergeId = -1
        val graph = Array(4) { Array(4) { Node("", mergeId) } }
        for (command in commands) {
            println(command)
            val comm = command.split(" ")
            when (comm[0]) {
                "UPDATE" -> {
                    if (comm.size == 4) {
                        val r = comm[1].toInt()
                        val c = comm[2].toInt()
                        if (graph[r][c].mergeId > -1){
                            for (i in graph.indices) {
                                for (j in graph[i].indices) {
                                    if (graph[i][j].mergeId == graph[r][c].mergeId)
                                        graph[i][j].value = comm[3]
                                }
                            }
                        }else{
                            graph[r][c].value = comm[3]
                        }
                    } else {
                        val value1 = comm[1]
                        val value2 = comm[2]
                        for (i in graph.indices) {
                            for (j in graph[i].indices) {
                                if (graph[i][j].value == value1)
                                    graph[i][j].value = value2
                            }
                        }
                    }
                }
                "MERGE" -> {
                    val (r1, c1, r2, c2) = intArrayOf(
                        comm[1].toInt(), comm[2].toInt(),
                        comm[3].toInt(), comm[4].toInt()
                    )
                    mergeId++
                    val value = if (graph[r1][c1].value == "" && graph[r2][c2].value != "")
                        graph[r2][c2].value
                    else
                        graph[r1][c1].value

                    if (graph[r1][c1].mergeId != -1){
                        val checkId = graph[r1][c1].mergeId
                        for (i in graph.indices) {
                            for (j in graph[i].indices) {
                                if (graph[i][j].mergeId == checkId){
                                    graph[i][j].let {
                                        it.value = value
                                        it.mergeId = mergeId
                                    }
                                }
                            }
                        }
                    }else {
                        graph[r1][c1].let {
                            it.value = value
                            it.mergeId = mergeId
                        }
                    }

                    if (graph[r2][c2].mergeId > -1){
                        val checkId = graph[r2][c2].mergeId
                        for (i in graph.indices) {
                            for (j in graph[i].indices) {
                                if (graph[i][j].mergeId == checkId)
                                    graph[i][j].let {
                                        it.value = value
                                        it.mergeId = mergeId
                                    }
                            }
                        }
                    }else {
                        graph[r2][c2].let {
                            it.value = value
                            it.mergeId = mergeId
                        }
                    }
                }
                "UNMERGE" -> {
                    val (r, c) = intArrayOf(
                        comm[1].toInt(), comm[2].toInt()
                    )
                    val id = graph[r][c].mergeId
                    val value = graph[r][c].value

                    if (id > -1){
                        for (i in graph.indices){
                            for (j in graph[i].indices){
                                if (graph[i][j].mergeId == id){
                                    graph[i][j].value = ""
                                    graph[i][j].mergeId = -1
                                }
                            }
                        }
                    }

                    graph[r][c].value = value
                }
                "PRINT" -> {
                    val (r, c) = intArrayOf(
                        comm[1].toInt(), comm[2].toInt()
                    )
                    if (graph[r][c].value == "")
                        answer.add("EMPTY")
                    else answer.add(graph[r][c].value)
                }
            }
            for (i in graph.indices){
                for (j in graph[i].indices){
                    print("${graph[i][j].value}${graph[i][j].mergeId} ")
                }
                println()
            }
            println()
        }

        return answer.toTypedArray()
    }

    data class Node(
        var value: String,
        var mergeId: Int
    )
}

fun main(){
    Test5().solution(
        arrayOf("UPDATE 1 1 a",
            "UPDATE 1 2 b",
            "UPDATE 2 1 c",
            "UPDATE 2 2 d",
            "MERGE 1 1 1 2",
            "MERGE 2 2 2 1",
            "MERGE 2 1 1 1",
            "PRINT 1 1",
            "UNMERGE 2 2",
            "MERGE 1 1 1 2",
            "UPDATE 1 1 a",
            )
    )
}