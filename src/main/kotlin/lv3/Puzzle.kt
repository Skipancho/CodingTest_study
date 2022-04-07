package lv3

class Puzzle {
    private lateinit var table: Array<IntArray>
    private lateinit var board : Array<IntArray>
    private lateinit var visited : Array<BooleanArray>
    private val dr = intArrayOf(-1, 0, 1, 0) // 위,오,밑,왼
    private val dc = intArrayOf(0, 1, 0, -1)
    private val subPoints = intArrayOf(0,0,0,0)//minR,minC,maxR,maxC
    fun solution(game_board: Array<IntArray>, table: Array<IntArray>): Int {
        var answer: Int = 0
        this.table = table
        board = game_board
        visited = Array(table.size){ BooleanArray(table.size){false} }
        val puzzles = mutableListOf<Array<Array<IntArray>>>()
        val blanks = mutableListOf<Array<IntArray>>()
        val completeSet = hashSetOf<Int>()
        for (r in table.indices){
            for (c in table.indices){
                if (!visited[r][c] && table[r][c] == 1){
                    subPoints[0]=r
                    subPoints[1]=c
                    subPoints[2]=r
                    subPoints[3]=c
                    dfs_table(r,c)
                    val puzzleSet = Array(4){ arrayOf<IntArray>() }
                    var puzzle = Array(subPoints[2]-subPoints[0]+1){i ->
                        IntArray(subPoints[3]-subPoints[1] + 1){j ->
                            table[subPoints[0]+i][subPoints[1]+j]
                        }
                    }

                    puzzleSet[0] = puzzle
                    for (i in 1..3){
                        puzzle = rotate(puzzle)
                        puzzleSet[i] = puzzle
                    }
                    puzzles.add(puzzleSet)
                }
            }
        }
        visited = Array(table.size){ BooleanArray(table.size){false} }

        for (r in board.indices){
            for (c in board.indices){
                if (!visited[r][c] && board[r][c] == 0){
                    subPoints[0]=r
                    subPoints[1]=c
                    subPoints[2]=r
                    subPoints[3]=c
                    dfs_board(r,c)
                    val blank = Array(subPoints[2]-subPoints[0]+1){i ->
                        IntArray(subPoints[3]-subPoints[1] + 1){j ->
                            board[subPoints[0]+i][subPoints[1]+j]
                        }
                    }
                    blanks.add(blank)
                }
            }
        }
        for (blank in blanks){
            for (i in puzzles.indices){
                if (completeSet.contains(i)) continue
                var complete = false
                for (puzzle in puzzles[i]){
                    if (blank.size == puzzle.size && blank[0].size == puzzle[0].size){
                        var check = true
                        var sum = 0
                        for (r in blank.indices){
                            for (c in blank[0].indices){
                                if (blank[r][c] + puzzle[r][c] != 1){
                                    check = false
                                    break
                                }else if (puzzle[r][c] == 1){
                                    sum++
                                }
                            }
                            if (!check)break
                        }
                        if (check){
                            answer += sum
                            completeSet.add(i)
                            complete = true
                            break
                        }
                    }
                }
                if (complete) break
            }
        }
        return answer
    }

    fun dfs_table(r : Int, c : Int){
        visited[r][c] = true
        for (i in 0..3){
            val nr = r + dr[i]
            val nc = c + dc[i]
            if (!inRange(nr,nc)||visited[nr][nc]) continue
            if (table[nr][nc] == 0){
                visited[nr][nc] = true
                continue
            }
            when(i){
                0 -> subPoints[0]--
                1 -> subPoints[3]++
                2 -> subPoints[2]++
                3 -> subPoints[1]--
            }
            dfs_table(nr,nc)
        }
    }

    fun dfs_board(r : Int, c : Int){
        visited[r][c] = true
        for (i in 0..3){
            val nr = r + dr[i]
            val nc = c + dc[i]
            if (!inRange(nr,nc)||visited[nr][nc]) continue
            if (board[nr][nc] == 1){
                visited[nr][nc] = true
                continue
            }
            when(i){
                0 -> subPoints[0]--
                1 -> subPoints[3]++
                2 -> subPoints[2]++
                3 -> subPoints[1]--
            }
            dfs_board(nr,nc)
        }
    }

    private fun rotate(puzzle : Array<IntArray>) : Array<IntArray>{
        val row_len = puzzle.size
        val col_len = puzzle[0].size
        val temp = Array(col_len){IntArray(row_len){0} }
        for (i in 0 until row_len) {
            for (j in 0 until col_len) {
                temp[j][row_len - i - 1] = puzzle[i][j]
            }
        }
        return temp
    }

    private fun inRange(r : Int, c : Int) : Boolean =
        (r in table.indices && c in table.indices)

}