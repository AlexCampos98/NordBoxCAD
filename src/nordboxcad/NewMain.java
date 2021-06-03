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
        
        ApuntoEjercicio apuntoEjercicio = new ApuntoEjercicio(5, 1, "2021-6-2", 46, 5);
        
        boxCAD.crearEjeBench(apuntoEjercicio);
    }
}
