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
        
        EjercicioBenchUsuario benchUsuario = new EjercicioBenchUsuario(0, 4, 3, null, null);
        
        ArrayList<EjercicioBenchUsuario> al = boxCAD.ejeBenchUsuario(benchUsuario);
        
        Iterator<EjercicioBenchUsuario> i = al.iterator();
        
        while (i.hasNext())
        {            
            System.out.println(i.next().toString());
        }
    }
}
