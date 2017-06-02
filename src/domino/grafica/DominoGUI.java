/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domino.grafica;

import domino.control.GestioDominoIG;
import domino.model.Fitxa;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;
import java.util.ArrayDeque;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author ALUMNEDAM
 */
public class DominoGUI extends javax.swing.JFrame {

    JPanel pnord, pImatges, pCentre, pSud, pEst, pOest;
    String[] nombreJugadores;
    GestioDominoIG gestioDominoIG;

    //Constructor
    public DominoGUI(String[] nombreJugadores, GestioDominoIG gestioDominoIG) {
        super("Domino");
        this.nombreJugadores = nombreJugadores;
        this.gestioDominoIG = gestioDominoIG;
        //this.pack();
        configuracio();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Juego");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("Jugadores");
        jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jCheckBoxMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 531, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 409, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    private void jCheckBoxMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    /**
     * Genera el tablero con los jugadores, sus respectivos bordes y el fondo
     * verde para destacar un area de juego.
     */
    private void configuracio() {

        pSud = new JPanel();
        pOest = new JPanel();
        pEst = new JPanel();
        pCentre = new JPanel();
        pnord = new JPanel();

        pnord.setBackground(Color.GRAY);
        pSud.setBackground(Color.GRAY);
        pOest.setBackground(Color.GRAY);
        pEst.setBackground(Color.GRAY);
//        ImageIcon icon = new ImageIcon("fichas\\tablero.jpg"); 
//        JLabel thumb = new JLabel();
//        thumb.setIcon(icon);
        pCentre.setBackground(Color.GREEN);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Label label1 = new Label(nombreJugadores[0]);
        Label label2 = new Label(nombreJugadores[1]);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 800));
        //pCentrar
        this.setLocationRelativeTo(null);
        //Vuelve al primer panel
        this.getContentPane();
        this.setLayout(new BorderLayout());
        pSud.add(label1);
        pnord.add(label2);
        this.add(pnord, BorderLayout.NORTH);
        this.add(pSud, BorderLayout.SOUTH);
        this.add(pOest, BorderLayout.WEST);
        this.add(pEst, BorderLayout.EAST);
        this.add(pCentre, BorderLayout.CENTER);
        this.pack();
    }

    /**
     * Metodo que se encarga de dibujar los botones y las fichas del jugador con
     * el array de fichas de control.
     *
     * @param numeroJugador
     */
    public void imprimirBotones(int numeroJugador) {
        JButton boton;
        ArrayList<JButton> arrayBotones = gestioDominoIG.getBotonesFicha();
        JButton pasar = new JButton("PASAR");
        switch (numeroJugador) {
            case 0:
                for (int i = 0; i < arrayBotones.size(); i++) {
                    boton = arrayBotones.get(i);
                    pSud.add(boton);

                }
                pasar.addActionListener(gestioDominoIG);
                pasar.setActionCommand("Pasar");
                pSud.add(pasar);
                break;
            case 1:
                for (int i = 0; i < arrayBotones.size(); i++) {
                    boton = arrayBotones.get(i);
                    pnord.add(boton);
                }
                break;
            case 2:
                break;
            case 3:
                break;
        }

    }

    /**
     * Actualiza el listado de fichas, le llega un array de fichas del jugador,
     * el turno, y la ficha jugada, y se encarga de generar un array nuevo del
     * jugador sin la ficha jugada.
     *
     * @param fichasJugador
     * @param jugador
     * @param fichaJugada
     */
    public void actualizarFichaJugador(ArrayList<Fitxa> fichasJugador, int jugador, Fitxa fichaJugada) {

        JButton pasar = new JButton("PASAR");
        ArrayList<JButton> arrayBotones = gestioDominoIG.getBotonesFicha();
        switch (jugador) {
            case 0:

                pSud.removeAll();
                for (int i = 0; i < fichasJugador.size(); i++) {
                    JButton b = new JButton();
                    String numeros = fichasJugador.get(i).valors[0] + "-" + fichasJugador.get(i).valors[1];
                    ImageIcon im = new ImageIcon("fichas\\" + numeros + ".png");
                    b.setActionCommand(fichasJugador.get(i).toString());
                    b.addActionListener(gestioDominoIG);
                    b.setIcon(im);
                    if (!fichaJugada.toString().equals(fichasJugador.get(i).toString())) {
                        pSud.add(b);
                    } else {
                        gestioDominoIG.getJugador1().remove(fichasJugador.get(i));
                    }
                }
                pasar.addActionListener(gestioDominoIG);
                pasar.setActionCommand("Pasar");
                pSud.add(pasar);
                break;
        }
        pSud.updateUI();
        pnord.updateUI();
    }

    /**
     * Muestra las fichas jugadas con el nuevo estado de juego.
     *
     * @param fichasJugadas
     */
    public void imprimirFichasJugadas(ArrayDeque<Fitxa> fichasJugadas) {
        pCentre.removeAll();
        System.out.println("Fitxes Tauler: ");
        for (Fitxa f : fichasJugadas) {
            JButton b = new JButton();
            String numeros = f.valors[0] + "" + f.valors[1];
            System.out.print(f.toString());
            ImageIcon im = new ImageIcon("fichas\\" + numeros + ".gif");
            b.setIcon(im);
            pCentre.add(b);
        }

        pCentre.updateUI();

    }

    /**
     * Selecciona donde se pone la ficha.
     *
     * @return
     */
    public boolean orientacionFicha() {
        boolean sentido = true;
        Object[] izquierdaDerecha = {"Izquierda",
            "Derecha"};
        int orientacionFicha = JOptionPane.showOptionDialog(null,
                "Seleccione una opcion", "Respuesta menu",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                izquierdaDerecha,
                izquierdaDerecha[1]);

        switch (orientacionFicha) {
            case 0:
                sentido = true;
                break;
            case 1:
                sentido = false;
                break;
        }

        return sentido;
    }

    // Variables declaration - do not modify                     
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration                   
}
