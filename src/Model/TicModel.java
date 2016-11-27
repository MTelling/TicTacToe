package Model;

/**
 * Created by Morten on 26/11/2016.
 */
public class TicModel {

    // Board is represented
    private Mark[][] board = new Mark[3][3];
    private Mark currentMark;
    private Mark winner;

    public TicModel() {
        init();
    }

    public void init() {
        this.board = new Mark[3][3];
        this.currentMark = Mark.CROSS;
        this.winner = null;
    }

    public boolean placeMark(int x, int y) {

        //x and y can't be larger than board size.
        if (x > 2 || y > 2) return false;

        //Place mark if possible and the game is not won
        if (!isWon() && board[y][x] == null) {
            board[y][x] = currentMark;
            currentMark = (currentMark == Mark.CROSS) ? Mark.CIRCLE : Mark.CROSS;

            // After each succesful move, check if the board is won.
            checkIfWon();

            return true;
        } else {
            return false;
        }


    }

    private void checkIfWon() {
        // First check all columns:
        for (int x = 0; x < 3; x++) {
            if (board[0][x] == board[1][x] && board[1][x] == board[2][x]) {
                winner = (board[0][x] != null) ? board[0][x] : winner;
            }
        }

        // Then check all rows
        for (int y = 0; y < 3; y++) {
            if (board[y][0] == board[y][1] && board[y][1] == board[y][2]) {
                winner = (board[y][0] != null) ? board[y][0] : winner;
            }
        }

        // At last check the diagonal
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            winner = (board[0][0] != null) ? board[0][0] : winner;

        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            winner = (board[0][2] != null) ? board[0][2] : winner;
        }


    }

    public Mark[][] getBoard() {
        return board;
    }

    public Mark getCurrentMark() {
        return currentMark;
    }

    public boolean isWon() {
        return winner != null;
    }

    public Mark getWinner() {
        return winner;
    }


}
