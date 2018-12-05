package tictactoe;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by pwilkin on 08-Nov-18.
 */
public class Board {

    protected Player[][] board;
    protected Player currentPlayer = Player.CROSS;

    public Board() {
        this.board = new Player[3][3];
    }

    public Player getPlayerForField(int row, int col) {
        return board[row][col];
    }

    public Boolean checkFreeSpaces() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) return true;
            }
        }
        return false;
    }

    public void clearBoard(){
        for(int i = 0; i<3;i++){
            for(int j = 0; j<3; j++){
                board[i][j] = null;
            }
        }
    }

    public void computerPlayerMove(){
        int row = ThreadLocalRandom.current().nextInt(0, 3);
        int col = ThreadLocalRandom.current().nextInt(0, 3);
        if(checkFreeSpaces()) {
            if (board[row][col] == null) {
                board[row][col] = currentPlayer;
                currentPlayer = currentPlayer == Player.CROSS ? Player.CIRCLE : Player.CROSS;
            } else computerPlayerMove();
        }
    }

    public void makeMove(int row, int col) {
        if (board[row][col] == null) {
            board[row][col] = currentPlayer;
            currentPlayer = currentPlayer == Player.CROSS ? Player.CIRCLE : Player.CROSS;
        } else {
            throw new IllegalArgumentException("Row " + row + ", column " + col + " is already occupied!");
        }

    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private Player checkRows() {
        for (int i = 0; i < 3; i++) {
            Player first = board[0][i];
            if (first != null) {
                boolean wins = true;
                for (int j = 1; j < 3; j++) {
                    if (first != board[j][i]) {
                        wins = false;
                        break;
                    }
                }
                if (wins) {
                    return first;
                }
            }
        }
        return null;
    }

    private Player checkColumns() {
        for (int i = 0; i < 3; i++) {
            Player first = board[i][0];
            if (first != null) {
                boolean wins = true;
                for (int j = 1; j < 3; j++) {
                    if (first != board[i][j]) {
                        wins = false;
                        break;
                    }
                }
                if (wins) {
                    return first;
                }
            }
        }
        return null;
    }

    private Player checkTopLeftDiagonal() {
        Player first = board[0][0];
        if (first != null) {
            boolean wins = true;
            for (int i = 1; i < 3; i++) {
                if (first != board[i][i]) {
                    wins = false;
                    break;
                }
            }
            if (wins) {
                return first;
            }
        }
        return null;
    }

    private Player checkTopRightDiagonal() {
        Player first = board[0][2];
        if (first != null) {
            boolean wins = true;
            for (int i = 1; i < 3; i++) {
                if (first != board[i][2-i]) {
                    wins = false;
                    break;
                }
            }
            if (wins) {
                return first;
            }
        }
        return null;
    }

    public Player checkVictory() {
        // check rows
        Player winner = checkRows();
        // check columns
        if (winner == null) {
            winner = checkColumns();
        }
        // check first diagonal
        if (winner == null) {
            winner = checkTopLeftDiagonal();
        }
        // check second diagonal
        if (winner == null) {
            winner = checkTopRightDiagonal();
        }
        return winner;
    }

}
