package SwingGUI;

import Model.TicModel;

/**
 * Created by Morten on 27/11/2016.
 */
public class TicDriver {

    public static void main(String[] args) {

        TicModel ticModel = new TicModel();
        new TicWindow(ticModel);
    }
}
