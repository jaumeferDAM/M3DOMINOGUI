package domino.control;

import domino.model.Fitxa;
import domino.model.Joc;
import domino.model.Torn;
import domino.model.Jugador;
import domino.vista.VistaText;
import domino.grafica.DominoGUI;
import dominogui.Opcions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GestioDominoIG implements ActionListener {

    ArrayList<Fitxa> fichasJugador1;
    private Torn jugada;
    ArrayList<Fitxa> fichasJugador2;
    DominoGUI juego;
    String[] nombreJugadores;
    private Joc joc;
    Opcions opcions;
    ArrayList<JButton> botonesFicha;
    int opcionMenu;

    //Constructor
    public GestioDominoIG() {
        opcions = new Opcions(this);
        opcions.setVisible(true);
        fichasJugador1 = new ArrayList();
        fichasJugador2 = new ArrayList();
    }

    /**
     * Comprovar que boton se ha pulsado del juego recibiendo el evento y
     * gestionar el juego.
     *
     * @param evento
     */
    @Override
    public void actionPerformed(ActionEvent evento) {
        String respuestaBoton = evento.getActionCommand();

        switch (respuestaBoton) {
            //Se ha pulsado el boton de jugar
            case "Jugar":
                //Recibimos el numero de jugadores e iniciamos el juego.
                nombreJugadores = opcions.getNombreJugadores();
                this.joc = new Joc(opcions.getNumeroJugadores(), 28, 7);
                joc.iniciar(nombreJugadores);
                jugada = new Torn(joc);
                juego = new DominoGUI(nombreJugadores, this);
                juego.setVisible(true);
                opcions.setVisible(false);
                jugada.inicial();
                //Generamos las fichas para la primera jugada para todos los jugadores.
                for (int i = 0; i < opcions.getNumeroJugadores(); i++) {
                    generarBotones(joc.getJugadors()[i].getFitxes(), i);
                    this.juego.imprimirBotones(i);
                }
                break;

            //Se haa pulsado el boton de pasar
            case "Pasar":
                jugada.passar();
                break;
            default:
                switch (joc.getTorn()) {
                    //Mi turno
                    case 0:
                        /* Convierto el string de ficha que devuelve la respuesta en un
                            arrray de char, obtengo los numeros y los guardo en 
                            un array.
                         */
                        int[] array = new int[2];
                        char numeros[] = respuestaBoton.toCharArray();
                        int numero1 = Character.getNumericValue(numeros[14]);
                        int numero2 = Character.getNumericValue(numeros[17]);
                        array[0] = numero1;
                        array[1] = numero2;
                        //Genero una ficha nueva y compruebo si hay que girarla.
                        Fitxa f = new Fitxa(array);
                        boolean girar = juego.orientacionFicha();
                        jugada.colocarUnaFitxa(f, girar);
                        if (!girar) {
                            f.canviarOrientacio();
                        }
                        juego.actualizarFichaJugador(fichasJugador1, joc.getTorn(), f);
                        break;
                }
                joc.actualitzarEstat();
                break;
        }
        this.juego.imprimirFichasJugadas(joc.getFitxesJugades());
    }

    public ArrayList<Fitxa> getJugador1() {
        return fichasJugador1;
    }

    /**
     * Genera los botones del juego con su ficha correspondiente.
     *
     * @param fitxas
     * @param numeroJugador
     * @return
     */
    public ArrayList<JButton> generarBotones(List<Fitxa> fitxas, int numeroJugador) {
        botonesFicha = new ArrayList();
        if (numeroJugador == 0) {
            //Fichas jugador
            for (Fitxa f : fitxas) {
                fichasJugador1.add(f);
                JButton b = new JButton();
                String numeros = f.valors[0] + "-" + f.valors[1];
                ImageIcon im = new ImageIcon("fichas\\" + numeros + ".png");
                b.setActionCommand(f.toString());
                b.addActionListener(this);
                b.setIcon(im);
                botonesFicha.add(b);
            }
        } else {
            //Fichas rival/es
            for (Fitxa f : fitxas) {
                JButton b = new JButton();
                fichasJugador2.add(f);
                ImageIcon im = new ImageIcon("fichas\\backv.gif");
                b.setActionCommand(f.toString());
                b.setIcon(im);
                botonesFicha.add(b);
            }
        }
        return botonesFicha;
    }

    public ArrayList<JButton> getBotonesFicha() {
        return botonesFicha;
    }

}
