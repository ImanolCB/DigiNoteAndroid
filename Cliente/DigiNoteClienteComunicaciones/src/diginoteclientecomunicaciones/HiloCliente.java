/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diginoteclientecomunicaciones;

import diginotepojo.Enlace;
import diginotepojo.ExcepcionDigiNoteBD;
import diginotepojo.Lenguaje;
import diginotepojo.Usuario;
import diginotepojocomunicaciones.ExcepcionDigiNoteComunicacion;
import java.util.concurrent.Semaphore;

/**
 *
 * Clase del cliente de comunicacion que recibe la peticion que desea realizar
 * el cliente y genera un hilo por cada peticion realizada. Extiende de thread
 *
 * @author Imanol Callejo Baranda
 * @version 1.0.0
 * @since Mayo 2023
 */
public class HiloCliente extends Thread {

    private Integer solicitud;
    private Object objeto;
    private Object objeto2;
    private Object resultado;

    public static final int USER_ADD = 1;
    public static final int USER_DELETE = 2;
    public static final int USER_EDIT = 3;
    public static final int USER_SHOW = 4;
    public static final int USER_SHOW_ALL = 5;

    public static final int LANGUAGE_ADD = 11;
    public static final int LANGUAGE_DELETE = 12;
    public static final int LANGUAGE_EDIT = 13;
    public static final int LANGUAGE_SHOW = 14;
    public static final int LANGUAGE_SHOW_ALL = 15;

    public static final int LINK_ADD = 21;
    public static final int LINK_DELETE = 22;
    public static final int LINK_EDIT = 23;
    public static final int LINK_SHOW = 24;
    public static final int LINK_SHOW_ALL = 25;
    public static final int LINK_FAVORITE_SHOW = 26;
    public static final int LINK_FAVORITE_EDIT = 27;

    private final Semaphore s = new Semaphore(1);

    /**
     * Constructor vacio de la clase HiloCliente.
     */
    public HiloCliente() {
    }

    /**
     * Constructor parcialmente parametrizado de la clase HiloCliente.
     *
     * @param solicitud Es de tipo Object y contiene los datos que le pasa a la
     * clase DigiNoteComunicaciones
     */
    public HiloCliente(Integer solicitud) {
        this.solicitud = solicitud;
        this.resultado = null;
    }

    /**
     * Constructor parcialmente parametrizado con un solo objeto de la clase
     * HiloCliente
     *
     * @param solicitud Es de tipo Integer y contiene el numero de peticion que
     * se desea realizar
     * @param objeto Es de tipo Object y contiene los datos que le pasa a la
     * clase DigiNoteComunicaciones
     */
    public HiloCliente(Integer solicitud, Object objeto) {
        this.solicitud = solicitud;
        this.objeto = objeto;
        this.resultado = null;
    }

    /**
     * Constructor completamente parametrizado los 2 objetos de la clase
     * HiloCliente
     *
     * @param solicitud Es de tipo Integer y contiene el numero de peticion que
     * se desea realizar
     * @param objeto Es de tipo Object y contiene los datos que le pasa a la
     * clase DigiNoteComunicaciones
     * @param objeto2 Es de tipo Object y contiene los datos que le pasa a la
     * clase DigiNoteComunicaciones
     */
    public HiloCliente(Integer solicitud, Object objeto, Object objeto2) {
        this.solicitud = solicitud;
        this.objeto = objeto;
        this.objeto2 = objeto2;
        this.resultado = null;
    }

    /**
     * Metodo GET que obtiene el valor de la solicitud de la clase HiloCliente
     *
     * @return Integer de la peticion que se desea realizar.
     */
    public Integer getSolicitud() {
        return solicitud;
    }

    /**
     * Metodo SET que establece el numero de la peticion que se dese realizar.
     *
     * @param solicitud De tipo Integer
     */
    public void setSolicitud(Integer solicitud) {
        this.solicitud = solicitud;
    }

    /**
     * Metodo GET que obtiene el valor del objeto de la clase HiloCliente
     *
     * @return Object con los datos necesarios para realizar la peticion.
     */
    public Object getObjeto() {
        return objeto;
    }

    /**
     * Establece los datos necesarios para realizar la peticion
     *
     * @param objeto De tipo Object
     */
    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }

    /**
     * Metodo GET qeu obtiene el valor del objeto de la clase HiloCliente
     *
     * @return Object
     */
    public Object getObjeto2() {
        return objeto2;
    }

    /**
     * Metodo SET que establece el valor del objeto2 de la clase HiloCliente
     *
     * @param objeto2 de tipo Object
     */
    public void setObjeto2(Object objeto2) {
        this.objeto2 = objeto2;
    }

    /**
     * Metodo GET que obtiene el valor del resultadode la clase HiloCliente
     *
     * @return Integer
     */
    public Object getResultado() {
        return resultado;
    }

    /**
     * Metodo SET que establece el valor del resultado de la clase HiloCliente
     *
     * @param resultado de tipo Object
     */
    public void setResultado(Object resultado) {
        this.resultado = resultado;
    }

    /**
     * Muestra los valores de todos los campos almacenados de la clase
     * HiloCliente
     *
     * @return String
     */
    @Override
    public String toString() {
        return "HiloCliente{" + "solicitud=" + solicitud + ", objeto=" + objeto + ", objeto2=" + objeto2 + '}';
    }

    /**
     * Metodo de ejecución del hilo del cliente de comunicaciones. Segun la
     * opcion que recibe del cliente, genera la peticion que se va a a mandar al
     * servidor de comunicaciones que corresponda.
     *
     */
    @Override
    public void run() {

//        System.out.println(Thread.currentThread());
        DigiNoteClienteComunicaciones c = new DigiNoteClienteComunicaciones();

        switch (solicitud) {
            case USER_ADD:
                // Recogida de objeto para realizar la petición necesaria
                wait_sem();
                Usuario u = (Usuario) objeto;
                 {
                    try {

                        setResultado(c.insertarUsuario(u));

                    } catch (ExcepcionDigiNoteBD ex) {
                        System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                        setResultado(ex.getMensajeErrorUsuario());
                    } catch (ExcepcionDigiNoteComunicacion ex) {
                        System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                        setResultado(ex.getMensajeErrorUsuario());
                    }
                }
                signal_sem();
                break;
            case USER_DELETE: {
                wait_sem();
                Integer id = (Integer) objeto;

                try {
//                    System.out.println(c.eliminarUsuario(id));
                    setResultado(c.eliminarUsuario(id));

                } catch (ExcepcionDigiNoteBD ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                } catch (ExcepcionDigiNoteComunicacion ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                }
            }
            signal_sem();
            break;
            case USER_EDIT:
                wait_sem();
                Integer ida = (Integer) objeto;
                Usuario us = (Usuario) objeto2;

                try {

//                    System.out.println(c.actualizarUsuario(ida, us));
                    setResultado(c.actualizarUsuario(ida, us));

                } catch (ExcepcionDigiNoteBD ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                } catch (ExcepcionDigiNoteComunicacion ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                }
                signal_sem();
                break;
            case USER_SHOW:
                wait_sem();
                Integer id2 = (Integer) objeto;

                try {

//                    System.out.println(c.consultarUsuario(id2));
                    setResultado(c.consultarUsuario(id2));

                } catch (ExcepcionDigiNoteBD ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                } catch (ExcepcionDigiNoteComunicacion ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                }
                signal_sem();
                break;
            case USER_SHOW_ALL:
                wait_sem();
                try {
//                    ArrayList<Usuario> arrayU = c.consultarUsuarios();
//
//                    for (int i = 0; i < arrayU.size(); i++) {
//                        System.out.println(arrayU.get(i));
//                    }

                    setResultado(c.consultarUsuarios());

                } catch (ExcepcionDigiNoteBD ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                } catch (ExcepcionDigiNoteComunicacion ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                }
                signal_sem();
                break;
            case LANGUAGE_ADD:
                wait_sem();
                Lenguaje l = (Lenguaje) objeto;

                try {

//                    System.out.println(c.insertarLenguaje(l));
                    setResultado(c.insertarLenguaje(l));

                } catch (ExcepcionDigiNoteBD ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                } catch (ExcepcionDigiNoteComunicacion ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                }
                signal_sem();
                break;
            case LANGUAGE_DELETE:
                wait_sem();
                Integer idl = (Integer) objeto;

                try {

//                    System.out.println(c.eliminarLenguaje(idl));
                    setResultado(c.eliminarLenguaje(idl));

                } catch (ExcepcionDigiNoteBD ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                } catch (ExcepcionDigiNoteComunicacion ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                }
                signal_sem();
                break;
            case LANGUAGE_EDIT:
                wait_sem();
                Integer idlen = (Integer) objeto;
                Lenguaje len = (Lenguaje) objeto2;

                 {
                    try {
//                        System.out.println(c.actualizarLenguaje(idlen, len));
                        setResultado(c.actualizarLenguaje(idlen, len));

                    } catch (ExcepcionDigiNoteBD ex) {
                        System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                        setResultado(ex.getMensajeErrorUsuario());
                    } catch (ExcepcionDigiNoteComunicacion ex) {
                        System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                        setResultado(ex.getMensajeErrorUsuario());
                    }
                }
                signal_sem();
                break;
            case LANGUAGE_SHOW:
                wait_sem();
                Integer idsh = (Integer) objeto;

                try {

//                    System.out.println(c.consultarLenguaje(idsh));
                    setResultado(c.consultarLenguaje(idsh));

                } catch (ExcepcionDigiNoteBD ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                } catch (ExcepcionDigiNoteComunicacion ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                }
                signal_sem();
                break;
            case LANGUAGE_SHOW_ALL:
                wait_sem();
                try {
//                    ArrayList<Lenguaje> arrayL = c.consultarLenguajes();
//
//                    for (int i = 0; i < arrayL.size(); i++) {
//                        System.out.println(arrayL.get(i));
//                    }
                    setResultado(c.consultarLenguajes());

                } catch (ExcepcionDigiNoteBD ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                } catch (ExcepcionDigiNoteComunicacion ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                }
                signal_sem();
                break;
            case LINK_ADD:
                wait_sem();
                Enlace e = (Enlace) objeto;

                try {

//                    System.out.println(c.insertarEnlace(e));
                    setResultado(c.insertarEnlace(e));

                } catch (ExcepcionDigiNoteBD ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                } catch (ExcepcionDigiNoteComunicacion ex) {
                    System.out.println("Administradorr: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                }
                signal_sem();
                break;
            case LINK_DELETE:
                wait_sem();
                Integer idld = (Integer) objeto;

                try {
//                    System.out.println(c.eliminarEnlace(idld));
                    setResultado(c.eliminarEnlace(idld));

                } catch (ExcepcionDigiNoteBD ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                } catch (ExcepcionDigiNoteComunicacion ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                }
                signal_sem();
                break;
            case LINK_EDIT:
                wait_sem();
                Integer idmod = (Integer) objeto;
                Enlace en = (Enlace) objeto2;

                try {
//                    System.out.println(c.actualizarEnlace(idmod, en));
                    setResultado(c.actualizarEnlace(idmod, en));

                } catch (ExcepcionDigiNoteBD ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                } catch (ExcepcionDigiNoteComunicacion ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                }
                signal_sem();
                break;
            case LINK_SHOW:
                wait_sem();
                Integer idli = (Integer) objeto;

                try {
//                    System.out.println(c.consultarEnlace(idli));
                    setResultado(c.consultarEnlace(idli));

                } catch (ExcepcionDigiNoteBD ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                } catch (ExcepcionDigiNoteComunicacion ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                }
                signal_sem();
                break;
            case LINK_SHOW_ALL:
                wait_sem();
                try {
//                    ArrayList<Enlace> array = c.consultarEnlaces();
//
//                    for (int i = 0; i < array.size(); i++) {
//                        System.out.println(array.get(i));
//                    }

                    setResultado(c.consultarEnlaces());

                } catch (ExcepcionDigiNoteBD ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                } catch (ExcepcionDigiNoteComunicacion ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                }
                signal_sem();
                break;
            case LINK_FAVORITE_SHOW:
                wait_sem();
                try {
//                    ArrayList<Enlace> arrayF = c.consultarEnlacesFavoritos();
//                    for (int i = 0; i < arrayF.size(); i++) {
//                        System.out.println(arrayF.get(i));
//                    }

                    setResultado(c.consultarEnlacesFavoritos());

                } catch (ExcepcionDigiNoteBD ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                } catch (ExcepcionDigiNoteComunicacion ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                }
                signal_sem();
                break;
            case LINK_FAVORITE_EDIT:
                wait_sem();
                Integer idfav = (Integer) objeto;
                Enlace enfav = (Enlace) objeto2;

                try {
//                    System.out.println(c.actualizarEnlaceFavorito(idfav, enfav));

                    setResultado(c.actualizarEnlaceFavorito(idfav, enfav));

                } catch (ExcepcionDigiNoteBD ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                } catch (ExcepcionDigiNoteComunicacion ex) {
                    System.out.println("Administrador: " + ex.getMensajeErrorAdministrador());
                    setResultado(ex.getMensajeErrorUsuario());
                }
                signal_sem();
                break;

            default:
                throw new AssertionError();
        }
    }

    /**
     * Método que implementa el método wait del hilo
     */
    public void wait_sem() {
        try {
            s.acquire();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Método que implementa el método signal del hilo
     */
    public void signal_sem() {
        s.release();
    }

}
