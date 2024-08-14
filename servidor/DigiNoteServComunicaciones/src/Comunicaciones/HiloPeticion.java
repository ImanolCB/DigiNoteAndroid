/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comunicaciones;

import diginotecad.DigiNoteCAD;
import diginotepojo.Enlace;
import diginotepojo.ExcepcionDigiNoteBD;
import diginotepojo.Lenguaje;
import diginotepojo.Usuario;
import diginotepojocomunicaciones.ExcepcionDigiNoteComunicacion;
import diginotepojocomunicaciones.Peticion;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hilo que genera peticiones para evitar esperas en la conexión a la base de
 * datos
 *
 * @author Imanol Callejo Baranda
 * @version 1.1.0
 * @since Mayo 2023
 */
public class HiloPeticion extends Thread {

    ServerSocket socketServidor;
    Socket clienteConectado;

    public HiloPeticion(ServerSocket socketServidor, Socket clienteSocket) {
        this.socketServidor = socketServidor;
        this.clienteConectado = clienteSocket;
    }

    @Override
    public void run() {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Object resultado = null;
        try {

            ois = new ObjectInputStream(clienteConectado.getInputStream());
            Peticion peticion = (Peticion) ois.readObject();
            DigiNoteCAD cad = new DigiNoteCAD();
            switch (peticion.getIdOperacion()) {

                case Peticion.INSERTAR_USUARIO:

                    Usuario u = (Usuario) peticion.getObjeto();

                    resultado = cad.insertarUsuario(u);

                    break;
                case Peticion.ELIMINAR_USUARIO:

                    Integer ie = (Integer) peticion.getPk();

                    resultado = cad.eliminarUsuario(ie);

                    break;
                case Peticion.MODIFICAR_USUARIO:

                    Integer ida = (Integer) peticion.getPk();
                    Usuario us = (Usuario) peticion.getObjeto();

                    resultado = cad.actualizarUsuario(ida, us);

                    break;
                case Peticion.MOSTRAR_USUARIO:

                    Integer im = (Integer) peticion.getPk();

                    resultado = cad.consultarUsuario(im);

                    break;
                case Peticion.MOSTRAR_USUARIOS:

                    resultado = cad.consultarUsuarios();

                    break;
                case Peticion.INSERTAR_LENGUAJE:

                    Lenguaje l = (Lenguaje) peticion.getObjeto();

                    resultado = cad.insertarLenguaje(l);

                    break;
                case Peticion.ELIMINAR_LENGUAJE:

                    Integer idl = (Integer) peticion.getPk();

                    resultado = cad.eliminarLenguaje(idl);

                    break;
                case Peticion.MODIFICAR_LENGUAJE:

                    Integer idlen = (Integer) peticion.getPk();
                    Lenguaje len = (Lenguaje) peticion.getObjeto();

                    resultado = cad.actualizarLenguaje(idlen, len);

                    break;
                case Peticion.MOSTRAR_LENGUAJE:

                    Integer idsh = (Integer) peticion.getPk();

                    resultado = cad.consultarLenguaje(idsh);

                    break;
                case Peticion.MOSTRAR_LENGUAJES:

                    resultado = cad.consultarLenguajes();

                    break;
                case Peticion.INSERTAR_ENLACE:

                    Enlace enlace = (Enlace) peticion.getObjeto();
        
                    resultado = cad.insertarEnlace(enlace);
                    break;
                case Peticion.ELIMINAR_ENLACE:

                    Integer idld = (Integer) peticion.getPk();

                    resultado = cad.eliminarEnlace(idld);

                    break;
                case Peticion.MODIFICAR_ENLACE:

                    Integer idmod = (Integer) peticion.getPk();
                    Enlace en = (Enlace) peticion.getObjeto();

                    resultado = cad.actualizarEnlace(idmod, en);

                    break;
                case Peticion.MOSTRAR_ENLACE:

                    Integer idli = (Integer) peticion.getPk();

                    resultado = cad.consultarEnlace(idli);

                    break;
                case Peticion.MOSTRAR_ENLACES:

                    resultado = cad.consultarEnlaces();

                    break;
                case Peticion.MOSTRAR_ENLACES_FAVORITOS:

                    resultado = cad.consultarEnlacesFavoritos();

                    break;
                case Peticion.MODIFICAR_ENLACE_FAVORITO:

                    Integer idfav = (Integer) peticion.getPk();
                    Enlace enfav = (Enlace) peticion.getObjeto();

                    resultado = cad.actualizarEnlaceFavorito(idfav, enfav);

                    break;

                default:
                    throw new AssertionError();
            }

        } catch (IOException ex) {
            Logger.getLogger(HiloPeticion.class.getName()).log(Level.SEVERE, null, ex);
//            System.err.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HiloPeticion.class.getName()).log(Level.SEVERE, null, ex);
//            System.err.println(ex.getStackTrace());
        } catch (ExcepcionDigiNoteBD ex) {
            ExcepcionDigiNoteComunicacion e = new ExcepcionDigiNoteComunicacion();
            e.setMensajeErrorAdministrador(ex.getMensajeErrorAdministrador());
            e.setMensajeErrorUsuario(ex.getMensajeErrorUsuario());
            resultado = e;

        } finally {
            try {
                oos = new ObjectOutputStream(clienteConectado.getOutputStream());
                oos.writeObject(resultado);

                ois.close();
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(HiloPeticion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
