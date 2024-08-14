package diginotepojo;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Clase que contiene el objeto Usuario utilizado en los proyectos: DigiNoteCAD,
 * DigiNoteClienteComunicaciones y DigiNoteServComunicaciones.
 *
 *
 * @author Imanol Callejo Baranda
 * @version 1.1.0
 * @since Mayo 2023
 */
public class Usuario implements Serializable {

    private Integer usuarioId;
    private String email;
    private String password;

    static final long serialVersionUID = 0xCCCL;

    /**
     * Constructor vacio de la clase Usuario
     */
    public Usuario() {
    }

    /**
     *
     * Constructor parcialmenet parametrizado con el usuarioId de la clase
     * Usuario
     *
     * @param usuarioId de tipo Integer
     */
    public Usuario(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     *
     * Constructor completamente parametrizado de la clase Usuario
     *
     * @param usuarioId de tipo Integer
     * @param email de tipo String
     * @param password de tipo String
     */
    public Usuario(Integer usuarioId, String email, String password) {
        this.usuarioId = usuarioId;
        this.email = email;
        this.password = password;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    /**
     *
     * Metodo SET para establecer el valor del usuarioId de la clase Usuario
     *
     * @param usuarioId
     */
    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getEmail() {
        return email;
    }

    /**
     *
     * Metodo SET para establecer el valor del email de la clase Usuario
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    /**
     *
     * Metodo SET para establecer el valor del password de la clase Usuario
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" + "usuarioId=" + usuarioId + ", email=" + email + ", password=" + password + '}';
    }

}
