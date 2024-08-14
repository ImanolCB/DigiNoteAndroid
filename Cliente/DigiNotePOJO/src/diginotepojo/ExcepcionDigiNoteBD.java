package diginotepojo;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Clase que contiene el objeto Excepcion utilizado en los proyectos:
 * DigiNoteCAD, DigiNoteClienteComunicaciones y DigiNoteServComunicaciones.
 * Sirve para almacenar las excepciones de manera más controlada.
 *
 * @author Imanol Callejo Baranda
 * @version 1.1.0
 * @since Mayo 2023
 */
public class ExcepcionDigiNoteBD extends Exception implements Serializable {

    private String mensajeErrorAdministrador;
    private Integer codigoError;
    private String sentenciaSQL;
    private String mensajeErrorUsuario;

    static final long serialVersionUID = 0xEEEL;

    /**
     *
     * Constructor vacio de la clase ExcepcionDigiNoteBD
     *
     */
    public ExcepcionDigiNoteBD() {
    }

    /**
     *
     * Constructor completo de la clase ExcepcionDigiNoteBD
     *
     * @param mensajeErrorAdministrador de tipo String
     * @param codigoError de tipo Integer
     * @param sentenciaSQL de tipo String
     * @param mensajeErrorUsuario de tipo String
     */
    public ExcepcionDigiNoteBD(String mensajeErrorAdministrador, Integer codigoError, String sentenciaSQL, String mensajeErrorUsuario) {
        this.mensajeErrorAdministrador = mensajeErrorAdministrador;
        this.codigoError = codigoError;
        this.sentenciaSQL = sentenciaSQL;
        this.mensajeErrorUsuario = mensajeErrorUsuario;
    }

    /**
     *
     * Constructor parcialmente parametrizado con los mensajes de error de la
     * clase ExcepcionDigiNoteBD
     *
     * @param mensajeErrorAdministrador de tipo String
     * @param mensajeErrorUsuario de tipo String
     */
    public ExcepcionDigiNoteBD(String mensajeErrorAdministrador, String mensajeErrorUsuario) {
        this.mensajeErrorAdministrador = mensajeErrorAdministrador;
        this.mensajeErrorUsuario = mensajeErrorUsuario;
    }

    /**
     *
     * Metodo GET para obtener el mensaje de error de administrador de la clase
     * ExcepcionDigiNoteBD
     *
     * @return String
     *
     */
    public String getMensajeErrorAdministrador() {
        return mensajeErrorAdministrador;
    }

    public void setMensajeErrorAdministrador(String mensajeErrorAdministrador) {
        this.mensajeErrorAdministrador = mensajeErrorAdministrador;
    }

    /**
     *
     * Metodo GET para obtener el codigo de error de la clase
     * ExcepcionDigiNoteBD
     *
     * @return Integer
     */
    public Integer getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(Integer codigoError) {
        this.codigoError = codigoError;
    }

    /**
     *
     * Metodo GET para obtener la sentencia SQL de la clase ExcepcionDigiNoteBD
     *
     * @return String
     */
    public String getSentenciaSQL() {
        return sentenciaSQL;
    }

    public void setSentenciaSQL(String sentenciaSQL) {
        this.sentenciaSQL = sentenciaSQL;
    }

    /**
     *
     * Metodo GET para obtener el mensaje de error de Usuario de la clase
     * ExcepcionDigiNoteBD
     *
     * @return
     */
    public String getMensajeErrorUsuario() {
        return mensajeErrorUsuario;
    }

    public void setMensajeErrorUsuario(String mensajeErrorUsuario) {
        this.mensajeErrorUsuario = mensajeErrorUsuario;
    }

    /**
     * Metodo que muestra todos los valores de los campos de la clase
     * ExcepcionDigiNoteBD
     *
     * @return String
     */
    @Override
    public String toString() {
        return "ExcepcionDigiNoteBD{" + "mensajeErrorAdministrador=" + mensajeErrorAdministrador + ", codigoError=" + codigoError + ", sentenciaSQL=" + sentenciaSQL + ", mensajeErrorUsuario=" + mensajeErrorUsuario + '}';
    }

}
