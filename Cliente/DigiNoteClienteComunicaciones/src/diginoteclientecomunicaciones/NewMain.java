/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diginoteclientecomunicaciones;

import diginotepojo.Enlace;
import diginotepojo.Lenguaje;
import diginotepojo.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Imanol Callejo Baranda
 * @version 1.1.0
 * @since Mayo 2023 Interfaz provisional para el cliente de comunicaciones
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//
//        Usuario u = new Usuario(null, "Error@Controlado.CO", "OOOOO");
//        Lenguaje l = new Lenguaje(null, "3-6-23", "2131165370", new Usuario(3));
        Enlace e = new Enlace(null, "Azure", "Azuriin", "https://azure.es", null, "N", new Lenguaje(40));
//        Enlace efav = new Enlace("S".toUpperCase());
//        HiloCliente clienteAdd = new HiloCliente(HiloCliente.USER_ADD, u);
//        HiloCliente clienteDelete = new HiloCliente(HiloCliente.USER_DELETE, 70);
//        HiloCliente clienteShow = new HiloCliente(HiloCliente.USER_SHOW, 67);
//        HiloCliente clienteEdit = new HiloCliente(HiloCliente.USER_EDIT, 67, new Usuario(null, "IGNATIUUUS@TPM.JA", "LACABRA"));
//        HiloCliente clienteShowAll = new HiloCliente(HiloCliente.USER_SHOW_ALL);
//        HiloCliente lenguajeAdd = new HiloCliente(HiloCliente.LANGUAGE_ADD, l);
//        HiloCliente lenguajeDELETE = new HiloCliente(HiloCliente.LANGUAGE_DELETE, 8);
//        HiloCliente lenguajeEdit = new HiloCliente(HiloCliente.LANGUAGE_EDIT, 4, l);
//        HiloCliente lenguajeShow = new HiloCliente(HiloCliente.LANGUAGE_SHOW, 3);
//        HiloCliente lenguajeShowAll = new HiloCliente(HiloCliente.LANGUAGE_SHOW_ALL);
        HiloCliente linkAdd = new HiloCliente(HiloCliente.LINK_ADD, e);
//        HiloCliente linkDelete = new HiloCliente(HiloCliente.LINK_DELETE, 13);
//        HiloCliente linkEdit = new HiloCliente(HiloCliente.LINK_EDIT, 23, e);
//        HiloCliente linkShow = new HiloCliente(HiloCliente.LINK_SHOW, 24);
//        HiloCliente linkShowAll = new HiloCliente(HiloCliente.LINK_SHOW_ALL);
//        HiloCliente linkShowAllFav = new HiloCliente(HiloCliente.LINK_FAVORITE_SHOW);
//        HiloCliente linkEditFav = new HiloCliente(HiloCliente.LINK_FAVORITE_EDIT,7, efav);

//        clienteAdd.start();
//        clienteDelete.start();
//        clienteShow.start();
//        clienteEdit.start();
//        clienteShowAll.start();
//        lenguajeAdd.start();
//        lenguajeDELETE.start();
//        lenguajeEdit.start();
//        lenguajeShow.start();
//        lenguajeShowAll.start();
        linkAdd.start();
//        linkDelete.start();
//        linkEdit.start();
//        linkShow.start();
//        linkShowAll.start();
//        linkShowAllFav.start();
//        linkEditFav.start();
/*
        while (true) {
            if (lenguajeAdd.isAlive()) {
                System.out.println("VIVO");
            } else {
                System.out.println("RESULTADO: " + lenguajeAdd.getResultado());
            }

//                System.out.println("RESULTADO: " + i);
            break;
        }

        while (true) {
            if (linkShowAll.isAlive()) {
                System.out.println("VIVO");
            } else {
                ArrayList<Enlace> a = (ArrayList<Enlace>) linkShowAll.getResultado();
                for (int i = 0; i < a.size(); i++) {
                    if (a.get(i).getLenguaje().getLenguajeId() == 23) {

                        System.out.println(a.get(i).getEnlaceId() + " \t | \t " + a.get(i).getTitulo() + " \t | \t " + a.get(i).getLink() + " \t | \t " + a.get(i).getLenguaje().getLenguajeId());
                    }
                }
//                System.out.println("RESULTADO: " + i);
                break;
            }

        }
         */

        while (true) {
            if (linkAdd.isAlive()) {
                System.out.println("VIVO");
            } else {
                System.out.println("RESULTADO: " + linkAdd.getResultado());
            }
            break;
        }
    }

}
