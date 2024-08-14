/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diginotecad;

import diginotepojo.Enlace;
import diginotepojo.ExcepcionDigiNoteBD;
import diginotepojo.Lenguaje;
import diginotepojo.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Clase que implementa el componente de acceso a datos.
 *
 * @author Imanol Callejo Baranda
 * @version 1.1.0
 * @since Mayo 2023
 */
public class DigiNoteCAD {

    Connection conexion;

    /**
     * Constructor del componente de acceso a datos.
     *
     * @throws ExcepcionDigiNoteBD cuando el driver que soporta el codigo Java
     * en la base de datos no se ha encontrado
     */
    public DigiNoteCAD() throws ExcepcionDigiNoteBD {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            ExcepcionDigiNoteBD e = new ExcepcionDigiNoteBD();
            e.setMensajeErrorAdministrador(ex.getMessage());
            e.setMensajeErrorUsuario("Error general en el sistema. Consulte con el administrador");
            //ARROJA UNA EXCEPCIÓN DE TIPO ExcepcionDigiNoteBD PARA QUE LO GESTIONE EL HILO DE PETICION
            throw e;
        }

    }

    /**
     * Metodo para establecer la conexion con la base de datos
     *
     * @throws ExcepcionDigiNoteBD cuando no pueda conectarse a la base de datos
     */
    private void conectar() throws ExcepcionDigiNoteBD {
        //jdbc:oracle:thin:  -> es la URL del controlador jdbc de oracle utilizado para la conexión a la BD
        //@127.0.0.1  -> es la dirección IP donde se encuentra la BD
        //1521  -> es el puerto de escuche de las conexiones
        //xe  -> es la instancia de la BD Express Edition
        try {
            conexion = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "DIGINOTE", "kk");
        } catch (SQLException ex) {
            ExcepcionDigiNoteBD e = new ExcepcionDigiNoteBD();
            e.setMensajeErrorAdministrador(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(null);
            e.setMensajeErrorUsuario("Error general del sistemaa. Consulte con el administrador.");
            //ARROJA UNA EXCEPCIÓN DE TIPO ExcepcionDigiNoteBD PARA QUE LO GESTIONE EL HILO DE PETICION
            throw e;
        }
    }

    //USUARIOS
    /**
     * Metodo para insertar un nuevo registro de usuario en la base de datos.
     * Utiliza ID autoincremental
     *
     * @since 1.0
     * @param usuario parametro de tipo Usuario
     * @return int del numero de registros afectados
     * @throws ExcepcionDigiNoteBD por utilizacion incorrecta de una sentencia
     * SQL.
     */
    public int insertarUsuario(Usuario usuario) throws ExcepcionDigiNoteBD {
        conectar();
        String dml = "insert into USUARIO(USUARIO_ID, EMAIL, PASSWORD) values (SECUENCIA_INCREMENT_USUARIO_ID.nextval, ?, ?)";
        int registrosAfectados;
        try {

            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setString(1, usuario.getEmail());
            sentenciaPreparada.setString(2, usuario.getPassword());

            registrosAfectados = sentenciaPreparada.executeUpdate();

            sentenciaPreparada.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionDigiNoteBD e = new ExcepcionDigiNoteBD();
            e.setMensajeErrorAdministrador(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(dml);
            switch (ex.getErrorCode()) {
                case 0001:
                    e.setMensajeErrorUsuario("El Usuario ya está en uso, prueba con otro distinto");
                    break;
                case 2290:
                    e.setMensajeErrorUsuario("El email no está escrito correctamente. Debe cumplir el siguiente formato xxxxx@xxxxx.xx ");
                    break;
                case 1400:
                    e.setMensajeErrorUsuario("El email o la contraseña del usuario no están definidos");
                    break;
                default:
                    e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
//                    e.setMensajeErrorUsuario(e.getCodigoError().toString());
                    break;
            }
            //ARROJA UNA EXCEPCIÓN DE TIPO ExcepcionDigiNoteBD PARA QUE LO GESTIONE EL HILO DE PETICION
            throw e;
        }
        return registrosAfectados;
    }

    /**
     * Metodo para eliminar el registro de un usuario de la base de datos.
     *
     * @since 1.0
     * @param usuarioId parametro de tipo Integer
     * @return int del numero de registros afectados
     * @throws ExcepcionDigiNoteBD por utilizacion incorrecta de una sentencia
     * SQL.
     */
    public int eliminarUsuario(Integer usuarioId) throws ExcepcionDigiNoteBD {
        conectar();
       
        String dml = "delete from USUARIO "
                + "where usuario_id = ?";
        int registrosAfectados;
        try {
            
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setInt(1, usuarioId);

            registrosAfectados = sentenciaPreparada.executeUpdate();

            sentenciaPreparada.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionDigiNoteBD e = new ExcepcionDigiNoteBD();
            e.setMensajeErrorAdministrador(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(dml);
            switch (ex.getErrorCode()) {
                case 936:
                    e.setMensajeErrorUsuario("El dato necesario para eliminar el usuario no esta definido");
                    break;
                case 2292:
                    e.setMensajeErrorUsuario("No se puede eliminar el usuario sin eliminar anteriormente los lenguajes con sus respectivos enlaces");
                    break;
                default:
                    e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
                    break;
            }
            //ARROJA UNA EXCEPCIÓN DE TIPO ExcepcionDigiNoteBD PARA QUE LO GESTIONE EL HILO DE PETICION
            throw e;
        }
        return registrosAfectados;
    }

    /**
     * Metodo para modificar los datos de un registro de usuario ya existente en
     * la base de datos. La ID no se modifica.
     *
     * @since 1.0
     * @param usuarioId parametro de tipo Integer
     * @param usuario parametro de tipo usuario
     * @return int del numero de registros afectados
     * @throws ExcepcionDigiNoteBD por utilizacion incorrecta de una sentencia
     * SQL.
     */
    public int actualizarUsuario(Integer usuarioId, Usuario usuario) throws ExcepcionDigiNoteBD {

        int registrosAfectados;
        String dml = "update USUARIO set EMAIL= ?,password=? where usuario_id =" + usuarioId;
        try {
            conectar();

            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setString(1, usuario.getEmail());
            sentenciaPreparada.setString(2, usuario.getPassword());

            registrosAfectados = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();

            conexion.close();

        } catch (SQLException ex) {
            ExcepcionDigiNoteBD e = new ExcepcionDigiNoteBD();
            e.setMensajeErrorAdministrador(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(dml);
            switch (ex.getErrorCode()) {
                case 00001:
                    e.setMensajeErrorUsuario("Ese usuario ya existe. Prueba con otro");
                    break;
                    case 2290:
                    e.setMensajeErrorUsuario("El email no está escrito correctamente. Debe cumplir el siguiente formato xxxxx@xxxxx.xx ");
                    break;
                case 12899:
                    e.setMensajeErrorUsuario("El email es demasiado largo. No puede superar 40 caracteres");
                    break;
                case 1407:
                    e.setMensajeErrorUsuario("La contraseña o el correo no pueden estar vacias");
                    break;
                default:
                    e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
                    break;
            }
            //ARROJA UNA EXCEPCIÓN DE TIPO ExcepcionDigiNoteBD PARA QUE LO GESTIONE EL HILO DE PETICION
            throw e;
        }
        return registrosAfectados;
    }

    /**
     * Metodo para mostrar los datos de un registro de usuario de la base de
     * datos.
     *
     * @since 1.0
     * @param usuarioId parametro de tipo Integer
     * @return objeto de tipo Usuario
     * @throws ExcepcionDigiNoteBD por utilizacion incorrecta de una sentencia
     * SQL.
     */
    public Usuario consultarUsuario(Integer usuarioId) throws ExcepcionDigiNoteBD {
        conectar();
        Usuario u = new Usuario();
        try {
            Statement sentencia = conexion.createStatement();

            String dql = "select * from USUARIO  U "
                    + "where U.USUARIO_ID = " + usuarioId;

            ResultSet resultado = sentencia.executeQuery(dql);
            if (resultado.next()) {

                u.setUsuarioId(resultado.getInt("usuario_Id"));
                u.setEmail(resultado.getString("email"));
                u.setPassword(resultado.getString("password"));

            }
            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionDigiNoteBD exc = new ExcepcionDigiNoteBD();
            exc.setMensajeErrorAdministrador(ex.getMessage());
            exc.setCodigoError(ex.getErrorCode());
            exc.setSentenciaSQL(null);
            exc.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
            //ARROJA UNA EXCEPCIÓN DE TIPO ExcepcionDigiNoteBD PARA QUE LO GESTIONE EL HILO DE PETICION
            throw exc;
        }
        return u;
    }

    /**
     * Metodo para mostrar todos los registros existentes de usuarios en la base
     * de datos.
     *
     * @since 1.0
     * @return ArrayList de tipo Usuario
     * @throws ExcepcionDigiNoteBD por utilizacion incorrecta de una sentencia
     * SQL.
     */
    public ArrayList<Usuario> consultarUsuarios() throws ExcepcionDigiNoteBD {
        conectar();
        ArrayList<Usuario> arrayListUsuarios = new ArrayList<>();
        Usuario u;
        String dql;

        try {
            Statement sentencia = conexion.createStatement();
            dql = "select * from USUARIO U ";
            ResultSet resultado = sentencia.executeQuery(dql);

            while (resultado.next()) {
                u = new Usuario();
                u.setUsuarioId(resultado.getInt("usuario_Id"));
                u.setEmail(resultado.getString("email"));
                u.setPassword(resultado.getString("password"));

                arrayListUsuarios.add(u);
            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionDigiNoteBD exc = new ExcepcionDigiNoteBD();
            exc.setMensajeErrorAdministrador(ex.getMessage());
            exc.setCodigoError(ex.getErrorCode());
            exc.setSentenciaSQL(null);
            exc.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
            //ARROJA UNA EXCEPCIÓN DE TIPO ExcepcionDigiNoteBD PARA QUE LO GESTIONE EL HILO DE PETICION
            throw exc;
        }
        return arrayListUsuarios;
    }

    //LENGUAJES
    /**
     * Metodo para insertar un nuevo registro de lenguaje en la base de datos.
     * Utiliza ID autoincremental
     *
     * @since 1.0
     * @param lenguaje parametro de tipo Lenguaje
     * @return int del numero de registros afectados
     * @throws ExcepcionDigiNoteBD por utilizacion incorrecta de una sentencia
     * SQL.
     */
    public int insertarLenguaje(Lenguaje lenguaje) throws ExcepcionDigiNoteBD {
        conectar();
        String dml = "insert into LENGUAJE(LENGUAJE_ID, NOMBRE, IMAGEN, USUARIO_ID) values (SECUENCIA_INCREMENT_LENGUA_ID.nextval, ?, ?, ?)";
        int registrosAfectados;
        try {

            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setString(1, lenguaje.getNombre());
            sentenciaPreparada.setString(2, lenguaje.getImagen());
            sentenciaPreparada.setInt(3, lenguaje.getUsuario().getUsuarioId());

            registrosAfectados = sentenciaPreparada.executeUpdate();

            sentenciaPreparada.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionDigiNoteBD e = new ExcepcionDigiNoteBD();
            e.setMensajeErrorAdministrador(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(dml);
            switch (ex.getErrorCode()) {
                case 0001:
                    e.setMensajeErrorUsuario("El lenguaje ya existe ");
                    break;
                case 1400:
                    e.setMensajeErrorUsuario("Uno de los campos no están definidos. Primero el nombre");
                    break;
                default:
                    e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
                    //e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
//                    ex.getMessage();
                    break;
            }
            //ARROJA UNA EXCEPCIÓN DE TIPO ExcepcionDigiNoteBD PARA QUE LO GESTIONE EL HILO DE PETICION
            throw e;
        }
        return registrosAfectados;
    }

    /**
     * Metodo para eliminar el registro de un lenguaje de la base de datos.
     *
     * @since 1.0
     * @param lenguajeId parametro de tipo Integer
     * @return int del numero de registros afectados
     * @throws ExcepcionDigiNoteBD por utilizacion incorrecta de una sentencia
     * SQL.
     */
    public int eliminarLenguaje(Integer lenguajeId) throws ExcepcionDigiNoteBD {
        conectar();
        
        String dml = "delete from LENGUAJE "
                + "where LENGUAJE_ID = ?";
        int registrosAfectados;
        try {
            
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setInt(1, lenguajeId);

            registrosAfectados = sentenciaPreparada.executeUpdate();

            sentenciaPreparada.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionDigiNoteBD e = new ExcepcionDigiNoteBD();
            e.setMensajeErrorAdministrador(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(dml);
            switch (ex.getErrorCode()) {
                case 936:
                    e.setMensajeErrorUsuario("El dato necesario para eliminar el usuario no esta definido");
                    break;
                case 2292:
                    e.setMensajeErrorUsuario("No se puede eliminar el usuario sin eliminar anteriormente los lenguajes con sus respectivos enlaces");
                    break;
                default:
                    e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
                    break;
            }
            //ARROJA UNA EXCEPCIÓN DE TIPO ExcepcionDigiNoteBD PARA QUE LO GESTIONE EL HILO DE PETICION
            throw e;
        }
        return registrosAfectados;
    }

    /**
     * Metodo para modificar los datos de un registro de lenguaje ya existente
     * en la base de datos. La ID no se modifica.
     *
     * @since 1.0
     * @param lenguajeId parametro de tipo Integer
     * @param lenguaje parametro de tipo lenguaje
     * @return int del numero de registros afectados
     * @throws ExcepcionDigiNoteBD por utilizacion incorrecta de una sentencia
     * SQL.
     */
    public int actualizarLenguaje(Integer lenguajeId, Lenguaje lenguaje) throws ExcepcionDigiNoteBD {

        int registrosAfectados;
        String dml = "update LENGUAJE set NOMBRE= ?,IMAGEN= ? where LENGUAJE_ID= " + lenguajeId;
        try {
            conectar();

            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setString(1, lenguaje.getNombre());
            sentenciaPreparada.setString(2, lenguaje.getImagen());

            registrosAfectados = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();

            conexion.close();

        } catch (SQLException ex) {
            ExcepcionDigiNoteBD e = new ExcepcionDigiNoteBD();
            e.setMensajeErrorAdministrador(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(dml);
            switch (ex.getErrorCode()) {
                case 12899:
                    e.setMensajeErrorUsuario("El email es demasiado largo. No puede superar 40 caracteres");
                    break;
                case 1400:
                    e.setMensajeErrorUsuario("La contraseña no puede estar vacia");
                    break;
                default:
                    e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
                    break;
            }
            //ARROJA UNA EXCEPCIÓN DE TIPO ExcepcionDigiNoteBD PARA QUE LO GESTIONE EL HILO DE PETICION
            throw e;
        }
        return registrosAfectados;
    }

    /**
     * Metodo para mostrar los datos de un registro de lenguaje de la base de
     * datos.
     *
     * @since 1.0
     * @param lenguajeId parametro de tipo Integer
     * @return objeto de tipo Usuario
     * @throws ExcepcionDigiNoteBD por utilizacion incorrecta de una sentencia
     * SQL.
     */
    public Lenguaje consultarLenguaje(Integer lenguajeId) throws ExcepcionDigiNoteBD {
        conectar();
        Lenguaje l = new Lenguaje();
        try {
            Statement sentencia = conexion.createStatement();

            String dql = "select * from LENGUAJE  L, USUARIO U "
                    + "where L.LENGUAJE_ID = " + lenguajeId
                    + "and U.USUARIO_ID = L.USUARIO_ID ";

            ResultSet resultado = sentencia.executeQuery(dql);
            if (resultado.next()) {

                l.setLenguajeId(resultado.getInt("lenguaje_Id"));
                l.setNombre(resultado.getString("nombre"));
                l.setImagen(resultado.getString("imagen"));

                Usuario u = new Usuario();
                u.setUsuarioId(resultado.getInt("usuario_Id"));
                u.setEmail(resultado.getString("email"));
                u.setPassword(resultado.getString("password"));

                l.setUsuario(u);

            }
            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionDigiNoteBD exc = new ExcepcionDigiNoteBD();
            exc.setMensajeErrorAdministrador(ex.getMessage());
            exc.setCodigoError(ex.getErrorCode());
            exc.setSentenciaSQL(null);
            exc.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
            //ARROJA UNA EXCEPCIÓN DE TIPO ExcepcionDigiNoteBD PARA QUE LO GESTIONE EL HILO DE PETICION
            throw exc;
        }
        return l;
    }

    /**
     * Metodo para mostrar todos los registros existentes de lenguajes en la
     * base de datos.
     *
     * @since 1.0
     * @return ArrayList de tipo Lenguaje
     * @throws ExcepcionDigiNoteBD por utilizacion incorrecta de una sentencia
     * SQL.
     */
    public ArrayList<Lenguaje> consultarLenguajes() throws ExcepcionDigiNoteBD {
        conectar();
        ArrayList<Lenguaje> arrayListLenguajes = new ArrayList<>();
        Lenguaje l;
        String dql;

        try {
            Statement sentencia = conexion.createStatement();
            dql = "select * from LENGUAJE  L, USUARIO U "
                    + "where U.USUARIO_ID = L.USUARIO_ID ";
            ResultSet resultado = sentencia.executeQuery(dql);

            while (resultado.next()) {
                l = new Lenguaje();
                l.setLenguajeId(resultado.getInt("lenguaje_Id"));
                l.setNombre(resultado.getString("nombre"));
                l.setImagen(resultado.getString("imagen"));

                Usuario u = new Usuario();
                u.setUsuarioId(resultado.getInt("usuario_Id"));
                u.setEmail(resultado.getString("email"));
                u.setPassword(resultado.getString("password"));

                l.setUsuario(u);

                arrayListLenguajes.add(l);
            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionDigiNoteBD exc = new ExcepcionDigiNoteBD();
            exc.setMensajeErrorAdministrador(ex.getMessage());
            exc.setCodigoError(ex.getErrorCode());
            exc.setSentenciaSQL(null);
            exc.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
            //ARROJA UNA EXCEPCIÓN DE TIPO ExcepcionDigiNoteBD PARA QUE LO GESTIONE EL HILO DE PETICION
            throw exc;
        }
        return arrayListLenguajes;
    }

    //ENLACES
    /**
     *
     * Metodo para insertar un nuevo registro de enlace en la base de datos.
     * Utiliza ID autoincremental
     *
     * @since 1.0
     * @param enlace parametro de tipo Enlace
     * @return int del numero de registros afectados
     * @throws ExcepcionDigiNoteBD por utilizacion incorrecta de una sentencia
     * SQL.
     */
    public int insertarEnlace(Enlace enlace) throws ExcepcionDigiNoteBD {
        conectar();
        String dml = "insert into ENLACE(ENLACE_ID, TITULO, DESCRIPCION, LINK, FECHA, FAVORITO, LENGUAJE_ID) values (SECUENCIA_INCREMENT_ENLACE_ID.nextval, ?, ?, ?, DEFAULT, ?, ?)";
        int registrosAfectados;
        try {

            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setString(1, enlace.getTitulo());
            sentenciaPreparada.setString(2, enlace.getDescripcion());
            sentenciaPreparada.setString(3, enlace.getLink());
            sentenciaPreparada.setString(4, enlace.getFavorito());
            sentenciaPreparada.setInt(5, enlace.getLenguaje().getLenguajeId());
            registrosAfectados = sentenciaPreparada.executeUpdate();

            sentenciaPreparada.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionDigiNoteBD e = new ExcepcionDigiNoteBD();
            e.setMensajeErrorAdministrador(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(dml);
            switch (ex.getErrorCode()) {
                
                case 1:
                    e.setMensajeErrorUsuario("El enlace ya existe");
                    break;
                case 6512:
                    e.setMensajeErrorUsuario("El valor de favorito no es el correcto");
                    break;
                case 1400:
                    e.setMensajeErrorUsuario("El titulo, el link(http ...), la asignacion de favorito o el lenguaje del enlace no está definido");
                    break;
                case 2290:
                    e.setMensajeErrorUsuario("El enlace debe comenzar por http");
                    break;
                default:
                    e.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
                    System.out.println("Error de insercion: " + ex.getMessage());
                    break;
            }
            //ARROJA UNA EXCEPCIÓN DE TIPO ExcepcionDigiNoteBD PARA QUE LO GESTIONE EL HILO DE PETICION
            throw e;
        }
        return registrosAfectados;
    }

    /**
     * Metodo para eliminar el registro de un enlace de la base de datos.
     *
     * @since 1.0
     * @param enlaceId parametro de tipo Integer
     * @return int del numero de registros afectados
     * @throws ExcepcionDigiNoteBD por utilizacion incorrecta de una sentencia
     * SQL.
     */
    public int eliminarEnlace(Integer enlaceId) throws ExcepcionDigiNoteBD {
        conectar();
        Enlace e;
        String dml = "delete from ENLACE "
                + "where enlace_id = ?";
        int registrosAfectados;
        try {
            e = new Enlace();
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setInt(1, enlaceId);

            registrosAfectados = sentenciaPreparada.executeUpdate();

            sentenciaPreparada.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionDigiNoteBD exc = new ExcepcionDigiNoteBD();
            exc.setMensajeErrorAdministrador(ex.getMessage());
            exc.setCodigoError(ex.getErrorCode());
            exc.setSentenciaSQL(dml);
            switch (ex.getErrorCode()) {

                case 936:
                    exc.setMensajeErrorUsuario("El dato necesario para eliminar el enlace no esta definido");
                    break;
                default:
                    exc.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador");
                    break;
            }
            //ARROJA UNA EXCEPCIÓN DE TIPO ExcepcionDigiNoteBD PARA QUE LO GESTIONE EL HILO DE PETICION
            throw exc;
        }
        return registrosAfectados;
    }

    /**
     * Metodo para modificar los datos de un registro de enlace ya existente en
     * la base de datos. La ID no se modifica.
     *
     * @since 1.0
     * @param enlaceId parametro de tipo Integer
     * @param enlace parametro de tipo Enlace
     * @return int del numero de registros afectados
     * @throws ExcepcionDigiNoteBD por utilizacion incorrecta de una sentencia
     * SQL.
     */
    public int actualizarEnlace(Integer enlaceId, Enlace enlace) throws ExcepcionDigiNoteBD {
        int registrosAfectados;
        String dml = "update ENLACE set TITULO= ?,DESCRIPCION= ?,LINK= ?, FAVORITO= ?,LENGUAJE_ID= ? where ENLACE_ID =" + enlaceId;
        try {
            conectar();

            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setString(1, enlace.getTitulo());
            sentenciaPreparada.setString(2, enlace.getDescripcion());
            sentenciaPreparada.setString(3, enlace.getLink());
            sentenciaPreparada.setString(4, enlace.getFavorito());
            sentenciaPreparada.setInt(5, enlace.getLenguaje().getLenguajeId());
            registrosAfectados = sentenciaPreparada.executeUpdate();

            registrosAfectados = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();

            conexion.close();

        } catch (SQLException ex) {
            ExcepcionDigiNoteBD e = new ExcepcionDigiNoteBD();
            e.setMensajeErrorAdministrador(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(dml);
            switch (ex.getErrorCode()) {
                case 2290:
                    e.setMensajeErrorUsuario("El valor de favorito seleccionado no es el correcto");
                    break;
                case 1407:
                    e.setMensajeErrorUsuario("El titulo, el link(http ...), la asignacion de favorito o el lenguaje del enlace no está definido");
                    break;
                    case 2291:
                    e.setMensajeErrorUsuario("No se puede identifiacer el lenguaje al que pertenece el enlace");
                    break;
                default:
                    e.setMensajeErrorUsuario("Error general del sistema. Consulte al administrador");
                    break;
            }
            //ARROJA UNA EXCEPCIÓN DE TIPO ExcepcionDigiNoteBD PARA QUE LO GESTIONE EL HILO DE PETICION
            throw e;
        }
        return registrosAfectados;
    }

    /**
     * Metodo para mostrar los datos de un registro de usuario de la base de
     * datos.
     *
     * @since 1.0
     * @param enlaceId parametro de tipo Integer
     * @return Objeto de tipo Enlace
     * @throws ExcepcionDigiNoteBD por utilizacion incorrecta de una sentencia
     * SQL.
     */
    public Enlace consultarEnlace(Integer enlaceId) throws ExcepcionDigiNoteBD {
        conectar();
        Enlace e = new Enlace();
        try {
            Statement sentencia = conexion.createStatement();

            String dql = "select * from ENLACE E, LENGUAJE  L, USUARIO  U "
                    + "where E.LENGUAJE_ID = L.LENGUAJE_ID "
                    + "and L.USUARIO_ID = U.USUARIO_ID "
                    + "and E.ENLACE_ID = " + enlaceId;

            ResultSet resultado = sentencia.executeQuery(dql);
            if (resultado.next()) {
                e.setEnlaceId(resultado.getInt("enlace_Id"));
                e.setTitulo(resultado.getString("titulo"));
                e.setDescripcion(resultado.getString("descripcion"));
                e.setLink(resultado.getString("link"));
                e.setFecha(resultado.getDate("fecha"));
                e.setFavorito(resultado.getString("favorito"));

                Lenguaje l = new Lenguaje();
                l.setLenguajeId(resultado.getInt("lenguaje_Id"));
                l.setNombre(resultado.getString("nombre"));
                l.setImagen(resultado.getString("imagen"));

                Usuario u = new Usuario();
                u.setUsuarioId(resultado.getInt("usuario_Id"));
                u.setEmail(resultado.getString("email"));
                u.setPassword(resultado.getString("password"));

                l.setUsuario(u);
                e.setLenguaje(l);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException ex) {
            ExcepcionDigiNoteBD exc = new ExcepcionDigiNoteBD();
            exc.setMensajeErrorAdministrador(ex.getMessage());
            exc.setCodigoError(ex.getErrorCode());
            exc.setSentenciaSQL(null);
            exc.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
            //ARROJA UNA EXCEPCIÓN DE TIPO ExcepcionDigiNoteBD PARA QUE LO GESTIONE EL HILO DE PETICION
            throw exc;
        }
        return e;
    }

    public ArrayList<Enlace> consultarEnlaces() throws ExcepcionDigiNoteBD {

        conectar();
        ArrayList<Enlace> arrayListEnlaces = new ArrayList<>();
        Enlace e;
        String dql;

        try {
            Statement sentencia = conexion.createStatement();
            dql = "select * from ENLACE E, LENGUAJE  L, USUARIO  U "
                    + "where E.LENGUAJE_ID = L.LENGUAJE_ID "
                    + "and L.USUARIO_ID = U.USUARIO_ID ";
            ResultSet resultado = sentencia.executeQuery(dql);

            while (resultado.next()) {

                e = new Enlace();
                e.setEnlaceId(resultado.getInt("enlace_Id"));
                e.setTitulo(resultado.getString("titulo"));
                e.setDescripcion(resultado.getString("descripcion"));
                e.setLink(resultado.getString("link"));
                e.setFecha(resultado.getDate("fecha"));
                e.setFavorito(resultado.getString("favorito"));

                Lenguaje l = new Lenguaje();
                l.setLenguajeId(resultado.getInt("lenguaje_Id"));
                l.setNombre(resultado.getString("nombre"));
                l.setImagen(resultado.getString("imagen"));

                Usuario u = new Usuario();
                u.setUsuarioId(resultado.getInt("usuario_Id"));
                u.setEmail(resultado.getString("email"));
                u.setPassword(resultado.getString("password"));

                l.setUsuario(u);
                e.setLenguaje(l);
                arrayListEnlaces.add(e);
            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionDigiNoteBD exc = new ExcepcionDigiNoteBD();
            exc.setMensajeErrorAdministrador(ex.getMessage());
            exc.setCodigoError(ex.getErrorCode());
            exc.setSentenciaSQL(null);
            exc.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
            //ARROJA UNA EXCEPCIÓN DE TIPO ExcepcionDigiNoteBD PARA QUE LO GESTIONE EL HILO DE PETICION
            throw exc;
        }
        return arrayListEnlaces;
    }

    //FAVORITOS
    /**
     * Metodo para mostrar todos los registros existentes de enlaces favoritos
     * en la base de datos.
     *
     * @since 1.0
     * @return ArrayList de tipo Enlace
     * @throws ExcepcionDigiNoteBD por utilizacion incorrecta de una sentencia
     * SQL.
     */
    public ArrayList<Enlace> consultarEnlacesFavoritos() throws ExcepcionDigiNoteBD {

        conectar();
        ArrayList<Enlace> arrayListEnlaces = new ArrayList<>();
        Enlace e;
        String dql;

        try {
            Statement sentencia = conexion.createStatement();
            dql = "select * from ENLACE E, LENGUAJE  L, USUARIO  U "
                    + "where E.LENGUAJE_ID = L.LENGUAJE_ID "
                    + "and L.USUARIO_ID = U.USUARIO_ID "
                    + "and E.FAVORITO like 'S' ";
            ResultSet resultado = sentencia.executeQuery(dql);

            while (resultado.next()) {

                e = new Enlace();
                e.setEnlaceId(resultado.getInt("enlace_Id"));
                e.setTitulo(resultado.getString("titulo"));
                e.setDescripcion(resultado.getString("descripcion"));
                e.setLink(resultado.getString("link"));
                e.setFecha(resultado.getDate("fecha"));
                e.setFavorito(resultado.getString("favorito"));

                Lenguaje l = new Lenguaje();
                l.setLenguajeId(resultado.getInt("lenguaje_Id"));
                l.setNombre(resultado.getString("nombre"));
                l.setImagen(resultado.getString("imagen"));

                Usuario u = new Usuario();
                u.setUsuarioId(resultado.getInt("usuario_Id"));
                u.setEmail(resultado.getString("email"));
                u.setPassword(resultado.getString("password"));

                l.setUsuario(u);
                e.setLenguaje(l);
                arrayListEnlaces.add(e);
            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {
            ExcepcionDigiNoteBD exc = new ExcepcionDigiNoteBD();
            exc.setMensajeErrorAdministrador(ex.getMessage());
            exc.setCodigoError(ex.getErrorCode());
            exc.setSentenciaSQL(null);
            exc.setMensajeErrorUsuario("Error general del sistema. Consulte con el administrador.");
            //ARROJA UNA EXCEPCIÓN DE TIPO ExcepcionDigiNoteBD PARA QUE LO GESTIONE EL HILO DE PETICION
            throw exc;
        }
        return arrayListEnlaces;
    }

    /**
     * Metodo para actualizar un registro existente de enlaces favorito en la
     * base de datos.
     *
     * @since 1.0
     * @return ArrayList de tipo Enlace
     * @throws ExcepcionDigiNoteBD por utilizacion incorrecta de una sentencia
     * SQL.
     */
    public int actualizarEnlaceFavorito(Integer enlaceId, Enlace enlace) throws ExcepcionDigiNoteBD {
        int registrosAfectados;
        String dml = "update ENLACE set FAVORITO= ? where ENLACE_ID =" + enlaceId;
        try {
            conectar();

            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setString(1, enlace.getFavorito());
            registrosAfectados = sentenciaPreparada.executeUpdate();

            registrosAfectados = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();

            conexion.close();

        } catch (SQLException ex) {
            ExcepcionDigiNoteBD e = new ExcepcionDigiNoteBD();
            e.setMensajeErrorAdministrador(ex.getMessage());
            e.setCodigoError(ex.getErrorCode());
            e.setSentenciaSQL(dml);
            switch (ex.getErrorCode()) {
                case 2290:
                    e.setMensajeErrorUsuario("El tipo de favorito no se ha defindo corerctamente");
                    break;
                case 1407:
                    e.setMensajeErrorUsuario("El tipo de favorito no está definido");
                    break;
                default:
                    e.setMensajeErrorUsuario("Error general del sistema. Consulte al administrador");
                    break;
            }
            //ARROJA UNA EXCEPCIÓN DE TIPO ExcepcionDigiNoteBD PARA QUE LO GESTIONE EL HILO DE PETICION
            throw e;
        }
        return registrosAfectados;
    }

}
