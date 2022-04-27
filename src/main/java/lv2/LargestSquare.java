package lv2;
/*
https://programmers.co.kr/learn/courses/30/lessons/12905

가장 큰 정사각형 찾기
문제 설명
1와 0로 채워진 표(board)가 있습니다. 표 1칸은 1 x 1 의 정사각형으로 이루어져 있습니다. 표에서 1로 이루어진 가장 큰 정사각형을 찾아 넓이를 return 하는 solution 함수를 완성해 주세요. (단, 정사각형이란 축에 평행한 정사각형을 말합니다.)

예를 들어

1	2	3	4
0	1	1	1
1	1	1	1
1	1	1	1
0	0	1	0
가 있다면 가장 큰 정사각형은

1	2	3	4
0	1	1	1
1	1	1	1
1	1	1	1
0	0	1	0
가 되며 넓이는 9가 되므로 9를 반환해 주면 됩니다.

제한사항
표(board)는 2차원 배열로 주어집니다.
표(board)의 행(row)의 크기 : 1,000 이하의 자연수
표(board)의 열(column)의 크기 : 1,000 이하의 자연수
표(board)의 값은 1또는 0으로만 이루어져 있습니다.
입출력 예
board	answer
[[0,1,1,1],[1,1,1,1],[1,1,1,1],[0,0,1,0]]	9
[[0,0,1,1],[1,1,1,1]]	4
입출력 예 설명
입출력 예 #1
위의 예시와 같습니다.

입출력 예 #2
| 0 | 0 | 1 | 1 |
| 1 | 1 | 1 | 1 |
로 가장 큰 정사각형의 넓이는 4가 되므로 4를 return합니다.
 */
public class LargestSquare {
    public int solution(int [][]board) {
        int answer = 0;
        int[][] cnt = new int[board.length+1][board[0].length+1];
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                cnt[i+1][j+1] = board[i][j];
            }
        }
        for (int i = 1; i < cnt.length; i++){
            for (int j =1; j < cnt[0].length ;j++){
                if (cnt[i][j]==0) continue;
                int min = Math.min(cnt[i-1][j],cnt[i][j-1]);
                cnt[i][j] = Math.min(min,cnt[i-1][j-1]) + 1;
                if (cnt[i][j] > answer) answer = cnt[i][j];
            }
        }
        return answer;
    }
}
