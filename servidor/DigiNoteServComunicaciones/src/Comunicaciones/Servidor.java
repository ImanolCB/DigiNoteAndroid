/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comunicaciones;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main del servidor que se encarga de generar los hilos de las peticiones con
 * los datos de los Sockets.
 *
 * @author Imanol Callejo Baranda
 * @version 1.1.0
 * @since Mayo 2023
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerSocket socketServidor = null;
        Socket clienteConectado = null;
        try {

            System.out.println("Servidor.Consola - Se abre un socket servidor en el puerto 30200 de la máquina local");
            int puertoServidor = 30200;
            socketServidor = new ServerSocket(puertoServidor);

            System.out.println("Servidor.Consola - El servidor se queda a la espera de algún cliente establezca conexión con el servidor");
            while (true) {
                clienteConectado = socketServidor.accept();
                HiloPeticion hp = new HiloPeticion(socketServidor, clienteConectado);
                System.out.println("Información del Hilo: \t> " + clienteConectado + "\n");
                hp.start();
            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
