package diginotepojo;

import java.io.Serializable;
import java.sql.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Clase que contiene el objeto Enlace utilizado en los proyectos: DigiNoteCAD,
 * DigiNoteClienteComunicaciones y DigiNoteServComunicaciones.
 *
 * @author Imanol Callejo Baranda
 * @version 1.1.0
 * @since Mayo 2023
 */
public class Enlace implements Serializable {

    private Integer enlaceId;
    private String titulo;
    private String descripcion;
    private String link;
    private Date fecha;
    private String favorito;
    private Lenguaje lenguaje;

    static final long serialVersionUID = 0xAAAL;

    /**
     *
     * Constructor vacio de la clase Enlace
     *
     */
    public Enlace() {
    }

    /**
     *
     * Constructor Parcialmente parametrizado con el campo favorito de la clase
     * Enlace
     *
     * @param favorito de tipo String
     *
     */
    public Enlace(String favorito) {
        this.favorito = favorito;
    }

    /**
     *
     * Constructor parcialmente parametrizado con el campo enlaceId de la clase
     * Enlace
     *
     * @param enlaceId de tipo Integer
     *
     */
    public Enlace(Integer enlaceId) {
        this.enlaceId = enlaceId;
    }

    /**
     *
     * Constructor completamente parametrizado de la clase Enlace
     *
     * @param enlaceId de tipo Integer
     * @param titulo de tipo String
     * @param descripcion de tipo String
     * @param link de tipo String
     * @param fecha de tipo Date
     * @param favorito de tipo String
     * @param lenguaje de tipo Lenguaje
     *
     */
    public Enlace(Integer enlaceId, String titulo, String descripcion, String link, Date fecha, String favorito, Lenguaje lenguaje) {
        this.enlaceId = enlaceId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.link = link;
        this.fecha = fecha;
        this.favorito = favorito;
        this.lenguaje = lenguaje;
    }

    /**
     * Metodo GET para obtener la ID del Enlace
     *
     * @return Integer
     */
    public Integer getEnlaceId() {
        return enlaceId;
    }

    /**
     * Metodo SET para establecer el valor de la ID del Enlace
     *
     * @param enlaceId
     */
    public void setEnlaceId(Integer enlaceId) {
        this.enlaceId = enlaceId;
    }

    /**
     * Metodo GET para obtener el titulo del Enlace
     *
     * @return String
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     *
     * Metodo SET para establecer el valor del titulo del Enlace
     *
     * @param titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     *
     * Metodo GET para obtener la descripcion del Enlace
     *
     * @return String
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     *
     * Metodo SET para establecer el valor de la descripcion del Enlace
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     *
     * Metodo GET para obtener el link del Enlace
     *
     * @return String
     */
    public String getLink() {
        return link;
    }

    /**
     *
     * Metodo SET para establecer el valor del link del Enlace
     *
     * @param link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     *
     * Metodo GET para obtener la fecha del Enlace
     *
     * @return Date
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     *
     * Metodo SET para establecer el valor de la fecha del Enlace
     *
     * @param fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     *
     * Metodo GET para obtener el estado favorito del Enlace
     *
     *
     * @return String
     */
    public String getFavorito() {
        return favorito;
    }

    /**
     *
     * Metodo SET para establecer el estado favorito del Enlace
     *
     * @param favorito
     */
    public void setFavorito(String favorito) {
        this.favorito = favorito;
    }

    /**
     *
     * Metodo GET para obtener lel lenguaje al que pertenece el Enlace
     *
     * @return Lenguaje
     */
    public Lenguaje getLenguaje() {
        return lenguaje;
    }

    /**
     * Metodo SET para establecer el el lenguaje al que pertenece el Enlace
     *
     * @param lenguaje
     */
    public void setLenguaje(Lenguaje lenguaje) {
        this.lenguaje = lenguaje;
    }

    /**
     *
     * Metodo que muestra los valores de los campos de la clase lenguaje
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Enlace{" + "enlaceId=" + enlaceId + ", titulo=" + titulo + ", descripcion=" + descripcion + ", link=" + link + ", fecha=" + fecha + ", favorito=" + favorito + ", lenguaje=" + lenguaje + '}';
    }

}
