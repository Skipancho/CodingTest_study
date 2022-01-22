package lv3

import kotlin.math.min

class Disappearing_floor {

    fun solution(board: Array<IntArray>, aloc: IntArray, bloc: IntArray): Int {
        val answer = moveA(board,aloc,bloc,0)
        return answer.cnt
    }

    private fun moveA(board : Array<IntArray>, aloc: IntArray, bloc: IntArray, cnt : Int) : State{
        val i = aloc[0]
        val j = aloc[1]
        var min = 25
        var max = 0
        val newBoard = board.map { it.clone() }.toTypedArray()

        if (board[i][j]==0){
            return State(cnt,false)
        }

        newBoard[i][j] = 0
        var state = State(cnt , false)
        if (i > 0 && board[i-1][j] == 1){
            state = moveB(newBoard, intArrayOf(i-1,j),bloc,cnt+1)
            if (state.win_A){
                min = minOf(min,state.cnt)
            }else{
                max = maxOf(max,state.cnt)
            }
        }
        if (i < board.lastIndex && board[i+1][j] == 1){
            state = moveB(newBoard, intArrayOf(i+1,j),bloc,cnt+1)
            if (state.win_A){
                min = minOf(min,state.cnt)
            }else{
                max = maxOf(max,state.cnt)
            }
        }
        if (j > 0 && board[i][j-1] == 1){
            state = moveB(newBoard, intArrayOf(i,j-1),bloc,cnt+1)
            if (state.win_A){
                min = minOf(min,state.cnt)
            }else{
                max = maxOf(max,state.cnt)
            }
        }
        if (j<board[i].lastIndex&& board[i][j+1] == 1){
            state = moveB(newBoard, intArrayOf(i,j+1),bloc, cnt+1)
            if (state.win_A){
                min = minOf(min,state.cnt)
            }else{
                max = maxOf(max,state.cnt)
            }
        }
        if (min == 25 && max == 0){
            return State(cnt,false)
        }
        if (min != 25){
            return State(min,true)
        }
        return State(max , false)
    }

    private fun moveB(board: Array<IntArray>, aloc: IntArray, bloc: IntArray, cnt: Int ) : State{
        val i = bloc[0]
        val j = bloc[1]
        var min = 25
        var max = 0
        val newBoard = board.map { it.clone() }.toTypedArray()

        if (board[i][j]==0) {
            return State(cnt, true)
        }
        newBoard[i][j] = 0

        var state = State(cnt,true)
        if (i > 0 && board[i-1][j] == 1){
            state = moveA(newBoard, aloc ,intArrayOf(i-1,j),cnt+1)
            if (state.win_A){
                max = maxOf(max,state.cnt)
            }else{
                min = minOf(min,state.cnt)
            }
        }
        if (i < board.lastIndex && board[i+1][j] == 1){
            state = moveA(newBoard, aloc, intArrayOf(i+1,j),cnt+1)
            if (state.win_A){
                max = maxOf(max,state.cnt)
            }else{
                min = minOf(min,state.cnt)
            }
        }
        if (j > 0 && board[i][j-1] == 1){
            state = moveA(newBoard, aloc,intArrayOf(i,j-1),cnt+1)
            if (state.win_A){
                max = maxOf(max,state.cnt)
            }else{
                min = minOf(min,state.cnt)
            }
        }
        if (j<board[i].lastIndex&& board[i][j+1] == 1){
            state = moveA(newBoard, aloc,intArrayOf(i,j+1), cnt+1)
            if (state.win_A){
                max = maxOf(max,state.cnt)
            }else{
                min = minOf(min,state.cnt)
            }
        }

        if (min == 25 && max == 0){
            return State(cnt,true)
        }
        if (min != 25) return State(min, false)

        return State(max,true)
    }

    data class State(
        val cnt: Int,
        val win_A : Boolean
    )
}