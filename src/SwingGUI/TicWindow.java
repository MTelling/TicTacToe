package SwingGUI;

import Model.TicModel;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Morten on 26/11/2016.
 */
public class TicWindow extends JFrame {

    private TicModel ticModel;


    public TicWindow(TicModel ticModel) {
        this.ticModel = ticModel;
        initComponents();
    }

    private void initComponents() {

        // Setup basics
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle(Constants.TITLE);
        this.setSize(new Dimension(Constants.WINDOW_SIZE, Constants.WINDOW_SIZE + Constants.INSETS_HEIGHT));

        // Create the drawing panel, where the game will be.
        TicDrawingPanel ticDrawingPanel = new TicDrawingPanel(ticModel);
        ticDrawingPanel.setSize(new Dimension(Constants.WINDOW_SIZE, Constants.WINDOW_SIZE));

        // Add the mouse listener to the drawing panel.
        TicMouseListener ticMouseListener = new TicMouseListener(ticModel, ticDrawingPanel);
        ticDrawingPanel.addMouseListener(ticMouseListener);

        // Now set the drawing panel to the contentpane of the JFrame.
        this.setContentPane(ticDrawingPanel);

        // And at last show the frame.
        this.setVisible(true);
    }
}
