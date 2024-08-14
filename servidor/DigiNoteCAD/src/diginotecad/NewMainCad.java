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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class NewMainCad {

    /**
     * @param args the command line arguments
     *
     */
    public static void main(String[] args) {

        try {
            DigiNoteCAD cad;

            cad = new DigiNoteCAD();

//---------------------------------------------- INSERTAR ENLACE -------------------------------------------------------------
//            Enlace e = new Enlace(null, null, "CAAAADDDD", "http://CAD", null, "S", new Lenguaje(4));
//            System.out.println(cad.insertarEnlace(e));
//----------------------------------------------- ELIMINAR ENLACE -----------------------------------------------------------
//        System.out.println(cad.eliminarEnlace(6));
//----------------------------------------------- ACTUALIZAR ENLACE -----------------------------------------------------------
//        System.out.println(cad.actualizarEnlace(6, new Enlace(null, "pruebaCAD", "probandoCAD", "httpsssss:", null, "N", (new Lenguaje(2)))));
//---------------------------------------------- CONSULTAR ENLACE CON ID x ---------------------------------------------------
//        Enlace e = cad.consultarEnlace(23);
//        System.out.println(e);
//---------------------------------------------- CONSULTAR TODOS LOS ENLACES -------------------------------------------------
//        ArrayList<Enlace> array = cad.consultarEnlaces();
//
//        for (int i = 0; i < array.size(); i++) {
//            System.out.println(array.get(i));
//        }
//---------------------------------------------- CONSULTAR TODOS LOS ENLACES FAVORITOS-------------------------------------------------
//        ArrayList<Enlace> array = cad.consultarEnlacesFavoritos();
//
//        for (int i = 0; i < array.size(); i++) {
//            System.out.println(array.get(i));
//        }
//----------------------------------------------- ACTUALIZAR ENLACE FAVORITO -----------------------------------------------------------
            System.out.println(cad.actualizarEnlaceFavorito(6, new Enlace("")));




//---------------------------------------------- INSERTAR LENGUAJE -------------------------------------------------------------
//            Lenguaje l = new Lenguaje(null, "CAD", "image", new Usuario(46));
//            System.out.println(cad.insertarLenguaje(l));
//----------------------------------------------- ELIMINAR LENGUAJE -----------------------------------------------------------
//        System.out.println(cad.eliminarLenguaje(6));
//----------------------------------------------- ACTUALIZAR LENGUAJE -----------------------------------------------------------
//                       System.out.println(cad.actualizarLenguaje(4, new Lenguaje(null, "JAVA", "ICONO JAVA", new Usuario(2))));
//---------------------------------------------- CONSULTAR LENGUAJE CON ID x ---------------------------------------------------
//        Lenguaje l = cad.consultarLenguaje(4);
//        System.out.println(l);
//---------------------------------------------- CONSULTAR TODOS LOS LENGUAJES -------------------------------------------------
//        ArrayList<Lenguaje> arrayL = cad.consultarLenguajes();
//
//        for (int i = 0; i < arrayL.size(); i++) {
//            System.out.println(arrayL.get(i));
//        }



//----------------------------------------------- INSERTAR USUARIO -----------------------------------------------------------
//        Usuario u = new Usuario(null, "testCAD@CAD.lol", "CADi");
//        System.out.println(cad.insertarUsuario(u));
//----------------------------------------------- ELIMINAR USUARIO -----------------------------------------------------------
//        System.out.println(cad.eliminarUsuario(68));
//----------------------------------------------- ACTUALIZAR USUARIO -----------------------------------------------------------
//          System.out.println(cad.actualizarUsuario(4, new Usuario(4, "dam@221.es", null)));
//----------------------------------------------- CONSULTAR USUARIO CON ID x--------------------------------------------------
//        Usuario u = cad.consultarUsuario(46);
//        System.out.println(u);
//----------------------------------------------- CONSULTAR TODOS LOS USUARIOS -----------------------------------------------
//        ArrayList<Usuario> arrayU = cad.consultarUsuarios();
//
//        for (int i = 0; i < arrayU.size(); i++) {
//            System.out.println(arrayU.get(i));
//        }
        } catch (ExcepcionDigiNoteBD ex) {
            System.out.println(ex.getMensajeErrorUsuario());
        }
    }

}