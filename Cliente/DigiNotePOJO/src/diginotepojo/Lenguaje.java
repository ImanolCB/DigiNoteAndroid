package diginotepojo;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Clase que contiene el objeto Lenguaje utilizado en los proyectos:
 * DigiNoteCAD, DigiNoteClienteComunicaciones y DigiNoteServComunicaciones.
 *
 *
 * @author Imanol Callejo Baranda
 * @version 1.1.0
 * @since Mayo 2023
 *
 */
public class Lenguaje implements Serializable {

    private Integer lenguajeId;
    private String nombre;
    private String imagen;
    private Usuario usuario;

    static final long serialVersionUID = 0xBBBL;

    /**
     *
     * Constructor vacio de la calse Lenguaje
     *
     */
    public Lenguaje() {
    }

    /**
     * Constructor parcialmente parametrizado con el campo lenguajeId de la
     * clase Lenguaje
     *
     * @param lenguajeId
     */
    public Lenguaje(Integer lenguajeId) {
        this.lenguajeId = lenguajeId;
    }

    /**
     *
     * Constructor completamente parametrizado de la clase Lenguaje
     *
     * @param lenguajeId de tipo Integer
     * @param nombre de tipo String
     * @param imagen de tipo String
     * @param usuario de tipo Usuario
     */
    public Lenguaje(Integer lenguajeId, String nombre, String imagen, Usuario usuario) {
        this.lenguajeId = lenguajeId;
        this.nombre = nombre;
        this.imagen = imagen;
        this.usuario = usuario;
    }

    /**
     *
     * Metodo GET para obtener la ID de la clase Lenguaje
     *
     * @return Integer
     */
    public Integer getLenguajeId() {
        return lenguajeId;
    }

    /**
     *
     * Metodo SET para establecer el valor del lenguajeId de la clase Lenguaje
     *
     * @param lenguajeId de tipo Integer
     */
    public void setLenguajeId(Integer lenguajeId) {
        this.lenguajeId = lenguajeId;
    }

    /**
     *
     * Metodo GET para obtener el nombre de la clase Lenguaje
     *
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * Metodo SET para establecer el valor del nombre de la clase Lenguaje
     *
     * @param nombre de tipo String
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * Metodo GET para obtener la ruta de la imagen de la clase Lenguaje
     *
     * @return String
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Metodo SET para establecer el valor de la ruta de la imagen de la clase
     * Lenguaje
     *
     * @param imagen de tipo String
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     *
     * Metodo GET para obtener el Usuario de la clase Lenguaje
     *
     * @return Usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     *
     * Metodo SET para establecer el usuario de la clase Lenguaje
     *
     * @param usuario de tipo Usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Metodo para ver todos los valores de los campos de la clase Lenguaje
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Lenguaje{" + "lenguajeId=" + lenguajeId + ", nombre=" + nombre + ", imagen=" + imagen + ", usuario=" + usuario + '}';
    }

}
