/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diginotepojocomunicaciones;

import java.io.Serializable;

/**
 * Clase que contiene el objeto Excepcion utilizado en los proyectos:
 * DigiNoteClienteComunicaciones y DigiNoteServComunicaciones.
 *
 * Almacena los errores ocurridos durante la comunicacion entre el cliente y el
 * servidor.
 *
 *
 * @author Imanol Callejo Baranda
 * @version 1.1.0
 * @since Mayo 2023
 */
public class ExcepcionDigiNoteComunicacion extends Exception implements Serializable {

    private String mensajeErrorAdministrador;
    private String mensajeErrorUsuario;

    static final long serialVersionUID = 0x222L;

    /**
     *
     * Constructor vacio de la clase ExcepcionDigiNoteComunicacion
     *
     */
    public ExcepcionDigiNoteComunicacion() {
    }

    /**
     *
     * Constructor completo de la clase ExcepcionDigiNoteComunicacion
     *
     * @param mensajeErrorAdministrador de tipo String
     * @param mensajeErrorUsuario de tipo String
     *
     */
    public ExcepcionDigiNoteComunicacion(String mensajeErrorAdministrador, String mensajeErrorUsuario) {
        this.mensajeErrorAdministrador = mensajeErrorAdministrador;
        this.mensajeErrorUsuario = mensajeErrorUsuario;
    }

    /**
     *
     * Metodo GET para obtener el mensaje de error de administrador de la clase
     * ExcepcionDigiNoteComunicacion
     *
     * @return String
     */
    public String getMensajeErrorAdministrador() {
        return mensajeErrorAdministrador;
    }

    /**
     *
     * Metodo SET para establecer el valor del mensaje de error de administrador
     * de la clase ExcepcionDigiNoteComunicacion
     *
     * @param mensajeErrorAdministrador de tipo String
     */
    public void setMensajeErrorAdministrador(String mensajeErrorAdministrador) {
        this.mensajeErrorAdministrador = mensajeErrorAdministrador;
    }

    /**
     *
     * Metodo GET para obtener el mensaje de error de usuario de la clase
     * ExcepcionDigiNoteComunicacion
     *
     * @return String
     */
    public String getMensajeErrorUsuario() {
        return mensajeErrorUsuario;
    }

    /**
     *
     * Metodo SET para establecer el valor del mensaje de error de usuario de la
     * clase ExcepcionDigiNoteComunicacion
     *
     * @param mensajeErrorUsuario de tipo String
     */
    public void setMensajeErrorUsuario(String mensajeErrorUsuario) {
        this.mensajeErrorUsuario = mensajeErrorUsuario;
    }

    @Override
    public String toString() {
        return "ExcepcionDigiNoteComunicacion{" + "mensajeErrorAdministrador=" + mensajeErrorAdministrador + ", mensajeErrorUsuario=" + mensajeErrorUsuario + '}';
    }

}
