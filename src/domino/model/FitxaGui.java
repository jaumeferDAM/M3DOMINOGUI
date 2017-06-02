/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino.model;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author ALUMNEDAM
 */
public class FitxaGui extends JButton {

    private void configFitxes() {
        for (int i = 0; i < 28; i++) {
            ImageIcon imatge;
            imatge = new ImageIcon("fcihas/0-1.png");
            JButton jb = new JButton(imatge);
        }
    }
}
