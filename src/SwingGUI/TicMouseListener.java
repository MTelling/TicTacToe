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

    TicMouseListener (TicModel ticModel, TicDrawingPanel ticDrawingPanel) {
        this.ticModel = ticModel;
        this.ticDrawingPanel = ticDrawingPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Do nothing
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX() / Constants.FIELD_SIZE;
        int y = e.getY() / Constants.FIELD_SIZE;

        ticModel.placeMark(x, y);

        ticDrawingPanel.updateUI();

        if (ticModel.isWon()) {
            // Put winning dialog on event queue to prevent blocking.
            // Then reset the board and update the ui.
            EventQueue.invokeLater(() -> ticDrawingPanel.displayWinner());
            EventQueue.invokeLater(() -> ticModel.init());
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
