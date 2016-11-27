package SwingGUI;

import Model.TicModel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Morten on 27/11/2016.
 */
public class TicMouseListener implements MouseListener {

    //The listener acts as the controller and should have access to both model and view.
    private TicModel ticModel;
    private TicDrawingPanel ticDrawingPanel;

    public TicMouseListener (TicModel ticModel, TicDrawingPanel ticDrawingPanel) {
        this.ticModel = ticModel;
        this.ticDrawingPanel = ticDrawingPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Do nothing
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX() / (Constants.WINDOW_SIZE / 3);
        int y = e.getY() / (Constants.WINDOW_SIZE / 3);

        boolean isPlaced = ticModel.placeMark(x, y);

        if (isPlaced) {
            ticDrawingPanel.updateUI();

            if (ticModel.isWon()) {
                // Put winning dialog on event queue to prevent blocking.
                // Then reset the board and update the ui.
                EventQueue.invokeLater(() -> ticDrawingPanel.displayWinner());
                EventQueue.invokeLater(() -> ticModel.init());
                EventQueue.invokeLater(() -> ticDrawingPanel.updateUI());
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Do nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Do nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Do nothing
    }
}
