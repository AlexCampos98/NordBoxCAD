/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nordboxcad;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Alejandro Campos Maestre
 */
public class NewMain
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {      
        NordBoxCADCliente boxCAD = new NordBoxCADCliente();
        
        Evento evento = new Evento();
        evento.setFecha("2021/06/04");
        
        ArrayList<Evento> eventos = boxCAD.obtenerEventosFecha(evento);
        
        Iterator<Evento> iterator = eventos.iterator();
        System.out.println(iterator.hasNext());
        while(iterator.hasNext())
        {
            System.out.println("A");
            System.out.println(iterator.next().toString());
        }
    }
}
