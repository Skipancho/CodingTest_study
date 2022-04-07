package lv3
/*
https://programmers.co.kr/learn/courses/30/lessons/84021

퍼즐 조각 채우기
문제 설명
테이블 위에 놓인 퍼즐 조각을 게임 보드의 빈 공간에 적절히 올려놓으려 합니다. 게임 보드와 테이블은 모두 각 칸이 1x1 크기인 정사각 격자 모양입니다. 이때, 다음 규칙에 따라 테이블 위에 놓인 퍼즐 조각을 게임 보드의 빈칸에 채우면 됩니다.

조각은 한 번에 하나씩 채워 넣습니다.
조각을 회전시킬 수 있습니다.
조각을 뒤집을 수는 없습니다.
게임 보드에 새로 채워 넣은 퍼즐 조각과 인접한 칸이 비어있으면 안 됩니다.
다음은 퍼즐 조각을 채우는 예시입니다.

puzzle_5.png

위 그림에서 왼쪽은 현재 게임 보드의 상태를, 오른쪽은 테이블 위에 놓인 퍼즐 조각들을 나타냅니다. 테이블 위에 놓인 퍼즐 조각들 또한 마찬가지로 [상,하,좌,우]로 인접해 붙어있는 경우는 없으며, 흰 칸은 퍼즐이 놓이지 않은 빈 공간을 나타냅니다. 모든 퍼즐 조각은 격자 칸에 딱 맞게 놓여있으며, 격자 칸을 벗어나거나, 걸쳐 있는 등 잘못 놓인 경우는 없습니다.

이때, 아래 그림과 같이 3,4,5번 조각을 격자 칸에 놓으면 규칙에 어긋나므로 불가능한 경우입니다.

puzzle_6.png

3번 조각을 놓고 4번 조각을 놓기 전에 위쪽으로 인접한 칸에 빈칸이 생깁니다.
5번 조각의 양 옆으로 인접한 칸에 빈칸이 생깁니다.
다음은 규칙에 맞게 최대한 많은 조각을 게임 보드에 채워 넣은 모습입니다.

puzzle_7.png

최대한 많은 조각을 채워 넣으면 총 14칸을 채울 수 있습니다.

현재 게임 보드의 상태 game_board, 테이블 위에 놓인 퍼즐 조각의 상태 table이 매개변수로 주어집니다. 규칙에 맞게 최대한 많은 퍼즐 조각을 채워 넣을 경우, 총 몇 칸을 채울 수 있는지 return 하도록 solution 함수를 완성해주세요.

제한사항
3 ≤ game_board의 행 길이 ≤ 50
game_board의 각 열 길이 = game_board의 행 길이
즉, 게임 보드는 정사각 격자 모양입니다.
game_board의 모든 원소는 0 또는 1입니다.
0은 빈칸, 1은 이미 채워진 칸을 나타냅니다.
퍼즐 조각이 놓일 빈칸은 1 x 1 크기 정사각형이 최소 1개에서 최대 6개까지 연결된 형태로만 주어집니다.
table의 행 길이 = game_board의 행 길이
table의 각 열 길이 = table의 행 길이
즉, 테이블은 game_board와 같은 크기의 정사각 격자 모양입니다.
table의 모든 원소는 0 또는 1입니다.
0은 빈칸, 1은 조각이 놓인 칸을 나타냅니다.
퍼즐 조각은 1 x 1 크기 정사각형이 최소 1개에서 최대 6개까지 연결된 형태로만 주어집니다.
game_board에는 반드시 하나 이상의 빈칸이 있습니다.
table에는 반드시 하나 이상의 블록이 놓여 있습니다.
입출력 예
game_board	table	result
[[1,1,0,0,1,0],[0,0,1,0,1,0],[0,1,1,0,0,1],[1,1,0,1,1,1],[1,0,0,0,1,0],[0,1,1,1,0,0]]	[[1,0,0,1,1,0],[1,0,1,0,1,0],[0,1,1,0,1,1],[0,0,1,0,0,0],[1,1,0,1,1,0],[0,1,0,0,0,0]]	14
[[0,0,0],[1,1,0],[1,1,1]]	[[1,1,1],[1,0,0],[0,0,0]]	0
입출력 예 설명
입출력 예 #1

입력은 다음과 같은 형태이며, 문제의 예시와 같습니다.

puzzle_9.png

입출력 예 #2

블록의 회전은 가능하지만, 뒤집을 수는 없습니다.
 */
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
                    dfs_table(r,c,-1)
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
                    dfs_board(r,c,-1)
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

    fun dfs_table(r : Int, c : Int , d : Int){
        visited[r][c] = true
        when(d){
            0 -> subPoints[0] = minOf(subPoints[0],r)
            1 -> subPoints[3] = maxOf(subPoints[3],c)
            2 -> subPoints[2] = maxOf(subPoints[2],r)
            3 -> subPoints[1] = minOf(subPoints[1],c)
        }
        for (i in 0..3){
            val nr = r + dr[i]
            val nc = c + dc[i]
            if (!inRange(nr,nc)||visited[nr][nc]) continue
            if (table[nr][nc] == 0){
                visited[nr][nc] = true
                continue
            }
            dfs_table(nr,nc,i)
        }
    }

    fun dfs_board(r : Int, c : Int, d: Int){
        visited[r][c] = true
        when(d){
            0 -> subPoints[0] = minOf(subPoints[0],r)
            1 -> subPoints[3] = maxOf(subPoints[3],c)
            2 -> subPoints[2] = maxOf(subPoints[2],r)
            3 -> subPoints[1] = minOf(subPoints[1],c)
        }
        for (i in 0..3){
            val nr = r + dr[i]
            val nc = c + dc[i]
            if (!inRange(nr,nc)||visited[nr][nc]) continue
            if (board[nr][nc] == 1){
                visited[nr][nc] = true
                continue
            }
            dfs_board(nr,nc,i)
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