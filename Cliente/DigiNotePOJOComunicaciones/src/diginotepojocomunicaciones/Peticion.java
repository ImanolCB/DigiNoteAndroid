/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diginotepojocomunicaciones;

import java.io.Serializable;

/**
 * Clase que contiene el objeto Petición utilizado en los proyectos:
 * DigiNoteClienteComunicaciones y DigiNoteServComunicaciones.
 *
 * Sirve para encapsular las peticiones que desea hacer el usuario al servidor
 * con los datos necerios para procesar el pedido. Cada tipo de petición está
 * definida con una variable estatica final de tipo Integer. Los objetos de las
 * clases que se emplean para realizar el pedido se almacenan en variables de
 * tipo Object.
 *
 * @author Imanol Callejo Baranda
 * @version 1.1.0
 * @since Mayo 2023
 */
public class Peticion implements Serializable {

    private Integer idOperacion;
    private Integer pk;
    private Object objeto;

    static final long serialVersionUID = 0x111L;

    public static final int INSERTAR_USUARIO = 1;
    public static final int ELIMINAR_USUARIO = 2;
    public static final int MODIFICAR_USUARIO = 3;
    public static final int MOSTRAR_USUARIO = 4;
    public static final int MOSTRAR_USUARIOS = 5;
    public static final int INSERTAR_LENGUAJE = 6;
    public static final int ELIMINAR_LENGUAJE = 7;
    public static final int MODIFICAR_LENGUAJE = 8;
    public static final int MOSTRAR_LENGUAJE = 9;
    public static final int MOSTRAR_LENGUAJES = 10;
    public static final int INSERTAR_ENLACE = 11;
    public static final int ELIMINAR_ENLACE = 12;
    public static final int MODIFICAR_ENLACE = 13;
    public static final int MOSTRAR_ENLACE = 14;
    public static final int MOSTRAR_ENLACES = 15;
    public static final int MOSTRAR_ENLACES_FAVORITOS = 16;
    public static final int MODIFICAR_ENLACE_FAVORITO = 17;
    public static final int ELIMINAR_ENLACE_FAVORITO = 18;

    /**
     *
     * Constructor parcialmente parametrizado con laidOperacion de la clase
     * Peticion
     *
     * @param idOperacion de tipo Integer
     */
    public Peticion(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }

    /**
     *
     * Constructor parcialmente parametrizado con el idOperacion y la PK de la
     * clase Peticion
     *
     * @param idOperacion de tipo Integer
     * @param pk de tipo Integer
     */
    public Peticion(Integer idOperacion, Integer pk) {
        this.idOperacion = idOperacion;
        this.pk = pk;
    }

    /**
     *
     * Constructor parcialmente parametrizado con el idOperacion y el objeto de
     * la clase Peticion
     *
     * @param idOperacion de tipo Integer
     * @param objeto de tipo Object
     */
    public Peticion(Integer idOperacion, Object objeto) {
        this.idOperacion = idOperacion;
        this.objeto = objeto;
    }

    /**
     *
     * Constructor completamente parametrizado de la clase Peticion
     *
     * @param idOperacion de tipo Integer
     * @param pk de tipo Integer
     * @param objeto de tipo Object
     */
    public Peticion(Integer idOperacion, Integer pk, Object objeto) {
        this.idOperacion = idOperacion;
        this.pk = pk;
        this.objeto = objeto;
    }

    /**
     *
     * Metodo GET para obtener la idOperacion de la clase Peticion
     *
     * @return Integer
     */
    public Integer getIdOperacion() {
        return idOperacion;
    }

    /**
     * Metodo SET para establecer el valor de la idOperacion de la clase
     * Peticion
     *
     * @param idOperacion
     */
    public void setIdOperacion(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }

    /**
     *
     * Metodo GET para obtener el valor de la PK de la clase Peticion
     *
     * @return Integer
     */
    public Integer getPk() {
        return pk;
    }

    /**
     *
     * Metodo SET para establecer el valor de laPK de la clase Peticion
     *
     * @param pk de tipo Integer
     */
    public void setPk(Integer pk) {
        this.pk = pk;
    }

    /**
     *
     * Metodo GET para obtener el valor del objeto de la clase Peticion
     *
     * @return Object
     */
    public Object getObjeto() {
        return objeto;
    }

    /**
     *
     * Metodo SET para establecer el valor del objeto de la clase Peticion
     *
     * @param objeto
     */
    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }

    /**
     *
     * Metodo para mostrar todos los valores almacenados en los campos de la
     * clase Peticion
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Peticion{" + "idOperacion=" + idOperacion + ", pk=" + pk + ", objeto=" + objeto + '}';
    }

}
