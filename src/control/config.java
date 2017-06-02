/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import domino.model.Joc;

/**
 *
 * @author ALUMNEDAM
 */
public class config {

    private int jugadors, fitxes, numFitxesJugador;

    public config(int jugadors, int fitxes, int numFitxesJugador) {
        Joc joc = new Joc(4, 28, 7);

        this.jugadors = jugadors;
        this.fitxes = fitxes;
        this.numFitxesJugador = numFitxesJugador;
    }

}
