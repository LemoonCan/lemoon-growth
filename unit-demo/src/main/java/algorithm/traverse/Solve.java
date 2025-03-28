package algorithm.traverse;

/**
 * 被环绕的区域
 * https://leetcode-cn.com/problems/surrounded-regions/submissions/
 * @author lee
 * @since 2021/11/23
 */
public class Solve {
    public static void main(String[] args) {
        char[][] board = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        Solve solve = new Solve();
        solve.solve(board);

        for (int i = 0; i < board.length; i++) {
            System.out.print("{");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print("}\n");
        }
    }

    int[][] rotate = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * 性质：
     * 1.只要有一个是无环绕X的O,则所有相邻O都无环绕X
     * 2.边缘O无环绕X
     *
     * 找出无环绕X的O变成A，可进行广度优先遍历，也可深度优先遍历
     *
     * @param board
     */
    public void solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            dfs(board, i, 0);
            dfs(board, i, board.length - 1);
        }
        for (int i = 0; i < board[0].length; i++) {
            dfs(board, 0, i);
            dfs(board, board[0].length - 1, i);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j]=='O'){
                    board[i][j]='X';
                }else if(board[i][j]=='A'){
                    board[i][j]='O';
                }
            }
        }
    }

    /**
     * @param board
     * @param i
     * @param j
     */
    public void dfs(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length
                || board[i][j] != 'O') {
            return;
        }
        board[i][j] = 'A';
        for (int k = 0; k < rotate.length; k++) {
            dfs(board, i + rotate[k][0], j + rotate[k][1]);
        }
    }
}
