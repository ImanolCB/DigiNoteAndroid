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
import diginotepojocomunicaciones.Peticion;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cliente de comunicaciones encargado de preparar la petición al servidor con
 * los datos necesarios para que posteriormente el servidor de comunicaciones
 * realice las operaciones necesarias
 *
 * @author Imanol Callejo Baranda
 * @version 1.1.0
 * @since Mayo 2023
 */
public class DigiNoteClienteComunicaciones {

    //Ip local cuando el comunicadorServ se encuentra en la misma máquina
    private final String ip = "192.168.4.21";
    //Puerto de acceso del servidor
    private final Integer puerto = 30200;

    /**
     * Metodo de la clase DigiNoteClienteComunicaciones que prepara la peticion
     * de insertar un usuario.
     *
     * @param usuario de tipo Usuario
     * @return Integer
     * @throws ExcepcionDigiNoteBD
     * @throws ExcepcionDigiNoteComunicacion
     */
    public Integer insertarUsuario(Usuario usuario) throws ExcepcionDigiNoteBD, ExcepcionDigiNoteComunicacion {
        try {
            //Direccion IP del servidor al que se quiere recurrir (Red)
            String equipoServidor = ip;
            //Puerto de acceso de la base de datos (Transporte)
            int puertoServidor = puerto;
            //Canal de comunicacion del cliente formado por IP y Puerto del servidor
            Socket socketCliente = new Socket(equipoServidor, puertoServidor);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            //Instancia de un objeto tipo peticion encargado de almacenar la peticion del usuario y los parametros necesarios para la realizacion de la peticion
            Peticion peticion = new Peticion(Peticion.INSERTAR_USUARIO, usuario);
            //Escritura de la peticion sobre el flujo de datos, con los datos necesarios para la operacion 
            oos.writeObject(peticion);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            //Almacenamiento del contenido obtenido en la variable respuesta de tipo Object al leer el flujo de salida. Utiliza un casteo de tipo Object
            Object respuesta = (Object) ois.readObject();

            //Cierre de ambos flujos de datos
            oos.close();
            ois.close();

            //Comprobacion si la respuesta de peticion es de tipo Integer para devolver el numero de registros afectados
            if (respuesta.getClass().getCanonicalName().equals("java.lang.Integer")) {
                Integer registrosAfectados = (Integer) respuesta;
                return registrosAfectados;
            } //Control de excepcion ocurrido en la base de datos
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojo.ExcepcionDigiNoteBD")) {
                ExcepcionDigiNoteBD e = (ExcepcionDigiNoteBD) respuesta;
                throw e;
            } //Control de excepcion al ocurrir una excepcion en los comunicadores
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojocomunicaciones.ExcepcionDigiNoteComunicacion")) {
                ExcepcionDigiNoteComunicacion e = (ExcepcionDigiNoteComunicacion) respuesta;
                throw e;
            }

            //Captura y tratamiento de las posibles excepciones generales
        } catch (IOException ex) {
            System.out.println("Log IOException: " + ex.getMessage());
            ExcepcionDigiNoteComunicacion e = new ExcepcionDigiNoteComunicacion();
//            e.getMensajeErrorAdministrador(ex.getMessage());
//            e.getMensajeErrorUsuario("Error generla del sistema. Consulte con el administrador");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DigiNoteClienteComunicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Metodo de la clase DigiNoteClienteComunicaciones que prepara la peticion
     * de eliminar un usuario.
     *
     * @param usuarioId de tipo Integer
     * @return Integer
     * @throws ExcepcionDigiNoteBD
     * @throws ExcepcionDigiNoteComunicacion
     */
    public Integer eliminarUsuario(Integer usuarioId) throws ExcepcionDigiNoteBD, ExcepcionDigiNoteComunicacion {

        try {
            //Direccion IP del servidor al que se quiere recurrir (Red)
            String equipoServidor = ip;
            //Puerto de acceso de la base de datos (Transporte)
            int puertoServidor = puerto;
            //Canal de comunicacion del cliente formado por IP y Puerto del servidor
            Socket socketCliente = new Socket(equipoServidor, puertoServidor);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            //Instancia de un objeto tipo peticion encargado de almacenar la peticion del usuario y los parametros necesarios para la realizacion de la peticion
            Peticion peticion = new Peticion(Peticion.ELIMINAR_USUARIO, usuarioId);
            //Escritura de la peticion sobre el flujo de datos, con los datos necesarios para la operacion 
            oos.writeObject(peticion);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            //Almacenamiento del contenido obtenido en la variable respuesta de tipo Object al leer el flujo de salida. Utiliza un casteo de tipo Object
            Object respuesta = (Object) ois.readObject();

            //Cierre de ambos flujos de datos
            oos.close();
            ois.close();

            //Comprobacion si la respuesta de peticion es de tipo Integer para devolver el numero de registros afectados
            if (respuesta.getClass().getCanonicalName().equals("java.lang.Integer")) {
                Integer registrosAfectados = (Integer) respuesta;
                return registrosAfectados;
            } //Control de excepcion ocurrido en la base de datos
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojo.ExcepcionDigiNoteBD")) {
                ExcepcionDigiNoteBD e = (ExcepcionDigiNoteBD) respuesta;
                throw e;
            } //Control de excepcion al ocurrir una excepcion en los comunicadores
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojocomunicaciones.ExcepcionDigiNoteComunicacion")) {
                ExcepcionDigiNoteComunicacion e = (ExcepcionDigiNoteComunicacion) respuesta;
                throw e;
            }

            //Captura y tratamiento de las posibles excepciones generales
        } catch (IOException ex) {
            System.out.println("Log IOException: " + ex.getMessage());
            ExcepcionDigiNoteComunicacion e = new ExcepcionDigiNoteComunicacion();
//            e.getMensajeErrorAdministrador(ex.getMessage());
//            e.getMensajeErrorUsuario("Error generla del sistema. Consulte con el administrador");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DigiNoteClienteComunicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Metodo de la clase DigiNoteClienteComunicaciones que prepara la peticion
     * de actualizar un usuario.
     *
     * @param usuarioId de tipo Integer
     * @param usuario de tipo Usuario
     * @return Integer
     * @throws ExcepcionDigiNoteBD
     * @throws ExcepcionDigiNoteComunicacion
     */
    public Integer actualizarUsuario(Integer usuarioId, Usuario usuario) throws ExcepcionDigiNoteBD, ExcepcionDigiNoteComunicacion {

        try {
            //Direccion IP del servidor al que se quiere recurrir (Red)
            String equipoServidor = ip;
            //Puerto de acceso de la base de datos (Transporte)
            int puertoServidor = puerto;
            //Canal de comunicacion del cliente formado por IP y Puerto del servidor
            Socket socketCliente = new Socket(equipoServidor, puertoServidor);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            //Instancia de un objeto tipo peticion encargado de almacenar la peticion del usuario y los parametros necesarios para la realizacion de la peticion
            Peticion peticion = new Peticion(Peticion.MODIFICAR_USUARIO, usuarioId, usuario);
            //Escritura de la peticion sobre el flujo de datos, con los datos necesarios para la operacion 
            oos.writeObject(peticion);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            //Almacenamiento del contenido obtenido en la variable respuesta de tipo Object al leer el flujo de salida. Utiliza un casteo de tipo Object
            Object respuesta = (Integer) ois.readObject();

            //Cierre de ambos flujos de datos
            oos.close();
            ois.close();

            //Comprobacion si la respuesta de peticion es de tipo Integer para devolver el numero de registros afectados
            if (respuesta.getClass().getCanonicalName().equals("java.lang.Integer")) {
                Integer registrosAfectados = (Integer) respuesta;
//                System.out.println(respuesta);
                return registrosAfectados;
            } //Control de excepcion ocurrido en la base de datos
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojo.ExcepcionDigiNoteBD")) {
                ExcepcionDigiNoteBD e = (ExcepcionDigiNoteBD) respuesta;
                throw e;
            } //Control de excepcion al ocurrir una excepcion en los comunicadores
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojocomunicaciones.ExcepcionDigiNoteComunicacion")) {
                ExcepcionDigiNoteComunicacion e = (ExcepcionDigiNoteComunicacion) respuesta;
                throw e;
            }

            //Captura y tratamiento de las posibles excepciones generales
        } catch (IOException ex) {
            System.out.println("Log IOException: " + ex.getMessage());
            ExcepcionDigiNoteComunicacion e = new ExcepcionDigiNoteComunicacion();
//            e.getMensajeErrorAdministrador(ex.getMessage());
//            e.getMensajeErrorUsuario("Error generla del sistema. Consulte con el administrador");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DigiNoteClienteComunicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Metodo de la clase DigiNoteClienteComunicaciones que prepara la peticion
     * de consultar un usuario.
     *
     * @param usuarioId de tipo Integer
     * @return Usuario
     * @throws ExcepcionDigiNoteBD
     * @throws ExcepcionDigiNoteComunicacion
     */
    public Usuario consultarUsuario(Integer usuarioId) throws ExcepcionDigiNoteBD, ExcepcionDigiNoteComunicacion {

        try {
            //Direccion IP del servidor al que se quiere recurrir (Red)
            String equipoServidor = ip;
            //Puerto de acceso de la base de datos (Transporte)
            int puertoServidor = puerto;
            //Canal de comunicacion del cliente formado por IP y Puerto del servidor
            Socket socketCliente = new Socket(equipoServidor, puertoServidor);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            //Instancia de un objeto tipo peticion encargado de almacenar la peticion del usuario y los parametros necesarios para la realizacion de la peticion
            Peticion peticion = new Peticion(Peticion.MOSTRAR_USUARIO, usuarioId);
            //Escritura de la peticion sobre el flujo de datos, con los datos necesarios para la operacion 
            oos.writeObject(peticion);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            //Almacenamiento del contenido obtenido en la variable respuesta de tipo Object al leer el flujo de salida. Utiliza un casteo de tipo Object
            Object respuesta = (Object) ois.readObject();
//            System.out.println(respuesta);

            //Cierre de ambos flujos de datos
            oos.close();
            ois.close();

            //Comprobacion si la respuesta de peticion es de tipo Usuario para devolver el usuario indicado
            if (respuesta.getClass().getCanonicalName().equals("diginotepojo.Usuario")) {
                Usuario registrosAfectados = (Usuario) respuesta;
                return registrosAfectados;
            } //Control de excepcion ocurrido en la base de datos
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojo.ExcepcionDigiNoteBD")) {
                ExcepcionDigiNoteBD e = (ExcepcionDigiNoteBD) respuesta;
                throw e;
            } //Control de excepcion al ocurrir una excepcion en los comunicadores
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojocomunicaciones.ExcepcionDigiNoteComunicacion")) {
                ExcepcionDigiNoteComunicacion e = (ExcepcionDigiNoteComunicacion) respuesta;
                throw e;
            }

            //Captura y tratamiento de las posibles excepciones generales
        } catch (IOException ex) {
            System.out.println("Log IOException: " + ex.getMessage());
            ExcepcionDigiNoteComunicacion e = new ExcepcionDigiNoteComunicacion();
//            e.getMensajeErrorAdministrador(ex.getMessage());
//            e.getMensajeErrorUsuario("Error generla del sistema. Consulte con el administrador");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DigiNoteClienteComunicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Metodo de la clase DigiNoteClienteComunicaciones que prepara la peticion
     * de consultar usuarios.
     * <hr>
     *
     * @return Arraylist de tipo Usuario
     * @throws ExcepcionDigiNoteBD
     * @throws ExcepcionDigiNoteComunicacion
     */
    public ArrayList<Usuario> consultarUsuarios() throws ExcepcionDigiNoteBD, ExcepcionDigiNoteComunicacion {

        try {
            //Direccion IP del servidor al que se quiere recurrir (Red)
            String equipoServidor = ip;
            //Puerto de acceso de la base de datos (Transporte)
            int puertoServidor = puerto;
            //Canal de comunicacion del cliente formado por IP y Puerto del servidor
            Socket socketCliente = new Socket(equipoServidor, puertoServidor);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            //Instancia de un objeto tipo peticion encargado de almacenar la peticion del usuario y los parametros necesarios para la realizacion de la peticion
            Peticion peticion = new Peticion(Peticion.MOSTRAR_USUARIOS);
            //Escritura de la peticion sobre el flujo de datos, con los datos necesarios para la operacion 
            oos.writeObject(peticion);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            //Almacenamiento del contenido obtenido en la variable respuesta de tipo Object al leer el flujo de salida. Utiliza un casteo de tipo Object
            Object respuesta = (Object) ois.readObject();

            //Cierre de ambos flujos de datos
            oos.close();
            ois.close();

            //Comprobacion si la respuesta de peticion es de tipo Usuario para devolver el usuario indicado
            if (respuesta.getClass().getCanonicalName().equals("java.util.ArrayList")) {

                ArrayList<Usuario> registrosAfectados = (ArrayList<Usuario>) respuesta;
                return registrosAfectados;
            } //Control de excepcion ocurrido en la base de datos
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojo.ExcepcionDigiNoteBD")) {
                ExcepcionDigiNoteBD e = (ExcepcionDigiNoteBD) respuesta;
                throw e;
            } //Control de excepcion al ocurrir una excepcion en los comunicadores
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojocomunicaciones.ExcepcionDigiNoteComunicacion")) {
                ExcepcionDigiNoteComunicacion e = (ExcepcionDigiNoteComunicacion) respuesta;
                throw e;
            }

            //Captura y tratamiento de las posibles excepciones generales
        } catch (IOException ex) {
            ExcepcionDigiNoteComunicacion e = new ExcepcionDigiNoteComunicacion();

            throw e;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DigiNoteClienteComunicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    //LENGUAJE
    /**
     * Metodo de la clase DigiNoteClienteComunicaciones que prepara la peticion
     * de insertar un lenguaje.
     *
     * @param lenguaje de tipo Lenguaje
     * @return Integer
     * @throws ExcepcionDigiNoteBD
     * @throws ExcepcionDigiNoteComunicacion
     */
    public Integer insertarLenguaje(Lenguaje lenguaje) throws ExcepcionDigiNoteBD, ExcepcionDigiNoteComunicacion {

        try {
            //Direccion IP del servidor al que se quiere recurrir (Red)
            String equipoServidor = ip;
            //Puerto de acceso de la base de datos (Transporte)
            int puertoServidor = puerto;
            //Canal de comunicacion del cliente formado por IP y Puerto del servidor
            Socket socketCliente = new Socket(equipoServidor, puertoServidor);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            //Instancia de un objeto tipo peticion encargado de almacenar la peticion del usuario y los parametros necesarios para la realizacion de la peticion
            Peticion peticion = new Peticion(Peticion.INSERTAR_LENGUAJE, lenguaje);
            //Escritura de la peticion sobre el flujo de datos, con los datos necesarios para la operacion 
            oos.writeObject(peticion);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            //Almacenamiento del contenido obtenido en la variable respuesta de tipo Object al leer el flujo de salida. Utiliza un casteo de tipo Object
            Object respuesta = (Object) ois.readObject();

            //Cierre de ambos flujos de datos
            oos.close();
            ois.close();

            //Comprobacion si la respuesta de peticion es de tipo Integer para devolver el numero de registros afectados
            if (respuesta.getClass().getCanonicalName().equals("java.lang.Integer")) {
                Integer registrosAfectados = (Integer) respuesta;
                return registrosAfectados;
            } //Control de excepcion ocurrido en la base de datos
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojo.ExcepcionDigiNoteBD")) {
                ExcepcionDigiNoteBD e = (ExcepcionDigiNoteBD) respuesta;
                throw e;
            } //Control de excepcion al ocurrir una excepcion en los comunicadores
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojocomunicaciones.ExcepcionDigiNoteComunicacion")) {
                ExcepcionDigiNoteComunicacion e = (ExcepcionDigiNoteComunicacion) respuesta;
                throw e;
            }

            //Captura y tratamiento de las posibles excepciones generales
        } catch (IOException ex) {
            System.out.println("Log IOException: " + ex.getMessage());
            ExcepcionDigiNoteComunicacion e = new ExcepcionDigiNoteComunicacion();
//            e.getMensajeErrorAdministrador(ex.getMessage());
//            e.getMensajeErrorUsuario("Error generla del sistema. Consulte con el administrador");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DigiNoteClienteComunicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Metodo de la clase DigiNoteClienteComunicaciones que prepara la peticion
     * de eliminar un lenguaje.
     *
     * @param lenguajeId de tipo Integer
     * @return Integer
     * @throws ExcepcionDigiNoteBD
     * @throws ExcepcionDigiNoteComunicacion
     */
    public Integer eliminarLenguaje(Integer lenguajeId) throws ExcepcionDigiNoteBD, ExcepcionDigiNoteComunicacion {

        try {
            //Direccion IP del servidor al que se quiere recurrir (Red)
            String equipoServidor = ip;
            //Puerto de acceso de la base de datos (Transporte)
            int puertoServidor = puerto;
            //Canal de comunicacion del cliente formado por IP y Puerto del servidor
            Socket socketCliente = new Socket(equipoServidor, puertoServidor);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            //Instancia de un objeto tipo peticion encargado de almacenar la peticion del usuario y los parametros necesarios para la realizacion de la peticion
            Peticion peticion = new Peticion(Peticion.ELIMINAR_LENGUAJE, lenguajeId);
            //Escritura de la peticion sobre el flujo de datos, con los datos necesarios para la operacion 
            oos.writeObject(peticion);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            //Almacenamiento del contenido obtenido en la variable respuesta de tipo Object al leer el flujo de salida. Utiliza un casteo de tipo Object
            Object respuesta = (Object) ois.readObject();

            //Cierre de ambos flujos de datos
            oos.close();
            ois.close();

            //Comprobacion si la respuesta de peticion es de tipo Integer para devolver el numero de registros afectados
            if (respuesta.getClass().getCanonicalName().equals("java.lang.Integer")) {
                Integer registrosAfectados = (Integer) respuesta;
                return registrosAfectados;
            } //Control de excepcion ocurrido en la base de datos
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojo.ExcepcionDigiNoteBD")) {
                ExcepcionDigiNoteBD e = (ExcepcionDigiNoteBD) respuesta;
                throw e;
            } //Control de excepcion al ocurrir una excepcion en los comunicadores
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojocomunicaciones.ExcepcionDigiNoteComunicacion")) {
                ExcepcionDigiNoteComunicacion e = (ExcepcionDigiNoteComunicacion) respuesta;
                throw e;
            }

            //Captura y tratamiento de las posibles excepciones generales
        } catch (IOException ex) {
            System.out.println("Log IOException: " + ex.getMessage());
            ExcepcionDigiNoteComunicacion e = new ExcepcionDigiNoteComunicacion();
//            e.getMensajeErrorAdministrador(ex.getMessage());
//            e.getMensajeErrorUsuario("Error generla del sistema. Consulte con el administrador");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DigiNoteClienteComunicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Metodo de la clase DigiNoteClienteComunicaciones que prepara la peticion
     * de actualizar un lenguaje.
     *
     * @param lenguajeId de tipo Integer
     * @param lenguaje de tipo Lenguaje
     * @return Integer
     * @throws ExcepcionDigiNoteBD
     * @throws ExcepcionDigiNoteComunicacion
     */
    public Integer actualizarLenguaje(Integer lenguajeId, Lenguaje lenguaje) throws ExcepcionDigiNoteBD, ExcepcionDigiNoteComunicacion {

        try {
            //Direccion IP del servidor al que se quiere recurrir (Red)
            String equipoServidor = ip;
            //Puerto de acceso de la base de datos (Transporte)
            int puertoServidor = puerto;
            //Canal de comunicacion del cliente formado por IP y Puerto del servidor
            Socket socketCliente = new Socket(equipoServidor, puertoServidor);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            //Instancia de un objeto tipo peticion encargado de almacenar la peticion del usuario y los parametros necesarios para la realizacion de la peticion
            Peticion peticion = new Peticion(Peticion.MODIFICAR_LENGUAJE, lenguajeId, lenguaje);
            //Escritura de la peticion sobre el flujo de datos, con los datos necesarios para la operacion 
            oos.writeObject(peticion);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            //Almacenamiento del contenido obtenido en la variable respuesta de tipo Object al leer el flujo de salida. Utiliza un casteo de tipo Object
            Object respuesta = (Object) ois.readObject();

            //Cierre de ambos flujos de datos
            oos.close();
            ois.close();

            //Comprobacion si la respuesta de peticion es de tipo Integer para devolver el numero de registros afectados
            if (respuesta.getClass().getCanonicalName().equals("java.lang.Integer")) {
                Integer registrosAfectados = (Integer) respuesta;
                return registrosAfectados;
            } //Control de excepcion ocurrido en la base de datos
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojo.ExcepcionDigiNoteBD")) {
                ExcepcionDigiNoteBD e = (ExcepcionDigiNoteBD) respuesta;
                throw e;
            } //Control de excepcion al ocurrir una excepcion en los comunicadores
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojocomunicaciones.ExcepcionDigiNoteComunicacion")) {
                ExcepcionDigiNoteComunicacion e = (ExcepcionDigiNoteComunicacion) respuesta;
                throw e;
            }

            //Captura y tratamiento de las posibles excepciones generales
        } catch (IOException ex) {
            System.out.println("Log IOException: " + ex.getMessage());
            ExcepcionDigiNoteComunicacion e = new ExcepcionDigiNoteComunicacion();
//            e.getMensajeErrorAdministrador(ex.getMessage());
//            e.getMensajeErrorUsuario("Error generla del sistema. Consulte con el administrador");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DigiNoteClienteComunicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Metodo de la clase DigiNoteClienteComunicaciones que prepara la peticion
     * de consultar un lenguaje.
     *
     * @param lenguajeId de tipo Integer
     * @return Lenguaje
     * @throws ExcepcionDigiNoteBD
     * @throws ExcepcionDigiNoteComunicacion
     */
    public Lenguaje consultarLenguaje(Integer lenguajeId) throws ExcepcionDigiNoteBD, ExcepcionDigiNoteComunicacion {
        try {
            //Direccion IP del servidor al que se quiere recurrir (Red)
            String equipoServidor = ip;
            //Puerto de acceso de la base de datos (Transporte)
            int puertoServidor = puerto;
            //Canal de comunicacion del cliente formado por IP y Puerto del servidor
            Socket socketCliente = new Socket(equipoServidor, puertoServidor);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            //Instancia de un objeto tipo peticion encargado de almacenar la peticion del usuario y los parametros necesarios para la realizacion de la peticion
            Peticion peticion = new Peticion(Peticion.MOSTRAR_LENGUAJE, lenguajeId);
            //Escritura de la peticion sobre el flujo de datos, con los datos necesarios para la operacion 
            oos.writeObject(peticion);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            //Almacenamiento del contenido obtenido en la variable respuesta de tipo Object al leer el flujo de salida. Utiliza un casteo de tipo Object
            Object respuesta = (Object) ois.readObject();

            //Cierre de ambos flujos de datos
            oos.close();
            ois.close();

            //Comprobacion si la respuesta de peticion es de tipo Lenguaje para devolver el lenguaje indicado
            if (respuesta.getClass().getCanonicalName().equals("diginotepojo.Lenguaje")) {
                Lenguaje registrosAfectados = (Lenguaje) respuesta;
                return registrosAfectados;
            } //Control de excepcion ocurrido en la base de datos
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojo.ExcepcionDigiNoteBD")) {
                ExcepcionDigiNoteBD e = (ExcepcionDigiNoteBD) respuesta;
                throw e;
            } //Control de excepcion al ocurrir una excepcion en los comunicadores
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojocomunicaciones.ExcepcionDigiNoteComunicacion")) {
                ExcepcionDigiNoteComunicacion e = (ExcepcionDigiNoteComunicacion) respuesta;
                throw e;
            }

            //Captura y tratamiento de las posibles excepciones generales
        } catch (IOException ex) {
            System.out.println("Log IOException: " + ex.getMessage());
            ExcepcionDigiNoteComunicacion e = new ExcepcionDigiNoteComunicacion();
//            e.getMensajeErrorAdministrador(ex.getMessage());
//            e.getMensajeErrorUsuario("Error generla del sistema. Consulte con el administrador");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DigiNoteClienteComunicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Metodo de la clase DigiNoteClienteComunicaciones que prepara la peticion
     * de conslutar lenguajes.
     *
     * @return ArrayList de Lenguajes
     * @throws ExcepcionDigiNoteBD
     * @throws ExcepcionDigiNoteComunicacion
     */
    public ArrayList<Lenguaje> consultarLenguajes() throws ExcepcionDigiNoteBD, ExcepcionDigiNoteComunicacion {

        try {
            //Direccion IP del servidor al que se quiere recurrir (Red)
            String equipoServidor = ip;
            //Puerto de acceso de la base de datos (Transporte)
            int puertoServidor = puerto;
            //Canal de comunicacion del cliente formado por IP y Puerto del servidor
            Socket socketCliente = new Socket(equipoServidor, puertoServidor);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            //Instancia de un objeto tipo peticion encargado de almacenar la peticion del usuario y los parametros necesarios para la realizacion de la peticion
            Peticion peticion = new Peticion(Peticion.MOSTRAR_LENGUAJES);
            //Escritura de la peticion sobre el flujo de datos, con los datos necesarios para la operacion 
            oos.writeObject(peticion);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            //Almacenamiento del contenido obtenido en la variable respuesta de tipo Object al leer el flujo de salida. Utiliza un casteo de tipo Object
            Object respuesta = (Object) ois.readObject();

            //Cierre de ambos flujos de datos
            oos.close();
            ois.close();

            //Comprobacion si la respuesta de peticion es de tipo Lenguaje para devolver el array de lenguajes 
            if (respuesta.getClass().getCanonicalName().equals("java.util.ArrayList")) {
                ArrayList<Lenguaje> registrosAfectados = (ArrayList<Lenguaje>) respuesta;
                return registrosAfectados;
            } //Control de excepcion ocurrido en la base de datos
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojo.ExcepcionDigiNoteBD")) {
                ExcepcionDigiNoteBD e = (ExcepcionDigiNoteBD) respuesta;
                throw e;
            } //Control de excepcion al ocurrir una excepcion en los comunicadores
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojocomunicaciones.ExcepcionDigiNoteComunicacion")) {
                ExcepcionDigiNoteComunicacion e = (ExcepcionDigiNoteComunicacion) respuesta;
                throw e;
            }

            //Captura y tratamiento de las posibles excepciones generales
        } catch (IOException ex) {
            System.out.println("Log IOException: " + ex.getMessage());
            ExcepcionDigiNoteComunicacion e = new ExcepcionDigiNoteComunicacion();
//            e.getMensajeErrorAdministrador(ex.getMessage());
//            e.getMensajeErrorUsuario("Error generla del sistema. Consulte con el administrador");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DigiNoteClienteComunicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    //ENLACE
    /**
     * Metodo de la clase DigiNoteClienteComunicaciones que prepara la peticion
     * de insertar un enlace.
     *
     * @param enlace de tipo Enlace
     * @return Integer
     * @throws ExcepcionDigiNoteBD
     * @throws ExcepcionDigiNoteComunicacion
     */
    public Integer insertarEnlace(Enlace enlace) throws ExcepcionDigiNoteBD, ExcepcionDigiNoteComunicacion {

        try {
            //Direccion IP del servidor al que se quiere recurrir (Red)
            String equipoServidor = ip;
            //Puerto de acceso de la base de datos (Transporte)
            int puertoServidor = puerto;
            //Canal de comunicacion del cliente formado por IP y Puerto del servidor
            Socket socketCliente = new Socket(equipoServidor, puertoServidor);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            //Instancia de un objeto tipo peticion encargado de almacenar la peticion del usuario y los parametros necesarios para la realizacion de la peticion
            Peticion peticion = new Peticion(Peticion.INSERTAR_ENLACE, enlace);
            //Escritura de la peticion sobre el flujo de datos, con los datos necesarios para la operacion 
            oos.writeObject(peticion);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            //Almacenamiento del contenido obtenido en la variable respuesta de tipo Object al leer el flujo de salida. Utiliza un casteo de tipo Object
            Object respuesta = (Object) ois.readObject();

            //Cierre de ambos flujos de datos
            oos.close();
            ois.close();

            //Comprobacion si la respuesta de peticion es de tipo Integer para devolver el numero de registros afectados
            if (respuesta.getClass().getCanonicalName().equals("java.lang.Integer")) {
                Integer registrosAfectados = (Integer) respuesta;
                return registrosAfectados;
            } //Control de excepcion ocurrido en la base de datos
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojo.ExcepcionDigiNoteBD")) {
                ExcepcionDigiNoteBD e = (ExcepcionDigiNoteBD) respuesta;
                throw e;
            } //Control de excepcion al ocurrir una excepcion en los comunicadores
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojocomunicaciones.ExcepcionDigiNoteComunicacion")) {
                ExcepcionDigiNoteComunicacion e = (ExcepcionDigiNoteComunicacion) respuesta;
                throw e;
            }

            //Captura y tratamiento de las posibles excepciones generales
        } catch (IOException ex) {
            System.out.println("Log IOException: " + ex.getMessage());
            ExcepcionDigiNoteComunicacion e = new ExcepcionDigiNoteComunicacion();
//            e.getMensajeErrorAdministrador(ex.getMessage());
//            e.getMensajeErrorUsuario("Error generla del sistema. Consulte con el administrador");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DigiNoteClienteComunicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Metodo de la clase DigiNoteClienteComunicaciones que prepara la peticion
     * de eliminar un enlace.
     *
     * @param enlaceId de tipo Integer
     * @return Integer
     * @throws ExcepcionDigiNoteBD
     * @throws ExcepcionDigiNoteComunicacion
     */
    public Integer eliminarEnlace(Integer enlaceId) throws ExcepcionDigiNoteBD, ExcepcionDigiNoteComunicacion {

        try {
            //Direccion IP del servidor al que se quiere recurrir (Red)
            String equipoServidor = ip;
            //Puerto de acceso de la base de datos (Transporte)
            int puertoServidor = puerto;
            //Canal de comunicacion del cliente formado por IP y Puerto del servidor
            Socket socketCliente = new Socket(equipoServidor, puertoServidor);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            //Instancia de un objeto tipo peticion encargado de almacenar la peticion del usuario y los parametros necesarios para la realizacion de la peticion
            Peticion peticion = new Peticion(Peticion.ELIMINAR_ENLACE, enlaceId);
            //Escritura de la peticion sobre el flujo de datos, con los datos necesarios para la operacion 
            oos.writeObject(peticion);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            //Almacenamiento del contenido obtenido en la variable respuesta de tipo Object al leer el flujo de salida. Utiliza un casteo de tipo Object
            Object respuesta = (Object) ois.readObject();

            //Cierre de ambos flujos de datos
            oos.close();
            ois.close();

            //Comprobacion si la respuesta de peticion es de tipo Integer para devolver el numero de registros afectados
            if (respuesta.getClass().getCanonicalName().equals("java.lang.Integer")) {
                Integer registrosAfectados = (Integer) respuesta;
                return registrosAfectados;
            } //Control de excepcion ocurrido en la base de datos
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojo.ExcepcionDigiNoteBD")) {
                ExcepcionDigiNoteBD e = (ExcepcionDigiNoteBD) respuesta;
                throw e;
            } //Control de excepcion al ocurrir una excepcion en los comunicadores
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojocomunicaciones.ExcepcionDigiNoteComunicacion")) {
                ExcepcionDigiNoteComunicacion e = (ExcepcionDigiNoteComunicacion) respuesta;
                throw e;
            }

            //Captura y tratamiento de las posibles excepciones generales
        } catch (IOException ex) {
            System.out.println("Log IOException: " + ex.getMessage());
            ExcepcionDigiNoteComunicacion e = new ExcepcionDigiNoteComunicacion();
//            e.getMensajeErrorAdministrador(ex.getMessage());
//            e.getMensajeErrorUsuario("Error generla del sistema. Consulte con el administrador");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DigiNoteClienteComunicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Metodo de la clase DigiNoteClienteComunicaciones que prepara la peticion
     * de actualizar un enlace.
     *
     * @param enlaceId de tipo Integer
     * @param enlace de tipo Enlace
     * @return Integer
     * @throws ExcepcionDigiNoteBD
     * @throws ExcepcionDigiNoteComunicacion
     */
    public Integer actualizarEnlace(Integer enlaceId, Enlace enlace) throws ExcepcionDigiNoteBD, ExcepcionDigiNoteComunicacion {

        try {
            //Direccion IP del servidor al que se quiere recurrir (Red)
            String equipoServidor = ip;
            //Puerto de acceso de la base de datos (Transporte)
            int puertoServidor = puerto;
            //Canal de comunicacion del cliente formado por IP y Puerto del servidor
            Socket socketCliente = new Socket(equipoServidor, puertoServidor);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            //Instancia de un objeto tipo peticion encargado de almacenar la peticion del usuario y los parametros necesarios para la realizacion de la peticion
            Peticion peticion = new Peticion(Peticion.MODIFICAR_ENLACE, enlaceId, enlace);
            //Escritura de la peticion sobre el flujo de datos, con los datos necesarios para la operacion 
            oos.writeObject(peticion);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            //Almacenamiento del contenido obtenido en la variable respuesta de tipo Object al leer el flujo de salida. Utiliza un casteo de tipo Object
            Object respuesta = (Object) ois.readObject();

            //Cierre de ambos flujos de datos
            oos.close();
            ois.close();

            //Comprobacion si la respuesta de peticion es de tipo Integer para devolver el numero de registros afectados
            if (respuesta.getClass().getCanonicalName().equals("java.lang.Integer")) {
                Integer registrosAfectados = (Integer) respuesta;
                return registrosAfectados;
            } //Control de excepcion ocurrido en la base de datos
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojo.ExcepcionDigiNoteBD")) {
                ExcepcionDigiNoteBD e = (ExcepcionDigiNoteBD) respuesta;
                throw e;
            } //Control de excepcion al ocurrir una excepcion en los comunicadores
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojocomunicaciones.ExcepcionDigiNoteComunicacion")) {
                ExcepcionDigiNoteComunicacion e = (ExcepcionDigiNoteComunicacion) respuesta;
                throw e;
            }

            //Captura y tratamiento de las posibles excepciones generales
        } catch (IOException ex) {
            System.out.println("Log IOException: " + ex.getMessage());
            ExcepcionDigiNoteComunicacion e = new ExcepcionDigiNoteComunicacion();
//            e.getMensajeErrorAdministrador(ex.getMessage());
//            e.getMensajeErrorUsuario("Error generla del sistema. Consulte con el administrador");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DigiNoteClienteComunicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Metodo de la clase DigiNoteClienteComunicaciones que prepara la peticion
     * de consultar un enlace.
     *
     * @param enlaceId de tipo Integer
     * @return Enlace
     * @throws ExcepcionDigiNoteBD
     * @throws ExcepcionDigiNoteComunicacion
     */
    public Enlace consultarEnlace(Integer enlaceId) throws ExcepcionDigiNoteBD, ExcepcionDigiNoteComunicacion {

        try {
            //Direccion IP del servidor al que se quiere recurrir (Red)
            String equipoServidor = ip;
            //Puerto de acceso de la base de datos (Transporte)
            int puertoServidor = puerto;
            //Canal de comunicacion del cliente formado por IP y Puerto del servidor
            Socket socketCliente = new Socket(equipoServidor, puertoServidor);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            //Instancia de un objeto tipo peticion encargado de almacenar la peticion del usuario y los parametros necesarios para la realizacion de la peticion
            Peticion peticion = new Peticion(Peticion.MOSTRAR_ENLACE, enlaceId);
            //Escritura de la peticion sobre el flujo de datos, con los datos necesarios para la operacion 
            oos.writeObject(peticion);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            //Almacenamiento del contenido obtenido en la variable respuesta de tipo Object al leer el flujo de salida. Utiliza un casteo de tipo Object
            Object respuesta = (Object) ois.readObject();

            //Cierre de ambos flujos de datos
            oos.close();
            ois.close();

            //Comprobacion si la respuesta de peticion es de tipo Lenguaje para devolver el enlace indicado 
            if (respuesta.getClass().getCanonicalName().equals("diginotepojo.Enlace")) {
                Enlace registrosAfectados = (Enlace) respuesta;
                return registrosAfectados;
            } //Control de excepcion ocurrido en la base de datos
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojo.ExcepcionDigiNoteBD")) {
                ExcepcionDigiNoteBD e = (ExcepcionDigiNoteBD) respuesta;
                throw e;
            } //Control de excepcion al ocurrir una excepcion en los comunicadores
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojocomunicaciones.ExcepcionDigiNoteComunicacion")) {
                ExcepcionDigiNoteComunicacion e = (ExcepcionDigiNoteComunicacion) respuesta;
                throw e;
            }

            //Captura y tratamiento de las posibles excepciones generales
        } catch (IOException ex) {
            System.out.println("Log IOException: " + ex.getMessage());
            ExcepcionDigiNoteComunicacion e = new ExcepcionDigiNoteComunicacion();
//            e.getMensajeErrorAdministrador(ex.getMessage());
//            e.getMensajeErrorUsuario("Error generla del sistema. Consulte con el administrador");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DigiNoteClienteComunicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Metodo de la clase DigiNoteClienteComunicaciones que prepara la peticion
     * de consultar enlaces.
     *
     * @return ArrayList de Enlaces
     * @throws ExcepcionDigiNoteBD
     * @throws ExcepcionDigiNoteComunicacion
     */
    public ArrayList<Enlace> consultarEnlaces() throws ExcepcionDigiNoteBD, ExcepcionDigiNoteComunicacion {

        try {
            //Direccion IP del servidor al que se quiere recurrir (Red)
            String equipoServidor = ip;
            //Puerto de acceso de la base de datos (Transporte)
            int puertoServidor = puerto;
            //Canal de comunicacion del cliente formado por IP y Puerto del servidor
            Socket socketCliente = new Socket(equipoServidor, puertoServidor);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            //Instancia de un objeto tipo peticion encargado de almacenar la peticion del usuario y los parametros necesarios para la realizacion de la peticion
            Peticion peticion = new Peticion(Peticion.MOSTRAR_ENLACES);
            //Escritura de la peticion sobre el flujo de datos, con los datos necesarios para la operacion 
            oos.writeObject(peticion);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            //Almacenamiento del contenido obtenido en la variable respuesta de tipo Object al leer el flujo de salida. Utiliza un casteo de tipo Object
            Object respuesta = (Object) ois.readObject();

            //Cierre de ambos flujos de datos
            oos.close();
            ois.close();

            //Comprobacion si la respuesta de peticion es de tipo Lenguaje para devolver el array de Enlaces 
            if (respuesta.getClass().getCanonicalName().equals("java.util.ArrayList")) {
                ArrayList<Enlace> registrosAfectados = (ArrayList<Enlace>) respuesta;
                return registrosAfectados;
            } //Control de excepcion ocurrido en la base de datos
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojo.ExcepcionDigiNoteBD")) {
                ExcepcionDigiNoteBD e = (ExcepcionDigiNoteBD) respuesta;
                throw e;
            } //Control de excepcion al ocurrir una excepcion en los comunicadores
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojocomunicaciones.ExcepcionDigiNoteComunicacion")) {
                ExcepcionDigiNoteComunicacion e = (ExcepcionDigiNoteComunicacion) respuesta;
                throw e;
            }

            //Captura y tratamiento de las posibles excepciones generales
        } catch (IOException ex) {
            System.out.println("Log IOException: " + ex.getMessage());
            ExcepcionDigiNoteComunicacion e = new ExcepcionDigiNoteComunicacion();
//            e.getMensajeErrorAdministrador(ex.getMessage());
//            e.getMensajeErrorUsuario("Error generla del sistema. Consulte con el administrador");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DigiNoteClienteComunicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Metodo de la clase DigiNoteClienteComunicaciones que prepara la peticion
     * de consultar enlaces favoritos.
     *
     * @return ArrayList de Enlace
     * @throws ExcepcionDigiNoteBD
     * @throws ExcepcionDigiNoteComunicacion
     */
    public ArrayList<Enlace> consultarEnlacesFavoritos() throws ExcepcionDigiNoteBD, ExcepcionDigiNoteComunicacion {

        try {
            //Direccion IP del servidor al que se quiere recurrir (Red)
            String equipoServidor = ip;
            //Puerto de acceso de la base de datos (Transporte)
            int puertoServidor = puerto;
            //Canal de comunicacion del cliente formado por IP y Puerto del servidor
            Socket socketCliente = new Socket(equipoServidor, puertoServidor);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            //Instancia de un objeto tipo peticion encargado de almacenar la peticion del usuario y los parametros necesarios para la realizacion de la peticion
            Peticion peticion = new Peticion(Peticion.MOSTRAR_ENLACES_FAVORITOS);
            //Escritura de la peticion sobre el flujo de datos, con los datos necesarios para la operacion 
            oos.writeObject(peticion);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            //Almacenamiento del contenido obtenido en la variable respuesta de tipo Object al leer el flujo de salida. Utiliza un casteo de tipo Object
            Object respuesta = (Object) ois.readObject();

            //Cierre de ambos flujos de datos
            oos.close();
            ois.close();

            //Comprobacion si la respuesta de peticion es de tipo Lenguaje para devolver el array de Enlaces 
            if (respuesta.getClass().getCanonicalName().equals("java.util.ArrayList")) {
                ArrayList<Enlace> registrosAfectados = (ArrayList<Enlace>) respuesta;
                return registrosAfectados;
            } //Control de excepcion ocurrido en la base de datos
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojo.ExcepcionDigiNoteBD")) {
                ExcepcionDigiNoteBD e = (ExcepcionDigiNoteBD) respuesta;
                throw e;
            } //Control de excepcion al ocurrir una excepcion en los comunicadores
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojocomunicaciones.ExcepcionDigiNoteComunicacion")) {
                ExcepcionDigiNoteComunicacion e = (ExcepcionDigiNoteComunicacion) respuesta;
                throw e;
            }

            //Captura y tratamiento de las posibles excepciones generales
        } catch (IOException ex) {
            System.out.println("Log IOException: " + ex.getMessage());
            ExcepcionDigiNoteComunicacion e = new ExcepcionDigiNoteComunicacion();
//            e.getMensajeErrorAdministrador(ex.getMessage());
//            e.getMensajeErrorUsuario("Error generla del sistema. Consulte con el administrador");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DigiNoteClienteComunicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Metodo de la clase DigiNoteClienteComunicaciones que prepara la peticion
     * de actualizar un enlace favorito.
     *
     * @param enlaceId de tipo Integer
     * @param enlace de tipo Enlace
     * @return Integer
     * @throws ExcepcionDigiNoteBD
     * @throws ExcepcionDigiNoteComunicacion
     */
    public Integer actualizarEnlaceFavorito(Integer enlaceId, Enlace enlace) throws ExcepcionDigiNoteBD, ExcepcionDigiNoteComunicacion {

        try {
            //Direccion IP del servidor al que se quiere recurrir (Red)
            String equipoServidor = ip;
            //Puerto de acceso de la base de datos (Transporte)
            int puertoServidor = puerto;
            //Canal de comunicacion del cliente formado por IP y Puerto del servidor
            Socket socketCliente = new Socket(equipoServidor, puertoServidor);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            //Instancia de un objeto tipo peticion encargado de almacenar la peticion del usuario y los parametros necesarios para la realizacion de la peticion
            Peticion peticion = new Peticion(Peticion.MODIFICAR_ENLACE_FAVORITO, enlaceId, enlace);
            //Escritura de la peticion sobre el flujo de datos, con los datos necesarios para la operacion 
            oos.writeObject(peticion);

            //Instancia de un flujo de datos de tipo Object en el socket del cliente 
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            //Almacenamiento del contenido obtenido en la variable respuesta de tipo Object al leer el flujo de salida. Utiliza un casteo de tipo Object
            Object respuesta = (Object) ois.readObject();

            //Cierre de ambos flujos de datos
            oos.close();
            ois.close();

            //Comprobacion si la respuesta de peticion es de tipo Integer para devolver el numero de registros afectados
            if (respuesta.getClass().getCanonicalName().equals("java.lang.Integer")) {
                Integer registrosAfectados = (Integer) respuesta;
                return registrosAfectados;
            } //Control de excepcion ocurrido en la base de datos
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojo.ExcepcionDigiNoteBD")) {
                ExcepcionDigiNoteBD e = (ExcepcionDigiNoteBD) respuesta;
                throw e;
            } //Control de excepcion al ocurrir una excepcion en los comunicadores
            else if (respuesta.getClass().getCanonicalName().equals("diginotepojocomunicaciones.ExcepcionDigiNoteComunicacion")) {
                ExcepcionDigiNoteComunicacion e = (ExcepcionDigiNoteComunicacion) respuesta;
                throw e;
            }

            //Captura y tratamiento de las posibles excepciones generales
        } catch (IOException ex) {
            System.out.println("Log IOException: " + ex.getMessage());
            ExcepcionDigiNoteComunicacion e = new ExcepcionDigiNoteComunicacion();
//            e.getMensajeErrorAdministrador(ex.getMessage());
//            e.getMensajeErrorUsuario("Error generla del sistema. Consulte con el administrador");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DigiNoteClienteComunicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
