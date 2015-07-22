/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.pdc.ChessHits;

import nz.ac.aut.pdc.ChessHits.model.ChessHitsGame;
import nz.ac.aut.pdc.ChessHits.GUI.StartFrame;
import userDB.UserDatabase;

/**
 *
 *
 * @author Yue Li is gay but guy is the best
 * @version 23-07-2013 project file is created
 */
public class ChessHits {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ChessHitsGame game = new ChessHitsGame();
        final StartFrame gui = new StartFrame(game);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                gui.setVisible(true);
            }
        });

    }
}