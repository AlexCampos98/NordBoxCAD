/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nordboxcad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
        
        ArrayList<EjerciciosBench> bench;
        
        bench = boxCAD.ejeBench();
        
        Iterator<EjerciciosBench> i = bench.iterator();
        
        while (i.hasNext())
        {            
            EjerciciosBench ejerciciosBench = i.next();
            
            System.out.println(ejerciciosBench.toString());
        }
    }
}
