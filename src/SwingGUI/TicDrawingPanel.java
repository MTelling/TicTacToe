package SwingGUI;

import Model.Mark;
import Model.TicModel;

import javax.swing.*;
import java.awt.*;

import static SwingGUI.Constants.FIELD_SIZE;
import static SwingGUI.Constants.PADDING;

/**
 * Created by Morten on 27/11/2016.
 */
public class TicDrawingPanel extends JPanel {

    private TicModel ticModel;

    TicDrawingPanel(TicModel viewableTicModel) {
        this.ticModel = viewableTicModel;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        // Every time this panel is told to redraw it should paint the board and the marks.
        paintBoard(g);
        paintMarks(g);

    }


    private void paintBoard(Graphics g) {
        // First draw the vertical lines.
        g.drawLine(FIELD_SIZE, 0, FIELD_SIZE, Constants.WINDOW_SIZE);
        g.drawLine(FIELD_SIZE * 2, 0, FIELD_SIZE * 2, Constants.WINDOW_SIZE);

        // Then the horizontal lines.
        g.drawLine(0, FIELD_SIZE, Constants.WINDOW_SIZE, FIELD_SIZE);
        g.drawLine(0, FIELD_SIZE * 2, Constants.WINDOW_SIZE, FIELD_SIZE * 2);
    }

    private void paintMarks(Graphics g) {

        Mark[][] board = ticModel.getBoard();

        // Run through the board and place marks where needed.
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {

                Mark currMark = board[y][x];
                if (currMark != null) {
                    if (currMark == Mark.CROSS) {

                        // Draw cross with two lines.
                        g.drawLine(
                                x * FIELD_SIZE + PADDING,
                                y * FIELD_SIZE + PADDING,
                                (x+1) * FIELD_SIZE - PADDING,
                                (y+1) * FIELD_SIZE - PADDING
                                );

                        g.drawLine(
                                (x+1) * FIELD_SIZE - PADDING,
                                y * FIELD_SIZE + PADDING,
                                x * FIELD_SIZE + PADDING,
                                (y+1) * FIELD_SIZE - PADDING
                        );

                    } else if (currMark == Mark.CIRCLE) {

                        g.drawOval(
                                x * FIELD_SIZE + PADDING / 2,
                                y * FIELD_SIZE + PADDING / 2,
                                FIELD_SIZE - PADDING,
                                FIELD_SIZE - PADDING);
                    }
                }

            }
        }
    }

    /**
     * Simply gives the user a winning dialog.
     */
    void displayWinner() {
        String winningMessage = ticModel.getWinner().toString() + " has won!!";
        JOptionPane.showMessageDialog(this, winningMessage);
    }
}
