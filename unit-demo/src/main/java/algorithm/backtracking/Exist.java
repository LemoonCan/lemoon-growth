package algorithm.backtracking;

/**
 * 单词搜索
 * https://leetcode-cn.com/problems/word-search/
 *
 * @author lee
 * @since 2021/12/10
 */
public class Exist {
    public static void main(String[] args) {
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCB";
        Exist demo = new Exist();
        System.out.println(demo.exist(board,word));
    }

    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[i].length; j++) {
            if(dfs(board,i,j,word,0)){
                return true;
            }
        }
    }
        return false;
}

    boolean dfs(char[][] board, int i, int j, String word, int index) {
        if (index == word.length()) {
            return true;
        }
        if(i<0||i>=board.length||j<0||j>=board[i].length){
            return false;
        }

        if (board[i][j] == word.charAt(index)) {
            char ori = board[i][j];
            board[i][j]='#';
            if(dfs(board, i - 1, j, word, index + 1)){
                return true;
            }

            if(dfs(board, i + 1, j, word, index + 1)){
                return true;
            }
            if(dfs(board, i, j - 1, word, index + 1)){
                return true;
            }
            if(dfs(board, i, j + 1, word, index + 1)){
                return true;
            }
            board[i][j]=ori;
        }
        return false;
    }
}
